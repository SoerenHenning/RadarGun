package radargun;

import java.io.IOException;

import radargun.test.result.TestResult;
import teetime.framework.AbstractConsumerStage;

public class CSVExportStage extends AbstractConsumerStage<TestResult> {

	private final CSVExport csvExport;

	public CSVExportStage(final CSVExport csvExport) {
		this.csvExport = csvExport;
	}

	@Override
	protected void execute(final TestResult result) throws Exception {
		try {
			this.csvExport.export(result);
		} catch (final IOException e) {
			throw new IllegalStateException(e);
		}
	}

}
