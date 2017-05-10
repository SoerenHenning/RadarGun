package de.soerenhenning.perftest.test.result;

import org.openjdk.jmh.results.RunResult;

import de.soerenhenning.perftest.test.TestAssertion;

public interface TestResult {

	public TestAssertion getAssertion();

	public RunResult getRunResult();

	public boolean hasFailed();

	public boolean wasSuccesfull();

}
