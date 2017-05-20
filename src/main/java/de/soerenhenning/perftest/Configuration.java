package de.soerenhenning.perftest;

import de.soerenhenning.perftest.test.result.TestResult;
import teetime.framework.OutputPort;

public class Configuration extends teetime.framework.Configuration {

	private final OutputPort<TestResult> outputPort = null; // TODO Temp

	public Configuration(final Options options) {

	}

	public OutputPort<TestResult> getOutputPort() {
		return this.outputPort;
	}

}
