package com.ri.se.generator.deligators;

import com.ri.se.generator.Generate;
import com.ri.se.generator.entities.Schema;

public class SettingProjectDescDelegator implements Generate {

	@Override
	public String create(Schema schema) {
		return produceProjectDesc(schema);
	}

	private String produceProjectDesc(Schema schema) {
		return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" + 
				"<projectDescription>\n" + 
				"	<name>"+schema.getMavenGroupId()+"</name>\n" + 
				"	<comment></comment>\n" + 
				"	<projects>\n" + 
				"	</projects>\n" + 
				"	<buildSpec>\n" + 
				"		<buildCommand>\n" + 
				"			<name>org.eclipse.jdt.core.javabuilder</name>\n" + 
				"			<arguments>\n" + 
				"			</arguments>\n" + 
				"		</buildCommand>\n" + 
				"		<buildCommand>\n" + 
				"			<name>org.eclipse.m2e.core.maven2Builder</name>\n" + 
				"			<arguments>\n" + 
				"			</arguments>\n" + 
				"		</buildCommand>\n" + 
				"	</buildSpec>\n" + 
				"	<natures>\n" + 
				"		<nature>org.eclipse.jdt.core.javanature</nature>\n" + 
				"		<nature>org.eclipse.m2e.core.maven2Nature</nature>\n" + 
				"	</natures>\n" + 
				"</projectDescription>";
	}
}
