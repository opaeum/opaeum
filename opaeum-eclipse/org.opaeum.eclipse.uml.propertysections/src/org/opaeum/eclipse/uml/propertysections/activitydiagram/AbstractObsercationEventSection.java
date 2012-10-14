package org.opaeum.eclipse.uml.propertysections.activitydiagram;

import java.util.Collection;
import java.util.HashSet;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.StateMachine;
import org.eclipse.uml2.uml.StructuredActivityNode;
import org.opaeum.eclipse.EmfBehaviorUtil;
import org.opaeum.eclipse.EmfElementFinder;
import org.opaeum.eclipse.EmfStateMachineUtil;
import org.opaeum.eclipse.context.OpaeumEclipseContext;
import org.opaeum.eclipse.context.OpenUmlFile;
import org.opaeum.eclipse.uml.propertysections.base.AbstractChooserPropertySection;

public abstract class AbstractObsercationEventSection extends AbstractChooserPropertySection{
	private Group group;
	private Button onEntryBtn;
	private Button onExitBtn;
	public AbstractObsercationEventSection(){
		super();
	}
	protected void createWidgets(Composite composite){
		super.createWidgets(composite);
		this.group = getWidgetFactory().createGroup(composite, "");
		group.setLayout(new RowLayout(SWT.HORIZONTAL));
		onEntryBtn = getWidgetFactory().createButton(group, "On First Entry", SWT.RADIO);
		onEntryBtn.addSelectionListener(new SelectionListener(){
			@Override
			public void widgetSelected(SelectionEvent e){
				handleFirstEvent(((Button) e.getSource()).getSelection());
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent e){
			}
		});
		onExitBtn = getWidgetFactory().createButton(group, "On Last Exit", SWT.RADIO);
		onExitBtn.addSelectionListener(new SelectionListener(){
			@Override
			public void widgetSelected(SelectionEvent e){
				handleFirstEvent(!((Button) e.getSource()).getSelection());
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent e){
			}
		});
		;
	}
	public abstract boolean isFirstEvent();
	@Override
	public void populateControls(){
		super.populateControls();

		onExitBtn.setSelection(!isFirstEvent());
		onEntryBtn.setSelection(isFirstEvent());
	}
	@Override
	protected Object[] getComboFeatureValues(){
		EObject container = EmfElementFinder.getContainer(getEObject());
		Collection<Object> result = new HashSet<Object>();
		if(container instanceof StateMachine){
			result.addAll(EmfStateMachineUtil.getAllStates((StateMachine) container));
		}else if(container instanceof StructuredActivityNode){
			result.addAll(((StructuredActivityNode) container).getNodes());
		}else if(container instanceof Activity){
			result.addAll(((Activity) container).getNodes());
		}
		if(container instanceof Behavior){
			if(EmfBehaviorUtil.isStandaloneTask((Behavior) container)){
				Element observation = (Element) getEObject();
				OpenUmlFile ouf = OpaeumEclipseContext.getContextFor(observation).getEditingContextFor(observation);
				StateMachine to = ouf.getEmfWorkspace().getOpaeumLibrary().getTaskRequest();
				if(to != null){
					result.addAll(EmfStateMachineUtil.getAllStates(to));
				}
			}
		}
		result.add("");
		return result.toArray();
	}
	public abstract void handleFirstEvent(boolean a);
	protected void setSectionData(Composite composite){
		FormData data = new FormData();
		data.left = new FormAttachment(0, getStandardLabelWidth(composite, new String[]{getLabelText()}));
		data.right = new FormAttachment(70, 0);
		cSingleObjectChooser.getContentPane().setLayoutData(data);
		FormData groupData = new FormData();
		groupData.left = new FormAttachment(cSingleObjectChooser.getContentPane());
		groupData.right = new FormAttachment(100, 0);
		group.setLayoutData(groupData);
	}
}