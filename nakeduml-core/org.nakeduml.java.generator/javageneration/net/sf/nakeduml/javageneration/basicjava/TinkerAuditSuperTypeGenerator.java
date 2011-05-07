package net.sf.nakeduml.javageneration.basicjava;

import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.javageneration.AbstractJavaProducingVisitor;
import net.sf.nakeduml.javageneration.NakedClassifierMap;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedGeneralization;
import net.sf.nakeduml.metamodel.core.INakedInterface;
import net.sf.nakeduml.metamodel.core.INakedInterfaceRealization;

import org.nakeduml.java.metamodel.OJConstructor;
import org.nakeduml.java.metamodel.OJPathName;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedClass;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedInterface;

public class TinkerAuditSuperTypeGenerator extends AbstractJavaProducingVisitor{
	@VisitBefore(matchSubclasses = true)
	public void visitClass(INakedClassifier c){
		if(c.getGeneralizations().size() ==1){
			OJAnnotatedClass myClass = findAuditJavaClass(c);
			if(myClass != null){
				for(INakedGeneralization g:c.getNakedGeneralizations()){
					NakedClassifierMap map = new NakedClassifierMap(g.getGeneral());
					OJPathName javaTypePath = map.javaTypePath();
					OJPathName auditCopy = javaTypePath.getDeepCopy(); 
					auditCopy.replaceTail(auditCopy.getLast()+TinkerAuditCreator.AUDIT);
					myClass.setSuperclass(auditCopy);
					myClass.addToImports(auditCopy);
					OJConstructor constructor = myClass.getDefaultConstructor();
					constructor.getBody().addToStatements("super()");
				}
			}
		}else if(c.getNakedGeneralizations().size()>1){
			//TODO implement as validation rule
			System.out.println(c + " has more than one generalization");
		}
		for(INakedInterfaceRealization ir:c.getInterfaceRealizations()){
			OJAnnotatedClass myClass = findAuditJavaClass(c);
			OJPathName classifierPathname = OJUtil.classifierPathname(ir.getContract());
			OJPathName auditCopy = classifierPathname.getDeepCopy();
			auditCopy.replaceTail(auditCopy.getLast()+TinkerAuditCreator.AUDIT);
			myClass.addToImplementedInterfaces(auditCopy);
		}
	}
	@VisitBefore(matchSubclasses = true)
	public void visitInterface(INakedInterface c){
		if(c.getGeneralizations().isEmpty())
			return;
		OJAnnotatedInterface myIntf = (OJAnnotatedInterface) findAuditJavaClass(c);
		if(myIntf != null){
			for(INakedGeneralization g:c.getNakedGeneralizations()){
				OJPathName pathname = OJUtil.classifierPathname(g.getGeneral());
				OJPathName auditCopy = pathname.getDeepCopy();
				auditCopy.replaceTail(auditCopy.getLast()+TinkerAuditCreator.AUDIT);
				myIntf.getSuperInterfaces().add(auditCopy);
				myIntf.addToImports(auditCopy);
			}
		}
	}
}
