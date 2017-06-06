package radargun.experimental;

import java.util.Arrays;
import java.util.Map;

import org.openjdk.jmh.util.ClassUtils;

public class ClassnameDenserTest {

	public static void main(final String[] args) {
		final Map<String, String> denseClassNames = ClassUtils.denseClassNames(Arrays.asList(
				"de.soerenhenning.BenchmarkExample.DefaultState", "de.soerenhenning.BenchmarkExample.DefaultState2"));
		final Map<String, String> denseClassNames0 = ClassUtils.denseClassNames(Arrays.asList(
				"de.soerenhenning.benchmarkexample.DefaultState", "de.soerenhenning.benchmarkexample.DefaultState2"));
		final Map<String, String> denseClassNames2 = ClassUtils.denseClassNames(Arrays.asList(
				"de.soerenhenning.BenchmarkExample.DefaultState", "de.soerenhenning.BenchmarkExample34.DefaultState3"));
		final Map<String, String> denseClassNames3 = ClassUtils.denseClassNames(Arrays.asList(
				"de.soerenhenning.BenchmarkExample.DefaultState", "org.soerenhenning.BenchmarkExample.DefaultState4"));
		System.out.println(denseClassNames3);
	}

}
