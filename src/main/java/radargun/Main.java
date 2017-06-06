package radargun;

import org.openjdk.jmh.runner.RunnerException;

import teetime.framework.Execution;

public class Main {

	final Execution<Configuration> analysis;

	public Main(final Options options) {
		final Configuration configuration = new Configuration(options);
		this.analysis = new Execution<>(configuration);
	}

	public void execute() {
		this.analysis.executeBlocking();
	}

	public static void main(final String[] args) throws RunnerException {
		final Options options = Options.create(args);
		new Main(options).execute();
	}
}
