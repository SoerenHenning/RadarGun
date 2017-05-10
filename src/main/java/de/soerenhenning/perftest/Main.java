package de.soerenhenning.perftest;

import org.openjdk.jmh.runner.RunnerException;

public class Main {

	private final Configuration configuration;

	public Main(final Configuration configuration) {
		this.configuration = configuration;
	}

	public void execute() {

	}

	public static void main(final String[] args) throws RunnerException {
		final Configuration configuration = Configuration.create(args);
		new Main(configuration).execute();
	}
}
