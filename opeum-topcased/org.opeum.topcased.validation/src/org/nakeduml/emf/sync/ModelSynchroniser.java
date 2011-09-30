package org.opeum.emf.sync;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.opeum.feature.OpeumConfig;
import org.opeum.feature.WorkspaceMappingInfo;
import org.opeum.metamodel.validation.ErrorMap;
import org.opeum.metamodel.workspace.INakedModelWorkspace;
import org.opeum.metamodel.workspace.internal.NakedModelWorkspaceImpl;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.transaction.NotificationFilter;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.ResourceSetListener;
import org.eclipse.emf.transaction.RollbackException;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Model;

public class ModelSynchroniser implements ResourceSetListener {
	private static Map<Model, INakedModelWorkspace> upToDateWorkspaces = new HashMap<Model, INakedModelWorkspace>();
	private static Map<Model, INakedModelWorkspace> outOfSyncWorkspaces = new HashMap<Model, INakedModelWorkspace>();
	private static Set<Model> subscriptions = new HashSet<Model>();

	public static ErrorMap getErrorMap(Model m, OpeumConfig config) {
		return getWorkspace(m, config).getErrorMap();
	}

	public static INakedModelWorkspace getWorkspace(Model model, OpeumConfig cfg) {
		INakedModelWorkspace result = upToDateWorkspaces.get(model);
		if (result == null && model != null) {
			result = outOfSyncWorkspaces.remove(model);
			if (result == null) {
				result = new NakedModelWorkspaceImpl();
				WorkspaceMappingInfo mi = new WorkspaceMappingInfo(new File("c:/temp/model.vim"));
				result.setWorkspaceMappingInfo(mi);
			}
			//Do stuf here
			upToDateWorkspaces.put(model, result);
			if (!subscriptions.contains(model)) {
				TransactionalEditingDomain editingDomain = ((TransactionalEditingDomain) AdapterFactoryEditingDomain
						.getEditingDomainFor(model));
				editingDomain.addResourceSetListener(new ModelSynchroniser());
				subscriptions.add(model);
			}
		}
		return result;
	}

	public Command transactionAboutToCommit(ResourceSetChangeEvent event) throws RollbackException {
		return null;
	}

	@SuppressWarnings( { "unchecked", "deprecation" })
	public void resourceSetChanged(ResourceSetChangeEvent event) {
		for (Notification notification : event.getNotifications()) {
			if (notification.getNotifier() instanceof Element) {
				Element source = (Element) notification.getNotifier();
				if (source.getModel() != null) {
					switch (notification.getEventType()) {
						case Notification.ADD:
						case Notification.ADD_MANY:
						case Notification.CREATE:
						case Notification.MOVE:
						case Notification.REMOVE:
						case Notification.REMOVE_MANY:
						case Notification.REMOVING_ADAPTER:
						case Notification.RESOLVE:
						case Notification.SET:
						case Notification.UNSET:
							outOfSyncWorkspaces.put(source.getModel(), upToDateWorkspaces.remove(source.getModel()));
							break;
						default:
							System.out.println("Unknown Event Type:" + notification.getEventType());
					}
				}
			}
		}
	}

	public boolean isPrecommitOnly() {
		return false;
	}

	public boolean isPostcommitOnly() {
		return false;
	}

	public boolean isAggregatePrecommitListener() {
		return false;
	}

	public NotificationFilter getFilter() {
		return null;
	}
}
