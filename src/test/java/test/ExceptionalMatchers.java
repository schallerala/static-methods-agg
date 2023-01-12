package test;

import java.io.IOException;


import org.hamcrest.Matcher;

public class ExceptionalMatchers {

    public static Matcher<String> withExceptions() throws Error, IOException, RuntimeException {
        return null;
    }

}
