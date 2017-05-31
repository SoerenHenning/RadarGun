package radargun;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.util.ArrayList;
import java.util.List;

public class InputStreamsBuilder {

	private final List<InputStream> inputStreams = new ArrayList<>();

	public InputStreamsBuilder() {
	}

	public InputStreamsBuilder addClasspathLocation(final String classpathLocation) {
		final InputStream stream = this.getClass().getResourceAsStream(classpathLocation);
		if (stream != null) {
			this.inputStreams.add(stream);
		} else {
			// TODO file ignored
		}
		return this;
	}

	public InputStreamsBuilder addClasspathLocations(final Iterable<String> classpathLocation) {
		classpathLocation.forEach(this::addClasspathLocation);
		return this;
	}

	public InputStreamsBuilder addPath(final Path path) {
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

	public InputStreamsBuilder addPaths(final Iterable<Path> paths) {
		paths.forEach(this::addPath);
		return this;
	}

	public List<InputStream> build() {
		return this.inputStreams;
	}

}
