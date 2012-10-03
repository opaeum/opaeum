package org.opaeum.rwt.widgets
{
	import mx.core.ClassFactory;
	import mx.core.IVisualElement;
	import mx.skins.RectangularBorder;
	
	import org.opaeum.rwt.SWT;
	import org.opaeum.rwt.skins.CompositeSkin;
	
	import spark.components.RadioButtonGroup;
	import spark.components.SkinnableContainer;
	import spark.components.supportClasses.Skin;
	import spark.layouts.BasicLayout;
	import spark.skins.spark.SkinnableContainerSkin;
	
	public class RwtComposite extends SkinnableContainer implements IRwtControl
	{
		private var radioButtonGroup: RadioButtonGroup;
		private var hasBorder=false;
		public function RwtComposite(style:Array)
		{
			super();
			super.setStyle("skinFactory",new ClassFactory(CompositeSkin));
			layout=new BasicLayout();
			for(var i:Number=0;i<style.length;i++){
				switch(style[i]){
					case SWT.BORDER:
						hasBorder=true;
						break;
				}
			}

		}
		
		override protected function updateDisplayList(unscaledWidth:Number, unscaledHeight:Number):void
		{
			super.updateDisplayList(unscaledWidth, unscaledHeight);
			if(skin is CompositeSkin){
				var s:CompositeSkin=CompositeSkin(skin);
				if(hasBorder){
					s.borderStroke.weight=1;
					s.borderStroke.alpha=1;
				}else{
					s.borderStroke.alpha=0;
					s.borderStroke.weight=0;
				}
			}
		}
		
		
		override public function addElementAt(element:IVisualElement, index:int):IVisualElement
		{
			if(element is RwtRadioButton){
				if(radioButtonGroup ==null){
					radioButtonGroup=new RadioButtonGroup();
				}
				RwtRadioButton(element).group=radioButtonGroup;
			}
			return super.addElementAt(element, index);
		}
		
		
	}
}