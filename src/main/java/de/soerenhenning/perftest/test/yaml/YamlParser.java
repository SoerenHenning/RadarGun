package de.soerenhenning.perftest.test.yaml;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.yaml.snakeyaml.Yaml;

import de.soerenhenning.perftest.test.TestAssertion;

public class YamlParser {

	private static final String IDENTIFIER_KEY = "identifier";
	private static final String PARAMETERS_KEY = "parameters";
	private static final String TESTS_KEY = "tests";

	private final Map<?, ?> yamlDocument;

	public YamlParser(final Object loadedObject) throws YamlParsingException {
		if (loadedObject instanceof Map) {
			this.yamlDocument = (Map<?, ?>) loadedObject;
		} else {
			throw new YamlParsingException("The YAML block or document is not an associative array (map).");
		}
	}

	public String getIdentifier() throws YamlParsingException {
		final Object identifier = this.yamlDocument.get(IDENTIFIER_KEY);
		if (identifier == null) {
			throw new YamlParsingException("There is no identifier given.");
		}
		return identifier.toString();
	}

	public List<String> getParameters() throws YamlParsingException {
		final Object parameters = this.yamlDocument.get(PARAMETERS_KEY);
		if (parameters == null) {
			return Collections.emptyList();
		}
		if (!(parameters instanceof List<?>)) {
			throw new YamlParsingException("The declared parameters are not a list.");
		}
		return ((List<?>) parameters).stream().map(p -> p.toString()).collect(Collectors.toList());
	}

	public Map<String, TestAssertion> getTests() throws YamlParsingException {
		final Object rawTests = this.yamlDocument.get(TESTS_KEY);
		if (rawTests == null) {
			return Collections.emptyMap();
		}
		if (!(rawTests instanceof Map<?, ?>)) {
			throw new YamlParsingException("The declared tests are not an associative array (map).");
		}
		final Map<?, ?> tests = (Map<?, ?>) rawTests;
		final Map<String, TestAssertion> castedTests = new HashMap<>();
		for (final Map.Entry<?, ?> test : tests.entrySet()) {
			final Object key = test.getKey().toString();
			final Object rawAssertions = test.getValue();
			if (!(rawAssertions instanceof List<?>)) {
				throw new YamlParsingException("There are no bounds given for test \"" + key + "\".");
			}
			final List<?> assertions = (List<?>) rawAssertions;
			if (assertions.size() != 2) {
				throw new YamlParsingException("There are not excatly two bounds given for test \"" + key + "\".");
			}
			final Object rawLowerBound = assertions.get(0);
			final Object rawUpperBound = assertions.get(1);
			if (!(rawLowerBound instanceof Number)) {
				throw new YamlParsingException("The lower bound given for test \"" + key + "\" is not a number.");
			}
			if (!(rawUpperBound instanceof Number)) {
				throw new YamlParsingException("The upper bound given for test \"" + key + "\" is not a number.");
			}
			castedTests.put(key.toString(), new TestAssertion((double) rawLowerBound, (double) rawUpperBound));
		}
		return castedTests;
	}

	public static Collection<YamlParser> createAll(final InputStream inputStream) throws YamlParsingException {
		final Yaml yaml = new Yaml();
		final Iterable<Object> loadedObjects = yaml.loadAll(inputStream);
		final List<YamlParser> yamlParsers = new ArrayList<>();
		for (final Object loadedObject : loadedObjects) {
			yamlParsers.add(new YamlParser(loadedObject));
		}
		return yamlParsers;
	}
}
