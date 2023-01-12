package test;

import org.hamcrest.Matcher;

import java.util.function.BiFunction;
import java.util.function.Supplier;

import test.LambdaMatcher;

public class GroupMatchers {

	/** Hello */
	public static final Bla BLA = new Bla() {
		/** Bla
		*/
		@Override
		public void bla() {
			System.out.println("bla");
		}
	};

	private GroupMatchers() {
	}

	public static Matcher<GroupEO> orgId(Matcher<Long> matcher) {
		return new LambdaMatcher<>("organizationId", matcher, GroupEO::getOrganizationId);
	}

	public static Matcher<GroupEO> orgId(Matcher<Long> matcher, GroupEO sec, int i, char c) {
		return new LambdaMatcher<>("organizationId", matcher, GroupEO::getOrganizationId);
	}

	public static Matcher<GroupEO> securityGroupName(Matcher<String> matcher) {
		return new LambdaMatcher<>("name", matcher, GroupEO::getName);
	}

	/**
	 * Bonjour
	 *
	 * @param matcher
	 * @return
	 */
	@Deprecated
	public static Matcher<GroupEO> orgType(Matcher<BiFunction<Type, Supplier<Integer>, Long>> matcher2) {
		return null;
	}

	// /** Hello one liner comment */
	// public static Matcher<GroupEO> roleId(BiFunction<Long, GroupEO, GroupEO> matcher) {
	// 	return new LambdaMatcher<>("roleId", matcher, GroupEO::getRoleId);
	// }


	// public static <T, R> Function<T, R> bla(Function<GroupEO, R> function, boolean a, Function<R, GroupEO> b, Supplier<R> supplier) {
	// 	return function;
	// }

	// public static <T> int bla(T t) {
	// 	System.out.println("bla");
	// 	return 0;
	// }

	public interface Bla {
		void bla();
	}

    public static enum Type {
        A, B, C
    }

    public interface GroupEO {
        Long getOrganizationId();
        String getName();
    }
}
