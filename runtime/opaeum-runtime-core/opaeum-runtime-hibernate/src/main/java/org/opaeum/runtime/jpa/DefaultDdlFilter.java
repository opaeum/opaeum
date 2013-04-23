package org.opaeum.runtime.jpa;

public class DefaultDdlFilter implements DdlFilter{
	@Override
	public boolean shouldIssueForeignKey(String foreignKey){
		return true;
	}
	@Override
	public boolean shouldIssueIndex(String index){
		return true;
	}
}
