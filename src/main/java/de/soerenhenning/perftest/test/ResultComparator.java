package de.soerenhenning.perftest.test;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.openjdk.jmh.results.RunResult;

import de.soerenhenning.perftest.Test;
import de.soerenhenning.perftest.test.result.TestResult;
import de.soerenhenning.perftest.test.result.TestResultFactory;

public class ResultComparator {

	private final TestResultFactory testResultFactory = new TestResultFactory();
	private final Map<String, TestAssertion> assertions = new HashMap<>();

	public ResultComparator() {
		final Collection<Test> tests = null;
		for (final Test test : tests) {
			if (test.getMachineIdentifier().testCurrentMachine()) {
				this.assertions.putAll(test.getTests());
			}
		}
	}

	public TestResult compare(final RunResult runResult) {
		final TestAssertion assertion = this.assertions.get(null); // TODO
		return this.testResultFactory.create(runResult, assertion);
	}

}
