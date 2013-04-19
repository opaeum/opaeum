package org.opaeum.demo1.structuredbusiness.branch;

import java.util.Date;

import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.runtime.domain.DocumentType;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class IdBookInstanceSimulation_60ef8H_JEeK5usaVqVCtXw extends EntityInstanceSimulation<IdBook> {
	static final public IdBookInstanceSimulation_60ef8H_JEeK5usaVqVCtXw INSTANCE = new IdBookInstanceSimulation_60ef8H_JEeK5usaVqVCtXw();


	public IdBook createNewObject(CompositionNode parent) throws Exception {
		IdBook result = new IdBook();
		int documentTypeCount = 0;
		while ( documentTypeCount<1 ) {
			documentTypeCount++;
			result.setDocumentType((DocumentType)SimulationMetaData.getInstance().getNextValueForProperty("demo1_201302260606::IdBookInstanceSimulation","documentType"));
		}
		int idNumberCount = 0;
		while ( idNumberCount<1 ) {
			idNumberCount++;
			result.setIdNumber((String)SimulationMetaData.getInstance().getNextValueForProperty("demo1_201302260606::IdBookInstanceSimulation","idNumber"));
		}
		int fullNamesCount = 0;
		while ( fullNamesCount<1 ) {
			fullNamesCount++;
			result.setFullNames((String)SimulationMetaData.getInstance().getNextValueForProperty("demo1_201302260606::IdBookInstanceSimulation","fullNames"));
		}
		int dateOfBirthCount = 0;
		while ( dateOfBirthCount<1 ) {
			dateOfBirthCount++;
			result.setDateOfBirth((Date)SimulationMetaData.getInstance().getNextValueForProperty("demo1_201302260606::IdBookInstanceSimulation","dateOfBirth"));
		}
		return result;
	}
	
	public void populateReferences(CompositionNode in) throws Exception {
		IdBook instance = (IdBook)in;
	}

}