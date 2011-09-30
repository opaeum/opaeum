package org.opeum.tinker;

import java.io.Serializable;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;

import org.tinker.God;

@ConversationScoped
@Named("tinkerQuery")
public class TinkerQuery implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public String doStuff() {
		System.out.println("doing stuff man");
		God god = new God();
		god.setName("didthiswork");
		return "stuff";
	}
	
}
