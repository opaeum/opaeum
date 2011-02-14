package org.nakeduml.persistence;

import net.sf.nakeduml.seam3.persistence.ManagedHibernateSessionProvider;
import datagenerationtest.org.nakeduml.AbstractComponent;
import datagenerationtest.org.nakeduml.AbstractFolder;
import datagenerationtest.org.nakeduml.Canvas;
import datagenerationtest.org.nakeduml.CanvasDataGenerator;
import datagenerationtest.org.nakeduml.Component;
import datagenerationtest.org.nakeduml.ComponentDataGenerator;
import datagenerationtest.org.nakeduml.Finger;
import datagenerationtest.org.nakeduml.FingerDataGenerator;
import datagenerationtest.org.nakeduml.Folder;
import datagenerationtest.org.nakeduml.FolderDataGenerator;
import datagenerationtest.org.nakeduml.God;
import datagenerationtest.org.nakeduml.GodDataGenerator;
import datagenerationtest.org.nakeduml.GodFolder;
import datagenerationtest.org.nakeduml.GodFolderDataGenerator;
import datagenerationtest.org.nakeduml.Hand;
import datagenerationtest.org.nakeduml.HandDataGenerator;
import datagenerationtest.org.nakeduml.Module;
import datagenerationtest.org.nakeduml.Ring;
import datagenerationtest.org.nakeduml.RingDataGenerator;
import datagenerationtest.org.nakeduml.Subject;
import datagenerationtest.org.nakeduml.SubjectDataGenerator;
import datagenerationtest.org.nakeduml.SubjectFolder;
import datagenerationtest.org.nakeduml.SubjectFolderDataGenerator;
import datagenerationtest.org.nakeduml.UI;
import datagenerationtest.org.nakeduml.UIDataGenerator;
import datagenerationtest.org.nakeduml.User;
import datagenerationtest.org.nakeduml.UserDataGenerator;
import datagenerationtest.org.nakeduml.UserGroup;
import datagenerationtest.org.nakeduml.UserGroupDataGenerator;
import datagenerationtest.org.nakeduml.UserGroupModulePermission;
import datagenerationtest.org.nakeduml.UserGroupModulePermissionDataGenerator;
import datagenerationtest.util.FailedConstraintsException;
import datagenerationtest.util.InvariantError;
import datagenerationtest.util.InvariantException;
import datagenerationtest.util.Stdlib;


public abstract class BaseTest {

	public static Class<?>[] getTestClasses() {
		return new Class[] { UserUserGroupManyToManyTest.class, BaseTest.class, Stdlib.class, FailedConstraintsException.class, InvariantException.class, InvariantError.class, StartUpLoadData.class,
				GodDataGenerator.class, God.class, GodFolderDataGenerator.class, GodFolder.class, AbstractFolder.class, Folder.class,
				FolderDataGenerator.class, Subject.class, SubjectDataGenerator.class, SubjectFolder.class, SubjectFolderDataGenerator.class, Canvas.class,
				CanvasDataGenerator.class, Component.class, ComponentDataGenerator.class, AbstractComponent.class, UI.class, Hand.class,
				HandDataGenerator.class, User.class, UserDataGenerator.class, UserGroup.class, UserGroupDataGenerator.class, UserGroupModulePermission.class, UserGroupModulePermissionDataGenerator.class,  Finger.class, FingerDataGenerator.class, Ring.class, RingDataGenerator.class, UIDataGenerator.class, Module.class,
				ManagedHibernateSessionProvider.class };
	}

}
