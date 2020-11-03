package com.ri.se.generator.deligators;

import com.ri.se.generator.Generate;
import com.ri.se.generator.entities.Schema;

public class SettingClasspathDelegator implements Generate {

	@Override
	public String create(Schema schema) {
		return produceSettingClasspath(schema);
	}

	private String produceSettingClasspath(Schema schema) {
		return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" + 
				"<classpath>\n" + 
				"	<classpathentry kind=\"src\" output=\"target/classes\" path=\"src/main/java\">\n" + 
				"		<attributes>\n" + 
				"			<attribute name=\"optional\" value=\"true\"/>\n" + 
				"			<attribute name=\"maven.pomderived\" value=\"true\"/>\n" + 
				"		</attributes>\n" + 
				"	</classpathentry>\n" + 
				"	<classpathentry excluding=\"**\" kind=\"src\" output=\"target/classes\" path=\"src/main/resources\">\n" + 
				"		<attributes>\n" + 
				"			<attribute name=\"maven.pomderived\" value=\"true\"/>\n" + 
				"		</attributes>\n" + 
				"	</classpathentry>\n" + 
				"	<classpathentry kind=\"src\" output=\"target/test-classes\" path=\"src/test/java\">\n" + 
				"		<attributes>\n" + 
				"			<attribute name=\"optional\" value=\"true\"/>\n" + 
				"			<attribute name=\"maven.pomderived\" value=\"true\"/>\n" + 
				"			<attribute name=\"test\" value=\"true\"/>\n" + 
				"		</attributes>\n" + 
				"	</classpathentry>\n" + 
				"	<classpathentry excluding=\"**\" kind=\"src\" output=\"target/test-classes\" path=\"src/test/resources\">\n" + 
				"		<attributes>\n" + 
				"			<attribute name=\"maven.pomderived\" value=\"true\"/>\n" + 
				"			<attribute name=\"test\" value=\"true\"/>\n" + 
				"		</attributes>\n" + 
				"	</classpathentry>\n" + 
				"	<classpathentry kind=\"con\" path=\"org.eclipse.jdt.launching.JRE_CONTAINER/org.eclipse.jdt.internal.debug.ui.launcher.StandardVMType/J2SE-1.5\">\n" + 
				"		<attributes>\n" + 
				"			<attribute name=\"maven.pomderived\" value=\"true\"/>\n" + 
				"		</attributes>\n" + 
				"	</classpathentry>\n" + 
				"	<classpathentry kind=\"con\" path=\"org.eclipse.m2e.MAVEN2_CLASSPATH_CONTAINER\">\n" + 
				"		<attributes>\n" + 
				"			<attribute name=\"maven.pomderived\" value=\"true\"/>\n" + 
				"		</attributes>\n" + 
				"	</classpathentry>\n" + 
				"	<classpathentry kind=\"output\" path=\"target/classes\"/>\n" + 
				"</classpath>";
	}
}
