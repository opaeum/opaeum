package org.opaeum.feature;

import java.io.File;
import java.io.IOException;

public class Renamer{
	public static void main(String[] args) throws IOException,InterruptedException{
		File dir = new File(".");
		rename(dir);
	}
	public static void rename(File dir) throws InterruptedException,IOException{
		for(File child:dir.listFiles()){
			if(child.isDirectory()){
				if(!child.getName().equals("target")){
					rename(child);
				}
			}else if(child.isFile()){
				if(child.getName().equals("opaeum")){
					Runtime.getRuntime().exec("git mv opaeum opaeum").waitFor();
				}
			}
		}
	}
}
