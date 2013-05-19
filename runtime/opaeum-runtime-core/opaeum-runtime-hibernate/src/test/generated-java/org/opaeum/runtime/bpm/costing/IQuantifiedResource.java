package org.opaeum.runtime.bpm.costing;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.annotation.ParameterMetaInfo;
import org.opaeum.annotation.PropertyMetaInfo;
import org.opaeum.runtime.costing.IPricePerUnit;
import org.opaeum.runtime.costing.IQuantifiedResourceBase;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.runtime.domain.HibernateEntity;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.strategy.DateTimeStrategyFactory;
import org.w3c.dom.Element;

@NumlMetaInfo(applicationIdentifier="opaeum_hibernate_tests",uuid="252060@_mKcP8Pl5EeGYn9aDKdPmKA")
public interface IQuantifiedResource extends IQuantifiedResourceBase, HibernateEntity, CompositionNode, Serializable, IPersistentObject {
	public void addToPricePerUnit(PricePerUnit pricePerUnit);
	
	public void buildTreeFromXml(Element xml, Map<String, Object> map);
	
	public void clearPricePerUnit();
	
	public PricePerUnit createPricePerUnit();
	
	@NumlMetaInfo(uuid="252060@_4At4gPl6EeGYn9aDKdPmKA")
	public IPricePerUnit getPriceEffectiveOn(@ParameterMetaInfo(name="date",opaeumId=219601221862408466l,strategyFactory=DateTimeStrategyFactory.class,uuid="252060@_6XlpQPl6EeGYn9aDKdPmKA") Date date);
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=21174465550210726l,opposite="quantifiedResource",uuid="252060@_gDOkpPl6EeGYn9aDKdPmKA")
	public List<PricePerUnit> getPricePerUnit();
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=8621165129034210297l,opposite="quantifiedResource",uuid="252060@_gDOkoPl6EeGYn9aDKdPmKA")
	@NumlMetaInfo(uuid="252060@_mKcP8Pl5EeGYn9aDKdPmKA@252060@_gDOkoPl6EeGYn9aDKdPmKA")
	public List<QuantifiedResourcePricePerUnit> getQuantifiedResourcePricePerUnit_pricePerUnit();
	
	public QuantifiedResourcePricePerUnit getQuantifiedResourcePricePerUnit_pricePerUnitFor(PricePerUnit match);
	
	public String getUid();
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map);
	
	public void removeFromPricePerUnit(PricePerUnit pricePerUnit);
	
	public void setPricePerUnit(List<PricePerUnit> pricePerUnit);
	
	public String toXmlReferenceString();
	
	public String toXmlString();
	
	public void z_internalAddToQuantifiedResourcePricePerUnit_pricePerUnit(QuantifiedResourcePricePerUnit quantifiedResourcePricePerUnit_pricePerUnit);
	
	public void z_internalRemoveFromQuantifiedResourcePricePerUnit_pricePerUnit(QuantifiedResourcePricePerUnit quantifiedResourcePricePerUnit_pricePerUnit);

}