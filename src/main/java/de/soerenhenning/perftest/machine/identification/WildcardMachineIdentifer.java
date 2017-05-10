package de.soerenhenning.perftest.machine.identification;

public class WildcardMachineIdentifer implements MachineIdentifier {

	public WildcardMachineIdentifer() {
	}

	public boolean testCurrentMachine() {
		return true;
	}

}
