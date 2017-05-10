package de.soerenhenning.perftest.test;

public final class TestAssertion {

	private final int lowerBound;
	private final int upperBound;

	public TestAssertion(final int lowerBound, final int upperBound) {
		this.lowerBound = lowerBound;
		this.upperBound = upperBound;
	}

	public int getLowerBound() {
		return this.lowerBound;
	}

	public int getUpperBound() {
		return this.upperBound;
	}

}
