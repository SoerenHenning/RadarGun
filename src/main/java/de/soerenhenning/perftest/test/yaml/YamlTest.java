package de.soerenhenning.perftest.test.yaml;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

import de.soerenhenning.perftest.Test;
import de.soerenhenning.perftest.machine.identification.DismissIdentifier;
import de.soerenhenning.perftest.machine.identification.MachineIdentifier;
import de.soerenhenning.perftest.machine.identification.MachineIdentifiers;
import de.soerenhenning.perftest.test.TestAssertion;

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
			// TODO handle expections
			e.printStackTrace();
			// Return an identifier that matches no machine
			return new DismissIdentifier();
		}
	}

	@Override
	public Map<String, TestAssertion> getTests() {
		try {
			return this.assertionsParser.getTests();
		} catch (final YamlParsingException e) {
			// TODO handle exception
			return Collections.emptyMap();
		}

	}

	public static Collection<YamlParser> createAll(final InputStream inputStream) {
		final Iterable<Object> loadedObjects = YAML.loadAll(inputStream);
		final List<YamlParser> yamlParsers = new ArrayList<>();
		for (final Object loadedObject : loadedObjects) {
			try {
				yamlParsers.add(new YamlParser(loadedObject));
			} catch (final YamlParsingException e) {
				// TODO handle exception
				e.printStackTrace();
			}
		}
		return yamlParsers;
	}

}
