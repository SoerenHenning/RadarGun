package radargun.test.result;

import org.openjdk.jmh.results.RunResult;

import radargun.test.TestAssertion;

public class TestExceedsBoundsResult extends AbstractTestResult {

	public TestExceedsBoundsResult(final TestAssertion assertion, final RunResult runResult) {
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
