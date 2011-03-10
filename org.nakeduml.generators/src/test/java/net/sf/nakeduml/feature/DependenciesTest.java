package net.sf.nakeduml.feature;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.testng.annotations.Test;

@Test(groups = "quick")
public class DependenciesTest {
	public static class SomeStep implements TransformationStep{}
	public static class AbstractPhase implements TransformationPhase<SomeStep> {
		public Object[] execute(List features, TransformationContext context) {
			return null;
		}

		public void initialize(NakedUmlConfig config) {
		}
	}

	@PhaseDependency()
	public static class FirstPhase extends AbstractPhase {
	}

	@PhaseDependency(before = Phase2.class, after = FirstPhase.class)
	public static class Phase1 extends AbstractPhase {
	}

	@PhaseDependency(before = Phase3.class, after = Phase1.class)
	public static class Phase2 extends AbstractPhase {
	}

	@PhaseDependency()
	public static class LastPhase extends AbstractPhase {
	}

	@PhaseDependency(before = LastPhase.class, after = FirstPhase.class)
	// After Phase 2 due to the after attribute on Phase 2
	public static class Phase3 extends AbstractPhase {
	}

	@PhaseDependency(before = Phase3.class, after = Phase1.class)
	public static class Phase5 extends AbstractPhase {
	}

	@PhaseDependency(before = Phase5.class, after = Phase3.class)
	public static class Phase6 extends AbstractPhase {
	}

	@SuppressWarnings("unchecked")
	public void testPhases() throws Exception {
		Phases p = new Phases();
		p.initializeFromClasses(asStrinSet( Phase1.class , Phase2.class , Phase3.class, Phase5.class));
		assert 4 == p.getExecutionUnits().size();
		assert Phase1.class == p.getExecutionUnits().get(0).getClass();
		assert Phase2.class == p.getExecutionUnits().get(1).getClass() || Phase5.class == p.getExecutionUnits().get(1).getClass();
		assert Phase3.class == p.getExecutionUnits().get(3).getClass();
	}

	private Set<Class<? extends TransformationPhase<? extends TransformationStep>>> asStrinSet(Class<? extends TransformationPhase<? extends TransformationStep>>... theClass) {
		Set<Class<? extends TransformationPhase<? extends TransformationStep>>> result = new HashSet<Class<? extends TransformationPhase<? extends TransformationStep>>>();
		for (Class<? extends TransformationPhase<? extends TransformationStep>> class1 : theClass) {
			result.add(class1);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public void testCircularity() throws Exception {
		try {
			Phases p = new Phases();
			p.initializeFromClasses(asStrinSet(Phase3.class, Phase5.class, Phase6.class));
			assert false;
		} catch (CircularPrecessionException e) {
			assert true;
		}
	}
}
