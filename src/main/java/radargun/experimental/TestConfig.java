package radargun.experimental;

import java.util.List;

import radargun.Options;
import radargun.benchmarks.BenchmarkRunnerStage;
import radargun.comparsion.ResultComparator;
import radargun.comparsion.ResultComparatorStage;
import radargun.comparsion.result.TestResult;
import radargun.comparsion.yaml.YamlTest;
import teetime.framework.Configuration;
import teetime.stage.CollectorSink;

public class TestConfig extends Configuration {

	final CollectorSink<TestResult> collectorSink = new CollectorSink<>();

	public TestConfig(final Options options) {
		// Create base stages
		final BenchmarkRunnerStage benchmarkRunner = new BenchmarkRunnerStage(options.getRunner());
		final ResultComparatorStage resultComparator = new ResultComparatorStage(
				new ResultComparator(YamlTest.createAll(options.getYamlInputStreams())));
		super.connectPorts(benchmarkRunner.getOutputPort(), resultComparator.getInputPort());
		super.connectPorts(resultComparator.getOutputPort(), this.collectorSink.getInputPort());
	}

	public List<TestResult> getValues() {
		return this.collectorSink.getElements();
	}

}
