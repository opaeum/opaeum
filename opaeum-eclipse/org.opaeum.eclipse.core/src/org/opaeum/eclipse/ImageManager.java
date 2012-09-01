package org.opaeum.eclipse;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;

public class ImageManager{
	private static final String DEFAULT_IMAGE = "resources/icons/default.gif";
	public static final String IMAGE_PATH = "resources/icons/";
	public static final Image IMG_CHECKED = getImage(IMAGE_PATH + "checked.gif");
	public static final Image IMG_UNCHECKED = getImage(IMAGE_PATH + "unchecked.gif");
	public static final Image IMG_ADD = getImage(IMAGE_PATH + "Add.gif");
	public static final Image IMG_ADDREG = getImage(IMAGE_PATH + "AddReg.gif");
	public static final Image IMG_DELETE = getImage(IMAGE_PATH + "Delete.gif");
	public static final Image IMG_UP = getImage(IMAGE_PATH + "ArrowUp.gif");
	public static final Image IMG_DOWN = getImage(IMAGE_PATH + "ArrowDown.gif");
	public static final Image IMG_LEFT = getImage(IMAGE_PATH + "ArrowLeft.gif");
	public static final Image IMG_RIGHT = getImage(IMAGE_PATH + "ArrowRight.gif");
	public static final Image IMG_PROPERTY = getImage(IMAGE_PATH + "Property.gif");
	public static final Image IMG_STEREOTYPE = getImage(IMAGE_PATH + "Stereotype.gif");
	public static final Image IMG_LITERALBOOLEAN = getImage(IMAGE_PATH + "LiteralBoolean.gif");
	public static final Image IMG_LITERALSTRING = getImage(IMAGE_PATH + "LiteralString.gif");
	public static final Image IMG_LITERALINTEGER = getImage(IMAGE_PATH + "LiteralInteger.gif");
	public static final Image IMG_LITERALUNLIMITEDNATURAL = getImage(IMAGE_PATH + "LiteralUnlimitedNatural.gif");
	public static final Image IMG_ENUMERATION = getImage(IMAGE_PATH + "Enumeration.gif");
	public static final Image IMG_DATATYPE = getImage(IMAGE_PATH + "DataType.gif");
	public static final Image IMG_DISPLAY = getImage(IMAGE_PATH + "ConsoleView.gif");
	public static final Image IMG_STEREOTYPEPROPERTY = getImage(IMAGE_PATH + "Substitution.gif");
	public static final Image IMG_METACLASS = getImage(IMAGE_PATH + "Manifestation.gif");
	public static final Image IMG_UNKNOWN = getImage(IMAGE_PATH + "Clause.gif");
	public static final Image IMG_PRIMITIVETYPE = getImage(IMAGE_PATH + "PrimitiveType.gif");
	public static final Image IMG_STEREOTYPEDISPLAYED = getImage(IMAGE_PATH + "DisplayedStereotype_16x16.gif");
	public static final Image IMG_DISPLAYEDPROPERTY = getImage(IMAGE_PATH + "DisplayedProperty.gif");
	public static final Image IMG_PACKAGE = getImage(IMAGE_PATH + "Package.gif");
	public static final Image IMG_PROFILE = getImage(IMAGE_PATH + "Profile.gif");
	public static final Image IMG_CLASS = getImage(IMAGE_PATH + "Class.gif");
	public static final Image IMG_INSTANCESPEC = getImage(IMAGE_PATH + "InstanceSpecification.gif");
	public static final Image IMG_ASSOCIATION = getImage(IMAGE_PATH + "Association.gif");;
	public static final Image IMG_CONSOLEVIEW_WITH_QN = getImage(IMAGE_PATH + "ConsoleViewQN.gif");
	public static final Image DISPLAYED_STEREOTYPE_QN = getImage(IMAGE_PATH + "DisplayedStereotypeQN.gif");
	public static Image getImage(String key){
		ImageRegistry registry = OpaeumEclipsePlugin.getDefault().getImageRegistry();
		Image image = registry.get(key);
		if(image == null){
			ImageDescriptor desc = AbstractUIPlugin.imageDescriptorFromPlugin(OpaeumEclipsePlugin.PLUGIN_ID, key);
			registry.put(key, desc);
			image = registry.get(key);
		}
		if((image == null) && !key.equals(DEFAULT_IMAGE)){
			image = getImage(DEFAULT_IMAGE);
		}
		return image;
	}
}
