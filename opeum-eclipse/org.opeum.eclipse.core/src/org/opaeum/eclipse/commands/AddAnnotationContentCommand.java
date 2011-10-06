package org.opeum.eclipse.commands;

import java.util.Collection;
import java.util.Collections;

import org.opeum.emf.extraction.StereotypesHelper;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.EMFEditPlugin;
import org.eclipse.emf.edit.command.AbstractOverrideableCommand;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.uml2.uml.Element;

public class AddAnnotationContentCommand extends AbstractOverrideableCommand{
	public static Command create(EditingDomain domain,Element owner,Element value){
		return create(domain, owner, Collections.singleton(value), CommandParameter.NO_INDEX);
	}
	/**
	 * This creates a command to insert particular value at a particular index in the specified feature of the owner. The feature will often
	 * be null because the domain will deduce it.
	 */
	public static Command create(EditingDomain domain,Element owner,Element value,int index){
		return create(domain, owner, Collections.singleton(value), index);
	}
	/**
	 * This creates a command to add a collection of values to the specified feature of the owner. The feature will often be null because
	 * the domain will deduce it.
	 */
	public static Command create(EditingDomain domain,Element owner,Collection<? extends Element> collection,int index){
		return new AddAnnotationContentCommand(domain, owner, collection, index);
	}
	/**
	 * This caches the label.
	 */
	protected static final String LABEL = EMFEditPlugin.INSTANCE.getString("_UI_AddCommand_label");
	/**
	 * This caches the description.
	 */
	protected static final String DESCRIPTION = EMFEditPlugin.INSTANCE.getString("_UI_AddCommand_description");
	/**
	 * This caches the description for a list-based addition.
	 */
	protected static final String DESCRIPTION_FOR_LIST = EMFEditPlugin.INSTANCE.getString("_UI_AddCommand_description_for_list");
	/**
	 * This is the owner object upon which the command will act. It could be null in the case that we are dealing with an
	 * {@link org.eclipse.emf.common.util.EList}.
	 */
	protected EObject owner;
	/**
	 * This is the list to which the command will add the collection.
	 */
	protected EList<Element> ownerList;
	/**
	 * This is the position at which the objects will be inserted.
	 */
	protected int index;
	/**
	 * This is the value returned by {@link Command#getAffectedObjects}. The affected objects are different after an execute than after an
	 * undo, so we record it.
	 */
	protected Collection<?> affectedObjects;
	private Collection<? extends Element> collection;
	public AddAnnotationContentCommand(EditingDomain domain,Element owner,Collection<? extends Element> values,int index){
		super(domain, LABEL, DESCRIPTION);
		this.collection = values;
		this.owner = owner;
		this.index = index;
		ownerList = getOwnerList(owner);
	}
	@SuppressWarnings({
			"unchecked","rawtypes"
	})
	private EList<Element> getOwnerList(Element owner){
		return (EList) StereotypesHelper.getNumlAnnotation(owner).getContents();
	}
	/**
	 * This returns the owner object upon which the command will act. It could be null in the case that we are dealing with an
	 * {@link org.eclipse.emf.common.util.EList}.
	 */
	public EObject getOwner(){
		return owner;
	}
	/**
	 * This returns the list to which the command will add.
	 */
	public EList<?> getOwnerList(){
		return ownerList;
	}
	/**
	 * This returns the position at which the objects will be added.
	 */
	public int getIndex(){
		return index;
	}
	@Override
	protected boolean prepare(){
		if(ownerList == null || index != CommandParameter.NO_INDEX && (index < 0 || index > ownerList.size())){
			return false;
		}
		return true;
	}
	@Override
	public void doExecute(){
		// Simply add the collection to the list.
		//
		if(index == CommandParameter.NO_INDEX){
			ownerList.addAll(collection);
		}else{
			ownerList.addAll(index, collection);
		}
		affectedObjects = collection;
	}
	@Override
	public void doUndo(){
		// Remove the collection from the list by index.
		//
		int i = index != CommandParameter.NO_INDEX ? index : ownerList.size() - collection.size();
		ownerList.subList(i, i + collection.size()).clear();
		affectedObjects = owner == null ? Collections.EMPTY_SET : Collections.singleton(owner);
	}
	@Override
	public void doRedo(){
		// Simply add the collection to the list.
		//
		if(index == CommandParameter.NO_INDEX){
			ownerList.addAll(collection);
		}else{
			ownerList.addAll(index, collection);
		}
		affectedObjects = collection;
	}
	@Override
	public Collection<?> doGetResult(){
		return ownerList;
	}
	@Override
	public Collection<?> doGetAffectedObjects(){
		return affectedObjects;
	}
	@Override
	public String toString(){
		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (owner: " + owner + ")");
		result.append(" (ownerList: " + ownerList + ")");
		result.append(" (index: " + index + ")");
		result.append(" (affectedObjects:" + affectedObjects + ")");
		return result.toString();
	}
}