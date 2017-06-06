package radargun.comparsion;

import java.util.HashMap;
import java.util.Map;

import org.openjdk.jmh.results.RunResult;

import radargun.comparsion.result.TestResult;
import radargun.comparsion.result.TestResultFactory;

public class ResultComparator {

	private final TestResultFactory testResultFactory = new TestResultFactory();
	private final Map<String, Assertion> assertions = new HashMap<>();

	public ResultComparator(final Iterable<? extends Test> collection) {
		for (final Test test : collection) {
			if (test.getMachineIdentifier().testMachine()) {
				this.assertions.putAll(test.getTests());
			}
		}
	}

	public TestResult compare(final RunResult runResult) {
		final String testName = ""; // TODO
		final Assertion assertion = this.assertions.getOrDefault(testName, Assertion.DUMMY);
		return this.testResultFactory.create(runResult, assertion);
	}

}
