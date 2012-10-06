package org.opaeum.metamodel.workspace;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.ocl.TypeResolver;
import org.eclipse.ocl.types.OCLStandardLibrary;
import org.eclipse.ocl.uml.OCL;
import org.eclipse.uml2.uml.AcceptCallAction;
import org.eclipse.uml2.uml.Action;
import org.eclipse.uml2.uml.CallAction;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.DataType;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.MultiplicityElement;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Namespace;
import org.eclipse.uml2.uml.OpaqueAction;
import org.eclipse.uml2.uml.OpaqueBehavior;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.StateMachine;
import org.eclipse.uml2.uml.Type;
import org.opaeum.eclipse.emulated.IEmulatedElement;
import org.opaeum.eclipse.emulated.IEmulatedPropertyHolder;
import org.opaeum.emf.workspace.UriToFileConverter;
import org.opaeum.ocl.uml.AbstractOclContext;
import org.opaeum.ocl.uml.OpaqueActionActionContext;
import org.opaeum.ocl.uml.OpaqueBehaviorContext;
import org.opaeum.ocl.uml.OpaqueExpressionContext;
import org.opaeum.ocl.uml.ResponsibilityDefinition;

public class OpaeumLibrary  implements IPropertyEmulation{
	
	private OpaeumOcl ocl;
	private ArtificialElementFactory artificialElementFactory;

	public OpaeumLibrary(ResourceSet resourceSet,UriToFileConverter uriToFileConverter){
		this.setOcl(new OpaeumOcl(resourceSet));
		this.setArtificialElementFactory(new ArtificialElementFactory(getOcl(), uriToFileConverter));
	}

	public Classifier getMessageStructure(CallAction action){
		return getArtificialElementFactory().getMessageStructure(action);
	}

	public Classifier getMessageStructure(AcceptCallAction action2){
		return getArtificialElementFactory().getMessageStructure(action2);
	}

	public Property getEndToComposite(Classifier entity){
		return getArtificialElementFactory().getEndToComposite(entity);
	}

	public Property getArtificialEndToComposite(Classifier entity){
		return getArtificialElementFactory().getArtificialEndToComposite(entity);
	}

	public Classifier getMessageStructure(Namespace container){
		return getArtificialElementFactory().getMessageStructure(container);
	}

	public Set<Property> getDirectlyImplementedAttributes(Classifier c){
		return getArtificialElementFactory().getDirectlyImplementedAttributes(c);
	}

	public List<Property> getEffectiveAttributes(Classifier bc){
		return getArtificialElementFactory().getEffectiveAttributes(bc);
	}

	public IEmulatedPropertyHolder getEmulatedPropertyHolder(Classifier bc){
		return getArtificialElementFactory().getEmulatedPropertyHolder(bc);
	}

	public Property findEffectiveAttribute(Classifier fromClass,String name){
		return getArtificialElementFactory().findEffectiveAttribute(fromClass, name);
	}

	public Classifier getEventGeneratingClassifier(NamedElement event){
		return getArtificialElementFactory().getEventGeneratingClassifier(event);
	}

	public Classifier getMessageStructure(OpaqueAction n){
		return getArtificialElementFactory().getMessageStructure(n);
	}

	public ResponsibilityDefinition getResponsibilityDefinition(NamedElement node,String...deadlineOper){
		return getArtificialElementFactory().getResponsibilityDefinition(node, deadlineOper);
	}

	public String getImplementationCodeFor(Model m,String artefactName){
		return getArtificialElementFactory().getImplementationCodeFor(m, artefactName);
	}

	public void reset(){
		getArtificialElementFactory().reset();
		getOcl().reset();
	}

	public SortedSet<IEmulatedElement> getAllEmulatedElements(){
		return getArtificialElementFactory().getAllEmulatedElements();
	}

	public DataType getDateTimeType(){
		return getArtificialElementFactory().getDateTimeType();
	}

	public DataType getDurationType(){
		return getArtificialElementFactory().getDurationType();
	}

	public OpaqueExpressionContext getOclExpressionContext(OpaqueExpression valueSpec){
		return getArtificialElementFactory().getOclExpressionContext(valueSpec);
	}

	public DataType getCumulativeDurationType(){
		return getArtificialElementFactory().getCumulativeDurationType();
	}

	public DataType getDateType(){
		return getLibrary().getDateType();
	}

	public DataType getDefaultType(){
		return getLibrary().getDefaultType();
	}

	public Interface getEmailAddressType(){
		return getLibrary().getEmailAddressType();
	}

	public DataType getBooleanType(){
		return getLibrary().getBooleanType();
	}

	public DataType getIntegerType(){
		return getLibrary().getIntegerType();
	}

	public DataType getRealType(){
		return getLibrary().getRealType();
	}

	public DataType getStringType(){
		return getLibrary().getStringType();
	}

	public Classifier lookupStandardType(String standardType){
		return getLibrary().lookupStandardType(standardType);
	}

	public Interface getBusinessRole(){
		return getLibrary().getBusinessRole();
	}

	public Interface getTaskObject(){
		return getLibrary().getTaskObject();
	}

	public Interface getRequestObject(){
		return getLibrary().getRequestObject();
	}

	public Interface getResponsibilityObject(){
		return getLibrary().getResponsibilityObject();
	}

	public StateMachine getTaskRequest(){
		return getLibrary().getTaskRequest();
	}

	public Class getPersonNode(){
		return getLibrary().getPersonNode();
	}

	public Interface getProcessObject(){
		return getLibrary().getProcessObject();
	}

	public StateMachine getAbstractRequest(){
		return getLibrary().getAbstractRequest();
	}

	public StateMachine getProcessRequest(){
		return getLibrary().getProcessRequest();
	}

	public Interface getBusinessCollaboration(){
		return getLibrary().getBusinessCollaboration();
	}

	public Interface getBusiness(){
		return getLibrary().getBusiness();
	}

	public Interface getBusinessActor(){
		return getLibrary().getBusinessActor();
	}

	public Class getBusinessNetwork(){
		return getLibrary().getBusinessNetwork();
	}

	public ResourceSet getResourceSet(){
		return getLibrary().getResourceSet();
	}

	public Model findLibrary(String librName){
		return getLibrary().findLibrary(librName);
	}

	public OCLStandardLibrary<Classifier> getOclLibrary(){
		return getLibrary().getOclLibrary();
	}

	public Interface getParticipant(){
		return getLibrary().getParticipant();
	}

	public Interface getNotificationReceiver(){
		return getLibrary().getNotificationReceiver();
	}

	public Interface getTimedResource(){
		return getLibrary().getTimedResource();
	}

	public Interface getQuantifiedResource(){
		return getLibrary().getQuantifiedResource();
	}

	public Class getBusinessCalendar(){
		return getLibrary().getBusinessCalendar();
	}

	public Type getLibraryType(LibraryType typeOfChild){
		return getLibrary().getLibraryType(typeOfChild);
	}

	public DataType getDurationBasedCost(){
		return getLibrary().getDurationBasedCost();
	}

	public DataType getQuantityBasedCost(){
		return getLibrary().getQuantityBasedCost();
	}

	public Map<String,Operation> getAdditionalOperations(){
		return getLibrary().getAdditionalOperations();
	}

	public void setAdditionalOperations(Map<String,Operation> additionalOperations){
		getLibrary().setAdditionalOperations(additionalOperations);
	}

	public OpaqueBehaviorContext getOclBehaviorContext(OpaqueBehavior valueSpec){
		return getOcl().getOclBehaviorContext(valueSpec);
	}

	public OpaqueActionActionContext getOclActionContext(OpaqueAction valueSpec){
		return getOcl().getOclActionContext(valueSpec);
	}

	public OCL createOcl(Element context,Map<String,Classifier> vars){
		return getOcl().createOcl(context, vars);
	}

	public TypeResolver<Classifier,Operation,Property> getTypeResolver(){
		return getOcl().getTypeResolver();
	}

	public Collection<AbstractOclContext> getOclContexts(){
		return getOcl().getOclContexts();
	}

	public OpaqueExpressionContext getArtificationExpression(NamedElement ne,String tagName){
		return getOcl().getArtificationExpression(ne, tagName);
	}

	public Collection<OpaqueExpressionContext> getArtificialExpressions(NamedElement ne,String tagName){
		return getOcl().getArtificialExpressions(ne, tagName);
	}

	public Classifier getTargetType(Action a){
		return getOcl().getTargetType(a);
	}

	public Classifier getActualType(MultiplicityElement me){
		return getOcl().getActualType(me);
	}

	public OpaeumLib getLibrary(){
		return getOcl().getLibrary();
	}

	public OpaeumOcl getOcl(){
		return ocl;
	}

	private void setOcl(OpaeumOcl ocl){
		this.ocl = ocl;
	}


	private ArtificialElementFactory getArtificialElementFactory(){
		return artificialElementFactory;
	}

	private void setArtificialElementFactory(ArtificialElementFactory artificialElementFactory){
		this.artificialElementFactory = artificialElementFactory;
	}
	
	
}
