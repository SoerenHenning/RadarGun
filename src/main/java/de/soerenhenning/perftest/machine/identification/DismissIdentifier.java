package de.soerenhenning.perftest.machine.identification;

public class DismissIdentifier implements MachineIdentifier {

	@Override
	public boolean testMachine() {
		return false;
	}

}
