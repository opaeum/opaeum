package org.opaeum.rwt.widgets
{
	import flash.display.DisplayObject;
	import flash.events.Event;
	
	import mx.core.ContainerCreationPolicy;
	import mx.core.IFlexDisplayObject;
	import mx.core.INavigatorContent;
	import mx.core.mx_internal;
	import mx.events.FlexEvent;
	
	use namespace mx_internal;
	
	import org.opaeum.rwt.items.RwtTabItem;
	
	import spark.components.NavigatorContent;
	import spark.components.SkinnableContainer;
	import spark.layouts.HorizontalLayout;
	import spark.layouts.VerticalLayout;
	import mx.skins.ProgrammaticSkin;
	import mx.core.IInvalidating;
	import mx.core.mx_internal;
	import mx.controls.Button;
	import mx.core.IUITextField;
	import mx.styles.StyleProxy;
	import flexlib.containers.SuperTabNavigator;
	import flexlib.controls.SuperTabBar;
	import mx.core.Container;
	import mx.controls.scrollClasses.ScrollBar;
	import mx.core.ScrollPolicy;
	import mx.controls.PopUpButton;
	import mx.containers.Canvas;
	import mx.core.LayoutDirection;
	import flashx.textLayout.formats.Direction;
	import mx.containers.BoxDirection;
	import mx.core.EdgeMetrics;
	import org.opaeum.rwt.SWT;
	
	use namespace mx_internal;
	public class RwtTabFolder extends SuperTabNavigator implements IRwtControl
	{
		
		public var items:Array=new Array();
		private var position:String;
		public function RwtTabFolder(style:Array)
		{
			super();
			super.setStyle("paddingTop",0);
			super.setStyle("paddingBottom",0);
			super.setStyle("paddingLeft",0);
			super.setStyle("paddingRight",0);
			super.creationPolicy=ContainerCreationPolicy.ALL;
			tabEnabled = true;
			tabFocusEnabled = true;
			hasFocusableChildren = true;
			super.popUpButtonPolicy=POPUPPOLICY_OFF;
			for(var i:Number=0;i<style.length;i++){
				switch(style[i]){
					case SWT.LEFT:
						position="left";
						break;
					case SWT.RIGHT:
						position="right";
						break;
					case SWT.TOP:
						position="top";
						break;
					case SWT.BOTTOM:
						position="bottom";
						break;
				}
			}
		}
		
		public function addTabItem(item:RwtTabItem):void{
			if(item.control is INavigatorContent){
				var nc1:INavigatorContent=INavigatorContent(item.control);
				nc1["label"]=item.text;
				addChildAt(nc1 as DisplayObject,items.indexOf(item));
			}else{
				var nc:Canvas = new Canvas();
				nc.creationPolicy=ContainerCreationPolicy.ALL;
				nc.verticalScrollPolicy=ScrollPolicy.OFF;
				nc.horizontalScrollPolicy=ScrollPolicy.OFF;
				nc.label=item.text;
				nc.percentWidth=100;
				nc.percentHeight=100;
				addChildAt(nc,items.indexOf(item));
				nc.addEventListener(FlexEvent.CREATION_COMPLETE,function(ev:Event):void{
					item.control.visible=true;
					item.control.percentHeight=100;
					item.control.percentWidth=100;
					nc.addElement(item.control);
				});
			}
			
		}
		
		override protected function updateDisplayList(unscaledWidth:Number, unscaledHeight:Number):void {
			tabBar.setStyle("paddingBottom", 0);
			tabBar.setStyle("paddingTop", 0);
			setStyle("paddingTop", 0);
			for(var i:Number=0; i < tabBar.numChildren;i++){
				RwtTabBarButton(tabBar.getChildAt(i)).tabPosition=position;
				RwtTabBarButton(tabBar.getChildAt(i)).invalidateDisplayList();
			}
			switch(position){
				case "top":
					tabBar.direction="horizontal";
					super.updateDisplayList(unscaledWidth, unscaledHeight);
					border.move(border.x,border.y+1);
					break;
				case "bottom":
					tabBar.direction="horizontal";
					super.updateDisplayList(unscaledWidth, unscaledHeight);
					border.move(border.x,border.y-tabBarHeight);
					holder.move(holder.x, height- holder.height);
					break;
				case "left":
					tabBar.direction="vertical";
					super.updateDisplayList(unscaledWidth, unscaledHeight);
					holder.setActualSize(tabBarWidth,unscaledHeight);
					holder.move(1,0);
					border.move(tabBarWidth,0);
					border.setActualSize(width-tabBarWidth,unscaledHeight);
					break;
				case "right":
					tabBar.direction="vertical";
					super.updateDisplayList(unscaledWidth, unscaledHeight);
					border.setActualSize(width-tabBarWidth,unscaledHeight);
					holder.setActualSize(tabBarWidth,unscaledHeight);
					holder.move(width-tabBarWidth-1, 0);
					border.move(0,0);
					break;
			}
			
		}
		override protected function adjustFocusRect(object:DisplayObject = null):void {
			super.adjustFocusRect(object);
			
			// Undo changes made by superclass:
			// "Adjust the focus rect so it is below the tabs"
			// - and redo the same thing with width instead of height
			var focusObj:IFlexDisplayObject = IFlexDisplayObject(getFocusObject());
			
			if (focusObj)
			{
				switch(position){
					case "bottom":
						//						focusObj.move(focusObj.x, focusObj.y - tabBar.width)
						focusObj.move(focusObj.x, focusObj.y - tabBarHeight);
						break;
				}
				if (focusObj is IInvalidating){
					IInvalidating(focusObj).validateNow();
				}
				else if (focusObj is ProgrammaticSkin){
					ProgrammaticSkin(focusObj).validateNow();
				}
			}
		}		
		
		protected function get tabBarWidth():Number {
			if(tabBar.direction!="vertical"){
				throw new Error();
			}
			var tabWidth:Number = getStyle("tabWidth");
			
			if (isNaN(tabWidth))
				tabWidth = tabBar.getExplicitOrMeasuredWidth();
			
			return tabWidth - 1;
		}

		
		
		override public function addChildAt(element:DisplayObject, index:int):DisplayObject
		{
			//			ignore
			var i=numChildren; 
			if(element is INavigatorContent){
				super.addChildAt(element,index);
			}
			return element;
		}
		
		override protected function createTabBar():SuperTabBar
		{
			return new RwtTabBar();
		}
		override protected function get contentX():Number
		{
			var paddingLeft:Number = getStyle("paddingLeft");
			if (isNaN(paddingLeft)){
				paddingLeft = 0;
			}
			switch(this.position){
				case "bottom":
				case "top":
				case "right":
					return super.contentX;
				case "left":
					return tabBarWidth + paddingLeft;
					
			}
			return super.contentX;
		}
		override protected function get contentY():Number
		{
			switch(this.position){
				case "bottom":
				case "left":
				case "right":
					return 0;
				case "top":
					return super.contentY;
			}
			return super.contentY;
		}
		override protected function get contentHeight():Number {
			switch(this.position){
				case "bottom":
				case "top":
					return super.contentHeight;
				case "left":
				case "right":
					var vm:EdgeMetrics = viewMetricsAndPadding;
					return unscaledHeight - vm.top - vm.bottom;
			}
			return super.contentHeight;
		}
		
		override protected function get contentWidth():Number {
			switch(this.position){
				case "bottom":
				case "top":
					return super.contentWidth;
				case "left":
				case "right":
					var vm:EdgeMetrics = viewMetricsAndPadding;
					return unscaledWidth - tabBarWidth - vm.left - vm.right;
			}
			return super.contentHeight;
		}
		
		override protected function measure():void {
			super.measure();
			
			// remove the height addition made by superclass (tabs are
			// now on the side, not the top)
			var removedHeight:Number = tabBarWidth;
			measuredMinHeight -= removedHeight;
			measuredHeight -= removedHeight;
			
			// add width (same reason as above)
			var addedWidth:Number = tabBarWidth;
			measuredMinWidth += addedWidth;
			measuredWidth += addedWidth;
			
			
			
			// Make sure there is at least enough room
			// to draw all tabs at their minimum size.
			var tabWidth:Number = getStyle("tabWidth");
			if (isNaN(tabWidth)) tabWidth = 0;
			
			var minTabBarWidth:Number = numChildren * Math.max(tabWidth, 30);
			
			// Add view metrics.
			var vm:EdgeMetrics = viewMetrics;
			minTabBarWidth += (vm.top + vm.bottom);
			
			// Add horizontal gaps.
			if (numChildren > 1) 
				minTabBarWidth += (getStyle("horizontalGap") * (numChildren - 1));
			
			if (measuredHeight < minTabBarWidth) measuredHeight = minTabBarWidth+tabBarWidth;
		}		
	}
}