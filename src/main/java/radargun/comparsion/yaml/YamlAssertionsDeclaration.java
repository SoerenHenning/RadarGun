package radargun.comparsion.yaml;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;

import radargun.comparsion.Assertion;
import radargun.comparsion.AssertionsDeclaration;
import radargun.comparsion.machine.identification.DismissIdentifier;
import radargun.comparsion.machine.identification.MachineIdentifier;
import radargun.comparsion.machine.identification.MachineIdentifiers;

public class YamlAssertionsDeclaration implements AssertionsDeclaration {

	private static final Logger LOGGER = LoggerFactory.getLogger(YamlAssertionsDeclaration.class);

	private static final Yaml YAML = new Yaml();

	private final YamlParser assertionsParser;

	public YamlAssertionsDeclaration(final YamlParser assertionsParser) {
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
	public Map<String, Assertion> getAssertions() {
		try {
			return this.assertionsParser.getTests();
		} catch (final YamlParsingException e) {
			logException(e);
			return Collections.emptyMap();
		}

	}

	public static Collection<YamlAssertionsDeclaration> createAll(final InputStream inputStream) {
		final Iterable<Object> loadedObjects = YAML.loadAll(inputStream);
		final List<YamlAssertionsDeclaration> yamlParsers = new ArrayList<>();
		for (final Object loadedObject : loadedObjects) {
			try {
				yamlParsers.add(new YamlAssertionsDeclaration(new YamlParser(loadedObject)));
			} catch (final YamlParsingException e) {
				logException(e);
			}

		}
		return yamlParsers;
	}

	public static Collection<YamlAssertionsDeclaration> createAll(final Collection<InputStream> inputStreams) {
		return inputStreams.stream().flatMap(s -> YamlAssertionsDeclaration.createAll(s).stream())
				.collect(Collectors.toList());
	}

	private static void logException(final YamlParsingException exception) {
		LOGGER.warn("An error occured while processing a YAML file: {}", exception.getMessage());
	}

	private static void logException(final IllegalArgumentException exception) {
		LOGGER.warn("{}", exception.getMessage());
	}
}
