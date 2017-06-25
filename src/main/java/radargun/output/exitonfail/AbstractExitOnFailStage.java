package radargun.output.exitonfail;

import radargun.comparsion.result.TestResult;
import teetime.framework.AbstractConsumerStage;

public abstract class AbstractExitOnFailStage extends AbstractConsumerStage<TestResult> {

	private static final int EXIT_CODE = 1;

	protected void exit() {
		System.exit(EXIT_CODE);
	}

}
