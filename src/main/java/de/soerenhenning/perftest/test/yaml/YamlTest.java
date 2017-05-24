package de.soerenhenning.perftest.test.yaml;

import java.util.Map;

import de.soerenhenning.perftest.Test;
import de.soerenhenning.perftest.machine.identification.MachineIdentifier;
import de.soerenhenning.perftest.machine.identification.MachineIdentifiers;
import de.soerenhenning.perftest.test.TestAssertion;

public class YamlTest implements Test {

	private final YamlParser assertionsParser;

	public YamlTest(final YamlParser assertionsParser) {
		this.assertionsParser = assertionsParser;
	}

	@Override
	public MachineIdentifier getMachineIdentifier() {
		// TODO handle expections
		final String machineIdentifierName = this.assertionsParser.getIdentifier();
		// String[0] better than new String[list.size()]:
		// https://shipilev.net/blog/2016/arrays-wisdom-ancients
		final String[] machineIdentifierParameters = this.assertionsParser.getParameters().toArray(new String[0]);
		return MachineIdentifiers.getByClassName(machineIdentifierName, machineIdentifierParameters);
	}

	@Override
	public Map<String, TestAssertion> getTests() {
		// TODO handle exception
		return this.assertionsParser.getTests();

	}

}
