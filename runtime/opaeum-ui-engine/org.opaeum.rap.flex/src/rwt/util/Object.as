package rwt.util
{
	/**
	 * @author ampie
	 */
	public class Object extends Dummy
	{
		public function Object()
		{
			
		}
		public static function isEmpty(map:Array):Boolean
		{
			for (var key :*in map) {
				return false;
			}
			
			return true;
		}
		
		
		public static function hasMinLength(map, length)
		{
			var i = 0;
			
			for (var key in map)
			{
				if ((++i) >= length) {
					return true;
				}
			}
			
			return false;
		}
		
		
		public static function getLength(map)
		{
			var i = 0;
			
			for (var key in map) {
				i++;
			}
			
			return i;
		}
		
		
		var _shadowedKeys =
			[
				"isPrototypeOf",
				"hasOwnProperty",
				"toLocaleString",
				"toString",
				"valueOf"
			];
		
		
		static function getKeys (map):Array
		{
			var arr = [];
			
			for (var key in map) {
				arr.push(key);
			}
			
			return arr;
		}
		
		
		public static function getKeysAsString(map)
		{
			var keys = getKeys(map);
			if (keys.length == 0) {
				return "";
			}
			
			return '"' + keys.join('\", "') + '"';
		}
		
		
		public static function getValues(map)
		{
			var arr = [];
			
			for (var key in map) {
				arr.push(map[key]);
			}
			
			return arr;
		}
		
		
		public static function mergeWith(target, source, overwrite)
		{
			if (overwrite === undefined) {
				overwrite = true;
			}
			
			for (var key in source)
			{
				if (overwrite || target[key] === undefined) {
					target[key] = source[key];
				}
			}
			
			return target;
		}
		
		
		public static function carefullyMergeWith(target, source)
		{
			return rwt.util.Object.mergeWith(target, source, false);
		}
		
		
		public static  function merge(target, varargs)
		{
			var len = arguments.length;
			
			for (var i=1; i<len; i++) {
				mergeWith(target, arguments[i],true);
			}
			
			return target;
		}
		
		
		public  static function copy(source)
		{
			var clone = {};
			
			for (var key in source) {
				clone[key] = source[key];
			}
			
			return clone;
		}
		
		
		public  static function invert(map)
		{
			var result = {};
			
			for (var key in map) {
				result[map[key].toString()] = key;
			}
			
			return result;
		}
		
		
		public static function getKeyFromValue(obj, value)
		{
			for (var key in obj)
			{ 
				if (obj[key] === value) {
					return key;
				}
			}
			
			return null;
		}
		
		
		public  static function select(key, map) {
			return map[key];
		}
		
		
		public  static function fromArray(array:Array)
		{
			var obj = {};
			
			for (var i=0, l=array.length; i<l; i++)
			{
				obj[array[i].toString()] = true;
			}
			
			return obj;
		}
	}
}
