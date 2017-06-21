package radargun.output.print;

import java.io.PrintStream;

import radargun.comparsion.result.TestResult;

/**
 * This class prints a {@link TestResult} to the default system's output stream
 * or another one if configured.
 *
 * The format is [{@code <status>}] {@code <test-name>} Score: {@code <score>}
 * (Bounds: [{@code <lower-bound>}, {@code upper-bound}], where {@code <status>}
 * is one of SUCCESSFULL, FAILED, or NO RESULT, @{@code <test-name>} is the
 * test's name, {@code <score>} is the score's textual representation provided
 * by JHM, and {@code <lower-bound>} and {@code <upper-bound>} are the
 * assertion's bounds.
 *
 * @author Sören Henning
 *
 */
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
		final String label = result.getRunResult().getParams().getBenchmark();
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
