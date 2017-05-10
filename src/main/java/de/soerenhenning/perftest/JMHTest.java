package de.soerenhenning.perftest;

import java.util.Collection;

import org.openjdk.jmh.results.BenchmarkResult;
import org.openjdk.jmh.results.Result;
import org.openjdk.jmh.results.RunResult;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class JMHTest {

	public static void main(final String[] args) throws RunnerException {

		final Options opt = new OptionsBuilder().build();

		final Collection<RunResult> runResults = new Runner(opt).run();

		System.out.println("#############################################################");
		System.out.println("run() results:" + runResults);
		for (final RunResult runResult : runResults) {
			System.out.println("A run() result: " + runResult);
			System.out.println("  getAggregatedResult: " + runResult.getAggregatedResult());
			System.out.println("  getBenchmarkResults: " + runResult.getBenchmarkResults());
			for (final BenchmarkResult benchmarkResult : runResult.getBenchmarkResults()) {
				System.out.println("  A BenchmarkResult: " + benchmarkResult);
				System.out.println("  getScoreUnit: " + benchmarkResult.getScoreUnit());
				System.out.println("  getBenchmarkResults: " + benchmarkResult.getBenchmarkResults());
				System.out.println("  getIterationResults: " + benchmarkResult.getIterationResults());
				System.out.println("  getMetadata: " + benchmarkResult.getMetadata());
				System.out.println("  getParams: " + benchmarkResult.getParams());
				System.out.println("  getPrimaryResult: " + benchmarkResult.getPrimaryResult());
				System.out.println("  getSecondaryResults: " + benchmarkResult.getSecondaryResults());
			}
			System.out.println("  getPrimaryResult: " + runResult.getPrimaryResult());
			final Result primaryResult = runResult.getPrimaryResult();
			System.out.println("  getSecondaryResults: " + runResult.getSecondaryResults());
			System.out.println("  getParams: " + runResult.getParams());
			/*
			 * final Multimap<String, Result> results =
			 * runResult.getAggregatedResult().getBenchmarkResults(); for (final
			 * Map.Entry<String, Collection<Result>> entry : results.entrySet())
			 * { System.out.println(entry); }
			 */
		}
		System.out.println("#############################################################");
	}

}
