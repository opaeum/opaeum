package net.sf.nakeduml.obsolete.uimetamodel;

import java.io.Serializable;

import org.nakeduml.annotation.UserInteractionKind;


public class UIMState  implements Serializable{
	private static final long serialVersionUID = -3934535676949392268L;
	private String userInteraction;
	private SecureUserAction securityOnEdit = new SecureUserAction();
	private SecureUserAction securityOnView = new SecureUserAction();
	private String javaName;
	public final String getJavaName(){
		return this.javaName;
	}
	public final String getUserInteraction(){
		return this.userInteraction;
	}
	public UserInteractionKind getUserInteractionKind(){
		return UserInteractionKind.valueOf(this.javaName);
	}
	public final void setJavaName(String javaName){
		this.javaName = javaName;
	}
	public final void setUserInteraction(String userInteraction){
		this.userInteraction = userInteraction;
	}
	/**
	 * 
	 */
	public SecureUserAction getSecurityOnEdit(){
		return this.securityOnEdit;
	}
	public SecureUserAction getSecurityOnView(){
		return this.securityOnView;
	}
}
