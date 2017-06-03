package radargun.test;

import org.openjdk.jmh.results.RunResult;

import radargun.test.result.TestResult;
import teetime.stage.basic.AbstractTransformation;

public class ResultComparatorStage extends AbstractTransformation<RunResult, TestResult> {

	private final ResultComparator resultComparator;

	public ResultComparatorStage(final ResultComparator resultComparator) {
		this.resultComparator = resultComparator;
	}

	@Override
	protected void execute(final RunResult runResult) throws Exception {
		final TestResult testResult = this.resultComparator.compare(runResult);
		this.outputPort.send(testResult);
	}

}
