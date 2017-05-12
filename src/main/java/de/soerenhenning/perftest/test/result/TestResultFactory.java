package de.soerenhenning.perftest.test.result;

import java.util.function.ToDoubleFunction;

import org.openjdk.jmh.results.RunResult;

import de.soerenhenning.perftest.test.TestAssertion;

public class TestResultFactory {

	private final ToDoubleFunction<RunResult> scoreAccessor;

	public TestResultFactory() {
		this(PRIMARY_RESULT_ACCESSOR);
	}

	public TestResultFactory(final ToDoubleFunction<RunResult> scoreAccessor) {
		this.scoreAccessor = scoreAccessor;
	}

	public TestResult create(final RunResult runResult, final TestAssertion assertion) {
		final double score = this.scoreAccessor.applyAsDouble(runResult);

		if (score < assertion.getLowerBound()) {
			return new TestUndercutsBoundsResult(assertion, runResult);
		} else if (score > assertion.getUpperBound()) {
			return new TestExceedsBoundsResult(assertion, runResult);
		} else {
			return new TestInBoundsResult(assertion, runResult);
		}
	}

	public static final ToDoubleFunction<RunResult> PRIMARY_RESULT_ACCESSOR = r -> r.getPrimaryResult().getScore();

}
