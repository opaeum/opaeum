package org.opaeum.rwt.adapters
{
	import flash.events.MouseEvent;
	import flash.text.TextLineMetrics;
	
	import mx.controls.Alert;
	import mx.controls.CheckBox;
	import mx.core.UIComponent;
	import mx.events.FlexEvent;
	
	import org.eclipse.rwt.EventHandlerUtil;
	import org.opaeum.rap.EventUtil;
	import org.opaeum.rap.WidgetAdapter;
	import org.opaeum.rwt.SWT;
	import org.opaeum.rwt.widgets.IRwtButton;
	import org.opaeum.rwt.widgets.RwtButton;
	import org.opaeum.rwt.widgets.RwtCheckBox;
	import org.opaeum.rwt.widgets.RwtRadioButton;
	
	import rwt.remote.Server;
	
	import spark.components.Button;
	import spark.components.RadioButton;
	import spark.components.supportClasses.ButtonBarBase;
	import spark.components.supportClasses.ButtonBase;
	import spark.components.supportClasses.ToggleButtonBase;
	import spark.primitives.BitmapImage;
	
	public class ButtonAdapter extends WidgetAdapter
	{
		public function ButtonAdapter()
		{
			super.registerEventListenerType("selection", MouseEvent.CLICK,EventUtil.widgetSelected);				
			super.registerProperty("text","text");
			super.registerProperty("tabIndex","tabIndex");
			super.registerProperty("image","image");
			super.registerProperty("menu","menu");
		}
		override protected function setProperty(target:Object, swtName:String, value:*):void
		{
			super.setProperty(target, swtName, value);
			var label:IRwtButton=IRwtButton(target);
			var textSize:TextLineMetrics=label.measureText(label.text);
			if(swtName=="text" || swtName=="bounds"){
				
				if(label.width<textSize.width+ 25){
					// TODO find out why 25??
					label.width=textSize.width + 25;
				}
			}else if(swtName=="image"){
				label.setImage(value[0],value[1],value[2]);
			}
		}
		
		override protected function newInstance(args:*):Object
		{
			var style:Array=args.style;
			var result:ButtonBase;
			for (var i:Number=0; i<style.length;i++) 
			{
				switch(style[i]){
					case SWT.PUSH:
						result=new RwtButton(args.style);
						break;
					case SWT.RADIO:
						result= new RwtRadioButton(args.style);
						break;
					case SWT.CHECK:
						result= new RwtCheckBox(args.style);
						break;
				}	
			}
			result.addEventListener(FlexEvent.VALUE_COMMIT,function(me:FlexEvent):void{
				if(result is ToggleButtonBase){
					Server.getInstance().addParameter(result.id + ".selection",ToggleButtonBase(result).selected);
				}
			});
			return result;
			
			
		}
		
		
	}
	
}