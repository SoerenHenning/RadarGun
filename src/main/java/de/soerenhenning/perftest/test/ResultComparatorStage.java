package de.soerenhenning.perftest.test;

import org.openjdk.jmh.results.RunResult;

import de.soerenhenning.perftest.test.result.TestResult;
import teetime.stage.basic.AbstractTransformation;

public class ResultComparatorStage extends AbstractTransformation<RunResult, TestResult> {

	private final ResultComparator resultComparator = new ResultComparator(null); // TODO

	@Override
	protected void execute(final RunResult runResult) throws Exception {
		final TestResult testResult = this.resultComparator.compare(runResult);
		this.outputPort.send(testResult);
	}

}
