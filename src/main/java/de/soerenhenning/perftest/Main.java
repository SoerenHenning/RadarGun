package de.soerenhenning.perftest;

import org.openjdk.jmh.runner.RunnerException;

public class Main {

	private final Options options;

	public Main(final Options options) {
		this.options = options;
	}

	public void execute() {
		try {
			JMHTest.main(null);
		} catch (final RunnerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(final String[] args) throws RunnerException {
		// final Configuration configuration = Configuration.create(args);
		final Options options = null; // TODO temp
		new Main(options).execute();
	}
}
