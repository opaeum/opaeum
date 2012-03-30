package org.opaeum.demo1.datageneration.test;

import java.util.List;

import junit.framework.Assert;

import org.hsqldb.lib.FileUtil;
import org.junit.Test;
import org.opaeum.runtime.bpm.organization.BusinessNetwork;
import org.opaeum.runtime.bpm.organization.IBusinessRole;
import org.opaeum.runtime.bpm.organization.PersonNode;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.jpa.StandaloneJpaEnvironment;
import org.opaeum.runtime.persistence.ConversationalPersistence;
import org.opeum.demo1.util.Demo1DataGenerator;

public class DataGenerationTest{
	@Test
	public void test(){
//		FileUtil.getFileUtil().removeElement("/tmp/demo1DB.log");
//		FileUtil.getFileUtil().removeElement("/tmp/demo1DB.lck");
//		FileUtil.getFileUtil().removeElement("/tmp/demo1DB.script");
//		FileUtil.getFileUtil().removeElement("/tmp/demo1DB.properties");
//		FileUtil.getFileUtil().removeElement("/tmp/demo1DB.tmp");
//		Demo1DataGenerator.main(new String[0]);
		ConversationalPersistence persistence = StandaloneJpaEnvironment.getInstance().createConversationalPersistence();
		List<IPersistentObject> result = (List<IPersistentObject>) persistence.createQuery("from BusinessNetwork").executeQuery();
		Assert.assertEquals(1, result.size());
		
		BusinessNetwork bn= (BusinessNetwork) result.get(0);
		Assert.assertEquals(1, bn.getStructuredbusiness().size());
		PersonNode ampie = bn.getPerson("ampieb@gmail.com");
		Assert.assertNotNull(ampie);
		Assert.assertTrue(ampie.getBusinessRole().size()>0);
		for(IBusinessRole po:ampie.getBusinessRole()){
			System.out.println(po);
			System.out.println(po.getOwningObject());
		}
	}
}
