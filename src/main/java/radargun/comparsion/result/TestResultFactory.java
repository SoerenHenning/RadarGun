package radargun.comparsion.result;

import java.util.function.ToDoubleFunction;

import org.openjdk.jmh.results.RunResult;

import radargun.comparsion.Assertion;

public class TestResultFactory {

	public static final ToDoubleFunction<RunResult> PRIMARY_RESULT_ACCESSOR = r -> r.getPrimaryResult().getScore();

	private final ToDoubleFunction<RunResult> scoreAccessor;

	public TestResultFactory() {
		this(PRIMARY_RESULT_ACCESSOR);
	}

	public TestResultFactory(final ToDoubleFunction<RunResult> scoreAccessor) {
		this.scoreAccessor = scoreAccessor;
	}

	public TestResult create(final RunResult runResult, final Assertion assertion) {
		final double score = this.scoreAccessor.applyAsDouble(runResult);

		if (Double.isNaN(assertion.getLowerBound()) && Double.isNaN(assertion.getUpperBound())) {
			return new TestWithoutAssertionResult(assertion, runResult);
		} else if (Double.isNaN(score)) {
			return new TestNotExecutedResult(assertion, runResult);
		} else if (score < assertion.getLowerBound()) {
			return new TestUndercutsBoundsResult(assertion, runResult);
		} else if (score > assertion.getUpperBound()) {
			return new TestExceedsBoundsResult(assertion, runResult);
		} else {
			return new TestInBoundsResult(assertion, runResult);
		}
	}

}
