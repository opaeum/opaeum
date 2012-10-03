package org.opaeum.rwt.widgets
{
	import mx.collections.ICollectionView;
	import mx.collections.ListCollectionView;

	public interface IRwtMenu extends IRwtControl
	{
		function invalidateDisplayList():void;	
		function get children():ListCollectionView;
		function showSubMenu(sm:RwtMenu):void;

	}
}