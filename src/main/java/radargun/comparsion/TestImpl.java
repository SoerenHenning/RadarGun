package radargun.comparsion;

import java.util.Map;

import radargun.comparsion.machine.identification.MachineIdentifier;

//TODO name
class TestImpl implements Test {

	private final MachineIdentifier identifier;
	private final Map<String, Assertion> tests;

	protected TestImpl(final MachineIdentifier identifier, final Map<String, Assertion> tests) {
		super();
		this.identifier = identifier;
		this.tests = tests;
	}

	@Override
	public MachineIdentifier getMachineIdentifier() {
		return this.identifier;
	}

	@Override
	public Map<String, Assertion> getTests() {
		return this.tests;
	}

}
