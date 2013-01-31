
package rwt.protocol {
//	import com.adobe.serialization.json.JSON;
	
	import flash.utils.Dictionary;

public class MessageWriter{
	private var _disposed:Boolean;
	private var _meta:Object={};
	private var _operations:Array=new Array();
	private var _currentSetOperation:Object;
	public function dispose() :void{
      this._operations = null;
      this._meta = null;
      this._disposed = true;
    }

   public function hasOperations() :Boolean{
      return this._operations.length > 0;
    }

   public function createMessage() :String{
      if( this._disposed ) {
        throw new Error( "Protocol message writer already disposed!" );
      }
      var message :*= {
        "meta" : this._meta,
        "operations" : this._operations
      };
      return JSON.stringify(message );
    }

   public function appendMeta( property:String, value:* ) :void{
      this._meta[property] = value;
    }

   public function appendSet( targetId:String, property:String, value:* ) :void{
      var properties:* = this._getPropertiesObjectFor( targetId );
      properties[ property ] = value;
    }

   public function appendNotify( targetId:String, eventName:String, properties:* ):void {
      this._currentSetOperation = null;
      this._operations.push( [ "notify", targetId, eventName, properties ] );
    }

   public function appendCall( targetId:String, methodName:String, properties:* ) :void{
      this._currentSetOperation = null;
      this._operations.push( [ "call", targetId, methodName, properties ] );
    }

   public function _getPropertiesObjectFor( targetId :String):* {
      if( this._currentSetOperation === null || this._currentSetOperation[ 1 ] !== targetId ) {
        this._currentSetOperation = [ "set", targetId, {} ];
        this._operations.push( this._currentSetOperation );
      }
      return this._currentSetOperation[ 2 ];
    }

}
}