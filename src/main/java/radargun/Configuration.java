package radargun;

import java.nio.file.Path;
import java.util.Optional;

import radargun.benchmarks.BenchmarkRunnerStage;
import radargun.comparsion.ResultComparator;
import radargun.comparsion.ResultComparatorStage;
import radargun.comparsion.result.TestResult;
import radargun.comparsion.yaml.YamlAssertionsDeclaration;
import radargun.output.csv.CSVExport;
import radargun.output.csv.CSVExportStage;
import radargun.output.exitonfail.FinallyExitOnFailStage;
import radargun.output.exitonfail.ImmediatelyExitOnFailStage;
import radargun.output.print.ResultsPrinter;
import radargun.output.print.ResultsPrinterStage;
import teetime.framework.OutputPort;
import teetime.stage.basic.distributor.Distributor;
import teetime.stage.basic.distributor.strategy.CopyByReferenceStrategy;

/**
 * The Pipe-and-Filter configuration of RadarGun. The stages are created and
 * connected based on an {@link Options} object.
 *
 * TeeTime configurations are always composite stages itself. For further
 * processing of {@link TestResult}s, this configuration exposes one
 * {@link OutputPort} that can be used to connect further stages.
 *
 * @author Sören Henning
 *
 */
public class Configuration extends teetime.framework.Configuration {

	private final OutputPort<TestResult> outputPort;

	public Configuration(final Options options) {
		// Create base stages
		final BenchmarkRunnerStage benchmarkRunner = new BenchmarkRunnerStage(options.getRunner());
		final ResultComparatorStage resultComparator = new ResultComparatorStage(
				new ResultComparator(YamlAssertionsDeclaration.createAll(options.getYamlInputStreams())));
		final Distributor<TestResult> resultsDistributor = new Distributor<>(new CopyByReferenceStrategy());

		// Connect base stages
		super.connectPorts(benchmarkRunner.getOutputPort(), resultComparator.getInputPort());
		super.connectPorts(resultComparator.getOutputPort(), resultsDistributor.getInputPort());

		// Set exposed output port
		this.outputPort = resultsDistributor.getNewOutputPort();

		// Create optional stages
		if (options.isExitOnFail()) {
			final FinallyExitOnFailStage exitOnFailStage = new FinallyExitOnFailStage();
			super.connectPorts(resultsDistributor.getNewOutputPort(), exitOnFailStage.getInputPort());
		}
		if (options.isExitOnFailImmediately()) {
			final ImmediatelyExitOnFailStage exitOnFailStage = new ImmediatelyExitOnFailStage();
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
