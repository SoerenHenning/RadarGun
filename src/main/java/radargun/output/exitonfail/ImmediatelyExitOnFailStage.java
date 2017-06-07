package radargun.output.exitonfail;

import radargun.comparsion.result.TestResult;

public class ImmediatelyExitOnFailStage extends AbstractExitOnFailStage {

	@Override
	protected void execute(final TestResult result) throws Exception {
		if (result.hasFailed()) {
			super.exit();
		}
	}

}
