package org.opaeum.simulation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.math3.distribution.IntegerDistribution;
import org.apache.commons.math3.distribution.RealDistribution;
import org.opaeum.runtime.domain.CompositionNode;

public class SimulationMetaData{
	private final class RealProvider extends ValueProvider{
		private final RealDistribution rd;
		private RealProvider(RealDistribution rd){
			this.rd = rd;
		}
		@Override
		public Object getNextValue(){
			return rd.sample();
		}
	}
	private final class IntegerProvider extends ValueProvider{
		private IntegerDistribution id;
		private RealDistribution rd;
		private IntegerProvider(IntegerDistribution id){
			this.id = id;
		}
		private IntegerProvider(RealDistribution id){
			this.rd = id;
		}
		@Override
		public Object getNextValue(){
			if(id == null){
				return (int)Math.round(rd.sample());
			}else{
				return id.sample();
			}
		}
	}
	private Map<String,ValueProvider> propertySizeProviders = new HashMap<String,ValueProvider>();
	private Map<String,ValueProvider> propertyObjectValueProviders = new HashMap<String,ValueProvider>();
	private Map<String,List<Object>> objects = new HashMap<String,List<Object>>();
	private Map<String,EntityValueProvider> entitySimulationProviders = new HashMap<String,EntityValueProvider>();
	private Map<String,StructValueProvider> structSimulationProviders = new HashMap<String,StructValueProvider>();
	private static final SimulationMetaData INSTANCE = new SimulationMetaData();
	public EntityValueProvider getEntityValueProvider(String simulationName,String propertyName){
		return entitySimulationProviders.get(simulationName + propertyName);
	}
	public StructValueProvider getStructValueProvider(String simulationName,String propertyName){
		return structSimulationProviders.get(simulationName + propertyName);
	}
	@SuppressWarnings("unchecked")
	public void registerEntityInstanceSimulation(String simulationName,String propertyName,EntityInstanceSimulation id,double ratio){
		id.setValues((List<CompositionNode>) getAllInstances(id.getClass().getName()));// Ensure all instances of a given simulation share the same
																																					// collection
		EntityValueProvider daes = entitySimulationProviders.get(simulationName + propertyName);
		id.ratio = ratio;
		if(daes == null){
			daes = new EntityValueProvider();
			entitySimulationProviders.put(simulationName + propertyName, daes);
		}
		daes.buckets.add(id);
	}
	public void registerStructInstanceSimulation(String simulationName,String propertyName,StructInstanceSimulation id,double ratio){
		StructValueProvider dass = structSimulationProviders.get(simulationName + propertyName);
		if(dass == null){
			id.ratio = ratio;
			dass = new StructValueProvider();
			structSimulationProviders.put(simulationName + propertyName, dass);
		}
		dass.buckets.add(id);
	}
	public void registerPropertySizeGenerator(String simulationName,String propertyName,IntegerDistribution id){
		propertySizeProviders.put(simulationName + propertyName, new IntegerProvider(id));
	}
	public void registerPropertySizeGenerator(String simulationName,String propertyName,RealDistribution id){
		propertySizeProviders.put(simulationName + propertyName, new IntegerProvider(id));
	}
	public void registerPropertySizeGenerator(String simulationName,String propertyName,NumberRange range){
		NumberRangeDistribution value = (NumberRangeDistribution) propertySizeProviders.get(simulationName + propertyName);
		if(value == null){
			value = new NumberRangeDistribution(true);
			propertySizeProviders.put(simulationName + propertyName, value);
		}
		value.buckets.add(range);
	}
	public void populateReferences(){
		for(EntityValueProvider evp:this.entitySimulationProviders.values()){
			evp.populateReferences();
		}
		for(StructValueProvider svp:this.structSimulationProviders.values()){
			svp.populateReferences();
		}
	}
	public void registerIntegerValueDistribution(String simulationName,String propertyName,final IntegerDistribution id){
		registerValueProvider(simulationName, propertyName, new IntegerProvider(id));
	}
	public void registerRealValueDistribution(String simulationName,String propertyName,final RealDistribution rd){
		registerValueProvider(simulationName, propertyName, new RealProvider(rd));
	}
	public void registerPropertyValueDistributionRatio(String simulationName,String propertyName,Object en,double ratio){
		DistributionAcrossObjects value = (DistributionAcrossObjects) propertyObjectValueProviders.get(simulationName + propertyName);
		if(value == null){
			value = new DistributionAcrossObjects();
			registerValueProvider(simulationName, propertyName, value);
		}
		value.buckets.add(new ObjectBucket(en, ratio));
	}
	public void registerIntegerValueDistribution(String simulationName,String propertyName,NumberRange range){
		NumberRangeDistribution value = (NumberRangeDistribution) propertyObjectValueProviders.get(simulationName + propertyName);
		if(value == null){
			value = new NumberRangeDistribution(true);
			registerValueProvider(simulationName, propertyName, value);
		}
		value.buckets.add(range);
	}
	public void registerRealValueDistribution(String simulationName,String propertyName,NumberRange range){
		NumberRangeDistribution value = (NumberRangeDistribution) propertyObjectValueProviders.get(simulationName + propertyName);
		if(value == null){
			value = new NumberRangeDistribution(false);
			registerValueProvider(simulationName, propertyName, value);
		}
		value.buckets.add(range);
	}
	private void registerValueProvider(String simulationName,String propertyName,ValueProvider valueProvider){
		propertyObjectValueProviders.put(simulationName + propertyName, valueProvider);
	}
	public void calulcateManyPropertyDistributionRatios(String simulationName,String propertyName){
		// This will work for ManyToManies and unidirectional manies
		List<Object> collection = objects.get(simulationName);
		Object object = propertySizeProviders.get(simulationName + propertyName);
		if(object instanceof IntegerDistribution){
			IntegerDistribution id = (IntegerDistribution) object;
			int[] sample = id.sample(collection.size());
			for(int i = 0;i < sample.length;i++){
				registerPropertyValueDistributionRatio(simulationName, propertyName, collection.get(i), sample[i]);
			}
		}
	}
	public int getNextPropertySize(String simulationName,String propertyName){
		Number object = (Number) this.propertySizeProviders.get(simulationName + propertyName).getNextValue();
		return object.intValue();
	}
	public Object getNextValueForProperty(String simulationName,String propertyName){
		ValueProvider valueProvider = propertyObjectValueProviders.get(simulationName + propertyName);
		return valueProvider.getNextValue();
	}
	public Object createNewInstanceForProperty(String simulationName,String propertyName,CompositionNode parent){
		return entitySimulationProviders.get(simulationName + propertyName).createNewInstance(parent);
	}
	@SuppressWarnings("rawtypes")
	protected List getAllInstances(String simulationName){
		List<Object> collection = objects.get(simulationName);
		if(collection == null){
			collection = new ArrayList<Object>();
			objects.put(simulationName, collection);
		}
		return collection;
	}
	public static SimulationMetaData getInstance(){
		return INSTANCE;
	}
}
