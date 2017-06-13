package radargun.comparsion.yaml;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class YamlInputStreamsBuilder {

	private final List<InputStream> inputStreams = new ArrayList<>();

	public YamlInputStreamsBuilder() {
	}

	public YamlInputStreamsBuilder addClasspathLocation(final String classpathLocation) {
		final String fixedClasspathLocation = classpathLocation.charAt(0) == '/' ? classpathLocation
				: '/' + classpathLocation;
		final InputStream stream = this.getClass().getResourceAsStream(fixedClasspathLocation);
		if (stream != null) {
			this.inputStreams.add(stream);
		} else {
			// BETTER use logger
			System.out.println("File \"" + classpathLocation + "\" is not found in classpath and therefore ignored.");
		}
		return this;
	}

	public YamlInputStreamsBuilder addClasspathLocations(final Iterable<String> classpathLocations) {
		classpathLocations.forEach(this::addClasspathLocation);
		return this;
	}

	public YamlInputStreamsBuilder addPath(final Path path) {
		if (Files.isRegularFile(path)) {
			try {
				final InputStream stream = Files.newInputStream(path);
				this.inputStreams.add(stream);
			} catch (final IOException e) {
				// BETTER use logger
				System.out.println("An error occured while reading \"" + path + "\". File is ignored.");
			}
		} else if (Files.isDirectory(path)) {
			final PathMatcher filter = path.getFileSystem().getPathMatcher("glob:**.yaml");
			try {
				Files.list(path).filter(x -> filter.matches(x)).forEach(this::addPath);
			} catch (final IOException e) {
				// BETTER use logger
				System.out.println("An error occured while reading \"" + path + "\". Directory is ignored.");
			}
		} else {
			// BETTER use logger
			System.out.println("File or Directory \"" + path + "\" is not found and therefore ignored.");
		}
		return this;
	}

	public YamlInputStreamsBuilder addPaths(final Iterable<Path> paths) {
		paths.forEach(this::addPath);
		return this;
	}

	public YamlInputStreamsBuilder addInputStream(final InputStream inputStream) {
		this.inputStreams.add(inputStream);
		return this;
	}

	public YamlInputStreamsBuilder addInputStreams(final Collection<InputStream> inputStreams) {
		this.inputStreams.addAll(inputStreams);
		return this;
	}

	public List<InputStream> build() {
		return this.inputStreams;
	}

}
