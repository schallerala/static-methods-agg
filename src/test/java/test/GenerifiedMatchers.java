package test;

import org.hamcrest.Matcher;

import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Predicate;

public class GenerifiedMatchers {

    public static Matcher<Comparator<String>> generifiedType() {
        return null;
    }

    @SuppressWarnings("rawtypes")
    public static Matcher noGenerifiedType() {
        return null;
    }

    public static Matcher<Map<? extends Set<Long>, BiFunction<Long, Integer, Predicate<String>>>> crazyType() {
        return null;
    }

}
