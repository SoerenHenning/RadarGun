package de.soerenhenning.perftest;

import java.util.Map;

import de.soerenhenning.perftest.machine.identification.MachineIdentifier;
import de.soerenhenning.perftest.test.TestAssertion;

//TODO name
class TestImpl implements Test {

	private final MachineIdentifier identifier;
	private final Map<String, TestAssertion> tests;

	protected TestImpl(final MachineIdentifier identifier, final Map<String, TestAssertion> tests) {
		super();
		this.identifier = identifier;
		this.tests = tests;
	}

	@Override
	public MachineIdentifier getMachineIdentifier() {
		return this.identifier;
	}

	@Override
	public Map<String, TestAssertion> getTests() {
		return this.tests;
	}

}
