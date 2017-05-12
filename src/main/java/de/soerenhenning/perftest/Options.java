package de.soerenhenning.perftest;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import com.beust.jcommander.IStringConverter;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

public class Options {

	public Options() {
	}

	@Parameter(names = { "-log", "-verbose" }, description = "Level of verbosity")
	private final Integer verbose = 1;

	@Parameter(names = "-groups", description = "Comma-separated list of group names to be run")
	private String groups;

	@Parameter(names = "-debug", description = "Debug mode")
	private final boolean debug = false;

	@Parameter(names = "--assertions", converter = PathConverter.class, description = "...") // TODO
	private List<Path> file;

	@Parameter(names = "--exit-on-fail", description = "...") // TODO
	private final boolean exitOnFail = false;

	public static Options create(final String[] argv) {
		final Options configuration = new Options();
		JCommander.newBuilder().addObject(configuration).build().parse(argv);
		return configuration;
	}

	private static class PathConverter implements IStringConverter<Path> {
		@Override
		public Path convert(final String value) {
			return Paths.get(value);
		}
	}
}
