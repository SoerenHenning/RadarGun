package radargun.machine.identification;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

import radargun.util.Network;

public class MacAddressIdentifier implements MachineIdentifier {

	private final List<byte[]> macAddresses = new ArrayList<>();

	public MacAddressIdentifier(final String... macAddresses) {
		for (final String macAddress : macAddresses) {
			this.macAddresses.add(Network.parseMacAdress(macAddress));
		}

	}

	@Override
	public boolean testMachine() {
		try {
			final Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
			while (networkInterfaces.hasMoreElements()) {
				final NetworkInterface n = networkInterfaces.nextElement();
				final byte[] discoveredMacAddress = n.getHardwareAddress();
				if (discoveredMacAddress != null) {
					for (final byte[] macAddress : this.macAddresses) {
						if (Arrays.equals(macAddress, discoveredMacAddress)) {
							return true;
						}
					}
				}
			}
			return false;
		} catch (final SocketException e) {
			throw new IllegalStateException(e);
		}

	}

}
