package org.opaeum.organization.businesscalendar;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Transient;

import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.organization.util.OpaeumLibraryForBPMFormatter;
import org.opaeum.runtime.domain.CancelledEvent;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.runtime.domain.IEventGenerator;
import org.opaeum.runtime.domain.IntrospectionUtil;
import org.opaeum.runtime.domain.OutgoingEvent;
import org.opaeum.runtime.environment.Environment;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@NumlMetaInfo(uuid="252060@_x9fmQNb9EeCJ0dmaHEVVnw")
public class BusinessCalendar extends BusinessCalendarGenerated {
	/** Default constructor for BusinessCalendar
	 */
	public BusinessCalendar() {
	}

}