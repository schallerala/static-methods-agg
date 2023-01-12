package test;

import org.hamcrest.Matcher;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class G {

    public static <T, V extends List<String> & Comparable<String>> Matcher<Map<T, V[]>> x(Set<T> t, V v) {
        return null;
    }

}
