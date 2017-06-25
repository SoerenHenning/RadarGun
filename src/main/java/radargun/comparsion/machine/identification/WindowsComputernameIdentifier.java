package radargun.comparsion.machine.identification;

/**
 * This {@link MachineIdentifier} test the executing machine for one or more
 * given computer names. In most cases all systems that are positively tested by
 * this identifier should be also matched by a {@link NetworkAddressIdentifier}.
 * However, in some cases (e.g., no network interfaces available) this
 * identifier should find a machine which the {@link NetworkAddressIdentifier}
 * does not find.
 *
 * This identifier tests for the "COMPUTERNAME" environment variable and
 * therefore does only work on Windows systems.
 *
 * @author Sören Henning
 */
public class WindowsComputernameIdentifier implements MachineIdentifier {

	private final String[] computerNames;

	public WindowsComputernameIdentifier(final String... computerNames) {
		this.computerNames = computerNames;
	}

	@Override
	public boolean testMachine() {
		final String actualName = System.getenv("COMPUTERNAME");
		for (final String testName : this.computerNames) {
			if (actualName.equals(testName)) {
				return true;
			}
		}
		return false;
	}

}
