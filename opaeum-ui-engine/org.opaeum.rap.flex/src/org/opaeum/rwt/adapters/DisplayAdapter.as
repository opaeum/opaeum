package org.opaeum.rwt.adapters
{
	
	import flash.display.ColorCorrection;
	
	import flexlib.mdi.containers.MDICanvas;
	
	import org.eclipse.swt.FontSizeCalculation;
	import org.opaeum.rap.WidgetAdapter;
	
	import rwt.remote.Server;
	
	import spark.components.Application;
	
	public class DisplayAdapter extends WidgetAdapter
	{	
		private var _request:Server;
		public function DisplayAdapter()
		{
			super();
			super.registerCallableMethod("probe",probe);
			super.registerCallableMethod("init",init);
			super.registerCallableMethod("measureStrings",measureStrings);
		}
		public function probe(display:MDICanvas, args:*):void{
			FontSizeCalculation.probe(display,args.fonts);
		}
		public function measureStrings(display:MDICanvas, args:*):void{
			FontSizeCalculation.measureStringItems(display,args.strings);
		}
		public function init(display:MDICanvas, args:*):void{
			this._request=Server.getInstance();
			this._request.setUIRootId( args.rootId );
			this._request._parameters[ "rwt_initialize" ] = "true"; // skip json message writer
			this._request.getMessageWriter().appendMeta( "rwt_initialize", true );
			this._appendWindowSize(display);
			this._appendSystemDPI(display);
			this._appendColorDepth(display);
			this._appendInitialHistoryEvent(display);
			this._request.sendImmediate( true );

		}
		
		private function _appendInitialHistoryEvent(display:MDICanvas):void
		{
//			var o:Object=display.document;
//			var entryId:String = display.document.location.hash;
//			if( entryId !== "" ) {
//				_request.addParameter( "org.eclipse.rwt.events.historyNavigated", "true" );
//				_request.addParameter( "org.eclipse.rwt.events.historyNavigated.entryId", entryId.substr( 1 ) );
//			}
		}
		
		private function _appendColorDepth(display:MDICanvas):void
		{
//			this._request.addParameter( "w1.colorDepth",???);
			
		}
		
		private function _appendSystemDPI(display:MDICanvas):void
		{
			//TODO figure out how to calculate the seperate dpis (if necessary?)
			this._request.addParameter( "w1.dpi.x", RapApplication.rapInstance.runtimeDPI +"" );
			this._request.addParameter( "w1.dpi.y", RapApplication.rapInstance.runtimeDPI +"");
			
		}
		
		private function _appendWindowSize(display:MDICanvas):void
		{
			// Append document size to request
			var id:String = this._request.getUIRootId();
			this._request.addParameter( id + ".bounds.width", display.width +"");
			this._request.addParameter( id + ".bounds.height", display. height +"");
			
		}
	}
}