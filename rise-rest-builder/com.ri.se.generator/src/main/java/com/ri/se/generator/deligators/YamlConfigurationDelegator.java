package com.ri.se.generator.deligators;

import com.ri.se.generator.Generate;
import com.ri.se.generator.entities.Schema;

public class YamlConfigurationDelegator implements Generate {

	@Override
	public String create(Schema schema) {
		return produceYamlConfiguration(schema);
	}

	private String produceYamlConfiguration(Schema schema) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("defaultName: ${DW_DEFAULT_NAME:-RISE"+schema.getClassName()+"}\n" + 
				"template: RISE, %s!\n" + 
				"\n" + 
				"\n" + 
				"# Database settings.\n" + 
				"database:\n" + 
				"  driverClass: com.mysql.jdbc.Driver\n" + 
				"  url: jdbc:mysql://localhost:3306/"+schema.getMavenArtifact().toLowerCase()+"?createDatabaseIfNotExist=true\n" + 
				"  user: <CHNAGE_ME>\n" + 
				"  password: <CHNAGE_ME>\n" + 
				"  maxWaitForConnection: 1s\n" + 
				"  validationQuery: \"SELECT 1\"\n" + 
				"  validationQueryTimeout: 3s\n" + 
				"  minSize: 8\n" + 
				"  maxSize: 32\n" + 
				"  checkConnectionWhileIdle: false\n" + 
				"  evictionInterval: 10s\n" + 
				"  minIdleTime: 1 minute\n" + 
				"  checkConnectionOnBorrow: true\n" + 
				"       \n" + 
				"# Logging settings.\n" + 
				"logging:\n" + 
				"\n" + 
				"  # The default level of all loggers. Can be OFF, ERROR, WARN, INFO, DEBUG, TRACE, or ALL.\n" + 
				"  level: INFO\n" + 
				"\n" + 
				"  # Logger-specific levels.\n" + 
				"  loggers:\n" + 
				"\n" + 
				"    # Sets the level for 'com.bixsystem.ledger' to DEBUG.\n" + 
				"    com.ri.se: INFO\n" + 
				"\n" + 
				"    # Redirects SQL logs to a separate file\n" + 
				"    org.hibernate.SQL:\n" + 
				"      level: INFO\n" + 
				"\n" + 
				"# the key needs to match the suffix of the renderer\n" + 
				"viewRendererConfiguration:\n" + 
				"    .ftl:\n" + 
				"        strict_syntax: yes\n" + 
				"        whitespace_stripping: yes\n" + 
				"\n" + 
				"swagger:\n" + 
				"  resourcePackage: "+schema.getMavenGroupId()+".utils"+"");
		return buffer.toString();
	}
}
