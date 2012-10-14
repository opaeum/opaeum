package org.opaeum.eclipse.uml.propertysections.common;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.fieldassist.FieldDecoration;
import org.eclipse.jface.fieldassist.FieldDecorationRegistry;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.forms.IMessage;
import org.eclipse.ui.forms.IMessageManager;
import org.eclipse.ui.forms.IMessagePrefixProvider;
import org.eclipse.ui.forms.widgets.Hyperlink;

public class PropertySectionMessageManager implements IMessageManager{
	private static FieldDecoration standardError = FieldDecorationRegistry.getDefault().getFieldDecoration(FieldDecorationRegistry.DEC_ERROR);
	private static FieldDecoration standardWarning = FieldDecorationRegistry.getDefault().getFieldDecoration(FieldDecorationRegistry.DEC_WARNING);
	private static final DefaultPrefixProvider DEFAULT_PREFIX_PROVIDER = new DefaultPrefixProvider();
	private List<Message> messages = new ArrayList<Message>();
	private Hashtable<Control,ControlDecorator> decorators = new Hashtable<Control,ControlDecorator>();
	private boolean autoUpdate = true;
	private IMessagePrefixProvider prefixProvider = DEFAULT_PREFIX_PROVIDER;
	private int decorationPosition = SWT.LEFT | SWT.BOTTOM;
	static class Message implements IMessage{
		private Control control;
		private Object data;
		private Object key;
		private String message;
		private int type;
		private String prefix;
		Message(Object key,String message,int type,Object data){
			this.key = key;
			this.message = message;
			this.type = type;
			this.data = data;
		}
		public Object getKey(){
			return key;
		}
		public String getMessage(){
			return message;
		}
		public int getMessageType(){
			return type;
		}
		public Control getControl(){
			return control;
		}
		public Object getData(){
			return data;
		}
		public String getPrefix(){
			return prefix;
		}
	}
	static class DefaultPrefixProvider implements IMessagePrefixProvider{
		public String getPrefix(Control c){
			Composite parent = c.getParent();
			Control[] siblings = parent.getChildren();
			for(int i = 0;i < siblings.length;i++){
				if(siblings[i] == c){
					// this is us - go backward until you hit
					// a labelCombo-like widget
					for(int j = i - 1;j >= 0;j--){
						Control label = siblings[j];
						String ltext = null;
						if(label instanceof Label){
							ltext = ((Label) label).getText();
						}else if(label instanceof Hyperlink){
							ltext = ((Hyperlink) label).getText();
						}else if(label instanceof CLabel){
							ltext = ((CLabel) label).getText();
						}
						if(ltext != null){
							if(!ltext.endsWith(":")){
								return ltext + ": "; //$NON-NLS-1$
							}
							return ltext + " "; //$NON-NLS-1$
						}
					}
					break;
				}
			}
			return null;
		}
	}
	class ControlDecorator{
		private ControlDecoration decoration;
		private List<Message> controlMessages = new ArrayList<Message>();
		private String prefix;
		ControlDecorator(Control control){
			this.decoration = new ControlDecoration(control, decorationPosition);
		}
		public boolean isDisposed(){
			return decoration.getControl() == null;
		}
		void updatePrefix(){
			prefix = null;
		}
		void updatePosition(){
			Control control = decoration.getControl();
			decoration.dispose();
			this.decoration = new ControlDecoration(control, decorationPosition);
			update();
		}
		String getPrefix(){
			if(prefix == null){
				createPrefix();
			}
			return prefix;
		}
		private void createPrefix(){
			if(prefixProvider == null){
				prefix = ""; //$NON-NLS-1$
				return;
			}
			prefix = prefixProvider.getPrefix(decoration.getControl());
			if(prefix == null){
				// make a prefix anyway
				prefix = ""; //$NON-NLS-1$
			}
		}
		void addAll(List<Message> target){
			target.addAll(controlMessages);
		}
		void addMessage(Object key,String text,Object data,int type){
			Message message = PropertySectionMessageManager.this.addMessage(getPrefix(), key, text, data, type, controlMessages);
			message.control = decoration.getControl();
			if(isAutoUpdate()){
				update();
			}
		}
		boolean removeMessage(Object key){
			Message message = findMessage(key, controlMessages);
			if(message != null){
				controlMessages.remove(message);
				if(isAutoUpdate()){
					update();
				}
			}
			return message != null;
		}
		boolean removeMessages(){
			if(controlMessages.isEmpty()){
				return false;
			}
			controlMessages.clear();
			if(isAutoUpdate()){
				update();
			}
			return true;
		}
		public void update(){
			if(controlMessages.isEmpty()){
				decoration.setDescriptionText(null);
				decoration.hide();
			}else{
				List<Message> peers = createPeers(controlMessages);
				int type = ((IMessage) peers.get(0)).getMessageType();
				String description = createDetails(createPeers(peers), true);
				if(type == IMessageProvider.ERROR){
					decoration.setImage(standardError.getImage());
				}else if(type == IMessageProvider.WARNING){
					decoration.setImage(standardWarning.getImage());
				}
				decoration.setDescriptionText(description);
				decoration.show();
			}
		}
	}
	public void addMessage(Object key,String messageText,Object data,int type){
		addMessage(null, key, messageText, data, type, messages);
	}
	public void addMessage(Object key,String messageText,Object data,int type,Control control){
		ControlDecorator dec = decorators.get(control);
		if(dec == null){
			dec = new ControlDecorator(control);
			decorators.put(control, dec);
		}
		dec.addMessage(key, messageText, data, type);
	}
	public void removeMessage(Object key){
		Message message = findMessage(key, messages);
		if(message != null){
			messages.remove(message);
		}
	}
	public void removeMessages(){
		if(!messages.isEmpty()){
			messages.clear();
		}
	}
	public void removeMessage(Object key,Control control){
		ControlDecorator dec = decorators.get(control);
		if(dec == null){
			return;
		}
		dec.removeMessage(key);
	}
	public void removeMessages(Control control){
		ControlDecorator dec = decorators.get(control);
		if(dec != null){
			dec.removeMessages();
		}
	}
	public void removeAllMessages(){
		for(Enumeration<ControlDecorator> enm = decorators.elements();enm.hasMoreElements();){
			ControlDecorator control = enm.nextElement();
			control.removeMessages();
		}
		if(!messages.isEmpty()){
			messages.clear();
		}
	}
	private Message addMessage(String prefix,Object key,String messageText,Object data,int type,List<Message> list){
		Message message = findMessage(key, list);
		if(message == null){
			message = new Message(key, messageText, type, data);
			message.prefix = prefix;
			list.add(message);
		}else{
			message.message = messageText;
			message.type = type;
			message.data = data;
		}
		return message;
	}
	private Message findMessage(Object key,List<Message> list){
		for(int i = 0;i < list.size();i++){
			Message message = list.get(i);
			if(message.getKey().equals(key)){
				return message;
			}
		}
		return null;
	}
	public void update(){
		// Update decorations
		for(ControlDecorator dec:decorators.values()){
			dec.update();
		}
	}
	private static String getFullMessage(IMessage message){
		if(message.getPrefix() == null){
			return message.getMessage();
		}
		return message.getPrefix() + message.getMessage();
	}
	private List<Message> createPeers(List<Message> msg){
		List<Message> peers = new ArrayList<Message>();
		int maxType = 0;
		for(int i = 0;i < msg.size();i++){
			Message message = msg.get(i);
			if(message.type > maxType){
				peers.clear();
				maxType = message.type;
			}
			if(message.type == maxType){
				peers.add(message);
			}
		}
		return peers;
	}
	private String createDetails(List<Message> msg,boolean excludePrefix){
		StringWriter sw = new StringWriter();
		PrintWriter out = new PrintWriter(sw);
		for(int i = 0;i < msg.size();i++){
			if(i > 0){
				out.println();
			}
			IMessage m = msg.get(i);
			out.print(excludePrefix ? m.getMessage() : getFullMessage(m));
		}
		out.flush();
		return sw.toString();
	}
	public static String createDetails(IMessage[] msg){
		if(msg == null || msg.length == 0){
			return null;
		}
		StringWriter sw = new StringWriter();
		PrintWriter out = new PrintWriter(sw);
		for(int i = 0;i < msg.length;i++){
			if(i > 0){
				out.println();
			}
			out.print(getFullMessage(msg[i]));
		}
		out.flush();
		return sw.toString();
	}
	public String createSummary(IMessage[] msg){
		return createDetails(msg);
	}
	public IMessagePrefixProvider getMessagePrefixProvider(){
		return prefixProvider;
	}
	public void setMessagePrefixProvider(IMessagePrefixProvider provider){
		this.prefixProvider = provider;
		for(ControlDecorator dec:decorators.values()){
			dec.updatePrefix();
		}
	}
	public int getDecorationPosition(){
		return decorationPosition;
	}
	public void setDecorationPosition(int position){
		this.decorationPosition = position;
		for(ControlDecorator dec:decorators.values()){
			dec.updatePosition();
		}
	}
	public boolean isAutoUpdate(){
		return autoUpdate;
	}
	public void setAutoUpdate(boolean autoUpdate){
		boolean needsUpdate = !this.autoUpdate && autoUpdate;
		this.autoUpdate = autoUpdate;
		if(needsUpdate){
			update();
		}
	}
}
