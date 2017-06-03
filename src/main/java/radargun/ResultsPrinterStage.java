package radargun;

import java.io.PrintStream;

import radargun.test.result.TestResult;
import teetime.framework.AbstractConsumerStage;

public class ResultsPrinterStage extends AbstractConsumerStage<TestResult> {

	private final PrintStream printStream;

	public ResultsPrinterStage() {
		this.printStream = System.out;
	}

	public ResultsPrinterStage(final PrintStream printStream) {
		this.printStream = printStream;
	}

	@Override
	protected void execute(final TestResult result) throws Exception {
		// TODO
	}

}
