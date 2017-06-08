package radargun.output.csv;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import radargun.comparsion.result.TestResult;

public class CSVExport {

	private final Path directory;

	public CSVExport(final Path directory) {
		this.directory = directory;
	}

	public Path export(final TestResult result) throws IOException {
		final String testName = result.getRunResult().getParams().getBenchmark();
		final double score = result.getRunResult().getPrimaryResult().getScore();
		final double lowerBound = result.getAssertion().getLowerBound();
		final double upperBound = result.getAssertion().getUpperBound();
		final Path csvFile = this.getOrCreateCSVFile(testName + ".csv");
		return this.appendResult(csvFile, score, lowerBound, upperBound);
	}

	private Path getOrCreateCSVFile(final String name) throws IOException {
		final Path file = this.directory.resolve(name);
		if (Files.isRegularFile(file)) {
			return file;
		} else {
			return this.createCSVFile(file);
		}
	}

	private Path appendResult(final Path file, final double score, final double lowerBound, final double upperBound)
			throws IOException {
		return Files.write(file,
				('\n' + String.valueOf(score) + ',' + String.valueOf(lowerBound) + ',' + String.valueOf(upperBound))
						.getBytes(),
				StandardOpenOption.APPEND);
	}

	private Path createCSVFile(final Path file) throws IOException {
		Files.createDirectories(file.getParent());
		return Files.write(file, ("score,lowerBound,upperBound").getBytes(), StandardOpenOption.CREATE);
	}

}
