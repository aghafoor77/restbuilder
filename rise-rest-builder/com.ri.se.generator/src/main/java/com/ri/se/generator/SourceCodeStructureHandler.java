package com.ri.se.generator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.ri.se.generator.entities.Schema;

public class SourceCodeStructureHandler {
	
	
	private String groupId;
	private String out;
	
	public SourceCodeStructureHandler(Schema schema) {
		groupId = schema.getMavenGroupId();
		out = schema.getOut();
		if (!(out.endsWith("/") || out.endsWith("\\"))) {
			out += File.separator;
		}
	}
	
	public String getSrcJava(){
		String srcJava = groupId + File.separator + "src" + File.separator + "main" + File.separator + "java" 
				+ File.separator;
		String pakStrcutre = groupId.replace( ".", File.separator);
		String newpath= out+srcJava+pakStrcutre;
		if(new File(newpath).exists()) {
			return newpath+File.separator;
		}
		try {
			
			Files.createDirectories(Paths.get(newpath));
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
		return newpath+File.separator;
	}
	public String getSrcResources() {
		String srcRes = groupId + File.separator + "src" + File.separator + "main" + File.separator + "resources"
				+ File.separator;
		String pakStrcutre = groupId.replace( ".", File.separator);
		String newpath= out + srcRes + pakStrcutre;
		if(new File(newpath).exists()) {
			return newpath+File.separator;
		}
		try {
			
			Files.createDirectories(Paths.get(newpath));
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
		return newpath+File.separator;
	}
	public String getTestJava() {
		String testJava = groupId + File.separator + "src" + File.separator + "test" + File.separator + "java"
				+ File.separator;
		String pakStrcutre = groupId.replace( ".", File.separator);
		String newpath= out + testJava + pakStrcutre;
		if(new File(newpath).exists()) {
			return newpath+File.separator;
		}
		try {
			
			Files.createDirectories(Paths.get(newpath));
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
		return newpath+File.separator;
	}
	public String getTestResources(){
		String testRes = groupId + File.separator + "src" + File.separator + "test" + File.separator + "resources"
				+ File.separator;
		String pakStrcutre = groupId.replace( ".", File.separator);
		String newpath= out + testRes + pakStrcutre;
		if(new File(newpath).exists()) {
			return newpath+File.separator;
		}
		try {
			
			Files.createDirectories(Paths.get(newpath));
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
		return newpath+File.separator;
	}
	
	public String getProjectSource() {
		return out+groupId+File.separator;
	}
}
