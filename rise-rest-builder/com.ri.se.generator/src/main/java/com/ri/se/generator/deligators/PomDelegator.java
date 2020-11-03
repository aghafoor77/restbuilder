package com.ri.se.generator.deligators;

import com.ri.se.generator.Generate;
import com.ri.se.generator.entities.Schema;

public class PomDelegator implements Generate {

	public static String pomVersion = "1.0.0";
	@Override
	public String create(Schema schema) {
		return producePom(schema);
	}

	private String producePom(Schema schema) {
		StringBuffer buff = new StringBuffer();
		buff.append("<project xmlns=\"http://maven.apache.org/POM/4.0.0\"\n"
				+ "	xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n"
				+ "	xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd\">\n"
				+ "	<modelVersion>4.0.0</modelVersion>\n" + "	<groupId>" + schema.getMavenGroupId() + "</groupId>\n"
				+ "	<artifactId>" + schema.getMavenArtifact() + "</artifactId>\n"
				+ "	<version>"+pomVersion +"</version>\n");
		buff.append("<properties>\n" + 
				"		<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>\n" + 
				"		<project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>\n" + 
				"		<maven.compiler.source>1.8</maven.compiler.source>\n" + 
				"		<maven.compiler.target>1.8</maven.compiler.target>\n" + 
				"	</properties>\n" + 
				"	<dependencyManagement>\n" + 
				"		<dependencies>\n" + 
				"			<dependency>\n" + 
				"				<groupId>io.dropwizard</groupId>\n" + 
				"				<artifactId>dropwizard-bom</artifactId>\n" + 
				"				<version>1.1.2</version>\n" + 
				"				<type>pom</type>\n" + 
				"				<scope>import</scope>\n" + 
				"			</dependency>\n" + 
				"		</dependencies>\n" + 
				"	</dependencyManagement>\n" + 
				"\n" + 
				"	<dependencies>\n" + 
				"		<dependency>\n" + 
				"			<groupId>com.smoketurner</groupId>\n" + 
				"			<artifactId>dropwizard-swagger</artifactId>\n" + 
				"			<version>1.0.6-1</version>\n" + 
				"		</dependency>\n" + 
				"		<dependency>\n" + 
				"			<groupId>io.dropwizard</groupId>\n" + 
				"			<artifactId>dropwizard-auth</artifactId>\n" + 
				"			</dependency>\n" + 
				"		<dependency>\n" + 
				"			<groupId>io.dropwizard</groupId>\n" + 
				"			<artifactId>dropwizard-jdbi</artifactId>\n" + 
				"			</dependency>\n" + 
				"		<dependency>\n" + 
				"			<groupId>io.dropwizard</groupId>\n" + 
				"			<artifactId>dropwizard-core</artifactId>\n" + 
				"		</dependency>\n" + 
				"		<dependency>\n" + 
				"			<groupId>io.dropwizard</groupId>\n" + 
				"			<artifactId>dropwizard-auth</artifactId>\n" + 
				"		</dependency>\n" + 
				"		<dependency>\n" + 
				"			<groupId>org.glassfish.jersey.core</groupId>\n" + 
				"			<artifactId>jersey-client</artifactId>\n" + 
				"		</dependency>\n" + 
				"		<dependency>\n" + 
				"			<groupId>org.glassfish.jersey.media</groupId>\n" + 
				"			<artifactId>jersey-media-json-jackson</artifactId>\n" + 
				"		</dependency>\n" + 
				"		<dependency>\n" + 
				"			<groupId>io.dropwizard</groupId>\n" + 
				"			<artifactId>dropwizard-client</artifactId>\n" + 
				"		</dependency>\n" + 
				"		<dependency>\n" + 
				"			<groupId>io.dropwizard</groupId>\n" + 
				"			<artifactId>dropwizard-assets</artifactId>\n" + 
				"		</dependency>\n" + 
				"		<dependency>\n" + 
				"			<groupId>io.dropwizard</groupId>\n" + 
				"			<artifactId>dropwizard-http2</artifactId>\n" + 
				"		</dependency>\n" + 
				"		<dependency>\n" + 
				"			<groupId>io.dropwizard</groupId>\n" + 
				"			<artifactId>dropwizard-hibernate</artifactId>\n" + 
				"		</dependency>\n" + 
				"		<dependency>\n" + 
				"			<groupId>io.dropwizard</groupId>\n" + 
				"			<artifactId>dropwizard-migrations</artifactId>\n" + 
				"		</dependency>\n" + 
				"		<dependency>\n" + 
				"			<groupId>io.dropwizard</groupId>\n" + 
				"			<artifactId>dropwizard-views-freemarker</artifactId>\n" + 
				"		</dependency>\n" + 
				"		<dependency>\n" + 
				"			<groupId>mysql</groupId>\n" + 
				"			<artifactId>mysql-connector-java</artifactId>\n" + 
				"			<version>5.1.22</version>\n" + 
				"		</dependency>\n" + 
				"		<dependency>\n" + 
				"			<groupId>io.dropwizard</groupId>\n" + 
				"			<artifactId>dropwizard-views-mustache</artifactId>\n" + 
				"		</dependency>\n" + 
				"		<dependency>\n" + 
				"			<groupId>io.dropwizard</groupId>\n" + 
				"			<artifactId>dropwizard-metrics-graphite</artifactId>\n" + 
				"		</dependency>\n" + 
				"		<dependency>\n" + 
				"			<groupId>com.h2database</groupId>\n" + 
				"			<artifactId>h2</artifactId>\n" + 
				"		</dependency>\n" + 
				"		<dependency>\n" + 
				"			<groupId>io.dropwizard</groupId>\n" + 
				"			<artifactId>dropwizard-testing</artifactId>\n" + 
				"			<scope>test</scope>\n" + 
				"		</dependency>\n" + 
				"		<dependency>\n" + 
				"			<groupId>org.mockito</groupId>\n" + 
				"			<artifactId>mockito-core</artifactId>\n" + 
				"			<scope>test</scope>\n" + 
				"		</dependency>\n" + 
				"		<dependency>\n" + 
				"			<groupId>org.apache.pdfbox</groupId>\n" + 
				"			<artifactId>pdfbox</artifactId>\n" + 
				"			<version>2.0.11</version>\n" + 
				"		</dependency>\n" + 
				"		<dependency>\n" + 
				"			<groupId>org.web3j</groupId>\n" + 
				"			<artifactId>core</artifactId>\n" + 
				"			<version>4.5.11</version>\n" + 
				"		</dependency>\n" + 
				"		<dependency>\n" + 
				"			<groupId>javax.xml.bind</groupId>\n" + 
				"			<artifactId>jaxb-api</artifactId>\n" + 
				"			<version>2.1</version>\n" + 
				"		</dependency>\n" + 
				"	</dependencies>\n" + 
				"");
		
		buff.append("<build>\n" + 
				"		<plugins>\n" + 
				"			<plugin>\n" + 
				"				<groupId>org.apache.maven.plugins</groupId>\n" + 
				"				<artifactId>maven-compiler-plugin</artifactId>\n" + 
				"				<version>3.6.1</version>\n" + 
				"				<configuration>\n" + 
				"					<source>1.8</source>\n" + 
				"					<target>1.8</target>\n" + 
				"				</configuration>\n" + 
				"			</plugin>\n" + 
				"			<plugin>\n" + 
				"				<groupId>org.apache.maven.plugins</groupId>\n" + 
				"				<artifactId>maven-assembly-plugin</artifactId>\n" + 
				"				<version>3.1.1</version>\n" + 
				"\n" + 
				"				<configuration>\n" + 
				"					<descriptorRefs>\n" + 
				"						<descriptorRef>jar-with-dependencies</descriptorRef>\n" + 
				"					</descriptorRefs>\n" + 
				"					<archive>\n" + 
				"						<manifest>\n" + 
				"							<mainClass>"+schema.getMavenGroupId()+"."+schema.getClassName()+"Application</mainClass>\n" + 
				"						</manifest>\n" + 
				"					</archive>\n" + 
				"				</configuration>\n" + 
				"				<executions>\n" + 
				"					<execution>\n" + 
				"						<id>make-assembly</id>\n" + 
				"						<phase>package</phase>\n" + 
				"						<goals>\n" + 
				"							<goal>single</goal>\n" + 
				"						</goals>\n" + 
				"					</execution>\n" + 
				"				</executions>\n" + 
				"			</plugin>\n" + 
				"		</plugins>\n" + 
				"	</build>\n"
				+ "</project>");
		return buff.toString();

	}
}
