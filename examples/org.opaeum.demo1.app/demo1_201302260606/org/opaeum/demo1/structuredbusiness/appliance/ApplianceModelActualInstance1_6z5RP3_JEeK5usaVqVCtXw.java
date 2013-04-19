package org.opaeum.demo1.structuredbusiness.appliance;

import java.util.Date;

import org.opaeum.runtime.bpm.businesscalendar.TimeOfDay;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.runtime.strategy.DateTimeStrategyFactory;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class ApplianceModelActualInstance1_6z5RP3_JEeK5usaVqVCtXw extends EntityInstanceSimulation<ApplianceModel> {
	static final public ApplianceModel APPLIANCEMODELACTUALINSTANCE1 = new ApplianceModel();
	static final public ApplianceModelActualInstance1_6z5RP3_JEeK5usaVqVCtXw INSTANCE = new ApplianceModelActualInstance1_6z5RP3_JEeK5usaVqVCtXw();


	public ApplianceModel createNewObject(CompositionNode parent) throws Exception {
		ApplianceModel result = APPLIANCEMODELACTUALINSTANCE1;
		result.init(parent);
		int componentCount = 0;
		while ( componentCount<3 ) {
			componentCount++;
			SimulationMetaData.getInstance().getEntityValueProvider("demo1_201302260606::BusinessNetworkActualInstance1::businessCollaboration::ApplianceCollaborationContainedActualInstance::ApplianceCollaborationActualInstance1::applianceDoctor::ApplianceDoctorContainedActualInstance::ApplianceDoctorActualInstance1::applianceModel::ApplianceModelContainedActualInstance::ApplianceModelActualInstance1","component").createNewInstance(result);
		}
		result.setName("name1");
		int partNumberlCount = 0;
		while ( partNumberlCount<1 ) {
			partNumberlCount++;
			result.setPartNumberl((TimeOfDay)SimulationMetaData.getInstance().getStructValueProvider("demo1_201302260606::BusinessNetworkActualInstance1::businessCollaboration::ApplianceCollaborationContainedActualInstance::ApplianceCollaborationActualInstance1::applianceDoctor::ApplianceDoctorContainedActualInstance::ApplianceDoctorActualInstance1::applianceModel::ApplianceModelContainedActualInstance::ApplianceModelActualInstance1","partNumberl").createNewInstance());
		}
		result.setVendor(Vendor.WHIRLPOOL);
		result.setApplianceType(ApplianceType.DISHWASHER);
		result.setNamaell((Date)new DateTimeStrategyFactory().getStrategy(org.opaeum.runtime.strategy.FromStringConverter.class).fromString("2012-3-27 1:24:30"));
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) throws Exception {
		ApplianceModel instance = (ApplianceModel)in;
	}

}