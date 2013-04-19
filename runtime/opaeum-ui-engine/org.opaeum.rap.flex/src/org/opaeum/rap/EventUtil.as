package org.opaeum.rap
{
	import flash.display.Sprite;
	import flash.events.Event;
	import flash.events.FocusEvent;
	import flash.events.KeyboardEvent;
	import flash.events.MouseEvent;
	import flash.events.TimerEvent;
	import flash.sampler.startSampling;
	import flash.ui.Keyboard;
	import flash.utils.Timer;
	
	import mx.core.IVisualElement;
	import mx.core.UIComponent;
	
	import org.opaeum.rwt.widgets.IRwtControl;
	import org.opaeum.rwt.widgets.IRwtWidget;
	
	import rwt.protocol.ObjectRegistry;
	import rwt.remote.Request;
	import rwt.remote.Server;
	
	public class EventUtil
	{
		private static var _lastMouseDown:* ={
			widget : null,
			button : "",
			x : -1,
				y : -1,
				mouseUpCount : 0
		}		
		private static var _suspended:Boolean =false;
		
		
		public static function setSuspended( value:Boolean ) :void{
			_suspended = value;
		}
		
		public static function getSuspended() :Boolean{
			return _suspended;
		}
		
		private static var DOUBLE_CLICK_TIME :Number= 500;
		
		private static var _capturingWidget:IVisualElement = null;
		private static var _metaKey :Boolean= false;
		private static var startupTime:Date;
		public static function init():void{
			startupTime=new Date();
		}
		public static function eventTimestamp() :Number{
			return new Date().getTime() - startupTime.getTime();
		}
		
		public function  EventUtil()
		{
			
		}
		public static function  widgetDefaultSelected( evt:Event ):void {
			if( !getSuspended() ) {
				var id :String= ObjectRegistry.getId(evt.target);
				var req :Server= Server.getInstance();
				req.addEvent( "org.eclipse.swt.events.widgetDefaultSelected", id );
				addWidgetSelectedModifier(evt);
				req.send();
			}
		}
		
		public static function  widgetSelected( evt :Event) :void{
			var req:Server = Server.getInstance();
			var id:String = null;
			if(evt.target is IRwtWidget){
				id=IRwtWidget(evt.target).id;
			}else if(evt.target is IVisualElement){
				id=ObjectRegistry.getId(ObjectRegistry.findControl(IVisualElement( evt.target)));
			}
			if(id!=null){
				if(evt.target is UIComponent){
					var left:Number = UIComponent(evt.target).x;
					var top:Number = UIComponent(evt.target).y;
					var width:Number = UIComponent(evt.target).width;
					var height:Number = UIComponent(evt.target).height;
					doWidgetSelected( evt,id, left, top, width, height );
				}else{
					doWidgetSelected( evt,id, 0, 0, 0, 0);
				}
			}
		}
		
		public static function  doWidgetSelected(ev:Event, id:String, left:Number, top:Number, width:Number, height:Number ) :void{
			if( !getSuspended() ) {
				var req:Server = Server.getInstance();
				req.addEvent( "org.eclipse.swt.events.widgetSelected", id );
				addWidgetSelectedModifier(ev);
				req.addParameter( id + ".bounds.x", left );
				req.addParameter( id + ".bounds.y", top );
				req.addParameter( id + ".bounds.width", width );
				req.addParameter( id + ".bounds.height", height );
				req.send();
			}
		}
		
		public static function  addWidgetSelectedModifier(e:Event):void {
			if( !getSuspended() ) {
				var modifier:String = _getKeyModifier(e);
				if( modifier != "" ) {
					var req :Server= Server.getInstance();
					req.addParameter( "org.eclipse.swt.events.widgetSelected.modifier", modifier );
				}
			}
		}
		
		public static function  _getKeyModifier(ev:Event):String {
			var modifier:String = ""; 
			if(ev is MouseEvent){
				var e:MouseEvent=MouseEvent(ev);
				
				if( e.shiftKey ) {
					modifier += "shift,";
				}
				if( e.ctrlKey) {
					modifier += "ctrl,";
				}
				if( e.altKey ) {
					modifier += "alt,";
				}
			}else if(ev is KeyboardEvent){{
				var ke:KeyboardEvent=KeyboardEvent(ev);
				if( ke.shiftKey ) {
					modifier += "shift,";
				}
				if( ke.ctrlKey) {
					modifier += "ctrl,";
				}
				if( ke.altKey ) {
					modifier += "alt,";
				}
			}
				
			}
			return modifier;
		}
		
		public static function  focusGained( evt:FocusEvent ) :void{
			if( !getSuspended() ) {
				// [if] The focusControl parameter is added in the request in Shell.js
				var req :Server= Server.getInstance();
				req.send();
			}
		}
		
		public static function  focusLost( evt:FocusEvent ) :void{
			if( !getSuspended() ) {
				// [if] The focusControl parameter is added in the request in Shell.js
				var req:Server = Server.getInstance();
				req.send();
			}
		}
		
		///////////////////////
		// Mouse event handling
		
		public static function  mouseDown( evt:MouseEvent ):void {
			if(    !getSuspended() && evt.target is IVisualElement 
				&& _isRelevantMouseEvent( IVisualElement( evt.target), evt ) )
			{
				// disabled capturing as it interferes with Combo capturing
				// see https://bugs.eclipse.org/bugs/show_bug.cgi?id=262171
				//// from now on, redirect mouse event to this widget
				//this.setCapture( true );
				_capturingWidget = IVisualElement( evt.target);
				// Add parameters for double-click event
				if( _isDoubleClick( _capturingWidget, evt ) ) {
					_clearLastMouseDown(evt);
					_mouseDoubleClickParams(  _capturingWidget, evt );
				} else {
					// Store relevant data of current event to detect double-clicks
					var lastMouseDown:* = _lastMouseDown;
					lastMouseDown.widget = evt.target;
					lastMouseDown.button = "LEFT";
					lastMouseDown.x = evt.stageX;
					lastMouseDown.y = evt.stageY;
					lastMouseDown.mouseUpCount = 0;
					var timer:Timer = new Timer(DOUBLE_CLICK_TIME,1);
					timer.addEventListener(TimerEvent.TIMER,_clearLastMouseDown);
					timer.start();
				}
				// Collect request parameters and send
				_mouseDownParams( IVisualElement( evt.target), evt );
				var req:Server = Server.getInstance();
				req.send();
			}
		}
		
		public static function  mouseUp( evt:MouseEvent ) :void{
			if(    !getSuspended() && evt.target is IVisualElement  && _isRelevantMouseEvent(IVisualElement( evt.target), evt ) )
			{
				// disabled capturing as it interferes with Combo capturing
				// see https://bugs.eclipse.org/bugs/show_bug.cgi?id=262171
				//// release mouse event capturing
				//this.setCapture( false );
				_capturingWidget = null;
				// increase number of mouse-up events since last stored mouse down
				_lastMouseDown.mouseUpCount += 1;
				// Add mouse-up request parameter
				_mouseUpParams(IVisualElement( evt.target), evt );
				var req:Server = Server.getInstance();
				req.send();
			}
		}
		
		public static function  _isRelevantMouseEvent( widget:IVisualElement, evt:MouseEvent ) :Boolean{
			var result:Boolean = true;
			if(    widget !== _capturingWidget
				&& widget !== evt. target )
			{
				// find parent control and ensure that it is the same as the widget-
				// parameter. Otherwise the mouse event is ignored.
				var target:IVisualElement =IVisualElement( evt.target);
				var control:IVisualElement = ObjectRegistry.findControl( target );
				result = widget === control;
			}
			return result;
		}
		
		public static function  _clearLastMouseDown(e:Event) :void{
			var lastMouseDown:* = _lastMouseDown;
			lastMouseDown.widget = null;
			lastMouseDown.button = "";
			lastMouseDown.mouseUpCount = 0;
			lastMouseDown.x = -1;
			lastMouseDown.y = -1;
		}
		
		public static function  _isDoubleClick( widget:IVisualElement, evt:MouseEvent ) :Boolean{
			// TODO [rh] compare last position with current position and don't
			//      report double-click if deviation is too big
			var lastMouseDown:* = _lastMouseDown;
			return    lastMouseDown.mouseUpCount === 1
				&& lastMouseDown.widget === widget
				&& lastMouseDown.button ==(evt.buttonDown?"left":"right");
		}
		
		public static function  _mouseDownParams( widget:IVisualElement, evt:MouseEvent ):void {
			var id:String = ObjectRegistry.getId( widget );
			var req:Server = Server.getInstance();
			var button:Number = _determineMouseButton( evt );
			var modifier:String = _getKeyModifier(evt);
			req.addEvent( "org.eclipse.swt.events.mouseDown", id );
			req.addParameter( "org.eclipse.swt.events.mouseDown.button", button );
			req.addParameter( "org.eclipse.swt.events.mouseDown.x", evt.stageX );
			req.addParameter( "org.eclipse.swt.events.mouseDown.y", evt.stageY );
			req.addParameter( "org.eclipse.swt.events.mouseDown.time", eventTimestamp() );
			if( modifier !== "" ) {
				req.addParameter( "org.eclipse.swt.events.mouseDown.modifier", modifier );
			}
		}
		
		public static function  _mouseUpParams( widget:IVisualElement, evt:MouseEvent ) :void{
			var id:String = ObjectRegistry.getId( widget );
			var req:Server = Server.getInstance();
			var button:Number = _determineMouseButton( evt );
			var modifier:String = _getKeyModifier(evt);
			req.addEvent( "org.eclipse.swt.events.mouseUp", id );
			req.addParameter( "org.eclipse.swt.events.mouseUp.button", button );
			req.addParameter( "org.eclipse.swt.events.mouseUp.x", evt.stageX );
			req.addParameter( "org.eclipse.swt.events.mouseUp.y", evt.stageY );
			req.addParameter( "org.eclipse.swt.events.mouseUp.time", eventTimestamp() );
			if( modifier !== "" ) {
				req.addParameter( "org.eclipse.swt.events.mouseUp.modifier", modifier );
			}
		}
		
		public static function  _mouseDoubleClickParams( widget:IVisualElement, evt:MouseEvent ):void {
			var id:String = ObjectRegistry.getId(evt.target);
			var req:Server = Server.getInstance();
			var modifier:String = _getKeyModifier(evt);
			req.addEvent( "org.eclipse.swt.events.mouseDoubleClick", id );
			req.addParameter( "org.eclipse.swt.events.mouseDoubleClick.button",
				_determineMouseButton( evt ) );
			req.addParameter( "org.eclipse.swt.events.mouseDoubleClick.x",
				evt.stageX );
			req.addParameter( "org.eclipse.swt.events.mouseDoubleClick.y",
				evt.stageY );
			req.addParameter( "org.eclipse.swt.events.mouseDoubleClick.time",
				eventTimestamp() );
			if( modifier !== "" ) {
				req.addParameter( "org.eclipse.swt.events.mouseDoubleClick.modifier",
					modifier );
			}
		}
		
		public static function  _determineMouseButton( evt:MouseEvent ) :Number{
			if(evt.buttonDown){
				return 1;
			}else{
				return 3;
			}
		}
		
		public static function  helpRequested( evt:KeyboardEvent ):void {
			if( evt.keyCode == Keyboard.F1) {
				// stop further handling and default handling by the browser
				evt.stopPropagation();
				evt.preventDefault();
				// send help request to server
				var widget:IVisualElement =IVisualElement( evt.target);
				var id:String = ObjectRegistry.getId( widget );
				if( id === null ) {
					// find parent control for the widget that received the event in case
					// it wasn't the control itself that received the event
					widget = ObjectRegistry.findControl( widget );
					id = ObjectRegistry.getId( widget );
				}
				if( id != null ) {
					var req:Server = Server.getInstance();
					req.addEvent( "org.eclipse.swt.events.help", id );
					req.send();
				}
			}
		}
		
		public static function  menuDetectedByKey( evt:KeyboardEvent ) :void{
			//	      if( evt. keyCode === Keyboard.APPS?? ) {
			//	        // stop further handling and default handling by the browser
			//	        evt.stopPropagation();
			//	        evt.preventDefault();
			//	        var x = qx.event.type.MouseEvent.stageX;
			//	        var y = qx.event.type.MouseEvent.stageY;
			//	        sendMenuDetected( evt.target, x, y );
			//	      }
		}
		
		public static function  menuDetectedByMouse( evt:MouseEvent ) :void{
			if( evt.buttonDown==false) {
				// stop further handling and default handling by the browser
				evt.stopPropagation();
				evt.preventDefault();
				var x:Number = evt.stageX;
				var y:Number = evt.stageY;
				sendMenuDetected( UIComponent( evt.target), x, y );
			}
		}
		
		public static function  sendMenuDetected( widget:UIComponent, x:Number, y:Number ) :void{
			if( !getSuspended() ) {
				// send menu detect request to server
				// find parent control for the widget that received the event in case
				// it wasn't the control itself that received the event
				while( widget != null && ObjectRegistry.getId( widget )==null) {
					widget = widget.parent is UIComponent?UIComponent(widget.parent):null;
				}
				var id:String = ObjectRegistry.getId( widget );
				if( id != null) {
					var req:Server = Server.getInstance();
					req.addEvent( "org.eclipse.swt.events.menuDetect", id );
					req.addParameter( "org.eclipse.swt.events.menuDetect.x", x );
					req.addParameter( "org.eclipse.swt.events.menuDetect.y", y );
					req.send();
				}
			}
		}
		
	}
}
