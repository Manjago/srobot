package srobot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;

public class MinesCountResolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(Board.class);
    private static final Map<RedDigit, SearchPattern> DIGITS;
    private static final Finder FINDER = new BruteFinder();

    static {
        DIGITS = new HashMap<>();
        try {
            DIGITS.put(RedDigit.DIGIT0, new SearchPattern(Loader.load("digit-0.png")));
            DIGITS.put(RedDigit.DIGIT1, new SearchPattern(Loader.load("digit-1.png")));
            DIGITS.put(RedDigit.DIGIT2, new SearchPattern(Loader.load("digit-2.png")));
            DIGITS.put(RedDigit.DIGIT3, new SearchPattern(Loader.load("digit-3.png")));
            DIGITS.put(RedDigit.DIGIT4, new SearchPattern(Loader.load("digit-4.png")));
            DIGITS.put(RedDigit.DIGIT5, new SearchPattern(Loader.load("digit-5.png")));
            DIGITS.put(RedDigit.DIGIT6, new SearchPattern(Loader.load("digit-6.png")));
            DIGITS.put(RedDigit.DIGIT7, new SearchPattern(Loader.load("digit-7.png")));
            DIGITS.put(RedDigit.DIGIT8, new SearchPattern(Loader.load("digit-8.png")));
            DIGITS.put(RedDigit.DIGIT9, new SearchPattern(Loader.load("digit-9.png")));
        } catch (IOException e) {
            LOGGER.error("fail load images", e);
        }

    }

    private final BufferedImage big;


    public MinesCountResolver(BufferedImage big) {
        this.big = big;

    }

    public int getMinesCount(SimplePoint coord) {

        MinesInfoCollector collector = DIGITS.entrySet().stream().parallel().collect(
                () -> new MinesInfoCollector(coord),
                MinesInfoCollector::accept,
                MinesInfoCollector::accept);

        return collector.getMinesCount();
    }

    static class Recoder implements BinaryOperator<Integer> {

        private int multiplier = 1;

        @Override
        public Integer apply(Integer integer, Integer integer2) {
            multiplier *= 10;
            return integer + integer2 * multiplier;
        }
    }

    class MinesInfoCollector {

        private final SimplePoint rubikon;
        private final Map<Integer, Integer> digits = new HashMap<>();

        MinesInfoCollector(@Nonnull SimplePoint rubikon) {

            Objects.requireNonNull(rubikon);

            this.rubikon = rubikon;
        }

        void accept(Map.Entry<RedDigit, SearchPattern> item) {
            List<SimplePoint> points = FINDER.find(big, item.getValue());
            points.stream()
                    .filter(p -> p.getX() < rubikon.getX())
                    .map(SimplePoint::getX)
                    .forEach(x -> digits.put(x, item.getKey().getValue())
                    );
        }

        void accept(MinesInfoCollector other) {
            other.digits.forEach(digits::put);
        }

        int getMinesCount() {

            Optional<Integer> sum = digits.keySet().stream()
                    .sorted((o1, o2) -> -o1.compareTo(o2))
                    .map(digits::get)
                    .reduce(new Recoder());

            return sum.isPresent() ? sum.get() : 0;
        }

    }

}

