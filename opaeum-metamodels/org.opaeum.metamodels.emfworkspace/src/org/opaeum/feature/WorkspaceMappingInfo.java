package org.opaeum.feature;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import org.opaeum.runtime.environment.VersionNumber;
import org.opaeum.util.SortedProperties;


public class WorkspaceMappingInfo {
	private Properties properties;
	private Map<String,MappingInfo> mappingInfoMap = new HashMap<String,MappingInfo>();
	private int nakedUmlIdMaxValue;
	Random random = new Random();
	private VersionNumber versionNumber;
	private WorkspaceMappingInfo(){
		this.properties = new SortedProperties();
	}
	public WorkspaceMappingInfo(File file){
		this();
		if(file.exists()){
			try{
				load(new FileReader(file));
			}catch(FileNotFoundException e){
				throw new RuntimeException(e);
			}
		}
	}
	public WorkspaceMappingInfo(Reader reader){
		this();
		if(reader != null){
			load(reader);
		}
	}
	private void load(Reader reader){
		try{
			if(reader != null){
				final String NAKED_UML_MAX_VALUE = getClass().getName() + ".nakedUmlIdMaxValue";
				Set<String> knownProperties = new HashSet<String>();
				knownProperties.add(NAKED_UML_MAX_VALUE);
				properties.load(reader);
				nakedUmlIdMaxValue = Integer.parseInt(properties.getProperty(NAKED_UML_MAX_VALUE));
				Set<Entry<Object,Object>> entrySet = properties.entrySet();
				for(Entry<Object,Object> entry:entrySet){
					String id = (String) entry.getKey();
					if(!knownProperties.contains(id)){
						mappingInfoMap.put(id, new MappingInfo(id, (String) entry.getValue()));
					}
				}
			}
		}catch(RuntimeException r){
			throw r;
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	public int getOpaeumIdMaxValue(){
		return this.nakedUmlIdMaxValue;
	}
	public void removeMappingInfo(String id){
		properties.remove(id);
	}
	public void setVersion(VersionNumber a){
		this.versionNumber=a;
	}
	public VersionNumber getVersion(){
		return this.versionNumber;
	}
}
