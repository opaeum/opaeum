package org.opaeum.rwt.widgets
{
	import flash.utils.getTimer;
	
	import flexlib.controls.tabBarClasses.SuperTab;
	
	import mx.controls.Button;
	import mx.core.IUITextField;
	import mx.core.mx_internal;
	import org.opaeum.rwt.skins.TabSkin;
	
	use namespace mx_internal;
	
	public class RwtTabBarButton extends SuperTab
		
	{
		public var tabPosition:String;
		public function RwtTabBarButton()
		{
			super();
			super.setStyle("skin",TabSkin);
		}
		
		override mx_internal function layoutContents(unscaledWidth:Number, unscaledHeight:Number, offset:Boolean):void
		{
			// TODO Auto Generated method stub
			if(currentSkin is TabSkin ){
				var s:TabSkin=TabSkin( currentSkin);
				if(selected){
					switch(tabPosition){
						case "left":
							s.bottom.right=1;
							s.top.right=1;
							s.right.width=0;
							break;
						case "bottom":
							s.top.height=0;
							s.left.top=1;
							s.right.top=1;
							s.fill.top=0;
							break;
						case "top":
							s.bottom.height=0;
							s.left.bottom=1;
							s.right.bottom=1;
							break;
						case "right":
							s.left.width=0;
							s.bottom.left=1;
							s.top.left=1;
							s.fill.left=0;
							s.right.right=1;//For some reason the border does not display otherwise
							break;
						
					}
				}else{
					switch(tabPosition){
						case "left":
//							s.unselected.top=0;
							break;
						case "bottom":
//							s.unselected.top=0;
							break;
						case "top":
							break;
						case "right":
//							s.unselected.top=0;
							s.unselected.right=1;
							break;
					}
				}
			}
			super.mx_internal::layoutContents(unscaledWidth, unscaledHeight, offset);
		}
			
			override mx_internal function getTextField():IUITextField
			{
				// TODO Auto Generated method stub
				return super.mx_internal::getTextField();
			}
			
			
		}
	}