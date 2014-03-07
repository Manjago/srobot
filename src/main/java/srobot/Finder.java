package srobot;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Kirill Temnenkov (kdtemnen@mts.ru)
 */
public class Finder {
    private static void check(String msg, BufferedImage img){
        if (img == null || img.getHeight() < 1 || img.getWidth() < 1){
            throw new IllegalArgumentException(msg);
        }
    }

    public static List<LamePoint> find(BufferedImage searchBase, BufferedImage pattern) {

        check("searchBase", searchBase);
        check("pattern", pattern);

        int transp = pattern.getRGB(0,0);
        List<Pretender> pretenders = new ArrayList<>();
        List<Pretender> toRemove = new ArrayList<>();

        for (int j = 0; j < searchBase.getHeight(); ++j) {

            // нет смысла париться - уже по размеру не пролезаем
            boolean relax = j + pattern.getHeight() > searchBase.getHeight();

            for (int i = 0; i < searchBase.getWidth(); ++i) {
                int rgb = searchBase.getRGB(i, j);

                // проверяем старых претендеров
                for (Pretender p : pretenders) {

                    // мы все на той же горизонтали?
                    boolean isSameHor = j == p.getInBig().getY();
                    if (isSameHor) {
                        // в образце есть следующая точка?
                        LamePoint nextInTest = p.getInTest().nextHor();
                        boolean hasNextTestHor = nextInTest.getX() <= (pattern.getWidth() - 1);
                        if (hasNextTestHor) {
                            // раз есть - то можно проверять стандартным образом
                            int testRgb = pattern.getRGB(nextInTest.getX(), nextInTest.getY());
                            boolean isGood = testRgb == transp || testRgb == rgb;
                            if (!isGood) {
                                removeBad(toRemove, p);
                            } else {
                                // он хороший, продолжаем с ним работать, изменив содержимое
                                keepGood(p, nextInTest);
                            }
                        }

                    } else {
                        // мы уже на новой горизонтали, ничего себе!
                        // если образец еще не кончился - мы этого ему не простим
                        LamePoint nextInTestHor = p.getInTest().nextHor();
                        boolean hasNextTestHor = nextInTestHor.getX() <= (pattern.getWidth()- 1);
                        if (hasNextTestHor) {
                            removeBad(toRemove, p);
                        } else {
                            // и образец тоже кончился - пытаемся пересочить на следующую горизонталь и в образце

                            // однако же, если по x-координате образец родился позже, чем мы находимся теперь
                            // то смысла нет тут  находится

                            if (p.getBorn().getX() > i){
                                // сейчас проверять нет смысла
                                continue;
                            }


                            LamePoint nextInTest = p.getInTest().nextVer();
                            boolean hasNextTestVer = nextInTest.getY() <= (pattern.getHeight() - 1);
                            if (hasNextTestVer){
                                int testRgb = pattern.getRGB(nextInTest.getX(), nextInTest.getY());
                                boolean isGood = testRgb == transp || testRgb == rgb;
                                if (!isGood) {
                                    removeBad(toRemove, p);
                                } else {
                                    // он хороший, продолжаем с ним работать, изменив содержимое
                                    keepGood(p, nextInTest);
                                }
                            }

                        }
                    }

                }


                // добавляем новых претендеров

                if (!relax){
                    int testRgb = pattern.getRGB(0, 0);
                    if (testRgb == transp || testRgb == rgb) {
                        // годится
                        LamePoint inTest = new LamePoint(0, 0);
                        LamePoint inBig = new LamePoint(i, j);
                        Pretender p = new Pretender(inTest, inBig);
                        if (p.getBorn().equals(new LamePoint(1, 0))){
                            pretenders.add(p);
                        }
                    }
                }


                // удаляем неудачников
                for (Pretender p : toRemove) {
                    pretenders.remove(p);
                }
                toRemove.clear();
            }
        }


        System.out.printf("found %d pretenders", pretenders.size());
        System.out.print(pretenders);

        List<LamePoint> result = new ArrayList<>();
        int matches = pattern.getHeight() * pattern.getWidth();
        for(Pretender p : pretenders){
            if (p.getMatches() == matches){
                result.add(p.getBorn());
            }
        }
        return result;
    }

    private static void keepGood(Pretender p, LamePoint nextInTest) {
        p.setInTest(nextInTest);
        p.incMatches();
    }

    private static boolean removeBad(List<Pretender> toRemove, Pretender p) {
        return toRemove.add(p);
    }
}
