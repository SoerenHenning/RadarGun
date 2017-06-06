package radargun.output.print;

import java.io.PrintStream;

import radargun.comparsion.result.TestResult;

public class ResultsPrinter {

	private final PrintStream printStream;
	private boolean started = false;

	public ResultsPrinter() {
		this.printStream = System.out;
	}

	public ResultsPrinter(final PrintStream printStream) {
		this.printStream = printStream;
	}

	private void startPrinting() {
		if (this.started) {
			return;
		}
		this.printStream.println();
		this.started = true;
	}

	public void print(final TestResult result) {
		this.startPrinting();

		final String status = this.getStatus(result).toString();
		final String label = result.getRunResult().getParams().getBenchmark() + "."
				+ result.getRunResult().getPrimaryResult().getLabel();
		final String score = result.getRunResult().getPrimaryResult().toString();
		final String lowerBound = String.valueOf(result.getAssertion().getLowerBound());
		final String upperBound = String.valueOf(result.getAssertion().getUpperBound());

		this.printStream.println("[" + status + "] " + label + " Score: " + score + " (Bounds: [" + lowerBound + ", "
				+ upperBound + "])");
	}

	public Status getStatus(final TestResult result) {
		if (result.wasSuccesfull()) {
			return Status.SUCCESSFULL;
		} else if (result.hasFailed()) {
			return Status.FAILED;
		} else {
			return Status.NO_RESULT;
		}
	}

	public enum Status {

		FAILED("FAILED"), SUCCESSFULL("SUCCESSFULL"), NO_RESULT("NO RESULT");

		private final String name;

		private Status(final String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return this.name;
		}

	}

}
