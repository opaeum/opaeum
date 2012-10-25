package org.opaeum.eclipse;

import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.opaeum.feature.OpaeumConfig;
import org.opaeum.runtime.environment.VersionNumber;

public class VersionText extends Composite{
	private Text text;
	private VersionNumber version;
	public VersionText(Composite parent,int style){
		super(parent, style);
		StackLayout layout = new StackLayout();
		setLayout(layout);
		text = new Text(this, style);
		layout.topControl=text;
		text.addKeyListener(new KeyListener(){
			@Override
			public void keyReleased(KeyEvent e){
				version.parse(text.getText());
			}
			@Override
			public void keyPressed(KeyEvent e){
				if(!Character.isISOControl(e.character)){
					if(e.character == '.'){
						if(text.getCaretPosition() == 0 || text.getText().charAt(text.getCaretPosition() - 1) == '.' || text.getText().split("\\.").length > 2){
							e.doit = false;
						}
					}else if(e.character < '0' || e.character > '9'){
						e.doit = false;
					}
				}
			}
		});
	}
	public VersionNumber getVersion(){
		VersionNumber vn = new VersionNumber();
		vn.parse(text.getText());
		return vn;
	}
	public void setVersion(VersionNumber version){
		this.version = version;
		text.setText(version.toVersionString());
	}
	public void setEditable(boolean b){
		text.setEnabled(b);
		
	}
	public boolean isValid(){
		return OpaeumConfig.isValidVersionNumber(text.getText());
	}
	public String getText(){
		return text.getText();
	}
}
