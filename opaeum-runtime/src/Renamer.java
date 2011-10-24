import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Renamer{
	public static void main(String[] args) throws IOException,InterruptedException{
		File dir = new File("/home/ampie/Workspaces/workspace_sandbox/nakeduml/opaeum-runtime/");
		rename(dir);
	}
	public static void rename(File dir) throws InterruptedException,IOException{
		Set<String> ignore = new HashSet<String>();
		ignore.add("target");
		ignore.add(".git");
		ignore.add("bin");
		ignore.add("classes");
		for(File child:dir.listFiles()){
			if(child.isDirectory()){
				if(!ignore.contains(child.getName())){
					rename(child);
				}
			}
			child = replace(child, "opaeum", "opeum");
			child = replace(child, "Opaeum", "Opeum");
		}
	}
	public static File replace(File child,String s,String replacement) throws InterruptedException,IOException{
		if(child.getName().contains(s)){
			System.out.println("renaming " + child.getName());
			String gitMove = "git mv " + child.getName() + " " + child.getName().replaceAll(s, replacement);
			pipe(Runtime.getRuntime().exec(gitMove, new String[0], child.getParentFile()));
			child = new File(child.getParentFile(), child.getName().replaceAll(s, replacement));
			pipe(Runtime.getRuntime().exec("git add --a", new String[0], child.getParentFile()));
			pipe(Runtime.getRuntime().exec("git commit -a -m 'OpaeumRename'", new String[0], child.getParentFile()));
		}
		return child;
	}
	public static void pipe(Process exec) throws InterruptedException,IOException{
		System.out.println(exec.waitFor());
		BufferedReader r = new BufferedReader(new InputStreamReader(exec.getErrorStream()));
		String line = null;
		while((line = r.readLine()) != null){
			System.out.println(line);
		}
	}
}