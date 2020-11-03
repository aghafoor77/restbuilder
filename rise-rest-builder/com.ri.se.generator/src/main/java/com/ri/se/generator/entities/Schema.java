package com.ri.se.generator.entities;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Objects;

public class Schema {

	private String mavenGroupId;
	private String mavenArtifact;
	private String out;
	private String className;
	
	private int port;

	public HashMap<Integer, Entry> column = new HashMap();

	public Schema(Defination schema) {
		
		Entry idEntry = new Entry();
		if (schema.getColumn().containsKey(schema.getIdColumn())) {
			idEntry.setVariable(schema.getIdColumn());
			idEntry.setDatatype(schema.getColumn().get(schema.getIdColumn()));
			schema.getColumn().remove(schema.getIdColumn());
		} else {
			idEntry.setVariable(schema.getIdColumn());
			idEntry.setDatatype("int");
		}
		setIdColumn(schema.getIdColumn());
		//idEntry
		column.put(0, idEntry);
		if (Objects.isNull(schema.getMavenGroupId())) {
			setMavenGroupId("rise");
		} else {
			setMavenGroupId(schema.getMavenGroupId());
		}
		if (Objects.isNull(schema.getMavenArtifact())) {
			setMavenArtifact("rise");
		}else{
			setMavenArtifact(schema.getMavenArtifact());
		}
		
		Iterator iterator = schema.getColumn().keySet().iterator();
		int i=1;
		while (iterator.hasNext()) {
			String variable = iterator.next().toString();
			String dataType = schema.getColumn().get(variable).toString();
			Entry entry = new Entry();
			entry.setDatatype(dataType);
			entry.setVariable(variable);
			column.put(i, entry);
			i++;
		}
		this.className = schema.getClassName();
		this.port = schema.getPort();
		this.out = schema.getOut();
	}

	public String idColumn;

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
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

	public HashMap<Integer, Entry> getColumn() {
		return column;
	}

	public void setColumn(HashMap<Integer, Entry> column) {
		this.column = column;
	}

	public String getOut() {
		return out;
	}

	public void setOut(String out) {
		this.out = out;
	}
}
