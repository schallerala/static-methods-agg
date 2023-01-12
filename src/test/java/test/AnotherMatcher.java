package test;

import org.hamcrest.Matcher;
import org.hamcrest.Description;

@SuppressWarnings("rawtypes")
public class AnotherMatcher<T> implements Matcher<T> {
    @Override public void describeTo(Description description) { }
    @Override public boolean matches(Object item) { return false; }
    @Override public void describeMismatch(Object item, Description mismatchDescription) { }
    @Override @Deprecated public void _dont_implement_Matcher___instead_extend_BaseMatcher_() { }
    public static AnotherMatcher matcher3() { return null; }
}
