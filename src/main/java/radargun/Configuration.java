package radargun;

import java.nio.file.Path;
import java.util.Optional;

import radargun.benchmarks.BenchmarkRunnerStage;
import radargun.comparsion.ResultComparator;
import radargun.comparsion.ResultComparatorStage;
import radargun.comparsion.result.TestResult;
import radargun.comparsion.yaml.YamlTest;
import radargun.output.csv.CSVExport;
import radargun.output.csv.CSVExportStage;
import radargun.output.print.ResultsPrinter;
import radargun.output.print.ResultsPrinterStage;
import teetime.framework.OutputPort;
import teetime.stage.basic.distributor.Distributor;
import teetime.stage.basic.distributor.strategy.CopyByReferenceStrategy;

public class Configuration extends teetime.framework.Configuration {

	private final OutputPort<TestResult> outputPort;

	public Configuration(final Options options) {
		// Create base stages
		final BenchmarkRunnerStage benchmarkRunner = new BenchmarkRunnerStage(options.getRunner());
		final ResultComparatorStage resultComparator = new ResultComparatorStage(
				new ResultComparator(YamlTest.createAll(options.getYamlInputStreams())));
		final Distributor<TestResult> resultsDistributor = new Distributor<>(new CopyByReferenceStrategy());

		// Connect base stages
		super.connectPorts(benchmarkRunner.getOutputPort(), resultComparator.getInputPort());
		super.connectPorts(resultComparator.getOutputPort(), resultsDistributor.getInputPort());

		// Set exposed output port
		this.outputPort = resultsDistributor.getNewOutputPort();

		// Create optional stages
		if (options.isExitOnFail()) {
			final ExitOnFailStage exitOnFailStage = new ExitOnFailStage();
			super.connectPorts(resultsDistributor.getNewOutputPort(), exitOnFailStage.getInputPort());
		}
		final Optional<Path> csvDirectory = options.getCsvDirectory();
		if (csvDirectory.isPresent()) {
			final CSVExportStage csvExportStage = new CSVExportStage(new CSVExport(csvDirectory.get()));
			super.connectPorts(resultsDistributor.getNewOutputPort(), csvExportStage.getInputPort());
		}
		if (options.isOutput()) {
			final ResultsPrinterStage resultsPrinterStage = new ResultsPrinterStage(
					new ResultsPrinter(options.getOutputStream()));
			super.connectPorts(resultsDistributor.getNewOutputPort(), resultsPrinterStage.getInputPort());
		}

	}

	public OutputPort<TestResult> getOutputPort() {
		return this.outputPort;
	}

}
