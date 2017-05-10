package de.soerenhenning.perftest.test.result;

import org.openjdk.jmh.results.RunResult;

import de.soerenhenning.perftest.test.TestAssertion;

public class TestInBoundsResult extends AbstractTestResult {

	public TestInBoundsResult(final TestAssertion assertion, final RunResult runResult) {
		super(assertion, runResult);
	}

	@Override
	public boolean hasFailed() {
		return false;
	}

	@Override
	public boolean wasSuccesfull() {
		return true;
	}

}
