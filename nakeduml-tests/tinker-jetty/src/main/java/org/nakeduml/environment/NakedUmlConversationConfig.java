package org.nakeduml.environment;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Specializes;

import org.apache.myfaces.extensions.cdi.core.api.config.CodiConfig;
import org.apache.myfaces.extensions.cdi.core.api.config.ConfigEntry;
import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.config.ConversationConfig;

@Alternative
@ApplicationScoped
@Specializes
public class NakedUmlConversationConfig extends ConversationConfig implements CodiConfig {

    private static final long serialVersionUID = -1637900766842152725L;

    protected NakedUmlConversationConfig()
    {
    }

    /**
     * Timeout for {@link org.apache.myfaces.extensions.cdi.core.api.scope.conversation.ConversationScoped} beans,
     * which will be used if the conversation doesn't get closed manually.
     * 
     * @return timeout in minutes
     */
    @ConfigEntry
    public int getConversationTimeoutInMinutes()
    {
        return 30;
    }

    /*
     * event config
     */

    /**
     * Specifies if the
     * {@link org.apache.myfaces.extensions.cdi.core.api.scope.conversation.event.ScopeBeanEvent}
     * will be fired.
     *
     * @return true if the event should be fired, false otherwise
     */
    @ConfigEntry
    public boolean isScopeBeanEventEnabled()
    {
        return true;
    }

    /**
     * Specifies if the
     * {@link org.apache.myfaces.extensions.cdi.core.api.scope.conversation.event.AccessBeanEvent}
     * will be fired.
     *
     * @return true if the event should be fired, false otherwise
     */
    @ConfigEntry
    public boolean isAccessBeanEventEnabled()
    {
        return true;
    }

    /**
     * Specifies if the
     * {@link org.apache.myfaces.extensions.cdi.core.api.scope.conversation.event.UnscopeBeanEvent}
     * will be fired.
     *
     * @return true if the event should be fired, false otherwise
     */
    @ConfigEntry
    public boolean isUnscopeBeanEventEnabled()
    {
        return true;
    }

    /**
     * Specifies if the
     * {@link org.apache.myfaces.extensions.cdi.core.api.scope.conversation.event.StartConversationEvent}
     * will be fired.
     *
     * @return true if the event should be fired, false otherwise
     */
    @ConfigEntry
    public boolean isStartConversationEventEnabled()
    {
        return true;
    }

    /**
     * Specifies if the
     * {@link org.apache.myfaces.extensions.cdi.core.api.scope.conversation.event.CloseConversationEvent}
     * will be fired.
     *
     * @return true if the event should be fired, false otherwise
     */
    @ConfigEntry
    public boolean isCloseConversationEventEnabled()
    {
        return true;
    }
	
}
