package org.opaeum.reverse.popup.actions;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.dom.AbstractTypeDeclaration;
import org.eclipse.jdt.core.dom.IMethodBinding;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.DataType;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.LiteralUnlimitedNatural;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.ParameterDirectionKind;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;

public abstract class AbstractUmlGenerator{
	protected ClassifierFactory factory;
	protected JavaDescriptorFactory javaDescriptorFactory = new JavaDescriptorFactory();
	public abstract Collection<Element> generateUml(Map<ITypeBinding,AbstractTypeDeclaration> types,Package library,IProgressMonitor m) throws Exception;
	public Package getRootPackage(Resource resource){
		EList<EObject> contents = resource.getContents();
		for(EObject eObject:contents){
			if(eObject instanceof Package){
				return (Package) eObject;
			}
		}
		return null;
	}
	protected void populateOperations(Package modelOrProfile,Classifier owner,Entry<ITypeBinding,AbstractTypeDeclaration> t){
		IMethodBinding[] mds = t.getKey().getDeclaredMethods();
		for(IMethodBinding md:mds){
			if(Modifier.isPublic(md.getModifiers()) && !md.isConstructor() && !hasOperation(modelOrProfile, owner, md) && !isAccessor(md)){
				Operation oper = createOperation(owner, md);
				oper.setIsStatic(Modifier.isStatic(md.getModifiers()));
				ITypeBinding returnType = md.getReturnType();
				if(!returnType.getName().equals("void")){
					Parameter result = oper.getOwnedParameter("result", factory.getClassifierFor(returnType), false, true);
					result.setDirection(ParameterDirectionKind.RETURN_LITERAL);
					if(returnType.isArray() || factory.isCollection(returnType)){
						result.setUpper(LiteralUnlimitedNatural.UNLIMITED);
					}
				}
				ITypeBinding[] parameterTypes = md.getParameterTypes();
				for(int i = 0;i < parameterTypes.length;i++){
					if(parameterTypes[0].isArray() || factory.isCollection(parameterTypes[0])){
						Parameter umlParameter = oper.getOwnedParameters().get(i);
						umlParameter.setUpper(LiteralUnlimitedNatural.UNLIMITED);
						umlParameter.setName("parm" + i);
					}
				}
				if(!(owner instanceof Interface)){
					if(t.getValue() instanceof TypeDeclaration){
						TypeDeclaration td = (TypeDeclaration) t.getValue();
						for(MethodDeclaration m:td.getMethods()){
							IMethod iMethod = (IMethod) md.getMethodDeclaration().getJavaElement();
							try{
								if(m.getStartPosition() == iMethod.getSourceRange().getOffset()){
									oper.setBodyCondition(UMLFactory.eINSTANCE.createConstraint());
									oper.getBodyCondition().setName("javaBody");
									OpaqueExpression oe = UMLFactory.eINSTANCE.createOpaqueExpression();
									oper.getBodyCondition().setSpecification(oe);
									oe.getLanguages().add("java");
									oper.setIsQuery(true);
									String s = m.getBody().toString();
									oe.getBodies().add(s);
								}
							}catch(Exception e){
								System.out.println(e);
							}
						}
					}
				}
			}
		}
	}
	private boolean isAccessor(IMethodBinding md){
		boolean returnsVoid = md.getReturnType() == null || md.getReturnType().getName().equals("void");
		boolean setter = md.getName().startsWith("set") && md.getParameterTypes().length == 1 && returnsVoid;
		boolean getter = md.getName().startsWith("get") && !returnsVoid;
		return setter || getter;
	}
	private boolean hasOperation(Package modelOrProfile,Classifier intfce,IMethodBinding md){
		for(Operation oper:intfce.getOperations()){
			List<Parameter> args = new ArrayList<Parameter>();
			for(Parameter parameter:oper.getOwnedParameters()){
				if(parameter.getDirection() == ParameterDirectionKind.IN_LITERAL
						|| parameter.getDirection() == ParameterDirectionKind.INOUT_LITERAL){
					args.add(parameter);
				}
			}
			if(oper.getName().equals(md.getName()) && md.getParameterTypes().length == args.size()){
				boolean paramsMatch = true;
				for(int i = 0;i < md.getParameterTypes().length;i++){
					Parameter parm = args.get(i);
					if(factory.getClassifierFor(md.getParameterTypes()[i]) != parm.getType()){
						paramsMatch = false;
						break;
					}
				}
				if(paramsMatch){
					return true;
				}
			}
		}
		return false;
	}
	protected void populateAttributes(Package modelOrProfile,Classifier classifier,Entry<ITypeBinding,AbstractTypeDeclaration> t){
		Collection<PropertyDescriptor> fields = javaDescriptorFactory.getClassDescriptor(t.getKey()).getPropertyDescriptors().values();
		for(PropertyDescriptor pd:fields){
			Property found = findProperty(classifier, pd);
			if(found == null){
				Property attr = createAttribute(classifier, pd);
				attr.setIsReadOnly(pd.isReadOnly);
				attr.setIsDerived(pd.isReadOnly);
				if(pd.isMany()){
					attr.setUpper(-1);
				}
			}
		}
	}
	protected Property findProperty(Classifier classifier,PropertyDescriptor pd){
		return findProperty(pd, classifier.getAttributes());
	}
	protected Property findProperty(PropertyDescriptor pd,EList<Property> attributes){
		Property found = null;
		for(Property property:attributes){
			if(property.getName().equalsIgnoreCase(pd.getName())){
				found = property;
				break;
			}
		}
		return found;
	}
	private Operation createOperation(Classifier cls,IMethodBinding md){
		Operation oper = null;
		if(cls instanceof Interface){
			oper = ((Interface) cls).getOwnedOperation(md.getName(), getArgumentNames(md), getArgumentTypes(md), false, true);
		}else if(cls instanceof DataType){
			oper = ((DataType) cls).getOwnedOperation(md.getName(), getArgumentNames(md), getArgumentTypes(md), false, true);
		}else if(cls instanceof org.eclipse.uml2.uml.Class){
			oper = ((org.eclipse.uml2.uml.Class) cls).getOwnedOperation(md.getName(), getArgumentNames(md), getArgumentTypes(md), false, true);
		}
		return oper;
	}
	protected Property createAttribute(Classifier cls,PropertyDescriptor pd){
		Property attr = null;
		if(cls instanceof Interface){
			attr = ((Interface) cls).getOwnedAttribute(pd.getName(), factory.getClassifierFor(pd.getBaseType()), false,
					UMLPackage.eINSTANCE.getProperty(), true);
		}else if(cls instanceof org.eclipse.uml2.uml.Class){
			attr = ((org.eclipse.uml2.uml.Class) cls).getOwnedAttribute(pd.getName(), factory.getClassifierFor(pd.getBaseType()), false,
					UMLPackage.eINSTANCE.getProperty(), true);
		}else if(cls instanceof DataType){
			attr = ((DataType) cls).getOwnedAttribute(pd.getName(), factory.getClassifierFor(pd.getBaseType()), false,
					UMLPackage.eINSTANCE.getProperty(), true);
		}
		if(attr!=null && attr.getAssociation()!=null && attr.getAssociation().getMemberEnds().size()==1){
			System.out.println();
		}
		return attr;
	}
	private EList<String> getArgumentNames(IMethodBinding md){
		BasicEList<String> results = new BasicEList<String>();
		for(int i = 0;i < md.getParameterTypes().length;i++){
			results.add("parm" + i);
		}
		return results;
	}
	private EList<Type> getArgumentTypes(IMethodBinding md){
		BasicEList<Type> results = new BasicEList<Type>();
		ITypeBinding[] pds = md.getParameterTypes();
		for(ITypeBinding pd:pds){
			results.add(factory.getClassifierFor(pd));
		}
		return results;
	}
}
