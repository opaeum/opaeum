package org.opaeum.name;

import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.stat.correlation.Covariance;
import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;

public class CorrelationTest{
	public static void main(String[] args){
		Covariance c =new Covariance();
		double[][] data= {{1,2},{2,4},{3,6}};
		PearsonsCorrelation pc=new PearsonsCorrelation(data);
		RealMatrix cm = pc.getCorrelationMatrix();
		RealMatrix cpv = pc.getCorrelationPValues();
		System.out.println(cm);
		System.out.println(cpv);
	}
}
