package radargun.experimental;

import java.net.SocketException;

import radargun.machine.identification.MacAddressIdentifier;
import radargun.machine.identification.MachineIdentifier;

public class MacAddressTester {

	public static void main(final String[] args) throws SocketException {

		final MachineIdentifier identifier = new MacAddressIdentifier("34:F3:9A:6B:76:74");
		System.out.println(identifier.testMachine());

		final MachineIdentifier identifier2 = new MacAddressIdentifier("00:00:00:00:00:00:00:E0");
		System.out.println(identifier2.testMachine());

		final MachineIdentifier identifier3 = new MacAddressIdentifier("34:F3:9A:6B:76:73");
		System.out.println(identifier3.testMachine());

		final MachineIdentifier identifier4 = new MacAddressIdentifier("34:F3:9A:6B:76:77");
		System.out.println(identifier4.testMachine());

	}

}
