package de.soerenhenning.perftest.test.result;

import org.openjdk.jmh.results.RunResult;

import de.soerenhenning.perftest.test.TestAssertion;

abstract class AbstractTestResult implements TestResult {

	private final TestAssertion assertion;
	private final RunResult runResult;

	public AbstractTestResult(final TestAssertion assertion, final RunResult runResult) {
		this.assertion = assertion;
		this.runResult = runResult;
	}

	@Override
	public TestAssertion getAssertion() {
		return this.assertion;
	}

	@Override
	public RunResult getRunResult() {
		return this.runResult;
	}

}
