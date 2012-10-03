package rwt.util{
	public class  String {
	   public static function trim(str) {
	      return str.replace(/^\s+|\s+$/g, "");
	    }
	
	   public static function pad(str, length, ch, addRight)
	    {
	      if (typeof ch === "undefined") {
	        ch = "0";
	      }
	
	      var temp = "";
	
	      for (var i=str.length; i<length; i++) {
	        temp += ch;
	      }
	
	      if (addRight == true){
	        return str + temp;
	      } else {
	        return temp + str;
	      }
	    }
	
	   public static function toFirstUp(str) {
	      return str.charAt(0).toUpperCase() + str.substr(1);
	    }
	
	   public static function contains(str, substring) {
	      return str.indexOf(substring) != -1;
	    }
	
	
	   public static function format(pattern, args)
	    {
	      var str = pattern;
	
	      for (var i=0; i<args.length; i++) {
	        str = str.replace(new RegExp("%" + (i + 1), "g"), args[i]);
	      }
	
	      return str;
	    }
	
	
	   public static function escapeRegexpChars(str) {
	      return str.replace(/([\\\.\(\)\[\]\{\}\^\$\?\+\*])/g, "\\$1");
	    }

  }
}
