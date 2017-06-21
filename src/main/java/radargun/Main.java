package radargun;

import com.beust.jcommander.JCommander;

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
		final Options options = new Options();
		final JCommander jCommander = JCommander.newBuilder().addObject(options).build();
		jCommander.setProgramName(PROGRAM_NAME);
		jCommander.parse(args);
		if (options.isHelp()) {
			jCommander.usage();
		} else {
			new Main(options).execute();
		}
	}
}
