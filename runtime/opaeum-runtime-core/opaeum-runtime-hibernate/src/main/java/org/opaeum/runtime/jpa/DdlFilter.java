package org.opaeum.runtime.jpa;

public interface DdlFilter {
	boolean shouldIssueForeignKey(String foreignKey);
	boolean shouldIssueIndex(String index);

}
