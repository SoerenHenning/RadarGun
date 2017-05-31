package radargun;

import java.util.Map;

import radargun.machine.identification.MachineIdentifier;
import radargun.test.TestAssertion;

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
