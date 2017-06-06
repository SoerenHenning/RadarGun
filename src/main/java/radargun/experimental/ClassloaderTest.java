package radargun.experimental;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import radargun.comparsion.yaml.YamlInputStreamsBuilder;

public class ClassloaderTest {

	public static void main(final String[] args) throws IOException {

		System.out.println("Hi!");

		final YamlInputStreamsBuilder builder = new YamlInputStreamsBuilder();
		// builder.addClasspathLocation("test.txt");
		// builder.addClasspathLocation("testdir");
		builder.addPath(Paths.get(""));
		// builder.addPath(Paths.get("/"));
		final List<InputStream> streams = builder.build();

		System.out.println(streams);

		for (final InputStream stream : streams) {
			final List<String> filenames = new ArrayList<>();
			try (BufferedReader br = new BufferedReader(new InputStreamReader(stream))) {
				String resource;
				while ((resource = br.readLine()) != null) {
					filenames.add(resource);
				}

			}

			// System.out.println(filenames);
		}

	}

}
