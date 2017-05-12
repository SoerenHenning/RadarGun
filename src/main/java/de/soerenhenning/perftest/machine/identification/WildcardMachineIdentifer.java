package de.soerenhenning.perftest.machine.identification;

public class WildcardMachineIdentifer implements MachineIdentifier {

	public WildcardMachineIdentifer(final String... paramaters) {
	}

	@Override
	public boolean testCurrentMachine() {
		return true;
	}

}
