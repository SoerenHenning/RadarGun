package radargun.experimental;

import java.util.List;
import java.util.Map.Entry;

import org.openjdk.jmh.runner.RunnerException;

import radargun.Configuration;
import radargun.Options;
import teetime.framework.Execution;
import teetime.framework.ExecutionException;

public class TestMain {

	final Execution<Configuration> analysis;

	public TestMain(final Options options) {
		final Configuration configuration = new Configuration(options);
		this.analysis = new Execution<>(configuration);
	}

	public void execute() {
		try {
			this.analysis.executeBlocking();
		} catch (final ExecutionException e) {
			System.out.println("=======");
			for (final Entry<Thread, List<Exception>> entry : e.getThrownExceptions().entrySet()) {
				for (final Exception exception : entry.getValue()) {
					exception.printStackTrace();
				}
			}
		}

		// System.out.println(config.getValues());
	}

	public static void main(final String[] args) throws RunnerException {
		final Options options = Options.create(args);
		new TestMain(options).execute();
	}
}
