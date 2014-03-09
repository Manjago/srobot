package srobot;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Kirill Temnenkov (kdtemnen@mts.ru)
 */
public class SlowFinder implements Finder{

    public SlowFinder() {
    }

    private static void check(String msg, BufferedImage img) {
        if (img == null || img.getHeight() < 1 || img.getWidth() < 1) {
            throw new IllegalArgumentException(msg);
        }
    }

    public List<SimplePoint> find(BufferedImage searchBase, SearchPattern pattern) {

        check("searchBase", searchBase);
        check("pattern", pattern.getPattern());

        List<Pretender> pretenders = new ArrayList<>();
        List<Pretender> toRemove = new ArrayList<>();

        for (int j = 0; j < searchBase.getHeight(); ++j) {

            // нет смысла париться - уже по размеру не пролезаем
            boolean relax = j + pattern.getHeight() > searchBase.getHeight();

            //System.out.printf("row %d pretenders %d %s\n", j, pretenders.size(), pretenders);

            for (int i = 0; i < searchBase.getWidth(); ++i) {
                int rgb = searchBase.getRGB(i, j);

                // проверяем старых претендеров
                for (Pretender p : pretenders) {
                    checkOldPretenders(pattern, toRemove, j, i, rgb, p);
                }

                if (!relax) {
                    addNewPretender(pattern, pretenders, j, i, rgb);
                }

                removeLoosers(pretenders, toRemove);
            }
        }

        return makeResult(pattern, pretenders);
    }

    private static void checkOldPretenders(SearchPattern pattern, List<Pretender> toRemove, int j, int i, int rgb, Pretender p) {
        // мы все на той же горизонтали?
        boolean isSameHor = j == p.getInBig().getY();
        if (isSameHor) {
            checkSameHor(pattern, toRemove, rgb, p);
        } else {
            checkAnotherHor(pattern, toRemove, i, rgb, p);
        }
    }

    private static void checkAnotherHor(SearchPattern pattern, List<Pretender> toRemove, int i, int rgb, Pretender p) {
        // мы уже на новой горизонтали, ничего себе!
        // если образец еще не кончился - мы этого ему не простим
        SimplePoint nextInTestHor = p.getInTest().nextHor();
        boolean hasNextTestHor = nextInTestHor.getX() <= (pattern.getWidth() - 1);
        if (hasNextTestHor) {
            removeBad(toRemove, p);
        } else {
            // и образец тоже кончился - пытаемся пересочить на следующую горизонталь и в образце

            // однако же, если по x-координате образец родился позже, чем мы находимся теперь
            // то смысла нет тут  находится

            if (p.getBorn().getX() > i) {
                // сейчас проверять нет смысла
                return;
            }


            SimplePoint nextInTest = p.getInTest().nextVer();
            boolean hasNextTestVer = nextInTest.getY() <= (pattern.getHeight() - 1);
            if (hasNextTestVer) {
                boolean isGood = pattern.isGood(rgb, nextInTest);
                if (!isGood) {
                    removeBad(toRemove, p);
                } else {
                    // он хороший, продолжаем с ним работать, изменив содержимое
                    keepGood(p, nextInTest);
                }
            }

        }
    }

    private static void checkSameHor(SearchPattern pattern, List<Pretender> toRemove, int rgb, Pretender p) {
        // в образце есть следующая точка?
        SimplePoint nextInTest = p.getInTest().nextHor();
        boolean hasNextTestHor = nextInTest.getX() <= (pattern.getWidth() - 1);
        if (hasNextTestHor) {
            // раз есть - то можно проверять стандартным образом
            boolean isGood = pattern.isGood(rgb, nextInTest);
            if (!isGood) {
                removeBad(toRemove, p);
            } else {
                // он хороший, продолжаем с ним работать, изменив содержимое
                keepGood(p, nextInTest);
            }
        }
    }

    private static void addNewPretender(SearchPattern pattern, List<Pretender> pretenders, int j, int i, int rgb) {
        if (pattern.isGood(rgb, SimplePoint.ZERO)) {
            // годится
            SimplePoint inBig = new SimplePoint(i, j);
            Pretender p = new Pretender(SimplePoint.ZERO, inBig);
            pretenders.add(p);
        }
    }

    private static void removeLoosers(List<Pretender> pretenders, List<Pretender> toRemove) {
        for (Pretender p : toRemove) {
            pretenders.remove(p);
        }
        toRemove.clear();
    }

    private static List<SimplePoint> makeResult(SearchPattern pattern, List<Pretender> pretenders) {
        List<SimplePoint> result = new ArrayList<>();
        int matches = pattern.getHeight() * pattern.getWidth();
        for (Pretender p : pretenders) {
            if (p.getMatches() == matches) {
                result.add(p.getBorn());
            }
        }
        return result;
    }

    private static void keepGood(Pretender p, SimplePoint nextInTest) {
        p.setInTest(nextInTest);
        p.incMatches();
    }

    private static boolean removeBad(List<Pretender> toRemove, Pretender p) {
        return toRemove.add(p);
    }
}
