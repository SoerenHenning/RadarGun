package radargun.util;

public final class Network {

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
