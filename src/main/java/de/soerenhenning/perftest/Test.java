package de.soerenhenning.perftest;

import java.util.Map;

import de.soerenhenning.perftest.machine.identification.MachineIdentifier;
import de.soerenhenning.perftest.test.TestAssertion;

//TODO Name
public interface Test {

	public MachineIdentifier getMachineIdentifier();

	public Map<String, TestAssertion> getTests();

	public static Test of(final MachineIdentifier identifier, final Map<String, TestAssertion> tests) {
		return new TestImpl(identifier, tests);
	}
}
