package radargun;

import java.nio.file.Path;

import radargun.test.result.TestResult;
import teetime.framework.AbstractConsumerStage;

public class CSVExportStage extends AbstractConsumerStage<TestResult> {

	private final Path directory;

	public CSVExportStage(final Path directory) {
		this.directory = directory;
	}

	@Override
	protected void execute(final TestResult result) throws Exception {
		// TODO
	}

}
