package org.opeum.topcased.bpmn2;


public class BpmnIntermediateEventEditPart {
//	public BpmnIntermediateEventEditPart(GraphNode obj){
//		super(obj);
//	}
//	public Figure createFigure(){
//		Bpmn2IntermediateEventFigure figure = new Bpmn2IntermediateEventFigure();
//		figure.addFigureListener(new FigureListener(){
//			@Override
//			public void figureMoved(IFigure source){
//				GraphNode gn= (GraphNode) getModel();
//				System.out.println(gn.getSize());
//			}
//		});
//		figure.addLayoutListener(new LayoutListener(){
//
//			@Override
//			public void invalidate(IFigure container){
//				// TODO Auto-generated method stub
//				
//			}
//
//			@Override
//			public boolean layout(IFigure container){
//				return false;
//			}
//
//			@Override
//			public void postLayout(IFigure container){
//				GraphNode gn= (GraphNode) getModel();
//				System.out.println("GRaphNode Size=" +gn.getSize());
//				gn.setSize(container.getSize());
////				gn.getSize().height=container.getSize().height;
////				gn.getSize().width=container.getSize().width;
//				System.out.println("Figure Size=" +container.getSize());
//			}
//
//			@Override
//			public void remove(IFigure child){
//				// TODO Auto-generated method stub
//				
//			}
//
//			@Override
//			public void setConstraint(IFigure child,Object constraint){
//				// TODO Auto-generated method stub
//				
//			}
//			
//		});
//		return figure;
//	}
//	public void activate(){
//		super.activate();
//		if(getEObject() instanceof AcceptEventAction){
//			listenTriggers((AcceptEventAction) getEObject());
//		}
//		if(getEObject() != null && !getEObject().eAdapters().contains(getModelListener())){
//			getEObject().eAdapters().add(getModelListener());
//		}
//	}
//	public void deactivate(){
//		if(getEObject() != null){
//			getEObject().eAdapters().remove(getModelListener());
//		}
//		if(getEObject() instanceof AcceptEventAction){
//			unlistenTriggers((AcceptEventAction) getEObject());
//		}
//		super.deactivate();
//	}
//	private void listenTriggers(AcceptEventAction acceptEventAction){
//		for(Trigger trigger:acceptEventAction.getTriggers()){
//			if(!trigger.eAdapters().contains(getModelListener())){
//				trigger.eAdapters().add(getModelListener());
//			}
//			if(trigger.getEvent() != null && !trigger.getEvent().eAdapters().contains(getModelListener())){
//				trigger.getEvent().eAdapters().add(getModelListener());
//			}
//		}
//	}
//	private void unlistenTriggers(AcceptEventAction acceptEventAction){
//		for(Trigger trigger:acceptEventAction.getTriggers()){
//			if(trigger.getEvent() != null){
//				trigger.getEvent().eAdapters().remove(getModelListener());
//			}
//			trigger.eAdapters().remove(getModelListener());
//		}
//	}
//	private void unlistenTriggers(AcceptEventAction acceptEventAction,Trigger trigger){
//		for(Trigger aTrigger:acceptEventAction.getTriggers()){
//			if(aTrigger.getName().equals(trigger.getName())){
//				if(trigger.getEvent() != null){
//					trigger.getEvent().eAdapters().remove(getModelListener());
//				}
//				trigger.eAdapters().remove(getModelListener());
//			}
//		}
//	}
//	protected void handleModelChanged(Notification msg){
//		if(getEObject() instanceof AcceptEventAction){
//			updateTriggers(msg);
//			AcceptEventAction currentAcceptEventAction = (AcceptEventAction) getEObject();
//			if(!currentAcceptEventAction.getTriggers().isEmpty()){
//				Event event = currentAcceptEventAction.getTriggers().get(0).getEvent();
//				if(event instanceof TimeEvent){
//					 setImage("Timer");
//				}else if(event instanceof ChangeEvent){
//					 setImage("Condition");
//				}else if(event instanceof SignalEvent){
//					 setImage("Signal");
//				}
//			}
//		}
//		super.handleModelChanged(msg);
//	}
//	private void setImage(String imageName){
////		((Bpmn2IntermediateEventFigure) getFigure()).getImageFigure().setImage(Bpmn2Plugin.getDefault().getImageRegistry().get(imageName));
//	}
//	private void updateTriggers(Notification msg){
//		AcceptEventAction currentAcceptEventAction = (AcceptEventAction) getEObject();
//		if(msg.getEventType() == Notification.REMOVE && msg.getOldValue() instanceof Trigger){
//			unlistenTriggers(currentAcceptEventAction, (Trigger) msg.getOldValue());
//		}else{
//			listenTriggers(currentAcceptEventAction);
//		}
//	}
//	protected Color getPreferenceDefaultBackgroundColor(){
//		String backgroundColor = getPreferenceStore().getString(ActivityDiagramPreferenceConstants.ACCEPTEVENTACTION_DEFAULT_BACKGROUND_COLOR);
//		if(backgroundColor.length() != 0){
//			return Utils.getColor(backgroundColor);
//		}
//		return null;
//	}
//	protected Color getPreferenceDefaultForegroundColor(){
//		String foregroundColor = getPreferenceStore().getString(ActivityDiagramPreferenceConstants.ACCEPTEVENTACTION_DEFAULT_FOREGROUND_COLOR);
//		if(foregroundColor.length() != 0){
//			return Utils.getColor(foregroundColor);
//		}
//		return null;
//	}
//	protected Font getPreferenceDefaultFont(){
//		String preferenceFont = getPreferenceStore().getString(ActivityDiagramPreferenceConstants.ACCEPTEVENTACTION_DEFAULT_FONT);
//		if(preferenceFont.length() != 0){
//			return Utils.getFont(new FontData(preferenceFont));
//		}
//		return null;
//	}
}