package radargun.util;

public final class Network {

	/**
	 * Transforms a MAC addresses encoded as String with ":" as Separator into a
	 * byte array.
	 *
	 * @param macAddress
	 *            MAC address in String format, separated by ":"
	 * @return MAC address as byte array
	 */
	public static final byte[] parseMacAdress(final String macAddress) {
		final String[] macAddressParts = macAddress.split(":");
		final byte[] macAddressBytes = new byte[macAddressParts.length];
		for (int i = 0; i < macAddressParts.length; i++) {
			final Integer hex = Integer.parseInt(macAddressParts[i], 16);
			macAddressBytes[i] = hex.byteValue();
		}
		return macAddressBytes;
	}

}
