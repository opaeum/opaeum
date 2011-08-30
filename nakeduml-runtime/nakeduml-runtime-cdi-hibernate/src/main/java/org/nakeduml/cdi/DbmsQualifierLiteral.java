package org.nakeduml.cdi;

import javax.enterprise.util.AnnotationLiteral;

import org.nakeduml.runtime.persistence.DatabaseManagementSystem;

public class DbmsQualifierLiteral extends AnnotationLiteral<DbmsQualifier> implements DbmsQualifier{
	DatabaseManagementSystem dbms;
	public DbmsQualifierLiteral(DatabaseManagementSystem dbms){
		this.dbms = dbms;
	}
	@Override
	public DatabaseManagementSystem value(){
		return dbms;
	}
}
