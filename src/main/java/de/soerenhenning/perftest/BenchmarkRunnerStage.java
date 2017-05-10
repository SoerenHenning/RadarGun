package de.soerenhenning.perftest;

import java.util.Collection;

import org.openjdk.jmh.results.RunResult;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import teetime.framework.AbstractProducerStage;

public class BenchmarkRunnerStage extends AbstractProducerStage<RunResult> {

	@Override
	protected void execute() throws RunnerException {
		// TODO exception
		final Options opt = new OptionsBuilder().build();
		final Collection<RunResult> runResults = new Runner(opt).run();
		for (final RunResult runResult : runResults) {
			this.outputPort.send(runResult);
		}
		this.terminateStage();
	}

}
