package radargun;

import java.util.Map;

import radargun.machine.identification.MachineIdentifier;
import radargun.test.TestAssertion;

//TODO Name
public interface Test {

	public MachineIdentifier getMachineIdentifier();

	public Map<String, TestAssertion> getTests();

	public static Test of(final MachineIdentifier identifier, final Map<String, TestAssertion> tests) {
		return new TestImpl(identifier, tests);
	}
}
