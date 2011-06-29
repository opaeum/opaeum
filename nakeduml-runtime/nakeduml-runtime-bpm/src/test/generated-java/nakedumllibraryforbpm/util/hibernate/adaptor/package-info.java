@AnyMetaDefs({@AnyMetaDef(idType="long",metaValues={},name="Participant",metaType="string"),@AnyMetaDef(idType="long",metaValues={},name="BusinessRole",metaType="string"),@AnyMetaDef(idType="long",metaValues={},name="BusinessComponent",metaType="string"),@AnyMetaDef(idType="long",metaValues={},name="RequestObject",metaType="string"),@AnyMetaDef(idType="long",metaValues={},name="OperationProcessObject",metaType="string"),@AnyMetaDef(idType="long",metaValues={},name="TaskObject",metaType="string"),@AnyMetaDef(idType="long",metaValues={},name="TaskObjectMetaDef",metaType="string"),@AnyMetaDef(idType="long",metaValues={},name="OperationProcessMetaDef",metaType="string"),@AnyMetaDef(idType="long",metaValues={},name="ParticipantMetaDef",metaType="string")})
@NamedQueries(value=@NamedQuery(query="select processInstanceInfo.processInstanceId from ProcessInstanceInfo processInstanceInfo where :type in elements(processInstanceInfo.eventTypes)",name="ProcessInstancesWaitingForEvent"))
@FilterDef(defaultCondition="deleted_on > current_timestamp",name="noDeletedObjects")
package nakedumllibraryforbpm.util.hibernate.adaptor;
import javax.persistence.NamedQuery;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.AnyMetaDef;
import org.hibernate.annotations.AnyMetaDefs;
import javax.persistence.NamedQueries;
