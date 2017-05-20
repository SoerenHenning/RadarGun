package de.soerenhenning.perftest.test;

import org.openjdk.jmh.results.RunResult;

import de.soerenhenning.perftest.test.result.TestResult;
import de.soerenhenning.perftest.test.result.TestResultFactory;

public class ResultComparator {

	private final TestResultFactory testResultFactory = new TestResultFactory();

	public TestResult compare(final RunResult runResult) {
		final TestAssertion assertion = null;
		return this.testResultFactory.create(runResult, assertion);
	}

}
