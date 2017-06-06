package radargun.experimental;

import radargun.Options;

public class JCommanderTest {

	public static void main(final String[] args) {
		final Options options1 = Options.create();
		// final Options options2 = Options.create("--cpassertions");
		final Options options3 = Options.create("--cpassertions", "foo,bar");
		final Options options4 = Options.create();
		final Options options5 = Options.create();
		options3.getClasspathLocations().toString();
	}

}
