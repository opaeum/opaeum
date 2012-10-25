package org.opaeum.demo1.datageneration.test;

import org.hsqldb.lib.FileUtil;
import org.junit.Test;

public class DataGenerationTest{
	@Test
	public void test()throws Exception{
		FileUtil.getFileUtil().removeElement("/tmp/demo1DB.log");
		FileUtil.getFileUtil().removeElement("/tmp/demo1DB.lck");
		FileUtil.getFileUtil().removeElement("/tmp/demo1DB.script");
		FileUtil.getFileUtil().removeElement("/tmp/demo1DB.properties");
		FileUtil.getFileUtil().removeElement("/tmp/demo1DB.tmp");
//		Ejb3Configuration conf = (new Ejb3Configuration()).configure("demo1");
//		Configuration hibernateConfiguration = conf.getHibernateConfiguration();
//		hibernateConfiguration.getProperties().put("hibernate.dialect" ,"org.hibernate.dialect.PostgreSQL82Dialect");
//		hibernateConfiguration.getProperties().put("hibernate.dialect" ,"org.hibernate.dialect.PostgreSQL82Dialect");
//		hibernateConfiguration.getProperties().put("hibernate.dialect" ,"org.hibernate.dialect.PostgreSQL82Dialect");
//		hibernateConfiguration.getProperties().put("hibernate.dialect" ,"org.hibernate.dialect.PostgreSQL82Dialect");
//		hibernateConfiguration.getProperties().put("hibernate.connection.driver_class" ,"org.postgresql.Driver" );
//		hibernateConfiguration.getProperties().put("hibernate.connection.url" ,"jdbc:postgresql://localhost:5433/test" );
//		hibernateConfiguration.getProperties().put("hibernate.connection.username" ,"postgres" );
//		hibernateConfiguration.getProperties().put("hibernate.connection.password","postgres" );
//		SchemaExport schemaExport = new SchemaExport(hibernateConfiguration);
//		schemaExport.create(true, true);

		Demo1DataGenerator.main(new String[0]);
		
//		DriverManager.getConnection("jdbc:hsqldb:file:/tmp/demo1DB").createStatement().execute("SHUTDOWN");
//		ConversationalPersistence persistence = StandaloneJpaEnvironment.getInstance().createConversationalPersistence();
//		List<IPersistentObject> result = (List<IPersistentObject>) persistence.createQuery("from BusinessNetwork").executeQuery();
//		Assert.assertEquals(1, result.size());
//		
//		BusinessNetwork bn= (BusinessNetwork) result.get(0);
//		Assert.assertEquals(1, bn.getStructuredbusiness().size());
//		PersonNode ampie = bn.getPerson("ampieb@gmail.com");
//		Assert.assertNotNull(ampie);
//		Assert.assertTrue(ampie.getBusinessRole().size()>0);
//		for(IBusinessRole po:ampie.getBusinessRole()){
//			System.out.println(po);
//			System.out.println(po.getOwningObject());
//		}
	}
}
