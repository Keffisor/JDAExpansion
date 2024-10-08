package com.jdaplug.plugins;

import java.io.File;
import java.util.List;

public class Plugin {

	private File file = null;
	private String path;
	private String name;
	private JavaPlugin main;
	private String author = null;
	private String description = null;
	private String version = null;
	private List<String> depends = null;
	
	public Plugin(File file, String path, String name, JavaPlugin main, String author, String description, String version, List<String> depends) {
    	this.file = file;
		this.path = path;
    	this.name = name;
    	this.main = main;
    	this.author = author;
    	this.description = description;
    	this.version = version;
    	this.depends = depends;
    }
	
	public String getName() {
		return name;
	}
	
	public String getPath() {
		return path;
	}
	
	public JavaPlugin getMainClass() {
		return main;
	}
	
	public String getAuthor() {
	    return author;	
	}
	
	public String getDescription() {
	   return description;	
	}
	
	public String getVersion() {
		if(version == null) return "v1.0";
	    return version;
	}
	
	public List<String> getDependencies() {
		return depends;
	}
	
	public File getFile() {
		return file;
	}
}
