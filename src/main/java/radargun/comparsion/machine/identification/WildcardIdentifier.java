package radargun.comparsion.machine.identification;

public class WildcardIdentifier implements MachineIdentifier {

	public WildcardIdentifier(final String... paramaters) {
	}

	@Override
	public boolean testMachine() {
		return true;
	}

}
