package org.opaeum.runtime.bpm.costing;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.annotation.ParameterMetaInfo;
import org.opaeum.annotation.PropertyMetaInfo;
import org.opaeum.runtime.bpm.util.OpaeumLibraryForBPMFormatter;
import org.opaeum.runtime.costing.IRatePerTimeUnit;
import org.opaeum.runtime.costing.ITimedResourceBase;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.runtime.domain.HibernateEntity;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.strategy.DateTimeStrategyFactory;
import org.w3c.dom.Element;

@NumlMetaInfo(applicationIdentifier="demo1",uuid="252060@_aCyDYPjxEeGEN6Fz86uBYA")
public interface ITimedResource extends ITimedResourceBase, HibernateEntity, CompositionNode, Serializable, IPersistentObject {
	public void addToRatePerTimeUnit(RatePerTimeUnit ratePerTimeUnit);
	
	public void addToTimedResourceRatePerTimeUnit_ratePerTimeUnit(TimedResourceRatePerTimeUnit timedResourceRatePerTimeUnit_ratePerTimeUnit);
	
	public void buildTreeFromXml(Element xml, Map<String, Object> map);
	
	public void clearRatePerTimeUnit();
	
	public void clearTimedResourceRatePerTimeUnit_ratePerTimeUnit();
	
	public RatePerTimeUnit createRatePerTimeUnit();
	
	@NumlMetaInfo(uuid="252060@_V3n1EPjyEeGEN6Fz86uBYA")
	public IRatePerTimeUnit getRateEffectiveOn(@ParameterMetaInfo(name="date",opaeumId=818066613415140745l,strategyFactory=DateTimeStrategyFactory.class,uuid="252060@_nQHK4PjyEeGEN6Fz86uBYA") Date date);
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=534666560944430555l,opposite="timedResource",uuid="252060@_dIQKFPjyEeGEN6Fz86uBYA")
	@NumlMetaInfo(uuid="252060@_dIQKFPjyEeGEN6Fz86uBYA")
	public List<? extends RatePerTimeUnit> getRatePerTimeUnit();
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=726238315624324931l,opposite="timedResource",uuid="252060@_dIQKEPjyEeGEN6Fz86uBYA")
	@NumlMetaInfo(uuid="252060@_aCyDYPjxEeGEN6Fz86uBYA@252060@_dIQKEPjyEeGEN6Fz86uBYA")
	public List<TimedResourceRatePerTimeUnit> getTimedResourceRatePerTimeUnit_ratePerTimeUnit();
	
	public String getUid();
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map);
	
	public void removeFromRatePerTimeUnit(RatePerTimeUnit ratePerTimeUnit);
	
	public void removeFromTimedResourceRatePerTimeUnit_ratePerTimeUnit(TimedResourceRatePerTimeUnit timedResourceRatePerTimeUnit_ratePerTimeUnit);
	
	public void setRatePerTimeUnit(List<RatePerTimeUnit> ratePerTimeUnit);
	
	public void setTimedResourceRatePerTimeUnit_ratePerTimeUnit(List<TimedResourceRatePerTimeUnit> timedResourceRatePerTimeUnit_ratePerTimeUnit);
	
	public String toXmlReferenceString();
	
	public String toXmlString();
	
	public void z_internalAddToRatePerTimeUnit(RatePerTimeUnit ratePerTimeUnit);
	
	public void z_internalAddToTimedResourceRatePerTimeUnit_ratePerTimeUnit(TimedResourceRatePerTimeUnit timedResourceRatePerTimeUnit_ratePerTimeUnit);
	
	public void z_internalRemoveFromRatePerTimeUnit(RatePerTimeUnit ratePerTimeUnit);
	
	public void z_internalRemoveFromTimedResourceRatePerTimeUnit_ratePerTimeUnit(TimedResourceRatePerTimeUnit timedResourceRatePerTimeUnit_ratePerTimeUnit);

}