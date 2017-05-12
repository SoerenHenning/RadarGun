package de.soerenhenning.perftest;

import java.io.InputStream;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.yaml.snakeyaml.Yaml;

import de.soerenhenning.perftest.test.TestAssertion;

public class AssertionsParser {

	private static final String IDENTIFIER_KEY = "identifier";
	private static final String PARAMETERS_KEY = "parameters";
	private static final String TESTS_KEY = "tests";

	private final Map<?, ?> yamlDocument;

	public AssertionsParser(final Object loadedObject) {
		if (loadedObject instanceof Map) {
			this.yamlDocument = (Map<?, ?>) loadedObject;
		} else {
			throw new IllegalArgumentException("The YAML block or document is not an associative array (map).");
		}
	}

	public String getIdentifier() {
		final Object identifier = this.yamlDocument.get(IDENTIFIER_KEY);
		if (identifier == null) {
			throw new IllegalArgumentException("There is no identifier given.");
		}
		return identifier.toString();
	}

	public List<String> getParameters() {
		final Object parameters = this.yamlDocument.get(PARAMETERS_KEY);
		if (parameters == null) {
			return Collections.emptyList();
		}
		if (!(parameters instanceof List<?>)) {
			throw new IllegalArgumentException("The declared parameters are not a list.");
		}
		return ((List<?>) parameters).stream().map(p -> p.toString()).collect(Collectors.toList());
	}

	public Map<String, TestAssertion> getTests() {
		final Object rawTests = this.yamlDocument.get(TESTS_KEY);
		if (rawTests == null) {
			return Collections.emptyMap();
		}
		if (!(rawTests instanceof Map<?, ?>)) {
			throw new IllegalArgumentException("The declared tests are not an associative array (map).");
		}
		final Map<?, ?> tests = (Map<?, ?>) rawTests;
		return tests.entrySet().stream().collect(Collectors.toMap(e -> e.getKey().toString(), e -> {
			final Object key = e.getKey().toString();
			final Object rawAssertions = e.getValue();
			if (!(rawAssertions instanceof List<?>)) {
				throw new IllegalArgumentException("There are no bounds given for test \"" + key + "\".");
			}
			final List<?> assertions = (List<?>) rawAssertions;
			if (assertions.size() != 2) {
				throw new IllegalArgumentException("There are not excatly two bounds given for test \"" + key + "\".");
			}
			final Object rawLowerBound = assertions.get(0);
			final Object rawUpperBound = assertions.get(1);
			if (!(rawLowerBound instanceof Number)) {
				throw new IllegalArgumentException("The lower bound given for test \"" + key + "\" is not a number.");
			}
			if (!(rawUpperBound instanceof Number)) {
				throw new IllegalArgumentException("The upper bound given for test \"" + key + "\" is not a number.");
			}
			return new TestAssertion((double) rawLowerBound, (double) rawUpperBound);
		}, (a, b) -> a));
	}

	public static Collection<AssertionsParser> createAll(final InputStream inputStream) {
		final Yaml yaml = new Yaml();
		final Iterable<Object> loadedObjects = yaml.loadAll(inputStream);
		return StreamSupport.stream(loadedObjects.spliterator(), false).map(o -> new AssertionsParser(o))
				.collect(Collectors.toList());
	}
}
