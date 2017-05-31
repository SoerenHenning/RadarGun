package radargun;

import radargun.test.result.TestResult;
import teetime.framework.AbstractConsumerStage;

public class ExitOnFailStage extends AbstractConsumerStage<TestResult> {

	private static final int EXIT_CODE = 1;

	@Override
	protected void execute(final TestResult result) throws Exception {
		if (result.hasFailed()) {
			System.exit(EXIT_CODE);
		}
	}

}
