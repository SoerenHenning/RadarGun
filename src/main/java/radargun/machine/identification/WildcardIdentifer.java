package radargun.machine.identification;

public class WildcardIdentifer implements MachineIdentifier {

	public WildcardIdentifer(final String... paramaters) {
	}

	@Override
	public boolean testMachine() {
		return true;
	}

}
