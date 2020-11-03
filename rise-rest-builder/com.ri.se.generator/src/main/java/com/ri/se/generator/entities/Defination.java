package com.ri.se.generator.entities;

import java.util.HashMap;

public class Defination {

	private String mavenGroupId;
	private String mavenArtifact;
	private String className;
	private String out;
	private int port;

	public HashMap<String, String> column = new HashMap();
	public String idColumn;

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public HashMap<String, String> getColumn() {
		return column;
	}

	public void setColumn(HashMap<String, String> column) {
		this.column = column;
	}

	public String getIdColumn() {
		return idColumn;
	}

	public void setIdColumn(String idColumn) {
		this.idColumn = idColumn;
	}

	public String getMavenGroupId() {
		return mavenGroupId;
	}

	public void setMavenGroupId(String mavenGroupId) {
		this.mavenGroupId = mavenGroupId;
	}

	public String getMavenArtifact() {
		return mavenArtifact;
	}

	public void setMavenArtifact(String mavenArtifact) {
		this.mavenArtifact = mavenArtifact;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getOut() {
		return out;
	}

	public void setOut(String out) {
		this.out = out;
	}
	
}
