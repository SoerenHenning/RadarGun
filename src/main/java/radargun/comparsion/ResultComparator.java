package radargun.comparsion;

import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.openjdk.jmh.results.RunResult;

import radargun.comparsion.result.TestResult;
import radargun.comparsion.result.TestResultFactory;
import radargun.comparsion.yaml.YamlTest;

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
		final Assertion assertion = this.assertions.get(testName);
		return this.testResultFactory.create(runResult, assertion);
	}

	@Deprecated
	public static ResultComparator fromInputStreams(final Collection<InputStream> inputStreams) {
		return new ResultComparator(YamlTest.createAll(inputStreams));
	}

}
