package radargun.comparsion.yaml;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class YamlInputStreamsBuilder {

	private static final Logger LOGGER = LoggerFactory.getLogger(YamlAssertionsDeclaration.class);

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
			LOGGER.warn("File \"{}\" is not found in classpath and therefore ignored.", classpathLocation);
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
				LOGGER.warn("An error occured while reading \"{}\". File is ignored.", path);
			}
		} else if (Files.isDirectory(path)) {
			final PathMatcher filter = path.getFileSystem().getPathMatcher("glob:**.yaml");
			try {
				Files.list(path).filter(x -> filter.matches(x)).forEach(this::addPath);
			} catch (final IOException e) {
				LOGGER.warn("An error occured while reading \"{}\". Directory is ignored.", path);
			}
		} else {
			LOGGER.warn("File or Directory \"{}\" is not found and therefore ignored.", path);
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
