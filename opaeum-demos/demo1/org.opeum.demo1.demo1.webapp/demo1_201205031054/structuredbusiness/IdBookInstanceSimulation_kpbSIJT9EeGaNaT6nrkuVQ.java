package structuredbusiness;

import java.text.ParseException;

import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.runtime.domain.DocumentType;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class IdBookInstanceSimulation_kpbSIJT9EeGaNaT6nrkuVQ extends EntityInstanceSimulation {



	public IdBook createNewObject(CompositionNode parent) throws ParseException {
		IdBook result = new IdBook();
		int documentTypeCount = 0;
		while ( documentTypeCount<1 ) {
			documentTypeCount++;
			result.setDocumentType((DocumentType)SimulationMetaData.getInstance().getNextValueForProperty("demo1_201205031054::IdBookInstanceSimulation","documentType"));
		}
		int idNumberCount = 0;
		while ( idNumberCount<1 ) {
			idNumberCount++;
			result.setIdNumber((String)SimulationMetaData.getInstance().getNextValueForProperty("demo1_201205031054::IdBookInstanceSimulation","idNumber"));
		}
		int fullNamesCount = 0;
		while ( fullNamesCount<1 ) {
			fullNamesCount++;
			result.setFullNames((String)SimulationMetaData.getInstance().getNextValueForProperty("demo1_201205031054::IdBookInstanceSimulation","fullNames"));
		}
		return result;
	}
	
	public void populateReferences(CompositionNode in) throws ParseException {
		IdBook instance = (IdBook)in;
	}

}