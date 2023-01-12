package test;

import org.hamcrest.Matcher;

public class WithJavaDoc {

    /**
     * Look at me!
     *
     * @return something
     */
    public static Matcher<String> documented() {
        return null;
    }

}
