package org.nakeduml.uim.editparts;


import org.eclipse.draw2d.IFigure;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.Property;
import org.nakeduml.uim.NavigationToEntity;
import org.nakeduml.uim.PropertyRef;
import org.nakeduml.uim.UIMBinding;
import org.nakeduml.uim.UIMDataTable;
import org.nakeduml.uim.UIMField;
import org.nakeduml.uim.UIMPackage;
import org.nakeduml.uim.figures.IBindingFigure;
import org.nakeduml.uim.figures.UIMDataColumnFigure;
import org.topcased.draw2d.figures.ILabel;
import org.topcased.modeler.di.model.GraphNode;
import org.topcased.modeler.edit.EMFGraphNodeEditPart;

public class BoundEditPart extends EMFGraphNodeEditPart {
	public BoundEditPart(GraphNode obj) {
		super(obj);
	}

	@Override
	protected void refreshVisuals() {
		UIMBinding uIMBinding = getBinding();
		if (uIMBinding != null) {
			IBindingFigure fig = (IBindingFigure) getFigure();
			ILabel field = fig.getBindingLabel();
			if (uIMBinding != null && uIMBinding.getElement() != null) {
				IFigure parent = fig.getParent();
				while (!(parent.getParent() == null || parent instanceof UIMDataColumnFigure)) {
					parent = parent.getParent();
				}
				StringBuffer s = new StringBuffer();
				if (uIMBinding.getElement() instanceof Property) {
					if (parent instanceof UIMDataColumnFigure) {
						s.append("row.");
					} else {
						s.append("self.");
					}
					s.append(uIMBinding.getElement().getName());
				} else {// parameter or pin
					s.append("params[");
					s.append(uIMBinding.getElement().getName());
					s.append("]");

				}
				addString(uIMBinding.getNext(), s);
				field.setText(s.toString());
			} else {
				field.setText("select property");
			}
		}
		super.refreshVisuals();
	}

	private UIMBinding getBinding() {
		if (getEObject() instanceof UIMField) {
			return ((UIMField) getEObject()).getBinding();
		} else if (getEObject() instanceof UIMDataTable) {
			return ((UIMDataTable) getEObject()).getBinding();
		} else if (getEObject() instanceof NavigationToEntity) {
			return ((NavigationToEntity) getEObject()).getBinding();

		}
		return null;
	}

	void addString(PropertyRef pr, StringBuffer sb) {
		if (pr != null && pr.getProperty() != null) {
			sb.append(".");
			sb.append(pr.getProperty().getName());
			addString(pr.getNext(), sb);
		}
	}

	@Override
	protected void handleModelChanged(Notification msg) {

		Object notifier = msg.getNotifier();
		Object newValue = msg.getNewValue();
		Object oldValue = msg.getOldValue();

		if (notifier instanceof UIMField && notifier == getEObject()) {
			switch (msg.getFeatureID(UIMField.class)) {
			case UIMPackage.UIM_FIELD__BINDING:
				// TODO memory leak may result - recursively remove all
				// listening
				super.updateModelListening(oldValue, newValue);
				refreshVisuals();
			default:
				break;
			}
		}
		if (notifier instanceof UIMDataTable && notifier == getEObject()) {
			switch (msg.getFeatureID(UIMDataTable.class)) {
			case UIMPackage.UIM_DATA_TABLE__BINDING:
				// TODO memory leak may result - recursively remove all
				// listening
				super.updateModelListening(oldValue, newValue);
				refreshVisuals();
			default:
				break;
			}
		}
		if (notifier instanceof NavigationToEntity && notifier == getEObject()) {
			switch (msg.getFeatureID(NavigationToEntity.class)) {
			case UIMPackage.NAVIGATION_TO_ENTITY__BINDING:
				// TODO memory leak may result - recursively remove all
				// listening
				super.updateModelListening(oldValue, newValue);
				refreshVisuals();// ???necessary
			default:
				break;
			}
		}

		if (notifier instanceof UIMBinding) {
			switch (msg.getFeatureID(UIMBinding.class)) {
			case UIMPackage.UIM_BINDING__ELEMENT:
			case UIMPackage.UIM_BINDING__NEXT:
				super.updateModelListening(oldValue, newValue);
				break;
			}
		}
		if (notifier instanceof PropertyRef) {
			switch (msg.getFeatureID(PropertyRef.class)) {
			case UIMPackage.PROPERTY_REF__PROPERTY:
			case UIMPackage.PROPERTY_REF__NEXT:
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
	public void activate() {
		super.activate();
		listenBinding(getBinding());
	}

	private void listenBinding(UIMBinding uIMBinding) {
		if (uIMBinding != null
				&& !uIMBinding.eAdapters().contains(getModelListener())) {
			uIMBinding.eAdapters().add(getModelListener());
			PropertyRef next = uIMBinding.getNext();
			addListenerToPropertyRef(next);
		}
	}

	private void addListenerToPropertyRef(PropertyRef next) {
		if (next != null) {
			next.eAdapters().add(getModelListener());
			addListenerToPropertyRef(next.getNext());
		}
	}

	/**
	 * @see org.topcased.modeler.edit.EMFGraphNodeEditPart#deactivate()
	 */
	@Override
	public void deactivate() {
		EObject model = getEObject();
		if (model instanceof UIMField) {
			unlistenBinding(getBinding());
		}
		super.deactivate();
	}

	private void unlistenBinding(UIMBinding uIMBinding) {
		if (uIMBinding != null) {
			uIMBinding.eAdapters().remove(getModelListener());
			PropertyRef next = uIMBinding.getNext();
			removeListenersFromPropertyRef(next);
		}
	}

	private void removeListenersFromPropertyRef(PropertyRef next) {
		if (next != null) {
			next.eAdapters().remove(getModelListener());
			removeListenersFromPropertyRef(next.getNext());
		}
	}
}