package org.opaeum.uim.swt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabFolder2Adapter;
import org.eclipse.swt.custom.CTabFolderEvent;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class Snippet165 {

  public static void main(String[] args) {
  	Display display = new Display ();
  	final Shell shell = new Shell (display);
  	shell.setLayout(new GridLayout());
  	final ScrolledComposite sc = new ScrolledComposite(shell, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
  	sc.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
  	Composite c = new Composite(sc, SWT.NONE);
  	c.setLayout(new GridLayout(10, true));
  	for (int i = 0 ; i < 15; i++) {
  		Button b = new Button(c, SWT.PUSH);
  		b.setText("Button "+i);
  	}
  	sc.setContent(c);
  	sc.setExpandHorizontal(true);
  	sc.setExpandVertical(true);
  	sc.setShowFocusedControl(true);
    Link l = new Link(c, SWT.UNDERLINE_LINK);
		l.setText("This is a link to <a href=\"www.google.com\">Google</a>");
		l.addSelectionListener(new SelectionListener(){
			@Override
			public void widgetSelected(SelectionEvent e){
				new MessageBox(shell).open();
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent e){
				// TODO Auto-generated method stub
			}
		});
    final CTabFolder folder = new CTabFolder(c, SWT.BORDER);
    GridData gd=new GridData(SWT.FILL, SWT.FILL, true, false);
    folder.setLayoutData(gd);
    folder.setUnselectedImageVisible(false);
    folder.setUnselectedCloseVisible(false);
    for (int i = 0; i < 8; i++) {
      CTabItem item = new CTabItem(folder, SWT.CLOSE);
      item.setText("Item " + i);
      Text text = new Text(folder, SWT.MULTI | SWT.V_SCROLL
          | SWT.H_SCROLL);
      text.setText("Text for item " + i
          + "\n\none, two, three\n\nabcdefghijklmnop");
      item.setControl(text);
    }
    folder.setMinimizeVisible(true);
    folder.setMaximizeVisible(true);
    folder.addCTabFolder2Listener(new CTabFolder2Adapter() {
      public void minimize(CTabFolderEvent event) {
        folder.setMinimized(true);
        shell.layout(true);
      }

      public void maximize(CTabFolderEvent event) {
        folder.setMaximized(true);
        folder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,
            true));
        shell.layout(true);
      }

      public void restore(CTabFolderEvent event) {
        folder.setMinimized(false);
        folder.setMaximized(false);
        folder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,
            false));
        shell.layout(true);
      }
    });
  	sc.setMinSize(c.computeSize(SWT.DEFAULT, SWT.DEFAULT));

    shell.setSize(300, 300);
    shell.layout();
    shell.open();
    while (!shell.isDisposed()) {
      if (!display.readAndDispatch())
        display.sleep();
    }
    display.dispose();
  }
}
