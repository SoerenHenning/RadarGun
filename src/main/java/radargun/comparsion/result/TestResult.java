package radargun.comparsion.result;

import org.openjdk.jmh.results.RunResult;

import radargun.comparsion.Assertion;

public interface TestResult {

	public Assertion getAssertion();

	public RunResult getRunResult();

	public boolean hasFailed();

	public boolean wasSuccesfull();

}
