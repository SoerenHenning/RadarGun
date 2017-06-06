package radargun.output.print;

import radargun.comparsion.result.TestResult;
import teetime.framework.AbstractConsumerStage;

public class ResultsPrinterStage extends AbstractConsumerStage<TestResult> {

	private final ResultsPrinter resultsPrinter;

	public ResultsPrinterStage() {
		this.resultsPrinter = new ResultsPrinter();
	}

	public ResultsPrinterStage(final ResultsPrinter resultsPrinter) {
		this.resultsPrinter = resultsPrinter;
	}

	@Override
	protected void execute(final TestResult result) throws Exception {
		this.resultsPrinter.print(result);
	}

}
