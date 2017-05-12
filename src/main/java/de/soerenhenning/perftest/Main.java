package de.soerenhenning.perftest;

import org.openjdk.jmh.runner.RunnerException;

public class Main {

	private final Configuration configuration;

	public Main(final Configuration configuration) {
		this.configuration = configuration;
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
		final Configuration configuration = null; // TODO temp
		new Main(configuration).execute();
	}
}
