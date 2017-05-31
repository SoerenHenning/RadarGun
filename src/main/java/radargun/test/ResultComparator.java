package radargun.test;

import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import org.openjdk.jmh.results.RunResult;

import radargun.Test;
import radargun.test.result.TestResult;
import radargun.test.result.TestResultFactory;
import radargun.test.yaml.YamlTest;

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
