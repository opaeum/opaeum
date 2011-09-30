@TypeDef(name="ProcessRequestStateResolver",typeClass=ProcessRequestStateResolver.class)
@TypeDefs(value={@TypeDef(name="TaskRequestStateResolver",typeClass=TaskRequestStateResolver.class),@TypeDef(name="AbstractRequestStateResolver",typeClass=AbstractRequestStateResolver.class),@TypeDef(name="ProcessRequestStateResolver",typeClass=ProcessRequestStateResolver.class)})
@AnyMetaDefs({@AnyMetaDef(idType="long",metaValues={},name="OperationProcessObject",metaType="string"),@AnyMetaDef(idType="long",metaValues={},name="TaskObject",metaType="string"),@AnyMetaDef(idType="long",metaValues={},name="Participant",metaType="string"),@AnyMetaDef(idType="long",metaValues={},name="BusinessRole",metaType="string"),@AnyMetaDef(idType="long",metaValues={},name="RequestObject",metaType="string"),@AnyMetaDef(idType="long",metaValues={},name="BusinessComponent",metaType="string")})
@FilterDef(defaultCondition="deleted_on > current_timestamp",name="noDeletedObjects")
@NamedQueries(value=@NamedQuery(query="select processInstanceInfo.processInstanceId from ProcessInstanceInfo processInstanceInfo where :type in elements(processInstanceInfo.eventTypes)",name="ProcessInstancesWaitingForEvent"))
package org.opeum.runtime.bpm.util;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.hibernate.annotations.AnyMetaDef;
import org.hibernate.annotations.AnyMetaDefs;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import org.opeum.runtime.bpm.AbstractRequestStateResolver;
import org.opeum.runtime.bpm.ProcessRequestStateResolver;
import org.opeum.runtime.bpm.TaskRequestStateResolver;

