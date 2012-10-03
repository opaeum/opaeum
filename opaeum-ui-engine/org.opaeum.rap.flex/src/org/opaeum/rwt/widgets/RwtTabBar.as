package org.opaeum.rwt.widgets
{
	import flexlib.controls.SuperTabBar;
	
	import mx.controls.Button;
	import mx.controls.TabBar;
	import mx.core.ClassFactory;
	import mx.core.IFlexDisplayObject;
	import mx.core.mx_internal;

	use namespace mx_internal;
	public class RwtTabBar extends SuperTabBar
	{
		public function RwtTabBar()
		{
			super();
			super.navItemFactory=new ClassFactory(RwtTabBarButton);
		}
		
	}
}