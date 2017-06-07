package radargun.output.exitonfail;

import radargun.comparsion.result.TestResult;

public class FinallyExitOnFailStage extends AbstractExitOnFailStage {

	private boolean hasFailed = false;

	@Override
	protected void execute(final TestResult result) throws Exception {
		if (result.hasFailed()) {
			this.hasFailed = true;
		}
	}

	@Override
	public void onTerminating() throws Exception {
		if (this.hasFailed) {
			super.exit();
		}
		super.onTerminating();
	}

}
