
package org.eclipse.rwt{
	import flash.events.Event;
	import flash.events.KeyboardEvent;
	
	import mx.core.UIComponent;
	
	import rwt.protocol.ObjectRegistry;
	import rwt.remote.Server;
	
	public class KeyEventSupport {
		private var _keyBindings:Object;
		private var _cancelKeys:Object;
		private var _currentKeyCode:int;
		private var _bufferedEvents:Array;
		private var _keyEventRequestRunning:Boolean;
		private var _ignoreNextKeypress:Boolean;
		public function KeyEventSupport() {
			this._keyBindings = {};
			this._cancelKeys = {};
			this._currentKeyCode = -1;
			this._bufferedEvents = [];
			this._keyEventRequestRunning = false;
			this._ignoreNextKeypress = false;
			var req:Server = Server.getInstance();
			req.addEventListener( "received", this._onRequestReceived, this );
		}
		
		public function destruct() {
			var req = rwt.remote.Server.getInstance();
			req.removeEventListener( "received", this._onRequestReceived, this );
		}
		
		public function setKeyBindings( value ) {
			this._keyBindings = value;
		}
		
		public function setCancelKeys( value ) {
			this._cancelKeys = value;
		}
		
		public function _onKeyEvent( eventType, keyCode, charCode, domEvent ) {
			var control = this._getTargetControl();
			if( this._shouldSend( eventType, this._currentKeyCode, charCode, domEvent, control ) ) {
				this._sendKeyEvent( control, this._currentKeyCode, charCode, domEvent );
			}
			if( eventType === "keypress" || eventType === "keyup" ) {
				this._ignoreNextKeypress = false;
			}
			return !domEvent._noProcess;
		},
		
		/////////////
		// send event
		
		public function _shouldSend( eventType, keyCode, charCode, domEvent, control ) {
			var result = false;
			if( this._isRelevant( keyCode, eventType, domEvent ) ) {
				if( this._hasTraverseListener( control ) && this._isTraverseKey( keyCode ) ) {
					result = true;
				}
				if( !result && this._hasKeyListener( control ) ) {
					var activeKeys = control.getUserData( "activeKeys" );
					if( activeKeys ) {
						result = this._isActive( activeKeys, domEvent, keyCode, charCode );
					} else {
						result = true;
					}
				}
				if( !result ) {
					result = this._isActive( this._keyBindings, domEvent, keyCode, charCode );
				}
			}
			return result;
		}
		
		public function _isRelevant( keyCode:Number, eventType, domEvent ) {
			var result;
			if( eventType === "keypress" ) {
				// NOTE : modifier don't repeat
				result = !this._isModifier( keyCode ) && !this._ignoreNextKeypress;
			} else if( eventType === "keydown" ) {
				// NOTE : Prefered when keypress might not be fired, e.g. browser shortcuts that
				//        are not or can not be prevented/canceled. Key might not repeat in that case.
				//        Not to be used when charcode might be unkown (e.g. shift + char, special char)-
				result =    EventHandlerUtil.isNonPrintableKeyCode( keyCode )
					|| EventHandlerUtil.isSpecialKeyCode( keyCode );
				if( !result && ( domEvent.altKey || domEvent.ctrlKey ) ) {
					result = this._isAlphaNumeric( keyCode );
				}
				if( result ) {
					this._ignoreNextKeypress = true;
				}
			}
			if( domEvent.ctrlKey && keyCode === 9 ) {
				// Used by the browser to switch tabs, not useable
				result = false;
			}
			return result;
		}
		
		public function _onRequestReceived( evt:Event ) {
			if( this._keyEventRequestRunning ) {
				this._keyEventRequestRunning = false;
				this._checkBufferedEvents();
			}
		}
		
		public function _checkBufferedEvents() {
			while( this._bufferedEvents.length > 0 && !this._keyEventRequestRunning ) {
				var oldEvent = this._bufferedEvents.shift();
				this._sendKeyEvent.apply( this, oldEvent );
			}
		}
		
		public function _sendKeyEvent( widget:UIComponent, keyCode:Number, charCode:Number, domEvent:KeyboardEvent ) {
			if( this._keyEventRequestRunning ) {
				this._bufferedEvents.push( [ widget, keyCode, charCode, domEvent ] );
			} else {
				this._attachKeyEvent( widget, keyCode, charCode, domEvent );
				this._keyEventRequestRunning = true;
				this._sendRequestAsync();
			}
		}
		
		public function _sendRequestAsync() {
			Server.getInstance().sendImmediate( true );
		}
		
		public function _attachKeyEvent( widget:UIComponent, keyCode:Number, charCode:Number, domEvent:KeyboardEvent ) {
			var req:Server = Server.getInstance();
			var id:String;
			if( widget === null ) {
				id = "w1";
			} else {
				id = ObjectRegistry.getId( widget );
			}
			var finalCharCode = this._getCharCode( keyCode, charCode, domEvent );
			req.addEvent( "org.eclipse.swt.events.keyDown", id );
			req.addParameter( "org.eclipse.swt.events.keyDown.keyCode", keyCode );
			req.addParameter( "org.eclipse.swt.events.keyDown.charCode", finalCharCode );
			var modifier = "";
			if( domEvent.shiftKey ) {
				modifier += "shift,";
			}
			if( domEvent.ctrlKey) {
				modifier += "ctrl,";
			}
			if( domEvent.altKey ) {
				modifier += "alt,";
			}
			req.addParameter( "org.eclipse.swt.events.keyDown.modifier", modifier );
		}
		
		///////////////
		// cancel event
		
		public function _shouldCancel( keyCode, charCode, domEvent, control ) {
			var result = this._isActive( this._cancelKeys, domEvent, keyCode, charCode );
			if( !result ) {
				var cancelKeys = control ? control.getUserData( "cancelKeys" ) : null;
				if( cancelKeys ) {
					result = this._isActive( cancelKeys, domEvent, keyCode, charCode );
				}
			}
			return result;
		}
		
		/////////
		// helper
		
		public function _getTargetControl() {
			var result = org.eclipse.rwt.EventHandler.getCaptureWidget();
			if( !result ) {
				var focusRoot = org.eclipse.rwt.EventHandler.getFocusRoot();
				result = focusRoot === null ? null : focusRoot.getActiveChild();
			}
			var widgetManager = org.eclipse.swt.WidgetManager.getInstance();
			while( result !== null && !widgetManager.isControl( result ) ) {
				result = result.getParent ? result.getParent() : null;
			}
			return result;
		}
		
		public function _isActive( activeKeys, domEvent, keyCode, charCode ) {
			var result = false;
			var identifier = this._getKeyBindingIdentifier( domEvent, "keydown", keyCode, charCode );
			result = activeKeys[ identifier ] === true;
			if( !result ) {
				identifier = this._getKeyBindingIdentifier( domEvent, "keypress", keyCode, charCode );
				result = activeKeys[ identifier ] === true;
			}
			return result;
		}
		
		public function _getKeyBindingIdentifier( domEvent, eventType, keyCode, charCode ) {
			var result = [];
			if( eventType === "keydown" && !isNaN( keyCode ) && keyCode > 0 ) {
				if( domEvent.altKey ) {
					result.push( "ALT" );
				}
				if( domEvent.ctrlKey ) {
					result.push( "CTRL" ); //TODO Command @ apple?
				}
				if( domEvent.shiftKey ) {
					result.push( "SHIFT" );
				}
				result.push( "#" + keyCode.toString() );
			} else if( eventType === "keypress" && !isNaN( charCode ) && charCode > 0 ) {
				result.push( String.fromCharCode( charCode ) );
			}
			return result.join( "+" );
		}
		
		public function _getCharCode( keyCode, charCode, domEvent ) {
			var result = charCode;
			if( result === 0 && this._isAlphaNumeric( keyCode ) ) {
				if( domEvent.shiftKey && !this._isNumeric( keyCode ) ) {
					result = keyCode;
				} else {
					result = String.fromCharCode( keyCode ).toLowerCase().charCodeAt( 0 );
				}
			}
			return result;
		}
		
		public function _isModifier( keyCode ) {
			return keyCode >= 16 && keyCode <= 20 && keyCode !== 19;
		}
		
		public function _isAlphaNumeric( keyCode ) {
			return ( keyCode >= 65 && keyCode <= 90 ) || this._isNumeric( keyCode );
		}
		
		public function _isNumeric( keyCode ) {
			return keyCode >= 48 && keyCode <= 57;
		}
		
		public function _hasKeyListener( widget ) {
			return widget !== null && widget.getUserData( "keyListener" ) === true;
		}
		
		public function _hasTraverseListener( widget ) {
			return widget !== null && widget.getUserData( "traverseListener" ) === true;
		}
		
		public function _isTraverseKey( keyCode ) {
			var result = false;
			if( keyCode === 27 || keyCode === 13 || keyCode === 9 ) {
				result = true;
			}
			return result;
		}
		
	}
} 