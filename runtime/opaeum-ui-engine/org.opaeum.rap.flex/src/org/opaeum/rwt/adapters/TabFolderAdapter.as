package org.opaeum.rwt.adapters
{
	import org.opaeum.rap.WidgetAdapter;
	import org.opaeum.rwt.widgets.RwtTabFolder;
	
	public class TabFolderAdapter extends WidgetAdapter
	{
		public function TabFolderAdapter()
		{
			super();
		}
		
		override protected function newInstance(args:*):Object
		{
			var result:RwtTabFolder=new RwtTabFolder(args.style);
			return result;
		}
		
		
	}
}