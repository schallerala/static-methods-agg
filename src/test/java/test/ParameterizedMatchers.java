package test;

import org.hamcrest.Matcher;

import java.util.Collection;
import java.util.Set;

public class ParameterizedMatchers {

    public static Matcher<String> withParam(String someString, int[] numbers, Collection<Object> things) {
        return null;
    }

    public static Matcher<String> withArray(String[] array) {
        return null;
    }

    public static Matcher<String> withVarArgs(String... things) {
        return null;
    }

    public static Matcher<String> withGenerifiedParam(Collection<? extends Comparable<String>> things, Set<String[]>[] x) {
        return null;
    }

}
