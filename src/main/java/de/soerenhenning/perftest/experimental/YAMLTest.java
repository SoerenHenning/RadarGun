package de.soerenhenning.perftest.experimental;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.soerenhenning.perftest.InputStreamsBuilder;
import de.soerenhenning.perftest.machine.identification.MachineIdentifier;
import de.soerenhenning.perftest.machine.identification.MachineIdentifiers;
import de.soerenhenning.perftest.test.TestAssertion;
import de.soerenhenning.perftest.test.yaml.YamlParser;
import de.soerenhenning.perftest.test.yaml.YamlParsingException;

public class YAMLTest {

	public static void main(final String[] args) throws IOException, YamlParsingException {

		// final Yaml yaml = new Yaml();
		// final String document = "56: 25";
		// final Object o = yaml.load(document);
		// final Map<String, Object> map = (Map<String, Object>) o;
		// yaml.load(document); // loadAll

		// final String identiferName = map.get("identifier");

		// System.out.println(map);
		// final String first = map.keySet().iterator().next();
		// map.keySet().iterator().next().length();

		final List<String> classpathLocations = new ArrayList<>();
		final List<Path> paths = new ArrayList<>();

		final InputStreamsBuilder inputStreamsBuilder = new InputStreamsBuilder();
		inputStreamsBuilder.addClasspathLocations(classpathLocations);
		inputStreamsBuilder.addPaths(paths);
		final List<InputStream> inputStreams = inputStreamsBuilder.build();

		final List<YamlParser> assertionsParsers = new ArrayList<>();
		for (final InputStream inputStream : inputStreams) {
			assertionsParsers.addAll(YamlParser.createAll(inputStream));
		}

		final Map<String, TestAssertion> testAssertions = new HashMap<>();
		for (final YamlParser assertionsParser : assertionsParsers) {
			assertionsParser.getIdentifier();
			assertionsParser.getParameters();
			// ...
			final String machineIdentifierName = assertionsParser.getIdentifier();
			final String[] machineIdentifierParameters = {}; // assertionsParser.getParameters();
			final MachineIdentifier machineIdentifier = MachineIdentifiers.getByClassName(machineIdentifierName,
					machineIdentifierParameters);
			if (machineIdentifier.testCurrentMachine() == true) {
				testAssertions.putAll(assertionsParser.getTests());
			}
		}

	}

}
