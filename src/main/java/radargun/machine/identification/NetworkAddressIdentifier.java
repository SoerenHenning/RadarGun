package radargun.machine.identification;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * This {@link MachineIdentifier} tests the execution machine for a given array
 * of network addresses. Network addresses can either be IP addresses or host
 * names. A test is successful if at least one given network addresse
 * corresponds to the executing machine.
 *
 * @author Sören Henning
 *
 */
public class NetworkAddressIdentifier implements MachineIdentifier {

	private final String[] networkAddresses;

	public NetworkAddressIdentifier(final String... networkAddresses) {
		this.networkAddresses = networkAddresses;
	}

	@Override
	public boolean testMachine() {
		try {
			final Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
			while (networkInterfaces.hasMoreElements()) {
				final NetworkInterface networkInterface = networkInterfaces.nextElement();
				final Enumeration<InetAddress> InetAdresses = networkInterface.getInetAddresses();
				while (InetAdresses.hasMoreElements()) {
					final String discoveredIpAddress = InetAdresses.nextElement().getHostAddress();
					final String discoveredHostName = InetAdresses.nextElement().getHostName();
					for (final String ipAddress : this.networkAddresses) {
						if (discoveredIpAddress.equals(ipAddress)) {
							return true;
						}
						if (discoveredHostName.equals(ipAddress)) {
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
