package net.sf.nakeduml.javageneration.persistence;

import javax.persistence.GenerationType;

public class JpaIdStrategyFactory {

	public static JpaIdStrategy getStrategy(GenerationType strategy) {
		switch (strategy) {
		case AUTO:
			return new JpaIdAuto();
		case TABLE:
			return new JpaIdTable();
		default:
			return null;
		}
	}
	
}
