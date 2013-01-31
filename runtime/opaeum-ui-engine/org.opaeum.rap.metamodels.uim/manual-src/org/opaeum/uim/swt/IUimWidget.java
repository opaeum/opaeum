package org.opaeum.uim.swt;

import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.widgets.Composite;

public interface IUimWidget{
	void markForShot();

	boolean isDisposed();

	Composite getParent();

	void dispose();

	void setData(String figure,Object figure2);

	void addControlListener(ControlListener abstractEventAdapter);

	void layout();

	Object getData(String figure);

	Object getLayoutData();
}
