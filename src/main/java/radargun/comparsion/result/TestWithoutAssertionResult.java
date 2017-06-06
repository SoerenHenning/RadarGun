package radargun.comparsion.result;

import org.openjdk.jmh.results.RunResult;

import radargun.comparsion.Assertion;

public class TestWithoutAssertionResult extends AbstractTestResult {

	public TestWithoutAssertionResult(final Assertion assertion, final RunResult runResult) {
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
