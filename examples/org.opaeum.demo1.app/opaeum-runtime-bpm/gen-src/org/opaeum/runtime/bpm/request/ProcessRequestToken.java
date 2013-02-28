package org.opaeum.runtime.bpm.request;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.Table;

import org.opaeum.annotation.NumlMetaInfo;

@NumlMetaInfo(uuid="252060@_ciiWAI2-EeCrtavWRHwoHg")
@Table(name="process_request_token",schema="bpm")
@Inheritance(strategy=javax.persistence.InheritanceType.SINGLE_TABLE)
@Entity(name="ProcessRequestToken")
@DiscriminatorValue(	"252060@_ciiWAI2-EeCrtavWRHwoHg")
@DiscriminatorColumn(discriminatorType=javax.persistence.DiscriminatorType.STRING)
public class ProcessRequestToken<SME extends ProcessRequest> extends AbstractRequestToken<SME> {


	/** Constructor for ProcessRequestToken
	 * 
	 * @param parentToken 
	 */
	public ProcessRequestToken(ProcessRequestToken parentToken) {
	super(parentToken);
	}
	
	/** Constructor for ProcessRequestToken
	 */
	public ProcessRequestToken() {
	}


}