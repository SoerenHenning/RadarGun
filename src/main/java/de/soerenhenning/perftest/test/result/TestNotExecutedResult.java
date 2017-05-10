package de.soerenhenning.perftest.test.result;

import org.openjdk.jmh.results.RunResult;

import de.soerenhenning.perftest.test.TestAssertion;

public class TestNotExecutedResult extends AbstractTestResult {

	public TestNotExecutedResult(final TestAssertion assertion, final RunResult runResult) {
		super(assertion, runResult);
	}

	@Override
	public boolean hasFailed() {
		return false;
	}

	@Override
	public boolean wasSuccesfull() {
		return false;
	}

}
