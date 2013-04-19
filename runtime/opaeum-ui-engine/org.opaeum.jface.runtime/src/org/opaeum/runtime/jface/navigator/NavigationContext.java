package org.opaeum.runtime.jface.navigator;

import java.util.HashMap;
import java.util.Map;

import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.runtime.rwt.OpaeumRapSession;
import org.opaeum.uim.perspective.ClassNavigationConstraint;
import org.opaeum.uim.perspective.NavigatorConfiguration;

public class NavigationContext{
	private Map<IPersistentObject,PersistentObjectTreeItem> objectItemMap=new HashMap<IPersistentObject,PersistentObjectTreeItem>();
	private OpaeumRapSession opaeumRapSession;
	private Map<String,ClassNavigationConstraint> classConfigurations;
	
	public NavigationContext(OpaeumRapSession opaeumRapSession){
		super();
		this.opaeumRapSession = opaeumRapSession;
	}
	public ClassNavigationConstraint getClassConfiguration(String id){
		if(classConfigurations==null){
			classConfigurations=new HashMap<String,ClassNavigationConstraint>();
			for(ClassNavigationConstraint cc:getOpaeumRapSession().getApplication().getPerspectiveConfiguration().getExplorer().getClasses()){
				classConfigurations.put(cc.getUmlElementUid(), cc);				
			}
		}
		return classConfigurations.get(id);
	}
	public OpaeumRapSession getOpaeumRapSession(){
		return opaeumRapSession;
	}
	public Map<IPersistentObject,PersistentObjectTreeItem> getObjectItemMap(){
		return objectItemMap;
	}
	public void setObjectItemMap(Map<IPersistentObject,PersistentObjectTreeItem> objectItemMap){
		this.objectItemMap = objectItemMap;
	}
	public NavigatorConfiguration getNavigatorConfiguration(){
		return getOpaeumRapSession().getApplication().getPerspectiveConfiguration().getExplorer();
	}
	public Environment getEnvironment(){
		return opaeumRapSession.getApplication().getEnvironment();
	}
}
