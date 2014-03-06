package srobot;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Kirill Temnenkov (kdtemnen@mts.ru)
 */
public class Finder {
    public static void main(String[] args) throws IOException {

        BufferedImage big = Loader.load("test.png");
        if (big == null) {
            System.out.println("big not found");
            return;
        }

        BufferedImage test = Loader.load("1.png");
        if (test == null) {
            System.out.println("test not found");
            return;
        }


        int transp = new Color(255, 255, 255).getRGB();
        List<Pretender> pretenders = new ArrayList<>();
        List<Pretender> toRemove = new ArrayList<>();

        for (int i = 0; i < big.getWidth(); ++i) {
            for (int j = 0; j < big.getHeight(); ++j) {
                int rgb = big.getRGB(i, j);

                // проверяем старых претендеров
                for (Pretender p : pretenders) {

                    // мы все на той же горизонтали?
                    boolean isSameHor = i == p.getInBig().getX();
                    if (isSameHor) {
                        // в образце есть следующая точка?
                        LamePoint nextInTest = p.getInTest().nextHor();
                        boolean hasNextTestHor = nextInTest.getX() <= (test.getHeight() - 1);
                        if (hasNextTestHor) {
                            // раз есть - то можно проверять стандартным образом
                            int testRgb = test.getRGB(nextInTest.getX(), nextInTest.getY());
                            boolean isGood = testRgb == transp || testRgb == rgb;
                            if (!isGood) {
                                toRemove.add(p);
                            } else {
                                // он хороший, продолжаем с ним работать, изменив содержимое
                                p.setInTest(nextInTest);
                            }
                        } else {
                            // раз образец кончился (но мы еще на той же горизонтали в большом - то это не наш вариант
                            toRemove.add(p);
                        }

                    } else {
                        // мы уже на новой горизонтали, ничего себе!
                        // если образец еще не кончился - мы этого ему не простим
                        LamePoint nextInTestHor = p.getInTest().nextHor();
                        boolean hasNextTestHor = nextInTestHor.getX() <= (test.getHeight() - 1);
                        if (hasNextTestHor) {
                            toRemove.add(p);
                        } else {
                            // и образец тоже кончился - пытаемся пересочить на следующую горизонталь и в образце
                            LamePoint nextInTest = p.getInTest().nextVer();
                            int testRgb = test.getRGB(nextInTest.getX(), nextInTest.getY());
                            boolean isGood = testRgb == transp || testRgb == rgb;
                            if (!isGood) {
                                toRemove.add(p);
                            } else {
                                // он хороший, продолжаем с ним работать, изменив содержимое
                                p.setInTest(nextInTest);
                            }
                        }
                    }

                }


                // добавляем новых претендеров
                int testRgb = test.getRGB(0, 0);
                if (testRgb == transp || testRgb == rgb) {
                    // годится
                    LamePoint inTest = new LamePoint(0, 0);
                    LamePoint inBig = new LamePoint(i, j);
                    Pretender p = new Pretender(inTest, inBig);
                    pretenders.add(p);
                }

                // удаляем неудачников
                for (Pretender p : toRemove) {
                    pretenders.remove(p);
                }
                toRemove.clear();
            }
        }

        System.out.printf("found %d pretenders", pretenders.size());

    }
}
