package radargun;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;

import teetime.framework.Execution;

public class Main {

	private static final String PROGRAM_NAME = "RadarGun";

	final Execution<Configuration> analysis;

	public Main(final Options options) {
		final Configuration configuration = new Configuration(options);
		this.analysis = new Execution<>(configuration);
	}

	public void execute() {
		this.analysis.executeBlocking();
	}

	public static void main(final String[] args) {
		try {
			final Options options = new Options();
			final JCommander jCommander = JCommander.newBuilder().addObject(options).build();
			jCommander.setProgramName(PROGRAM_NAME);
			jCommander.parse(args);
			if (options.isHelp()) {
				jCommander.usage();
			} else {
				new Main(options).execute();
			}
		} catch (final ParameterException exception) {
			System.err.println(exception.getMessage()); // TODO Use logger
		}
	}
}
