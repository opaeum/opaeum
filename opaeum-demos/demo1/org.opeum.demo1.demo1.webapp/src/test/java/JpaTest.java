import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import junit.framework.TestCase;

import org.opaeum.annotation.BusinessActor;
import org.opaeum.runtime.bpm.organization.BusinessNetwork;
import org.opaeum.runtime.bpm.organization.IBusinessActor;
import org.opaeum.runtime.jpa.StandaloneJpaEnvironment;
import org.opaeum.runtime.organization.IBusinessNetwork;
import org.opaeum.runtime.persistence.ConversationalPersistence;

import rap.SelectionForContact;

import com.google.gdata.data.contacts.ContactEntry;
import com.google.gdata.data.extensions.FullName;
import com.google.gdata.data.extensions.Name;

public class JpaTest extends TestCase{
	public void testIt(){
		IBusinessNetwork bn = new BusinessNetwork();
		ConversationalPersistence p = StandaloneJpaEnvironment.getInstance().getPersistence();
		Structuredbusiness sb = new g();
		bn.addToBusinessCollaboration(sb);
		ContactEntry o = new ContactEntry();
		Name name = new Name();
		name.setFullName(new FullName("John Smith",""));
		o.setName(name);
		Collection<SelectionForContact> result = builSelectionsForContacts(o);
		for(SelectionForContact s:result){
			for(Class<?> c:s.getSelectedClasses()){
				IBusinessActor ba=(IBusinessActor) c.newInstance();
				ba.init(sb);
				ba.addToOwningObject();
				ba.setRepresentedPerson(bn.createPerson());
			} 
		}
		p.persist(sb);
		p.flush();
	}

	public Collection<SelectionForContact> builSelectionsForContacts(ContactEntry o){
		Collection<SelectionForContact> result = new ArrayList<SelectionForContact>();
		Collection<Class<?>> businessActors = StandaloneJpaEnvironment.getMetaInfoMap().getClassesByAnnotation(BusinessActor.class);
		businessActors=new ArrayList<Class<?>>(businessActors);
		Collections.sort((List<Class<?>>)businessActors,new Comparator<Class<?>>(){
			@Override
			public int compare(Class<?> o1,Class<?> o2){
				return o1.getSimpleName().compareTo(o2.getName());
			}
		});
		Collection<ContactEntry> contactEntries=Collections.singleton(o);
		for(ContactEntry contactEntry:contactEntries){
			result.add(new SelectionForContact(contactEntry,(List<Class<?>>) businessActors));
		}
		return result;
	}
}
