package de.soerenhenning.perftest;

import java.util.Map;

import de.soerenhenning.perftest.machine.identification.MachineIdentifier;
import de.soerenhenning.perftest.test.TestAssertion;

//TODO Name
public interface Test {

	public MachineIdentifier getMachineIdentifier();

	public Map<String, TestAssertion> getTests();
}
