package de.soerenhenning.perftest.machine.identification;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class IPAddressIdentifier implements MachineIdentifier {

	private final String[] ipAddresses;

	public IPAddressIdentifier(final String... ipAddresses) {
		this.ipAddresses = ipAddresses;
	}

	@Override
	public boolean testCurrentMachine() {
		try {
			final Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
			while (networkInterfaces.hasMoreElements()) {
				final NetworkInterface n = networkInterfaces.nextElement();
				final Enumeration<InetAddress> InetAdresses = n.getInetAddresses();
				while (InetAdresses.hasMoreElements()) {
					final InetAddress inetAddress = InetAdresses.nextElement();
					for (final String ipAddress : this.ipAddresses) {
						if (inetAddress.equals(ipAddress)) {
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
