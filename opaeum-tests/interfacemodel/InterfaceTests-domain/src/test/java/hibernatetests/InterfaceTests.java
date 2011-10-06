package hibernatetests;

import interfacetestmodel.interfacetests.JewelryCollection;
import interfacetestmodel.interfacetests.Ring;
import interfacetestmodel.interfacetests.RootClass;
import interfacetestmodel.util.HibernateConfigurator;

import javax.persistence.EntityManager;

import org.testng.annotations.Test;

public class InterfaceTests {
	@Test
	public void testCascadeAllOnManyToAny() {
		RootClass rootClass = new RootClass();
		JewelryCollection jc = new JewelryCollection(rootClass);
		Ring ring = new Ring();
		jc.addToJewelry(ring);
		EntityManager em = HibernateConfigurator.getInstance().getEntityManager();
		em.getTransaction().begin();
		em.persist(rootClass);
		assert ring.getId() != null : "Ring not persisted";
		em.getTransaction().commit();
		em.clear();
		em.getTransaction().begin();
		rootClass = em.find(RootClass.class, rootClass.getId());
		em.remove(rootClass);
		em.getTransaction().commit();
		em.clear();
		em.getTransaction().begin();
		assert em.find(Ring.class, ring.getId())==null:"Ring not deleted";
		em.getTransaction().commit();
		em.clear();
	}

}
