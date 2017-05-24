package de.soerenhenning.perftest.machine.identification;

public class DismissIdentifier implements MachineIdentifier {

	public DismissIdentifier(final String... paramaters) {
	}

	@Override
	public boolean testMachine() {
		return false;
	}

}
