package org.opaeum.rwt.adapters
{
	import flash.events.MouseEvent;
	import flash.text.TextLineMetrics;
	
	import flashx.textLayout.formats.TextAlign;
	
	import mx.containers.TitleWindow;
	import mx.controls.Alert;
	import mx.managers.PopUpManager;
	
	import org.opaeum.rap.WidgetAdapter;
	import org.opaeum.rwt.widgets.RwtLabel;
	
	import spark.components.HGroup;
	import spark.components.Label;
	import spark.layouts.VerticalAlign;
	
	public class LabelAdapter extends WidgetAdapter
	{
		public function LabelAdapter()
		{
			super();
			registerProperty("text","text");
			registerProperty("image");
		}
		
		override protected function setProperty(target:Object, swtName:String, value:*):void
		{
			super.setProperty(target, swtName, value);
			var label:RwtLabel=RwtLabel(target);
			if(swtName=="text" || swtName=="bounds"){
				if(label.text!=null){
					var textSize:TextLineMetrics=label.measureText(label.text);
					if(label.width<textSize.width+25){
						// TODO find out why 25??
						label.width=textSize.width + 25;
					}
				}
			}else if(swtName=="image"){
					label.image=buildImage(value);
			}
		}
		
		
		override protected function newInstance(args:*):Object
		{
			var result:RwtLabel=new RwtLabel(args.style);
			result.text=args.text;
			return result;
		}
		
		
	}
}