package de.soerenhenning.perftest.test;

import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import org.openjdk.jmh.results.RunResult;

import de.soerenhenning.perftest.Test;
import de.soerenhenning.perftest.test.result.TestResult;
import de.soerenhenning.perftest.test.result.TestResultFactory;
import de.soerenhenning.perftest.test.yaml.YamlTest;

public class ResultComparator {

	private final TestResultFactory testResultFactory = new TestResultFactory();
	private final Map<String, TestAssertion> assertions = new HashMap<>();

	public ResultComparator(final Iterable<Test> tests) {
		for (final Test test : tests) {
			if (test.getMachineIdentifier().testMachine()) {
				this.assertions.putAll(test.getTests());
			}
		}
	}

	public ResultComparator(final Stream<Test> tests) {
		tests.filter(t -> t.getMachineIdentifier().testMachine()).forEach(t -> this.assertions.putAll(t.getTests()));
	}

	public TestResult compare(final RunResult runResult) {
		final String testName = ""; // TODO
		final TestAssertion assertion = this.assertions.get(testName);
		return this.testResultFactory.create(runResult, assertion);
	}

	public static ResultComparator fromInputStreams(final Collection<InputStream> inputStreams) {
		return new ResultComparator(inputStreams.stream().flatMap(s -> YamlTest.createAll(s).stream()));
	}

}
