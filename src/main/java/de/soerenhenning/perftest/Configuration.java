package de.soerenhenning.perftest;

import de.soerenhenning.perftest.test.ResultComparatorStage;
import de.soerenhenning.perftest.test.result.TestResult;
import teetime.framework.OutputPort;
import teetime.stage.basic.distributor.Distributor;
import teetime.stage.basic.distributor.strategy.CopyByReferenceStrategy;

public class Configuration extends teetime.framework.Configuration {

	private final OutputPort<TestResult> outputPort;

	public Configuration(final Options options) {
		// Create base stages
		final BenchmarkRunnerStage benchmarkRunner = new BenchmarkRunnerStage(); // TODO
		final ResultComparatorStage resultComparator = new ResultComparatorStage();
		final Distributor<TestResult> resultsDistributor = new Distributor<>(new CopyByReferenceStrategy());

		// Connect base stages
		super.connectPorts(benchmarkRunner.getOutputPort(), resultComparator.getInputPort());
		super.connectPorts(resultComparator.getOutputPort(), resultsDistributor.getInputPort());

		// Set exposed output port
		this.outputPort = resultsDistributor.getNewOutputPort();

		if (true) { // TODO get from config
			final ExitOnFailStage exitOnFailStage = new ExitOnFailStage();
			super.connectPorts(resultsDistributor.getNewOutputPort(), exitOnFailStage.getInputPort());
		}

		if (true) { // TODO get from config
			// csv stage
			final ExitOnFailStage exitOnFailStage = new ExitOnFailStage();
			super.connectPorts(resultsDistributor.getNewOutputPort(), exitOnFailStage.getInputPort());
		}

		if (true) { // TODO get from config
			// printer stage
			// final ExitOnFailStage exitOnFailStage = new ExitOnFailStage();
			// super.connectPorts(resultsDistributor.getNewOutputPort(),
			// exitOnFailStage.getInputPort());
		}

	}

	public OutputPort<TestResult> getOutputPort() {
		return this.outputPort;
	}

}