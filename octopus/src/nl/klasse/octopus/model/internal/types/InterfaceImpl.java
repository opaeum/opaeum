package nl.klasse.octopus.model.internal.types;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.model.IInterface;
import nl.klasse.octopus.modelVisitors.IPackageVisitor;


public class InterfaceImpl extends ClassifierImpl implements IInterface {
	static public InterfaceImpl DUMMY = new InterfaceImpl("dummyInterface");
	private boolean isFacade = false;

	private List<IClassifier> implementingClasses;

	/**
	 * 
	 */
	public InterfaceImpl(String n) {
		super(n);
		implementingClasses = new ArrayList<IClassifier>();
	}
	
/***************************************************************************
 * Methods for visiting this IClassifier
 ***************************************************************************/

	public void accept(IPackageVisitor vis ) {
	  vis.interface_Before(this);
	  if( vis.visitAttributes() ){
		Iterator it = this.getAttributes().iterator();
		while( it.hasNext() ){
		  AttributeImpl att = (AttributeImpl) it.next();
		  vis.attribute(att);
		}
		it = this.getClassAttributes().iterator();
		while( it.hasNext() ){
		  AttributeImpl att = (AttributeImpl) it.next();
		  vis.attribute(att);
		}
	  }
	  if( vis.visitOperations() ){
		Iterator it = this.getOperations().iterator();
		while( it.hasNext() ){
		  OperationImpl op = (OperationImpl) it.next();
		  vis.operation_Before(op);
		  if( vis.visitParameters() ){
			Iterator params = op.getParameters().iterator();
			while( params.hasNext() ){
			  vis.parameter((ParameterImpl) params.next());
			}
		  }
		}
	  }
	  if( vis.visitNavigations() ){
		Iterator it = this.getNavigations().iterator();
		while( it.hasNext() ){
		  AssociationEndImpl att = (AssociationEndImpl) it.next();
		  vis.navigation(att);
		}
	  }
	  if( vis.visitStates() ){
		Iterator it = this.getStates().iterator();
		while( it.hasNext() ){
		  vis.state((StateImpl) it.next());
		}
	  }
	  vis.interface_After(this);
	}

	/**
	 * @param impl
	 */
	public void addImplementingClass(ClassifierImpl impl) {
		implementingClasses.add(impl);	
	}
	
	/**
	 * @param impl
	 */
	public void removeImplementingClass(ClassifierImpl impl) {
		implementingClasses.remove(impl);	
	}

	/**
	 * @return
	 */
	public Collection<IClassifier> getImplementingClasses() {
		return Collections.unmodifiableList(implementingClasses);	
	}

	/**
	 * @return
	 */
	public boolean isFacade() {
		return isFacade;
	}

	/**
	 * @param b
	 */
	public void setFacade(boolean b) {
		isFacade = b;
	}

}
