package com.ri.se.generator;

import java.util.HashMap;

import com.ri.se.generator.deligators.AppConfigurationDelegator;
import com.ri.se.generator.deligators.ApplicationDelegator;
import com.ri.se.generator.deligators.DAODelegator;
import com.ri.se.generator.deligators.EntityDelegator;
import com.ri.se.generator.deligators.MapperDelegator;
import com.ri.se.generator.deligators.ModelListDelegator;
import com.ri.se.generator.deligators.PomDelegator;
import com.ri.se.generator.deligators.ResourceDelegator;
import com.ri.se.generator.deligators.ServiceDelegator;
import com.ri.se.generator.deligators.SettingClasspathDelegator;
import com.ri.se.generator.deligators.SettingProjectDescDelegator;
import com.ri.se.generator.deligators.TemplateHealthCheckDelegator;
import com.ri.se.generator.deligators.YamlConfigurationDelegator;
import com.ri.se.generator.entities.Defination;
import com.ri.se.generator.entities.Schema;

public class RestBuilder {

	public RestBuilder() throws Exception {

		// genrest.json
		/*
		 * File f = new File(""); System.out.println(f.getAbsolutePath());
		 */

		Defination defination = new IOOperations().createDefination();
		/*
		 * defination.setClassName("DAPRequest"); HashMap<String, String> column = new
		 * HashMap<String, String>();
		 * 
		 * defination.setIdColumn("sha256"); defination.setMavenGroupId("com.ri.se");//
		 * "com.ri.se.dap.api.out");
		 * defination.setOut("/home/blockchain/Desktop/tvsp/dap/");
		 * column.put("publicKeyEC", "String "); // EC public key of the account being
		 * requested column.put("sha256", "String"); // may be the root hash of merkle
		 * tree and it will be treated as a message column.put("ecdsa", "String"); //
		 * (signature of sha256|others) column.put("metaData", "String"); // Schema of
		 * the values which were used to create sha256 column.put("others", "String");
		 * // may be used for future if it is null then consider empty string
		 * column.put("role", "String"); // Role, applied for column.put("reqRecDate",
		 * "Date"); // Date of request Received column.put("reqProcessedBy", "Date"); //
		 * Public key of the admin or supper user column.put("reqProcessedDate",
		 * "Date"); defination.setColumn(column);
		 */

		/*
		 * ObjectMapper objectMapper = new ObjectMapper();
		 * System.out.println(objectMapper.writeValueAsString(defination));
		 */
		// String path =
		// "/home/blockchain/Desktop/tvsp/dap/com.ri.se.dap.api/src/main/java/com/ri/se/dap/api/out/";
		// String path = "/home/blockchain/Desktop/tvsp/dap/com.ri.se/";
		Schema schema = new Schema(defination);

		// createMavenProjectStructure(schema2);
		// createPom(schema2);
		// System.out.println(createTableQuery(schema2));
		// System.out.println(new ResourceCreator ().create(schema2));

		// System.out.println(new CreateApplication().createHeader(schema2));
		/*
		 * System.out.println(new ResourceCreator ().createGet(schema2));
		 * 
		 * System.out.println(new ResourceCreator ().createList(schema2));
		 * System.out.println(new ResourceCreator ().createDelete(schema2));
		 * System.out.println(new ResourceCreator ().createPost(schema2));
		 * System.out.println(new ResourceCreator ().createPut(schema2));
		 */

		SourceCodeStructureHandler codeStructureHandler = new SourceCodeStructureHandler(schema);
		/*
		 * System.out.println(codeStructureHandler.getProjectSource()); boolean b =
		 * false; if (b) { return; }
		 */
		Generate gen = new EntityDelegator();
		// Create Entry
		String entity = gen.create(schema);
		new IOOperations().write(codeStructureHandler.getSrcJava() + schema.getClassName() + ".java", entity);

		// Create DAO
		gen = new DAODelegator();
		String dao = gen.create(schema);
		new IOOperations().write(codeStructureHandler.getSrcJava() + schema.getClassName() + "DAO" + ".java", dao);

		// Create Mapper
		gen = new MapperDelegator();
		String mapper = gen.create(schema);
		new IOOperations().write(codeStructureHandler.getSrcJava() + schema.getClassName() + "Mapper" + ".java",
				mapper);

		// Create Service
		gen = new ServiceDelegator();
		String service = gen.create(schema);
		new IOOperations().write(codeStructureHandler.getSrcJava() + schema.getClassName() + "Service" + ".java",
				service);

		// Create Configuration
		gen = new AppConfigurationDelegator();
		String configuration = gen.create(schema);
		new IOOperations().write(codeStructureHandler.getSrcJava() + schema.getClassName() + "Configuration" + ".java",
				configuration);

		// Create List
		gen = new ModelListDelegator();
		String list = gen.create(schema);
		new IOOperations().write(codeStructureHandler.getSrcJava() + schema.getClassName() + "List" + ".java", list);

		gen = new ResourceDelegator();
		String resource = gen.create(schema);
		new IOOperations().write(codeStructureHandler.getSrcJava() + schema.getClassName() + "Resource" + ".java",
				resource);

		gen = new ApplicationDelegator();
		String application = gen.create(schema);
		new IOOperations().write(codeStructureHandler.getSrcJava() + schema.getClassName() + "Application" + ".java",
				application);

		gen = new TemplateHealthCheckDelegator();
		String health = gen.create(schema);
		new IOOperations().write(codeStructureHandler.getSrcJava() + "TemplateHealthCheck" + ".java", health);

		gen = new PomDelegator();
		String pom = gen.create(schema);
		new IOOperations().write(codeStructureHandler.getProjectSource() + "pom" + ".xml", pom);

		gen = new YamlConfigurationDelegator();
		String yml = gen.create(schema);
		new IOOperations().write(codeStructureHandler.getProjectSource() + "config" + ".yml", yml);

		gen = new SettingClasspathDelegator();
		String projFile = gen.create(schema);
		new IOOperations().write(codeStructureHandler.getProjectSource() + ".classpath", projFile);

		gen = new SettingProjectDescDelegator();
		String projDesc = gen.create(schema);
		new IOOperations().write(codeStructureHandler.getProjectSource() + ".project", projDesc);


		String readme = createReadme(codeStructureHandler, schema);
		new IOOperations().write(codeStructureHandler.getProjectSource() + "README.txt", readme);

		
		System.err.println("");
		System.err.println("");
		System.err.println(
				"|/\\ /\\ /\\ /\\ /\\ /\\ /\\ /\\ /\\ /\\ /\\ /\\ /\\ /\\ /\\ /\\ /\\ /\\ /\\ /\\ /\\ /\\ /\\|");
		System.err.println("|....................................................................|");
		System.err.println("|....................... FOR MORE INFORMATION .......................|");
		System.err.println("|......................... READ README.txt ..........................|");
		System.err.println("|....................... OF GENERATED PROJECT .......................|");
		System.err.println("|....................................................................|");
		System.err.println(
				"|/\\ /\\ /\\ /\\ /\\ /\\ /\\ /\\ /\\ /\\ /\\ /\\ /\\ /\\ /\\ /\\ /\\ /\\ /\\ /\\ /\\ /\\ /\\|");
		System.err.println("");
		System.err.println("");
		System.err.println("\t\t\tREADME.txt");
		System.err.println(createReadme(codeStructureHandler, schema));
		System.err.println("....................................................................");
	}

	public String createReadme(SourceCodeStructureHandler codeStructureHandler, Schema schema) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("\n\n");
		buffer.append("\t\tRISE REST BUILDER v1.0:" + "\n");
		buffer.append("Please make sure that following software are already installed:" + "\n");
		buffer.append("\t");
		buffer.append("- Java Development Kit 1.8 or higher verion " + "\n");
		buffer.append("\t");
		buffer.append("- Maven" + "\n");
		buffer.append("\t");
		buffer.append("- Database (tested with mysql)" + "\n");

		buffer.append("1. Open command prompt/terminal/console." + "\n");
		buffer.append("2. Move to folloiwng path." + "\n");
		buffer.append("\t");
		buffer.append(codeStructureHandler.getProjectSource() + "\n");
		buffer.append("3. Execute following command. " + "\n");
		buffer.append("\t");
		buffer.append("mvn package \n");
		buffer.append("It will compile the source code and you will see folloiwng meesage on console \n");
		buffer.append("\t\t\t BUILD SUCCESS \n");
		buffer.append("4. Update configuration file.\n");
		buffer.append("\t");
		buffer.append("Open " + codeStructureHandler.getProjectSource()
				+ "config.yml file and update following database parameters.\n");
		buffer.append("\t\t\t");
		buffer.append("driverClass: <com.mysql.jdbc.Driver>\n");
		buffer.append("\t\t\t");
		buffer.append("url: <jdbc:mysql://localhost:3306/riserestbuilder?createDatabaseIfNotExist=true>\n");
		buffer.append("\t\t\t");
		buffer.append("user: <USER>\n");
		buffer.append("\t\t\t");
		buffer.append("password: <PASSWORD>\n");

		buffer.append("5. Deploy REST API.\n");
		buffer.append("\t");
		buffer.append("Execute following command to deploy REST API Services.\n");
		buffer.append("\t");
		buffer.append("java -cp target/" + schema.getMavenArtifact() + "-" + PomDelegator.pomVersion
				+ "-jar-with-dependencies.jar " + schema.getMavenGroupId() + "." + schema.getClassName()
				+ "Application server config.yml");
		buffer.append("\n");
		buffer.append("6. Open API Documentation.\n");
		buffer.append("\t");
		buffer.append("Open following URL in the browser and you can see the swagger documentation !");
		buffer.append("\n");
		buffer.append("\t\t\t");
		buffer.append("http://localhost:8080/swagger:");
		buffer.append("\n");
		return buffer.toString();
	}

	public static void main(String[] args) throws Exception {
		new RestBuilder();
	}
}
