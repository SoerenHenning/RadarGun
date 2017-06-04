package radargun;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import radargun.test.result.TestResult;

public class CSVExport {

	private final Path directory;

	public CSVExport(final Path directory) {
		this.directory = directory;
	}

	public Path export(final TestResult result) throws IOException {
		final String testName = result.getRunResult().getPrimaryResult().getLabel();
		final double score = result.getRunResult().getPrimaryResult().getScore();
		final double lowerBound = result.getAssertion().getLowerBound();
		final double upperBound = result.getAssertion().getUpperBound();
		final Path csvFile = this.getOrCreateCSVFile(testName + ".csv");
		return this.appendResult(csvFile, score, lowerBound, upperBound);
	}

	private Path getOrCreateCSVFile(final String name) throws IOException {
		final Path csvFile = this.directory.resolve(name);
		if (Files.isRegularFile(csvFile)) {
			return csvFile;
		} else {
			return this.writeHeader(csvFile);
		}
	}

	private Path appendResult(final Path path, final double score, final double lowerBound, final double upperBound)
			throws IOException {
		return Files.write(path,
				('\n' + String.valueOf(score) + ',' + String.valueOf(lowerBound) + ',' + String.valueOf(upperBound))
						.getBytes(),
				StandardOpenOption.APPEND);
	}

	private Path writeHeader(final Path path) throws IOException {
		return Files.write(path, ("score,lowerBound,upperBound").getBytes(), StandardOpenOption.CREATE);
	}

}
