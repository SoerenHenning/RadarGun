package radargun.comparsion;

import java.util.Map;

import radargun.comparsion.machine.identification.MachineIdentifier;

class AssertionsDeclarationImpl implements AssertionsDeclaration {

	private final MachineIdentifier identifier;
	private final Map<String, Assertion> tests;

	protected AssertionsDeclarationImpl(final MachineIdentifier identifier, final Map<String, Assertion> tests) {
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
