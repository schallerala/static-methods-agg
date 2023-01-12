package test;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;

import java.util.function.Function;

/**
 * Helper class to avoid having to create a new class for each object type and with the function lambda
 * keep the calls type safe.
 *
 * <p>This avoids use {@code hasProperty} matcher which doesn't validate the properties nor their type.
 *
 * @param <T> The input object type of the assertion
 * @param <F> The value etracted from the input object and evaluated by the matcher
 */
public class LambdaMatcher<T, F> extends TypeSafeDiagnosingMatcher<T> {

	private final String fieldName;
	private final Matcher<F> fieldMatcher;
	private final Function<T, F> fieldExtractor;

	public LambdaMatcher(String fieldName, Matcher<F> fieldMatcher, Function<T, F> fieldExtractor) {
		this.fieldName = fieldName;
		this.fieldMatcher = fieldMatcher;
		this.fieldExtractor = fieldExtractor;
	}

	@Override
	protected boolean matchesSafely(T item, Description mismatchDescription) {
		final F theValue = this.fieldExtractor.apply(item);

		if (this.fieldMatcher.matches(theValue)) {
			return true;
		}

		mismatchDescription.appendText(this.fieldName);
		this.fieldMatcher.describeMismatch(theValue, mismatchDescription);
		return false;
	}

	@Override
	public void describeTo(Description description) {
		description.appendText(this.fieldName).appendText(" ").appendDescriptionOf(this.fieldMatcher);
	}
}
