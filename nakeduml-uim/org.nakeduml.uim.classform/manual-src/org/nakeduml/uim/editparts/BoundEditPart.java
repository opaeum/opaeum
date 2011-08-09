package org.nakeduml.uim.editparts;

import org.eclipse.draw2d.IFigure;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.Property;
import org.nakeduml.uim.UimDataTable;
import org.nakeduml.uim.UimField;
import org.nakeduml.uim.UimPackage;
import org.nakeduml.uim.UserInteractionElement;
import org.nakeduml.uim.action.ActionPackage;
import org.nakeduml.uim.action.NavigationToEntity;
import org.nakeduml.uim.binding.BindingPackage;
import org.nakeduml.uim.binding.PropertyRef;
import org.nakeduml.uim.binding.UimBinding;
import org.nakeduml.uim.figures.IBindingFigure;
import org.nakeduml.uim.figures.UimColumnLayoutFigure;
import org.nakeduml.uim.modeleditor.editor.UimEditor;
import org.topcased.draw2d.figures.ILabel;
import org.topcased.modeler.di.model.GraphNode;
import org.topcased.modeler.edit.EMFGraphNodeEditPart;

public class BoundEditPart extends EMFGraphNodeEditPart{
	public BoundEditPart(GraphNode obj){
		super(obj);
	}
	@Override
	protected void refreshVisuals(){
		UimBinding uIMBinding = getBinding();
		if(uIMBinding != null){
			IBindingFigure fig = (IBindingFigure) getFigure();
			ILabel field = fig.getBindingLabel();
			if(field != null){
				if(uIMBinding != null && UimEditor.getCurrentUmlLinks().getTypedElement(uIMBinding) != null){
					IFigure parent = fig.getParent();
					while(!(parent.getParent() == null || parent instanceof UimColumnLayoutFigure)){
						parent = parent.getParent();
					}
					StringBuffer s = new StringBuffer();
					if(UimEditor.getCurrentUmlLinks().getTypedElement(uIMBinding) instanceof Property){
						if(parent instanceof UimColumnLayoutFigure){
							s.append("row.");
						}else{
							s.append("self.");
						}
						s.append(UimEditor.getCurrentUmlLinks().getTypedElement(uIMBinding).getName());
					}else{// parameter or pin
						s.append("params[");
						s.append(UimEditor.getCurrentUmlLinks().getTypedElement(uIMBinding).getName());
						s.append("]");
					}
					addString(uIMBinding.getNext(), s);
					field.setText(s.toString());
				}else{
					field.setText("select property");
				}
			}
		}
		super.refreshVisuals();
	}
	private UimBinding getBinding(){
		if(getEObject() instanceof UimField){
			return ((UimField) getEObject()).getBinding();
		}else if(getEObject() instanceof UimDataTable){
			return ((UimDataTable) getEObject()).getBinding();
		}else if(getEObject() instanceof NavigationToEntity){
			return ((NavigationToEntity) getEObject()).getBinding();
		}
		return null;
	}
	void addString(PropertyRef pr,StringBuffer sb){
		if(pr != null && UimEditor.getCurrentUmlLinks().getProperty(pr) != null){
			sb.append(".");
			sb.append(UimEditor.getCurrentUmlLinks().getProperty(pr).getName());
			addString(pr.getNext(), sb);
		}
	}
	private UserInteractionElement getUserInteractioinElement(){
		return (UserInteractionElement) getEObject();
	}
	@Override
	protected void handleModelChanged(Notification msg){
		Object notifier = msg.getNotifier();
		Object newValue = msg.getNewValue();
		Object oldValue = msg.getOldValue();
		if(notifier instanceof UimField && notifier == getEObject()){
			switch(msg.getFeatureID(UimField.class)){
			case UimPackage.UIM_FIELD__BINDING:
				// TODO memory leak may result - recursively remove all
				// listening
				super.updateModelListening(oldValue, newValue);
			default:
				break;
			}
		}
		if(notifier instanceof UimDataTable && notifier == getEObject()){
			switch(msg.getFeatureID(UimDataTable.class)){
			case UimPackage.UIM_DATA_TABLE__BINDING:
				// TODO memory leak may result - recursively remove all
				// listening
				super.updateModelListening(oldValue, newValue);
			default:
				break;
			}
		}
		if(notifier instanceof NavigationToEntity && notifier == getEObject()){
			switch(msg.getFeatureID(NavigationToEntity.class)){
			case ActionPackage.NAVIGATION_TO_ENTITY__BINDING:
				// TODO memory leak may result - recursively remove all
				// listening
				super.updateModelListening(oldValue, newValue);
			default:
				break;
			}
		}
		if(notifier instanceof UimBinding){
			switch(msg.getFeatureID(UimBinding.class)){
			case BindingPackage.UIM_BINDING__UML_ELEMENT_UID:
			case BindingPackage.UIM_BINDING__NEXT:
				super.updateModelListening(oldValue, newValue);
				break;
			}
		}
		if(notifier instanceof PropertyRef){
			switch(msg.getFeatureID(PropertyRef.class)){
			case BindingPackage.PROPERTY_REF__UML_ELEMENT_UID:
			case BindingPackage.PROPERTY_REF__NEXT:
				super.updateModelListening(oldValue, newValue);
				break;
			}
		}
		super.handleModelChanged(msg);
	}
	/**
	 * @see org.topcased.modeler.edit.EMFGraphNodeEditPart#activate()
	 */
	@Override
	public void activate(){
		super.activate();
		listenBinding(getBinding());
	}
	private void listenBinding(UimBinding uIMBinding){
		if(uIMBinding != null && !uIMBinding.eAdapters().contains(getModelListener())){
			uIMBinding.eAdapters().add(getModelListener());
			PropertyRef next = uIMBinding.getNext();
			addListenerToPropertyRef(next);
		}
	}
	private void addListenerToPropertyRef(PropertyRef next){
		if(next != null){
			next.eAdapters().add(getModelListener());
			addListenerToPropertyRef(next.getNext());
		}
	}
	/**
	 * @see org.topcased.modeler.edit.EMFGraphNodeEditPart#deactivate()
	 */
	@Override
	public void deactivate(){
		EObject model = getEObject();
		if(model instanceof UimField){
			unlistenBinding(getBinding());
		}
		super.deactivate();
	}
	private void unlistenBinding(UimBinding uIMBinding){
		if(uIMBinding != null){
			uIMBinding.eAdapters().remove(getModelListener());
			PropertyRef next = uIMBinding.getNext();
			removeListenersFromPropertyRef(next);
		}
	}
	private void removeListenersFromPropertyRef(PropertyRef next){
		if(next != null){
			next.eAdapters().remove(getModelListener());
			removeListenersFromPropertyRef(next.getNext());
		}
	}
}