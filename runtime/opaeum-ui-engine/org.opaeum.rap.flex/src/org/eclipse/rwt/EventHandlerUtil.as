
package org.eclipse.rwt
{
	public class EventHandlerUtil {
		
		static var _lastUpDownType :*= {}
		static var _lastKeyCode:String = null;
		
		static _userEventTypes :Array= [
			"mouseover",
			"mousemove",
			"mouseout",
			"mousedown",
			"mouseup",
			"click",
			"dblclick",
			"contextmenu",
			"keydown",
			"keypress",
			"keyup"
		];
		
		public static function _isFirstKeyDown( keyCode ) {
			return _lastUpDownType[ keyCode ] !== "keydown";
		}
		public static function keyCodeToIdentifier( keyCode ) {
			var result = "Unidentified";
			if( _numpadToCharCode[ keyCode ] !== undefined ) {
				result = String.fromCharCode( _numpadToCharCode[ keyCode ] );
			} else if( _keyCodeToIdentifierMap[ keyCode ] !== undefined ) {
				result = _keyCodeToIdentifierMap[ keyCode ];
			} else if( _specialCharCodeMap[ keyCode ] !== undefined ) {
				result = _specialCharCodeMap[ keyCode ];
			} else if( _isAlphaNumericKeyCode( keyCode ) ) {
				result = String.fromCharCode( keyCode );
			}
			return result;
		}
		
		public static function charCodeToIdentifier( charCode ) {
			var result;
			if( _specialCharCodeMap[ charCode ] !== undefined ) {
				result = _specialCharCodeMap[ charCode ];
			} else {
				result = String.fromCharCode( charCode ).toUpperCase();
			}
			return result;
		},
		
		function isNonPrintableKeyCode (keyCode:int):Boolean{
			return _keyCodeToIdentifierMap[ keyCode ] ? true : false;
		}
		
		public static function isSpecialKeyCode( keyCode ):Boolean {
			return _specialCharCodeMap[ keyCode ] ? true : false;
		}
		
		public static function isModifier( keyCode ) {
			return keyCode >= 16 && keyCode <= 20 && keyCode !== 19;
		}
		
		public static function _isAlphaNumericKeyCode( keyCode ) {
			var result = false;
			if(    ( keyCode >= _charCodeA && keyCode <= _charCodeZ )
				|| ( keyCode >= _charCode0 && keyCode <= _charCode9 ) )
			{
				result = true;
			}
			return result;
		}
		
		static var _specialCharCodeMap : {
			13  : "Enter",
			27  : "Escape",
			32 : "Space"
		}
		static var _keyCodeToIdentifierMap : {
			8   : "Backspace",
			9   : "Tab",
			16  : "Shift",
			17  : "Control",
			18  : "Alt",
			20  : "CapsLock",
			224 : "Meta",
			37  : "Left",
			38  : "Up",
			39  : "Right",
			40  : "Down",
			33  : "PageUp",
			34  : "PageDown",
			35  : "End",
			36  : "Home",
			45  : "Insert",
			46  : "Delete",
			112 : "F1",
			113 : "F2",
			114 : "F3",
			115 : "F4",
			116 : "F5",
			117 : "F6",
			118 : "F7",
			119 : "F8",
			120 : "F9",
			121 : "F10",
			122 : "F11",
			123 : "F12",
			144 : "NumLock",
			44  : "PrintScreen",
			145 : "Scroll",
			19  : "Pause",
			91  : "Win", // The Windows Logo key
			93  : "Apps" // The Application key (Windows Context Menu)
		};
		
		static var _numpadToCharCode : {
			96  : "0".charCodeAt( 0 ),
				97  : "1".charCodeAt( 0 ),
					98  : "2".charCodeAt( 0 ),
						99  : "3".charCodeAt( 0 ),
							100 : "4".charCodeAt( 0 ),
								101 : "5".charCodeAt( 0 ),
									102 : "6".charCodeAt( 0 ),
										103 : "7".charCodeAt( 0 ),
											104 : "8".charCodeAt( 0 ),
												105 : "9".charCodeAt( 0 ),
													106 : "*".charCodeAt( 0 ),
														107 : "+".charCodeAt( 0 ),
															109 : "-".charCodeAt( 0 ),
																110 : ",".charCodeAt( 0 ),
																	111 : "/".charCodeAt( 0 )
		};
		
		static var _charCodeA:Number = "A".charCodeAt( 0 );
		static var _charCodeZ:Number = "Z".charCodeAt( 0 );
		static var _charCode0:Number = "0".charCodeAt( 0 );
		static var _charCode9:Number= "9".charCodeAt( 0 );
		
	}
	
} 
