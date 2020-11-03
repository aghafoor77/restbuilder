package com.ri.se.generator.deligators;

import java.util.Objects;

import com.ri.se.generator.Generate;
import com.ri.se.generator.entities.Schema;

public class ApplicationDelegator implements Generate {

	@Override
	public String create(Schema schema) {
		return produceApplication(schema);
	}

	private String produceApplication(Schema schema) {
		StringBuffer buffer = new StringBuffer();
		if (!Objects.isNull(schema.getMavenGroupId())) {
			buffer.append("package " + schema.getMavenGroupId() + ";" + ln);
			buffer.append(ln);
		}

		buffer.append("import java.net.SocketException;" + ln);
		buffer.append("import java.util.Map;" + ln);

		buffer.append("import javax.sql.DataSource;" + ln);

		buffer.append("import org.glassfish.jersey.media.multipart.MultiPartFeature;" + ln);
		buffer.append("import org.skife.jdbi.v2.DBI;" + ln);

		buffer.append("import io.dropwizard.Application;" + ln);
		buffer.append("import io.dropwizard.assets.AssetsBundle;" + ln);
		buffer.append("import io.dropwizard.configuration.EnvironmentVariableSubstitutor;" + ln);
		buffer.append("import io.dropwizard.configuration.SubstitutingSourceProvider;" + ln);
		buffer.append("import io.dropwizard.db.DataSourceFactory;" + ln);
		buffer.append("import io.dropwizard.migrations.MigrationsBundle;" + ln);
		buffer.append("import io.dropwizard.setup.Bootstrap;" + ln);
		buffer.append("import io.dropwizard.setup.Environment;" + ln);
		buffer.append("import io.dropwizard.views.ViewBundle;" + ln);
		buffer.append("import io.federecio.dropwizard.swagger.SwaggerBundle;" + ln);
		buffer.append("import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;" + ln);

		buffer.append(ln);
		buffer.append(createBody(schema));
		return buffer.toString();
	}

	private String createBody(Schema schema) {
		StringBuffer buffer = new StringBuffer();
		buffer.append(ln);
		buffer.append(attHeaderPublic + " " + attHeaderClass + " " + schema.getClassName()
				+ "Application extends Application<" + schema.getClassName() + "Configuration> " + bs + ln + ln);
		buffer.append(ln + "\t");
		buffer.append(attHeaderPublic + " " + schema.getClassName() + "Application()" + bs + ln);
		buffer.append("\t\t");
		buffer.append("" + ln);
		buffer.append("\t");
		buffer.append(be + ln);
		buffer.append(ln);
		buffer.append(createName(schema));
		buffer.append(ln + ln);
		buffer.append(createMain(schema));
		buffer.append(ln + ln);
		buffer.append(createInit(schema));
		buffer.append(ln + ln);

		buffer.append(createRun(schema));
		buffer.append(ln + ln);
		/*
		 * buffer.append(createPut(schema));
		 */buffer.append(ln);
		buffer.append(be + ln);
		return buffer.toString();
	}

	public String createName(Schema schema) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("\t");
		buffer.append("@Override\n" + "	public String getName() {\n" + "		return \"" + schema.getClassName()
				+ "\";\n" + "	}");
		return buffer.toString();
	}

	public String createMain(Schema schema) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("\t");
		buffer.append("public static void main(String[] args) throws Exception {\n" + "\n" + "		new "
				+ schema.getClassName() + "Application().run(args);\n" + "	}");
		return buffer.toString();
	}

	public String createInit(Schema schema) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("\t");
		buffer.append("@Override" + ln);
		buffer.append("\t");
		buffer.append(
				"public void initialize(Bootstrap<" + schema.getClassName() + "Configuration> bootstrap)" + bs + ln);

		buffer.append("\t");
		buffer.append("	bootstrap.setConfigurationSourceProvider(new SubstitutingSourceProvider(" + ln);
		buffer.append(
				"			bootstrap.getConfigurationSourceProvider(), new EnvironmentVariableSubstitutor(false)));"
						+ ln);
		buffer.append("\t\t");
		buffer.append("bootstrap.addBundle(new AssetsBundle());" + ln);
		buffer.append("\t\t");
		buffer.append(
				"bootstrap.addBundle(new MigrationsBundle<" + schema.getClassName() + "Configuration>()" + bs + ln);
		buffer.append("\t\t\t");
		buffer.append("@Override" + ln);
		buffer.append("\t\t\t");
		buffer.append("public DataSourceFactory getDataSourceFactory(" + schema.getClassName()
				+ "Configuration configuration)" + bs + ln);
		buffer.append("\t\t\t\t");
		buffer.append("return configuration.getDataSourceFactory();" + ln);
		buffer.append("\t\t\t");
		buffer.append(be + ln);
		buffer.append("\t\t");
		buffer.append("});");
		buffer.append(ln);
		buffer.append("\t\t");
		buffer.append("bootstrap.addBundle(new ViewBundle<" + schema.getClassName() + "Configuration>()" + bs + ln);
		buffer.append("\t\t\t");
		buffer.append("@Override" + ln);
		buffer.append("\t\t\t");
		buffer.append("public Map<String, Map<String, String>> getViewConfiguration(" + schema.getClassName()
				+ "Configuration configuration) " + bs + ln);
		buffer.append("\t\t\t\t");
		buffer.append("return configuration.getViewRendererConfiguration();" + ln);
		buffer.append("\t\t\t");
		buffer.append("}" + ln);
		buffer.append("\t\t");
		buffer.append("});" + ln);
		buffer.append("\t\t");
		buffer.append("bootstrap.addBundle(new SwaggerBundle<" + schema.getClassName() + "Configuration>()" + bs + ln);
		buffer.append("\t\t\t");
		buffer.append("@Override" + ln);
		buffer.append("\t\t\t");
		buffer.append("public SwaggerBundleConfiguration getSwaggerBundleConfiguration(" + schema.getClassName()
				+ "Configuration configuration)" + bs + ln);
		buffer.append("\t\t\t");
		buffer.append("return configuration.getSwaggerBundleConfiguration();" + ln);
		buffer.append("\t\t\t");
		buffer.append("}" + ln);
		buffer.append("\t\t");
		buffer.append("});" + ln);
		buffer.append("\t");
		buffer.append("}" + ln);

		return buffer.toString();
	}

	public String createRun(Schema schema) {
		StringBuffer buffer = new StringBuffer();

		buffer.append("\t");
		buffer.append("private static final String SQL = \"sql\";" + ln + ln);
		buffer.append("\t");
		buffer.append("@Override" + ln);
		buffer.append("\t");
		buffer.append(
				"public void run("+schema.getClassName()+"Configuration configuration, Environment environment) throws SocketException {"
						+ ln);
		buffer.append("\t\t");
		buffer.append(
				"final DataSource dataSource = configuration.getDataSourceFactory().build(environment.metrics(), SQL);"
						+ ln);
		buffer.append("\t\t");
		buffer.append("DBI dbi = new DBI(dataSource);" + ln);
		buffer.append("\t\t");
		buffer.append("" + schema.getClassName() + "Service " + schema.getClassName().toLowerCase()
				+ "Service = dbi.onDemand(" + schema.getClassName() + "Service.class);" + ln);
		buffer.append("\t\t");
		buffer.append(schema.getClassName().toLowerCase() + "Service.create();" + ln);
		buffer.append("\t\t");
		buffer.append(schema.getClassName() + "Resource " + schema.getClassName().toLowerCase() + "Resource = new "
				+ schema.getClassName() + "Resource(" + schema.getClassName().toLowerCase() + "Service);" + ln);

		buffer.append("\t\t");

		buffer.append(
				"final TemplateHealthCheck healthCheck = new TemplateHealthCheck(configuration.getTemplate());" + ln);
		buffer.append("\t\t");
		buffer.append("environment.healthChecks().register(\"template\", healthCheck);" + ln);

		buffer.append("\t\t");
		buffer.append("environment.jersey().register(MultiPartFeature.class);" + ln);
		buffer.append("\t\t");
		buffer.append("environment.jersey().register(" + schema.getClassName().toLowerCase() + "Resource);" + ln);
		buffer.append("\t");
		buffer.append("}");
		return buffer.toString();
	}

}
