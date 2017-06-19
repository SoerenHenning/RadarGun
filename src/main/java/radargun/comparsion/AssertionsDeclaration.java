package radargun.comparsion;

import java.util.Map;

import radargun.comparsion.machine.identification.MachineIdentifier;

public interface AssertionsDeclaration {

	public MachineIdentifier getMachineIdentifier();

	public Map<String, Assertion> getTests();

	public static AssertionsDeclaration of(final MachineIdentifier identifier, final Map<String, Assertion> tests) {
		return new AssertionsDeclarationImpl(identifier, tests);
	}
}
