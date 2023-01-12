package test;

import org.hamcrest.Matcher;

public class MatchersWithDodgySignatures {

    public Matcher<String> notStatic() {
        return null;
    }

    static Matcher<String> notPublic() {
        return null;
    }

    public static Matcher<String> goodMethod() {
        return null;
    }

    public static String anotherGoodMethod() {
        return null;
    }

    public static void wrongReturnType() {
    }

}
