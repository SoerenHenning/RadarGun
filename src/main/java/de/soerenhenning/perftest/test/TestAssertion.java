package de.soerenhenning.perftest.test;

public final class TestAssertion {

	private final double lowerBound;
	private final double upperBound;

	public TestAssertion(final double lowerBound, final double upperBound) {
		this.lowerBound = lowerBound;
		this.upperBound = upperBound;
	}

	public double getLowerBound() {
		return this.lowerBound;
	}

	public double getUpperBound() {
		return this.upperBound;
	}

}
