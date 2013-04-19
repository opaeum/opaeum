package org.opaeum.runtime.environment;

import java.util.Properties;

public class VersionNumber implements Comparable<VersionNumber>{
	private static final String MAJOR = "opaeum.versioning.major";
	private static final String MINOR = "opaeum.versioning.minor";
	private static final String ITERATION = "opaeum.versioning.iteration";
	private static final String PATCH = "opaeum.versioning.patch";
	private static final String BUILD = "opaeum.versioning.build";
	private Integer major;
	private Integer minor;
	private Integer iteration;
	private Integer patch;
	private Integer build;
	public VersionNumber(){
	}
	public VersionNumber(String s){
		parse(s);
	}
	public String getSuffix(){
		return "_" + toVersionString().replaceAll("\\.", "_");
	}
	public void readFrom(Properties p){
		major = parse(p, MAJOR);
		minor = parse(p, MINOR);
		iteration = parse(p, ITERATION);
		patch = parse(p, PATCH);
		build = parse(p, BUILD);
	}
	private Integer parse(Properties p,String major2){
		String s = p.getProperty(major2);
		if(s != null && s.length() > 0){
			try{
				return Integer.parseInt(s);
			}catch(Exception e){
			}
		}
		return null;
	}
	public void writeTo(Properties p){
		put(p, major, MAJOR);
		put(p, minor, MINOR);
		put(p, iteration, ITERATION);
		put(p, patch, PATCH);
		put(p, build, BUILD);
	}
	private void put(Properties p,Integer n,String key){
		if(n == null){
			p.remove(key);
		}else{
			p.put(key, n.toString());
		}
	}
	public String toVersionString(){
		StringBuilder sb = new StringBuilder();
		if(major == null){
			sb.append("0");
		}else{
			sb.append(major);
		}
		if(minor != null){
			sb.append(".");
			sb.append(major);
		}
		if(iteration != null){
			sb.append(".");
			sb.append(iteration);
		}
		if(patch != null){
			sb.append(".");
			sb.append(patch);
		}
		if(build != null){
			sb.append(".");
			sb.append(build);
		}
		return sb.toString();
	}
	public void parse(String version){
		String[] split = version.split("\\.");
		if(split.length > 0 && split[0].length() > 0){
			major = Integer.valueOf(split[0]);
		}
		if(split.length > 1&& split[1].length() > 0){
			minor = Integer.valueOf(split[1]);
		}
		if(split.length > 2 && split[2].length() > 0){
			iteration = Integer.valueOf(split[2]);
		}
		if(split.length > 3 && split[3].length() > 0){
			patch = Integer.valueOf(split[3]);
		}
		if(split.length > 4 && split[4].length() > 0){
			build = Integer.valueOf(split[4]);
		}
	}
	public int compareTo(VersionNumber other){
		if(major != null && other.major != null && !major.equals(other.major)){
			return major - other.major;
		}else if(minor != null && other.minor != null && !minor.equals(other.minor)){
			return minor - other.minor;
		}else if(iteration != null && other.iteration != null && !iteration.equals(other.iteration)){
			return iteration - other.iteration;
		}else if(patch != null && other.patch != null && !patch.equals(other.patch)){
			return patch - other.patch;
		}else if(build != null && other.build != null && !build.equals(other.build)){
			return build - other.build;
		}else{
			return 0;
		}
	}
}
