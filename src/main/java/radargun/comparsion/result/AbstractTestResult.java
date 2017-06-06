package radargun.comparsion.result;

import org.openjdk.jmh.results.RunResult;

import radargun.comparsion.Assertion;

abstract class AbstractTestResult implements TestResult {

	private final Assertion assertion;
	private final RunResult runResult;

	public AbstractTestResult(final Assertion assertion, final RunResult runResult) {
		this.assertion = assertion;
		this.runResult = runResult;
	}

	@Override
	public Assertion getAssertion() {
		return this.assertion;
	}

	@Override
	public RunResult getRunResult() {
		return this.runResult;
	}

}
