package org.nakeduml.oodb;

import com.orientechnologies.orient.core.db.object.ODatabaseObjectTx;

public class TestOODb {

	public static void main(String[] args) {
		ODatabaseObjectTx db = new ODatabaseObjectTx ("local:localhost/petshop").open("admin", "admin");
		db.create();
		db.getEntityManager().registerEntityClasses("foo.domain");

		// CREATE A NEW ACCOUNT OBJECT AND FILL IT
		Account account = new Account();
		account.setName( "Luke" );
		account.setSurname( "Skywalker" );

		db.save( account );
		db.close();
	}
}
