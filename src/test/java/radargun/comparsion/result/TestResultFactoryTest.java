package radargun.comparsion.result;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openjdk.jmh.results.BenchmarkResult;
import org.openjdk.jmh.results.IterationResult;
import org.openjdk.jmh.results.ResultRole;
import org.openjdk.jmh.results.RunResult;
import org.openjdk.jmh.results.SingleShotResult;

import radargun.comparsion.Assertion;

public class TestResultFactoryTest {

	private TestResultFactory testResultFactory;

	@Before
	public void setUp() throws Exception {
		this.testResultFactory = new TestResultFactory();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCreateTestWithoutAssertionResult() {
		final RunResult runResult = this.createMockedRunResult(10.0);
		final Assertion assertion = Assertion.DUMMY;
		final TestResult testResult = this.testResultFactory.create(runResult, assertion);
		assertTrue(testResult instanceof TestWithoutAssertionResult);
	}

	@Test
	public void testTestUndercutsBoundsResult() {
		final RunResult runResult = this.createMockedRunResult(8.0);
		final Assertion assertion = new Assertion(9.0, 11.0);
		final TestResult testResult = this.testResultFactory.create(runResult, assertion);
		assertTrue(testResult instanceof TestUndercutsBoundsResult);
	}

	@Test
	public void testTestExceedsBoundsResult() {
		final RunResult runResult = this.createMockedRunResult(12.0);
		final Assertion assertion = new Assertion(9.0, 11.0);
		final TestResult testResult = this.testResultFactory.create(runResult, assertion);
		assertTrue(testResult instanceof TestExceedsBoundsResult);
	}

	@Test
	public void testCreateTestInBoundsResult() {
		final RunResult runResult = this.createMockedRunResult(10.0);
		final Assertion assertion = new Assertion(9.0, 11.0);
		final TestResult testResult = this.testResultFactory.create(runResult, assertion);
		assertTrue(testResult instanceof TestInBoundsResult);
	}

	private RunResult createMockedRunResult(final double value) {
		final IterationResult iterationResult = new IterationResult(null, null, null);
		iterationResult.addResult(new SingleShotResult(ResultRole.PRIMARY, "", (long) value, TimeUnit.NANOSECONDS));
		final Collection<IterationResult> iterationResults = Arrays.asList(iterationResult);
		final BenchmarkResult benchmarkResult = new BenchmarkResult(null, iterationResults);
		final Collection<BenchmarkResult> benchmarkResults = Arrays.asList(benchmarkResult);
		return new RunResult(null, benchmarkResults);
	}

}
