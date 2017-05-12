package de.soerenhenning.perftest;

import java.util.Collection;

import org.openjdk.jmh.results.RunResult;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import teetime.framework.AbstractProducerStage;

public class BenchmarkRunnerStage extends AbstractProducerStage<RunResult> {

	private final Runner runner;

	public BenchmarkRunnerStage() {
		final Options opt = new OptionsBuilder().build();
		this.runner = new Runner(opt);
	}

	public BenchmarkRunnerStage(final Runner runner) {
		this.runner = runner;
	}

	@Override
	protected void execute() throws RunnerException {
		// TODO exception
		final Collection<RunResult> runResults = this.runner.run();
		for (final RunResult runResult : runResults) {
			this.outputPort.send(runResult);
		}
		this.terminateStage();
	}

}
