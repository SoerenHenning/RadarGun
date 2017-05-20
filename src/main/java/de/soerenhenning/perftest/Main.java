package de.soerenhenning.perftest;

import org.openjdk.jmh.runner.RunnerException;

import de.soerenhenning.perftest.experimental.JMHTest;
import teetime.framework.Execution;

public class Main {

	private final Options options;

	public Main(final Options options) {
		this.options = options;
	}

	public void execute() {
		final Configuration configuration = new Configuration(this.options);
		final Execution<Configuration> analysis = new Execution<>(configuration);
		analysis.executeBlocking();
		// TODO Temp
		try {
			JMHTest.main(null);
		} catch (final RunnerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(final String[] args) throws RunnerException {
		// final Options options = Options.create(args);
		final Options options = null; // TODO temp
		new Main(options).execute();
	}
}
