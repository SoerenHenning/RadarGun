package radargun.comparsion;

public final class Assertion {

	private final double lowerBound;
	private final double upperBound;

	public Assertion(final double lowerBound, final double upperBound) {
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
