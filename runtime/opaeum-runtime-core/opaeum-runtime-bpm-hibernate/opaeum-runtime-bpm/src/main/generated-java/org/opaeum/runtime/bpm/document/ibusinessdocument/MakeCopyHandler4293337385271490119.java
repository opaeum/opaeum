package org.opaeum.runtime.bpm.document.ibusinessdocument;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.opaeum.annotation.PropertyMetaInfo;
import org.opaeum.runtime.bpm.document.IBusinessDocument;
import org.opaeum.runtime.environment.marshall.PropertyValue;
import org.opaeum.runtime.environment.marshall.Value;
import org.opaeum.runtime.event.ICallEventHandler;
import org.opaeum.runtime.persistence.AbstractPersistence;

public class MakeCopyHandler4293337385271490119 implements ICallEventHandler {
	private Date firstOccurrenceScheduledFor;
	private boolean isEvent;
	private IBusinessDocument result;

	/** Constructor for MakeCopyHandler4293337385271490119
	 * 
	 * @param isEvent 
	 */
	public MakeCopyHandler4293337385271490119(boolean isEvent) {
		this.firstOccurrenceScheduledFor=new Date(System.currentTimeMillis()+1000);
		this.isEvent=isEvent;
	}
	
	/** Default constructor for MakeCopyHandler4293337385271490119
	 */
	public MakeCopyHandler4293337385271490119() {
	}

	public int getConsumerPoolSize() {
		return 5;
	}
	
	public Date getFirstOccurrenceScheduledFor() {
		return this.firstOccurrenceScheduledFor;
	}
	
	public String getHandlerUuid() {
		return "252060@_nx6xcF9lEeG3X_yvufTVmw";
	}
	
	public boolean getIsEvent() {
		return this.isEvent;
	}
	
	public String getQueueName() {
		return "OpaeumLibraryForBPM::document::IBusinessDocument::makeCopy";
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=476776289867994663l,uuid="252060@_qe-NkF9lEeG3X_yvufTVmw")
	public IBusinessDocument getResult() {
		return this.result;
	}
	
	public boolean handleOn(Object t) {
		IBusinessDocument target = (IBusinessDocument)t;
		if ( isEvent ) {
			return target.consumeMakeCopyOccurrence();
		} else {
			setResult(target.makeCopy());
			return true;
		}
	}
	
	public Collection<PropertyValue> marshall() {
		Collection<PropertyValue> result = new ArrayList<PropertyValue>();
		result.add(new PropertyValue(-6l, Value.valueOf(isEvent)));
		result.add(new PropertyValue(350484219729487557l, Value.valueOf(this.getResult())));
		return result;
	}
	
	public Date scheduleNextOccurrence() {
		return new Date(System.currentTimeMillis() + 1000*60*60*24*10);
	}
	
	public void setIsEvent(boolean isEvent) {
		this.isEvent=isEvent;
	}
	
	public void setResult(IBusinessDocument result) {
		this.result=result;
	}
	
	public void unmarshall(Collection<PropertyValue> ps, AbstractPersistence persistence) {
		for ( PropertyValue p : ps ) {
			if ( p.getId()==350484219729487557l ) {
				this.setResult((IBusinessDocument)Value.valueOf(p.getValue(),persistence));
			} else {
			
			}
			if ( p.getId()==-6l ) {
				this.isEvent=(Boolean)Value.valueOf(p.getValue(),persistence);
			}
		}
	}

}