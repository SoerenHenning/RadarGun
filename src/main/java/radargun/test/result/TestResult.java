package radargun.test.result;

import org.openjdk.jmh.results.RunResult;

import radargun.test.TestAssertion;

public interface TestResult {

	public TestAssertion getAssertion();

	public RunResult getRunResult();

	public boolean hasFailed();

	public boolean wasSuccesfull();

}
