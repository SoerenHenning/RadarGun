package radargun.comparsion.result;

import org.openjdk.jmh.results.RunResult;

import radargun.comparsion.Assertion;

public class TestUndercutsBoundsResult extends AbstractTestResult {

	public TestUndercutsBoundsResult(final Assertion assertion, final RunResult runResult) {
		super(assertion, runResult);
	}

	@Override
	public boolean hasFailed() {
		return true;
	}

	@Override
	public boolean wasSuccesfull() {
		return false;
	}

}
