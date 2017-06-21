package radargun;

import java.io.InputStream;
import java.io.PrintStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.VerboseMode;

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.IStringConverter;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;

import radargun.comparsion.yaml.YamlInputStreamsBuilder;

public class Options {

	@Parameter(names = "--help", help = true, description = "Print usage")
	private boolean help;

	@Parameter(names = "--assertions", converter = PathConverter.class, description = "Comma-sperated list of assertions in the filesystem (files or directories)")
	private List<Path> files; // TODO name

	@Parameter(names = "--cp-assertions", description = "Comma-sperated list of assertions in the classpath (must be files)")
	private List<String> classpathLocations; // TODO name

	@Parameter(names = "--exit-on-fail-immediately", description = "Enable exit on fail of first test")
	private boolean exitOnFailImmediately = false;

	@Parameter(names = "--exit-on-fail", description = "Enable exit on fail after all tests run")
	private boolean exitOnFail = false;

	@Parameter(names = "--console-output", arity = 1, description = "Console output")
	private boolean output = true;

	@Parameter(names = "--jmh-output", description = "Enable JMH output")
	private boolean jmhOutput = false;

	@Parameter(names = "--output-stream", validateWith = PrintStreamValidator.class, converter = PrintStreamConverter.class, description = "Output stream ('out' or 'err')")
	private PrintStream outputStream = System.out;

	@Parameter(names = "--csv", converter = PathConverter.class, description = "Enables CSV output to specified directory")
	private Path csvDirectory = null;

	private Runner runner = null;

	private List<InputStream> additionallyInputStreams;

	public Options() {
	}

	public boolean isHelp() {
		return this.help;
	}

	private Runner getDefaultRunner() {
		final OptionsBuilder jmhOptionsBuilder = new OptionsBuilder();
		if (!this.isJmhOutput()) {
			jmhOptionsBuilder.verbosity(VerboseMode.SILENT);
		}
		return new Runner(jmhOptionsBuilder.build());
	}

	public Runner getRunner() {
		if (this.runner == null) {
			return this.getDefaultRunner();
		} else {
			return this.runner;
		}
	}

	public void setRunner(final Runner runner) {
		this.runner = runner;
	}

	public List<Path> getFiles() {
		if (this.files == null) {
			this.files = new ArrayList<>(4);
		}
		return this.files;
	}

	private List<Path> getFilesIfPresent() {
		if (this.files == null) {
			return Collections.emptyList();
		} else {
			return this.files;
		}
	}

	public List<String> getClasspathLocations() {
		if (this.classpathLocations == null) {
			this.classpathLocations = new ArrayList<>(4);
		}
		return this.classpathLocations;
	}

	private List<String> getClasspathLocationsIfPresent() {
		if (this.classpathLocations == null) {
			return Collections.emptyList();
		} else {
			return this.classpathLocations;
		}
	}

	public boolean isExitOnFail() {
		return this.exitOnFail;
	}

	public void setExitOnFail(final boolean exitOnFail) {
		this.exitOnFail = exitOnFail;
	}

	public boolean isExitOnFailImmediately() {
		return this.exitOnFailImmediately;
	}

	public void setExitOnFailImmediately(final boolean exitOnFailImmediately) {
		this.exitOnFailImmediately = exitOnFailImmediately;
	}

	public boolean isOutput() {
		return this.output;
	}

	public void setOutput(final boolean output) {
		this.output = output;
	}

	public PrintStream getOutputStream() {
		return this.outputStream;
	}

	public void setOutputStream(final PrintStream outputStream) {
		this.outputStream = outputStream;
	}

	public boolean isJmhOutput() {
		return this.jmhOutput;
	}

	public void setJmhOutput(final boolean jmhOutput) {
		this.jmhOutput = jmhOutput;
	}

	public void deactivateCsvOutput() {
		this.csvDirectory = null;
	}

	public void setCsvDirectory(final Path csvDirectory) {
		this.csvDirectory = csvDirectory;
	}

	public Optional<Path> getCsvDirectory() {
		return Optional.ofNullable(this.csvDirectory);
	}

	public List<InputStream> getAddionallyInputStreams() {
		if (this.additionallyInputStreams == null) {
			this.additionallyInputStreams = new ArrayList<>(4);
		}
		return this.additionallyInputStreams;
	}

	private List<InputStream> getAddionallyInputStreamsIfPresent() {
		if (this.additionallyInputStreams == null) {
			return Collections.emptyList();
		} else {
			return this.additionallyInputStreams;
		}
	}

	public Collection<InputStream> getYamlInputStreams() {
		return new YamlInputStreamsBuilder().addClasspathLocations(this.getClasspathLocationsIfPresent())
				.addPaths(this.getFilesIfPresent()).addInputStreams(this.getAddionallyInputStreamsIfPresent()).build();
	}

	public static Options create(final String... args) {
		final Options options = new Options();
		JCommander.newBuilder().addObject(options).build().parse(args);
		return options;
	}

	private static class PathConverter implements IStringConverter<Path> {
		@Override
		public Path convert(final String value) {
			return Paths.get(value);
		}
	}

	private static class PrintStreamConverter implements IStringConverter<PrintStream> {
		@Override
		public PrintStream convert(final String value) {
			switch (value) {
			case "out":
				return System.out;
			case "err":
				return System.err;
			default:
				// Should not happen because of previous validation
				throw new IllegalArgumentException("Invalid print stream: " + value);
			}
		}
	}

	private static class PrintStreamValidator implements IParameterValidator {
		@Override
		public void validate(final String name, final String value) throws ParameterException {
			switch (value) {
			case "out":
				return;
			case "err":
				return;
			default:
				throw new ParameterException("Parameter " + name + " has to be either \"out\" or \"err\"");
			}
		}
	}
}
