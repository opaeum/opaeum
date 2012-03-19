package org.opaeum.simulation;

import java.sql.Ref;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.math3.distribution.IntegerDistribution;
import org.apache.commons.math3.distribution.RealDistribution;

public class SimulationMetaData{
	private static class ObjectBucket implements Comparable<ObjectBucket>{
		Object value;
		int count;
		double ratio;
		public ObjectBucket(Object en,double ratio2){
			value = en;
			ratio = ratio2;
		}
		@Override
		public int compareTo(ObjectBucket o){
			if(getCountWeight() < o.getCountWeight()){
				return 1;
			}else{
				return -1;
			}
		}
		private double getCountWeight(){
			return count / ratio;
		}
		public Object nextValue(){
			count++;
			return value;
		}
	}
	private static class DistributionAcrossObjects{
		List<ObjectBucket> buckets = new ArrayList<SimulationMetaData.ObjectBucket>();
		public Object getNextValue(){
			Collections.sort(buckets);
			return buckets.get(0).nextValue();
		}
	}
	private Map<String,Object> propertySizeDistributions = new HashMap<String,Object>();
	private Map<String,RealDistribution> propertyRealValueDistributions = new HashMap<String,RealDistribution>();
	private Map<String,IntegerDistribution> propertyIntegerValueDistributions = new HashMap<String,IntegerDistribution>();
	private Map<String,DistributionAcrossObjects> propertyObjectValueDistributions = new HashMap<String,DistributionAcrossObjects>();
	private Map<Class<?>,List<Object>> objects = new HashMap<Class<?>,List<Object>>();
	private static final SimulationMetaData INSTANCE = new SimulationMetaData();
	public void registerPropertySizeGenerator(Class<?> c,String propertyName,IntegerDistribution id){
		propertySizeDistributions.put(c.getName() + propertyName, id);
	}
	public void registerPropertySizeGenerator(Class<?> c,String propertyName,RealDistribution id){
		propertySizeDistributions.put(c.getName() + propertyName, id);
	}
	public void registerPropertyValueGenerator(Class<?> c,String propertyName,RealDistribution id){
		propertyRealValueDistributions.put(c.getName() + propertyName, id);
	}
	public void registerPropertyValueGenerator(Class<?> c,String propertyName,IntegerDistribution id){
		propertyIntegerValueDistributions.put(c.getName() + propertyName, id);
	}
	public void registerPropertyDistributionRatio(Class<?> c,String propertyName,Class<?> type){
		List<Object> collection = objects.get(type);
		Object object = propertySizeDistributions.get(c.getName() + propertyName);
		if(object instanceof IntegerDistribution){
			IntegerDistribution id = (IntegerDistribution) object;
			int[] sample = id.sample(collection.size());
			for(int i = 0;i < sample.length;i++){
				registerPropertyDistributionRatio(c, propertyName, collection.get(i), sample[i]);
			}
		}
	}
	public void registerPropertyDistributionRatio(Class<?> c,String propertyName,Object en,double ratio){
		DistributionAcrossObjects value = propertyObjectValueDistributions.get(c.getName() + propertyName);
		if(value == null){
			value = new DistributionAcrossObjects();
			propertyObjectValueDistributions.put(c.getName() + propertyName, value);
		}
		value.buckets.add(new ObjectBucket(en, ratio));
	}
	public Object newInstance(Class<?> c){
		List<Object> collection = getAllInstances(c);
		Object result;
		try{
			result = c.newInstance();
		}catch(InstantiationException e){
			throw new RuntimeException(e);
		}catch(IllegalAccessException e){
			throw new RuntimeException(e);
		}
		collection.add(result);
		return result;
	}
	protected List<Object> getAllInstances(Class<?> c){
		List<Object> collection = objects.get(c);
		if(collection == null){
			collection = new ArrayList<Object>();
			objects.put(c, collection);
		}
		return collection;
	}
	public static SimulationMetaData getInstance(){
		return INSTANCE;
	}
	public int getInstanceCount(Class<?> c){
		return getAllInstances(c).size();
	}
	public int getNextPropertySize(Class<?> c,String propertyName){
		return this.propertyIntegerValueDistributions.get(c.getName()+propertyName).sample();
	}
	public double getNextRealValueForProperty(Class<?> c,String propertyName){
		return this.propertyRealValueDistributions.get(c.getName()+propertyName).sample();
	}
	public int getNextIntegerValueForProperty(Class<?> c,String propertyName){
		return 1;
	}
	public Object getNextObjectValueForProperty(Class<?> c,String propertyName){
		return propertyObjectValueDistributions.get(c.getName() + propertyName).getNextValue();
	}
}
