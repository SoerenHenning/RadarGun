package radargun.comparsion.yaml;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.yaml.snakeyaml.Yaml;

import radargun.comparsion.Assertion;
import radargun.comparsion.Test;
import radargun.comparsion.machine.identification.DismissIdentifier;
import radargun.comparsion.machine.identification.MachineIdentifier;
import radargun.comparsion.machine.identification.MachineIdentifiers;

//TODO Name
public class YamlTest implements Test {

	private static final Yaml YAML = new Yaml();

	private final YamlParser assertionsParser;

	public YamlTest(final YamlParser assertionsParser) {
		this.assertionsParser = assertionsParser;
	}

	@Override
	public MachineIdentifier getMachineIdentifier() {
		try {
			final String machineIdentifierName = this.assertionsParser.getIdentifier();
			// String[0] better than new String[list.size()]:
			// https://shipilev.net/blog/2016/arrays-wisdom-ancients
			final String[] machineIdentifierParameters = this.assertionsParser.getParameters().toArray(new String[0]);
			return MachineIdentifiers.getByClassName(machineIdentifierName, machineIdentifierParameters);
		} catch (final YamlParsingException e) {
			logException(e);
		} catch (final IllegalArgumentException e) {
			logException(e);
		}
		return new DismissIdentifier();
	}

	@Override
	public Map<String, Assertion> getTests() {
		try {
			return this.assertionsParser.getTests();
		} catch (final YamlParsingException e) {
			logException(e);
			return Collections.emptyMap();
		}

	}

	public static Collection<YamlTest> createAll(final InputStream inputStream) {
		final Iterable<Object> loadedObjects = YAML.loadAll(inputStream);
		final List<YamlTest> yamlParsers = new ArrayList<>();
		for (final Object loadedObject : loadedObjects) {
			try {
				yamlParsers.add(new YamlTest(new YamlParser(loadedObject)));
			} catch (final YamlParsingException e) {
				logException(e);
			}

		}
		return yamlParsers;
	}

	public static Collection<YamlTest> createAll(final Collection<InputStream> inputStreams) {
		return inputStreams.stream().flatMap(s -> YamlTest.createAll(s).stream()).collect(Collectors.toList());
	}

	private static void logException(final YamlParsingException exception) {
		// BETTER use logger
		System.out.println("An error occured while processing a YAML file:");
		System.out.println(exception.getMessage());
	}

	private static void logException(final IllegalArgumentException exception) {
		// BETTER use logger
		System.out.println(exception.getMessage());
	}
}
