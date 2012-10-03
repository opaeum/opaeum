

package rwt.runtime
{
	import mx.controls.Alert;
	
	import rwt.remote.Request;
	import rwt.remote.Server;

public class ErrorHandler {

	static var debug:Boolean = true;
	private static var errorObject:*;
   public static function processJavaScriptErrorInResponse( script, error, currentRequest ) {
      var content = _gatherErrorInfo( error, script, currentRequest );
      showErrorPage( content );
	  throw error;
    }

   public static function processJavaScriptError( error:Error ) {
      errorObject = error; // for later inspection by developer
      
	  trace(error.message);
	  trace(error.getStackTrace());
      if( debug ) {
        var content = "Javascript error occurred:";
        content += _gatherErrorInfo( error,"",null );
        showErrorPage( content );
        throw error;
      }

    }

   public static function showErrorPage( content ) {
	   Alert.show(content);
      _enableTextSelection();
      _freezeApplication();
    }

   public static function showErrorBox( content, freeze ) {
	   Alert.show(content);
    }

   public static function hideErrorBox() {
    }

   public static function _gatherErrorInfo( error, script, currentRequest:Request ) {
      var info = [];
      try {
        info.push( "Error: " + error + "\n" );
        if( script ) {
          info.push( "Script: " + script );
        }
        if( error instanceof Error ) {
          for( var key in error ) { // NOTE : does not work in webkit (no iteration)
            info.push( key + ": " + error[ key ] );
          }
          if( error.stack ) { // ensures stack is printed in webkit, might be printed twice in gecko
            info.push( "Stack: " + error.stack );
          }
       }
        if( currentRequest ) {
          info.push( "Request: " + currentRequest.getData() );
        }
      } catch( ex ) {
        // ensure we get a info no matter what
      }
      return info.join( "\n  " );
    }

   public static function _createOverlay() {
      return null;
    }

   public static function _createErrorPageArea() {
      return null;
    }

   public static function _createErrorBoxArea( width, height ) {
	   return null;
    }

   public static function _freezeApplication() {
    }

   public static function _enableTextSelection() {
    }

  }

}
