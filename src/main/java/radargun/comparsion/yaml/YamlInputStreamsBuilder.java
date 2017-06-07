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
		final InputStream stream = this.getClass().getResourceAsStream(classpathLocation);
		if (stream != null) {
			this.inputStreams.add(stream);
		} else {
			// TODO file ignored
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
				// TODO
			}
		} else if (Files.isDirectory(path)) {
			final PathMatcher filter = path.getFileSystem().getPathMatcher("glob:**.yaml");
			try {
				Files.list(path).filter(x -> filter.matches(x)).forEach(this::addPath);
			} catch (final IOException e) {
				// TODO
			}
		} else {
			// TODO File ignored
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