import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Renamer{
	public static void main(String[] args) throws IOException,InterruptedException{
		File dir = new File(".");
		rename(dir);
	}
	public static void rename(File dir) throws InterruptedException,IOException{
		Set<String> ignore = new HashSet<String>();
		ignore.add("target");
		ignore.add("opeum-runtime");
		ignore.add("target");
		ignore.add("target");
		ignore.add("target");
		for(File child:dir.listFiles()){
			if(child.isDirectory()){
				if(!ignore.contains(child.getName())){
					rename(child);
				}
				
			}else{
				replace(child, "opeum", "opaeum");
				replace(child, "Opeum", "Opaeum");
				replace(child, "Opium", "Opaeum");
				replace(child, "opium", "opaeum");
			}
		}
	}
	public static void replace(File child,String s,String replacement) throws InterruptedException,IOException{
		if(child.getName().contains(s)){
			System.out.println("renaming " + child.getName());
			Runtime.getRuntime().exec("git mv " + child.getName() + " " + child.getName().replaceAll(s, replacement)).waitFor();
		}
	}
}
