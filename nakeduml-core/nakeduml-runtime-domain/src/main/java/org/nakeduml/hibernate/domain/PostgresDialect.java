package org.nakeduml.hibernate.domain;

import java.sql.Types;

import org.hibernate.dialect.PostgresPlusDialect;

public class PostgresDialect extends PostgresPlusDialect{
	public PostgresDialect(){
		super();
		registerColumnType( Types.BLOB, "bytea" );

	}
}
