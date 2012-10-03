package rwt.remote {
	import flash.events.Event;
	import flash.events.IOErrorEvent;
	import flash.events.SecurityErrorEvent;
	import flash.net.URLLoader;
	import flash.net.URLRequest;
	import flash.net.URLRequestHeader;
	
	import mx.controls.Alert;
	import mx.messaging.messages.HTTPRequestMessage;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	import mx.rpc.http.mxml.HTTPService;
	
	public class Request{
		private var _async:Boolean;
		private var _success:Function;
		private var _error:Function;
		private var _data:String;
		private var _responseType:String;
		private var _method:String;
		private var _url:String;
		private var _request:URLLoader;
		private var _fromLocation:String;
		private var _requestCounter:Number;
		public var request:URLRequest;
		public  function Request(fromLocation:String, url:String, method:String, responseType:String ) {
		  this._url = url;
		  this._fromLocation=fromLocation;
		  this._method = method;
		  this._async = true;
		  this._success = null;
		  this._error = null;
		  this._data = null;
		  this._responseType = responseType;
		  this._request = new URLLoader();

		}
	
	   public function dispose():void {
	      if( this._request != null ) {
	        this._success = null;
	        this._error = null;
	        this._request = null;
	      }
	    }
	
	   public function send() :void{
	      var urlpar :String= "nocache=" + ( new Date() ).valueOf();
	      var post :Boolean= this._method === "POST";
	      if( !post ) {
	        urlpar += "&" + this._data;
	      }
		  var url :String= this._url +"/view"+ ( this._url.indexOf( "?" ) >= 0 ? "&" : "?" ) + urlpar;
		  var service:HTTPService=new HTTPService();
		  service.url=url;
		service.resultFormat="text";
		  service.contentType="application/x-www-form-urlencoded; charset=UTF-8";// "application/json";
		service.method=this._method;
		  service.addEventListener(ResultEvent.RESULT, _success);
		  service.addEventListener(FaultEvent.FAULT,_error);
	      this._configRequest(service);
		  if(post){
			  trace("======OUT=====");
			  trace(this._data);
			  service.send(_data);
		  }
	      if( !this._async ) {
	        this.dispose();
	      }
	    }
	
	   public function setAsynchronous( value:Boolean ):void {
	      this._async = value;
	    }
	
	   public function getAsynchronous() :Boolean{
	      return this._async;
	    }
	
	   public function setSuccessHandler( handler:Function, context:Object ) :void{
		   var r:Request=this;
	      this._success = function(evt:ResultEvent):void{
			  trace("======IN=====");
			  trace(evt.result);

			  handler.apply( context, [evt,r] ); 
		  };
		  
	    }
	
	   public function setErrorHandler( handler:Function, context:Object ) :void{
		   var r:Request=this;
	      this._error = function(evt:Event):void{ 
			  handler.apply(context, [evt,r]); 
		  };
	    }
	
	   public function setData( value:String ) :void{
	      this._data = value;
	    }
	
	   public function getData() :String{
	      return this._data;
	    }
	
	   public function _configRequest(request:HTTPService) :void{
		   //TODO has no effect - header gets ignored
	      var contentType :String= "application/x-www-form-urlencoded; charset=UTF-8";
		  request.headers="Content-Type: " + contentType  + " " + "Pragma: no-cache Cache-Control:no-cache";
//	      request.headers.push(new  URLRequestHeader( "Content-Type", contentType));
//	      request.headers.push(new URLRequestHeader( "Pragma", "no-cache")) ;
//	      request.headers.push(new URLRequestHeader( "Cache-Control", "no-cache"));
	    }
	   
	public function setRequestCounter(n:Number):void{
			this._requestCounter=n;
		}
	}
}
