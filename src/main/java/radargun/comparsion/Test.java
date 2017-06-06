package radargun.comparsion;

import java.util.Map;

import radargun.comparsion.machine.identification.MachineIdentifier;

//TODO Name
public interface Test {

	public MachineIdentifier getMachineIdentifier();

	public Map<String, Assertion> getTests();

	public static Test of(final MachineIdentifier identifier, final Map<String, Assertion> tests) {
		return new TestImpl(identifier, tests);
	}
}
