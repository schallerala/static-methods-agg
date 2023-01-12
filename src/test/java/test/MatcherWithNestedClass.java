package test;

import org.hamcrest.Matcher;

public class MatcherWithNestedClass {

    public static SpecificMatcher firstMethod() {
        return null;
    }

    public abstract class SpecificMatcher implements Matcher {
    }
}
