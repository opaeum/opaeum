// Created on 30.09.2007
package org.opaeum.rap.runtime.internal.datamodel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.BeansObservables;
import org.eclipse.core.databinding.conversion.IConverter;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.databinding.swt.ISWTObservableValue;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.rap.rms.data.IEntity;
import org.eclipse.rwt.RWT;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.events.ExpansionAdapter;
import org.eclipse.ui.forms.events.ExpansionEvent;
import org.eclipse.ui.forms.widgets.ColumnLayout;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.opaeum.rap.runtime.internal.Activator;
import org.opaeum.rap.runtime.internal.RMSMessages;


class PageUtil {
//  static final IConverter STRING_TO_DATE_CONVERTER
//    = new StringToDateConverter();
  static final IConverter DATE_TO_STRING_CONVERTER
    = new DateToStringConverter();

  private static final Status VALIDATION_SUCCESS
    = new Status( IStatus.OK, "org.opaeum.rap.runtime", "" ); //$NON-NLS-1$ //$NON-NLS-2$
  private static final Status VALIDATION_FAIL
    = new Status( IStatus.CANCEL, "org.opaeum.rap.runtime", "" ); //$NON-NLS-1$ //$NON-NLS-2$
  private static final Color COLOR_FG_SUCCESS
    = Display.getCurrent().getSystemColor( SWT.COLOR_BLACK );
  private static final Color COLOR_FG_FAIL
    = Display.getCurrent().getSystemColor( SWT.COLOR_WHITE );
  private static final Color COLOR_BG_FAIL
    = Display.getCurrent().getSystemColor( SWT.COLOR_DARK_RED );
  private static final Color COLOR_BG_SUCCESS
    = Display.getCurrent().getSystemColor( SWT.COLOR_WHITE );


  private static final class DateToStringConverter implements IConverter {
    public Object convert( final Object fromObject ) {
      Object result = null;
      if( fromObject != null ) {
        Date from = ( Date )fromObject;
        SimpleDateFormat dateFormat
          = new SimpleDateFormat( "MMM d yyyy", RWT.getLocale() );
        result = dateFormat.format( from );
      }
      return result;
    }

    public Object getFromType() {
      return Date.class;
    }

    public Object getToType() {
      return String.class;
    }
  }

  static class Container {
    final FormToolkit toolkit;
    final Composite client;

    Container( final FormToolkit toolkit, final Composite client ) {
      this.toolkit = toolkit;
      this.client = client;
    }
  }

  static abstract class Validator implements IValidator {
    final Text text;

    Validator( final Text text ) {
      this.text = text;
    }

    public IStatus validate( final Object value ) {
      IStatus result = doValidate( value );
      if( result.isOK() ) {
        text.setBackground( COLOR_BG_SUCCESS );
        text.setForeground( COLOR_FG_SUCCESS );
      } else {
        text.setBackground( COLOR_BG_FAIL );
        text.setForeground( COLOR_FG_FAIL );
      }
      return result;
    }

    abstract IStatus doValidate( final Object value );
  }

  final static class EMailValidator extends Validator {
    private final static Pattern PATTERN
      = Pattern.compile( "\\w[-._\\w]*\\w@\\w[-._\\w]*\\w\\.\\w{2,4}" ); //$NON-NLS-1$

    EMailValidator( final Text text ) {
      super( text);
    }

    @Override
    public IStatus doValidate( final Object value ) {
      IStatus result = VALIDATION_FAIL;
      if(    value == null
          || "".equals( value )  //$NON-NLS-1$
          || PATTERN.matcher( ( String )value ).matches() )
      {
        result = VALIDATION_SUCCESS;
      }
      return result;
    }
  }

  final static class PhoneNumberValidator extends Validator {
    PhoneNumberValidator( final Text text ) {
      super( text );
    }

    @Override
    IStatus doValidate( final Object value ) {
      IStatus result;
      try {
        String number = ( String )value;
        if( !"".equals( number ) ) { //$NON-NLS-1$
          if( number.startsWith( "+" ) ) { //$NON-NLS-1$
            number = number.substring( 1 );
          }
          Integer.parseInt( number );
        }
        result = VALIDATION_SUCCESS;
      } catch( final NumberFormatException e ) {
        result = VALIDATION_FAIL;
      }
      return result;
    }
  }


  private PageUtil() {
    // prevent instance creation
  }

  static Date getDate( final DateTime widget ) {
    Calendar calendar = Calendar.getInstance();
    calendar.set( widget.getYear(), widget.getMonth(), widget.getDay() );
    return calendar.getTime();
  }

  static Composite createBody( final ScrolledForm scrolledForm,
                               final String headImage )
  {
    final Composite parentBody = scrolledForm.getBody();

    final Composite header = new Composite( parentBody, SWT.NONE );
    header.setBackground( parentBody.getBackground() );
    final Label lblHeaderImage = new Label( header, SWT.NONE );
    final Image imgHeader = Activator.getDefault().getImage( headImage );
    lblHeaderImage.setImage( imgHeader );

    final Composite result = new Composite( parentBody, SWT.NONE );
    result.setBackground( parentBody.getBackground() );

    scrolledForm.addControlListener( new ControlAdapter() {
      @Override
      public void controlResized( final ControlEvent evt ) {
        Rectangle bounds = scrolledForm.getBounds();
        int headerHeight = imgHeader.getBounds().height;
        int width = bounds.width - 16;
        lblHeaderImage.setBounds( 0, 0, width, headerHeight );
        header.setBounds( 0, 0, width, headerHeight );

        int bodyHeight = bounds.height - headerHeight;
        Point size = result.getSize();
        bodyHeight = size.y > bodyHeight ? size.x : bodyHeight;
        result.setBounds( 0, headerHeight, width, bodyHeight );
      }
    } );

    ColumnLayout layout = new ColumnLayout();
    layout.topMargin = 0;
    layout.bottomMargin = 5;
    layout.leftMargin = 10;
    layout.rightMargin = 10;
    layout.horizontalSpacing = 10;
    layout.verticalSpacing = 10;
    layout.maxNumColumns = 4;
    layout.minNumColumns = 1;
    result.setLayout( layout );
    
    return result;
  }

  static Composite createGeneralInfoSection( final ScrolledForm form,
                                             final FormToolkit toolkit,
                                             final Composite body,
                                             final String entityName )
  {
    String sectionTitle = RMSMessages.get().PageUtil_GeneralInfo + entityName;
    String sectionDesc = RMSMessages.get().PageUtil_UsedToEditData;
    return createSection( form,
                          toolkit,
                          body,
                          sectionTitle,
                          sectionDesc,
                          3,
                          true );
  }

  static Composite createSection( final ScrolledForm form,
                                  final FormToolkit toolkit,
                                  final Composite body,
                                  final String title,
                                  final String desc,
                                  final int numColumns,
                                  final boolean expanded )
  {
    int style
      = ExpandableComposite.TWISTIE
      | ExpandableComposite.TITLE_BAR
      | Section.DESCRIPTION
      | ExpandableComposite.EXPANDED;
    Section section = toolkit.createSection( body, style );
    section.setExpanded( expanded );
    section.setText( title );
    section.setDescription( desc );
    Composite result = toolkit.createComposite( section );
    GridLayout layout = new GridLayout();
    layout.marginWidth = 0;
    layout.marginHeight = 0;
    layout.numColumns = numColumns;
    result.setLayout( layout );
    section.setClient( result );
    section.addExpansionListener( new ExpansionAdapter() {
      @Override
      public void expansionStateChanged( final ExpansionEvent e ) {
        form.reflow( false );
      }
    } );
    return result;
  }

  static Text createLabelText( final Container container,
                               final String labelContent,
                               final String value,
                               final boolean readOnly )
  {
    Composite client = container.client;
    FormToolkit toolkit = container.toolkit;
    Label label = toolkit.createLabel( client, labelContent );
    GridData gdLabel = new GridData();
    gdLabel.widthHint = 100;
    label.setLayoutData( gdLabel );
    int style = SWT.SINGLE | ( readOnly ? SWT.READ_ONLY : SWT.NONE );
    Text result = toolkit.createText( client, value, style );
    GridData gdResult = new GridData();
    gdResult.widthHint = 300;
    gdResult.horizontalSpan = 2;
    result.setLayoutData( gdResult );
    return result;
  }

  static CCombo createLabelCombo( final Container container,
                                  final String labelContent,
                                  final String value,
                                  final String[] items )
  {
    Composite client = container.client;
    FormToolkit toolkit = container.toolkit;
    Label label = toolkit.createLabel( client, labelContent );
    GridData gdLabel = new GridData();
    gdLabel.widthHint = 100;
    label.setLayoutData( gdLabel );
    CCombo result = new CCombo( client, SWT.READ_ONLY | SWT.BORDER );
    result.setItems( items );
    if( value != null ) {
      result.setText( value );
    }
    GridData gdResult = new GridData();
    gdResult.widthHint = 300;
    gdResult.horizontalSpan = 2;
    result.setLayoutData( gdResult );
    return result;
  }

  static Text createLabelMultiText( final Container container,
                                    final String labelContent,
                                    final String value )
  {
    Composite client = container.client;
    FormToolkit toolkit = container.toolkit;
    Label label = toolkit.createLabel( client, labelContent );
    GridData gdLabel = new GridData();
    gdLabel.widthHint = 100;
    label.setLayoutData( gdLabel );
    Text result = toolkit.createText( client, value, SWT.MULTI );
    GridData gdResult = new GridData();
    gdResult.widthHint = 300;
    gdResult.heightHint = 100;
    gdResult.horizontalSpan = 2;
    result.setLayoutData( gdResult );
    return result;
  }

  static Text createLabelTextButton( final Container container,
                                     final String labelContent,
                                     final String value,
                                     final String imageName,
                                     final SelectionListener listener )
  {
    final Composite client = container.client;
    final FormToolkit toolkit = container.toolkit;
    Label label = toolkit.createLabel( client, labelContent );
    GridData gdLabel = new GridData();
    gdLabel.widthHint = 100;
    label.setLayoutData( gdLabel );

    int style = SWT.SINGLE | SWT.READ_ONLY;
    Text result = toolkit.createText( client, value, style );
    GridData gdResult = new GridData();
    result.setLayoutData( gdResult );

    Button button
      = toolkit.createButton( client, "", SWT.PUSH | SWT.FLAT ); //$NON-NLS-1$
    Image imgDatePicker
      = Activator.getDefault().getImage( imageName );
    button.setImage( imgDatePicker );
    Point size = button.computeSize( SWT.DEFAULT, SWT.DEFAULT );
    gdResult.widthHint = 295 - size.x;
    button.addSelectionListener( listener );
    return result;
  }

  static DateTime createLabelDate( final Container container,
                                   final String labelContent,
                                   final Date date )
  {
    Composite client = container.client;
    FormToolkit toolkit = container.toolkit;
    Label label = toolkit.createLabel( client, labelContent );
    GridData gdLabel = new GridData();
    gdLabel.widthHint = 100;
    label.setLayoutData( gdLabel );
    int style = SWT.BORDER | SWT.DATE | SWT.DROP_DOWN;
    DateTime result = new DateTime( client, style );
    if( date != null ) {
      Calendar calendar = Calendar.getInstance();
      calendar.setTime( date );
      result.setDate( calendar.get( Calendar.YEAR ),
                      calendar.get( Calendar.MONTH ),
                      calendar.get( Calendar.DAY_OF_MONTH ) );
    }
    GridData gdResult = new GridData();
    gdResult.horizontalSpan = 2;
    result.setLayoutData( gdResult );
    return result;
  }

  static void bindCombo( final DataBindingContext bindingContext,
                         final IEntity entity,
                         final CCombo combo,
                         final String property )
  {
    ISWTObservableValue observeCombo
      = SWTObservables.observeSelection( combo );
    IObservableValue observeValue
      = BeansObservables.observeValue( entity, property );
    bind( bindingContext, observeCombo, observeValue, null, null );
  }

  static void bindText( final DataBindingContext bindingContext,
                        final IEntity entity,
                        final Text text,
                        final String property )
  {
    bindText( bindingContext, entity, text, property, null );
  }

  static void bindText( final DataBindingContext bindingContext,
                        final IEntity entity,
                        final Text text,
                        final String property,
                        final IValidator validator ) {
    ISWTObservableValue observeText
      = SWTObservables.observeText( text, SWT.Modify );
    IObservableValue observeValue
      = BeansObservables.observeValue( entity, property );
    UpdateValueStrategy targetToModel = new UpdateValueStrategy();
    targetToModel.setBeforeSetValidator( validator );
    UpdateValueStrategy modelToTarget = new UpdateValueStrategy();
    bind( bindingContext,
          observeText,
          observeValue,
          targetToModel,
          modelToTarget );
  }

  static void bindDate( final DataBindingContext bindingContext,
                        final IEntity entity,
                        final DateTime dateTime,
                        final String property ) {
    ISWTObservableValue observeSelection
      = SWTObservables.observeSelection( dateTime );
    IObservableValue observeValue
      = BeansObservables.observeValue( entity, property );
    UpdateValueStrategy targetToModel = new UpdateValueStrategy();
    UpdateValueStrategy modelToTarget = new UpdateValueStrategy();
    bind( bindingContext,
          observeSelection,
          observeValue,
          targetToModel,
          modelToTarget );
  }

  private static void bind( final DataBindingContext bindingContext,
                            final ISWTObservableValue observeText,
                            final IObservableValue observeValue,
                            final UpdateValueStrategy targetToModel,
                            final UpdateValueStrategy modelToTarget )
  {
    bindingContext.bindValue( observeText,
                              observeValue,
                              targetToModel,
                              modelToTarget );
  }

  static DataBindingContext createBindingContext() {
    if( Realm.getDefault() == null ) {
      SWTObservables.getRealm( Display.getCurrent() );
    }
    return new DataBindingContext();
  }
}
