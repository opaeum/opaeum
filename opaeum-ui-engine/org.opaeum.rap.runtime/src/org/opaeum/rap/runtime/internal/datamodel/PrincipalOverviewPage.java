// Created on 30.09.2007
package org.opaeum.rap.runtime.internal.datamodel;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.opaeum.rap.runtime.internal.Activator;
import org.opaeum.rap.runtime.internal.RMSMessages;
import org.opaeum.rap.runtime.internal.datamodel.PageUtil.Container;
import org.opaeum.rap.runtime.internal.datamodel.PageUtil.EMailValidator;
import org.opaeum.rap.runtime.internal.datamodel.PageUtil.PhoneNumberValidator;


public class PrincipalOverviewPage extends FormPage {
  private static final String OVERVIEW = "Overview"; //$NON-NLS-1$
  private final PrincipalCopy principal;
  
  private PhoneNumberValidator faxValidator;
  private PhoneNumberValidator mobileValidator;
  private PhoneNumberValidator phoneValidator;
  private EMailValidator eMailValidator;
  private Text txtPhone;
  private Text txtMobile;
  private Text txtFax;
  private Text txtEMail;
  
  public PrincipalOverviewPage( final FormEditor editor,
                                final PrincipalCopy principal ) {
    super( editor, OVERVIEW, RMSMessages.get().PrincipalOverviewPage_Title );
    this.principal = principal;
    this.principal.setDirtyNotificator( new Runnable() {
      public void run() {
        PrincipalOverviewPage.this.firePropertyChange( PROP_DIRTY );
      }
    } );
  }

  public void init( final IEditorSite site, final IEditorInput input ) {
    super.init( site, input );
    setTitleToolTip( RMSMessages.get().PrincipalOverviewPage_ToolTip );
  }
  
  protected void createFormContent( final IManagedForm managedForm ) {
    ScrolledForm scrolledForm = managedForm.getForm();
    FormToolkit toolkit = managedForm.getToolkit();
    Composite body
      = PageUtil.createBody( scrolledForm, Activator.IMG_FORM_HEAD_OVERVIEW );
    DataBindingContext ctx = PageUtil.createBindingContext();
    
    // general info section
    Composite info
      = PageUtil.createGeneralInfoSection( scrolledForm, 
                                           toolkit, 
                                           body, 
                                           principal.getName() );
    Container cInfo = new Container( toolkit, info );
    PageUtil.createLabelText( cInfo, RMSMessages.get().PrincipalOverviewPage_Name, principal.getName(), true );
    
    Text txtStreet = PageUtil.createLabelText( cInfo,
                                               RMSMessages.get().PrincipalOverviewPage_Street, 
                                               principal.getStreet(),
                                               false );
    PageUtil.bindText( ctx, principal, txtStreet, PrincipalCopy.STREET );
    Text txtCity = PageUtil.createLabelText( cInfo,
                                             RMSMessages.get().PrincipalOverviewPage_City,
                                             principal.getCity(),
                                             false );
    PageUtil.bindText( ctx, principal, txtCity, PrincipalCopy.CITY );
    Text txtPostCode = PageUtil.createLabelText( cInfo,
                                                 RMSMessages.get().PrincipalOverviewPage_ZIPPostalCode,
                                                 principal.getPostCode(),
                                                 false );
    PageUtil.bindText( ctx, principal, txtPostCode, PrincipalCopy.POST_CODE );
    CCombo cboCountry = PageUtil.createLabelCombo( cInfo, 
                                                  RMSMessages.get().PrincipalOverviewPage_Country, 
                                                  principal.getCountry(),
                                                  Activator.COUNTRIES );
    PageUtil.bindCombo( ctx, principal, cboCountry, PrincipalCopy.COUNTRY );

    // contact person section
    Composite contact
      = PageUtil.createSection( scrolledForm, 
                                toolkit, 
                                body, 
                                RMSMessages.get().PrincipalOverviewPage_ContactPerson, 
                                RMSMessages.get().PrincipalOverviewPage_EditContactInfo, 
                                3,
                                false );
    Container cContact = new Container( toolkit, contact );
    Text txtLastName = PageUtil.createLabelText( cContact,
                                                 RMSMessages.get().PrincipalOverviewPage_Lastname,
                                                 principal.getLastName(),
                                                 false );
    PageUtil.bindText( ctx, principal, txtLastName, PrincipalCopy.LAST_NAME );
    Text txtFirstName = PageUtil.createLabelText( cContact, 
                                                  RMSMessages.get().PrincipalOverviewPage_Firstname, 
                                                  principal.getFirstName(),
                                                  false );
    PageUtil.bindText( ctx, principal, txtFirstName, PrincipalCopy.FIRST_NAME );
    txtEMail = PageUtil.createLabelText( cContact,
                                         RMSMessages.get().PrincipalOverviewPage_EMail,
                                         principal.getEMail(),
                                         false );
    eMailValidator = new EMailValidator( txtEMail );
    PageUtil.bindText( ctx,
                       principal,
                       txtEMail,
                       PrincipalCopy.EMAIL,
                       eMailValidator );
    txtPhone = PageUtil.createLabelText( cContact, 
                                         RMSMessages.get().PrincipalOverviewPage_Phone,
                                         principal.getPhoneNumber(),
                                         false );
    phoneValidator = new PhoneNumberValidator( txtPhone );
    PageUtil.bindText( ctx,
                       principal,
                       txtPhone,
                       PrincipalCopy.PHONE_NUMBER,
                       phoneValidator );
    txtMobile = PageUtil.createLabelText( cContact,
                                          RMSMessages.get().PrincipalOverviewPage_Mobile,
                                          principal.getMobileNumber(),
                                          false );
    mobileValidator = new PhoneNumberValidator( txtMobile );
    PageUtil.bindText( ctx,
                       principal,
                       txtMobile,
                       PrincipalCopy.MOBILE_NUMBER,
                       mobileValidator );
    txtFax = PageUtil.createLabelText( cContact,
                                       RMSMessages.get().PrincipalOverviewPage_Fax,
                                       principal.getFaxNumber(),
                                       false );
    faxValidator = new PhoneNumberValidator( txtFax );
    PageUtil.bindText( ctx,
                       principal,
                       txtFax,
                       PrincipalCopy.FAX_NUMBER,
                       faxValidator );
  }

  @Override
  public boolean isDirty() {
    return principal.isDirty();
  }
  
  @Override
  public void doSave( final IProgressMonitor monitor ) {
    if(    faxValidator.validate( txtFax.getText() ).isOK()
        && mobileValidator.validate( txtMobile.getText() ).isOK()
        && phoneValidator.validate( txtPhone.getText() ).isOK()
        && eMailValidator.validate( txtEMail.getText() ).isOK() )
    {
      principal.save();
    } else {
      //TODO : [yao]NLS#
              
      Object[] param = new Object[] { principal.getName() };     
      String msg
        = NLS.bind( RMSMessages.get().PrincipalOverviewPage_CouldNotChange,
                                param );
      MessageDialog.openInformation( getSite().getShell(),
                                     RMSMessages.get().PrincipalOverviewPage_SaveAborted, 
                                     msg );
    }
  }
}
