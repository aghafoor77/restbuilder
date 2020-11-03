package com.ri.se.generator.deligators;

import com.ri.se.generator.Generate;
import com.ri.se.generator.entities.Schema;

public class AppConfigurationDelegator implements Generate {

	@Override
	public String create(Schema schema) {
		return produceAppConfiguration(schema);
	}

	private String produceAppConfiguration(Schema schema) {

		StringBuffer buffer = new StringBuffer();

		buffer.append("package " + schema.getMavenGroupId() + ";" + ln + ln);

		buffer.append("import java.util.Date;" + ln);

		buffer.append("\n" + "import java.util.Collections;\n" + "import java.util.Map;\n" + "\n"
				+ "import javax.validation.Valid;\n" + "import javax.validation.constraints.NotNull;\n" + "\n"
				+ "import org.hibernate.validator.constraints.NotEmpty;\n" + "\n"
				+ "import com.fasterxml.jackson.annotation.JsonProperty;\n"
				+ "import com.google.common.collect.ImmutableMap;\n" + "\n" + "import io.dropwizard.Configuration;\n"
				+ "import io.dropwizard.db.DataSourceFactory;\n"
				+ "import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;\n" + "\n" + "public class "
				+ schema.getClassName() + "Configuration extends Configuration {\n" + "  \n" + "    @NotEmpty\n"
				+ "    private String defaultName = \"" + schema.getClassName() + "\";\n" + "@NotEmpty\n"
				+ "    private String template;\n" + "	\n" + "	@JsonProperty\n" + "	public String getTemplate() {\n"
				+ "		return template;\n" + "	}\n" + "\n" + "	@JsonProperty\n"
				+ "	public void setTemplate(String template) {\n" + "		this.template = template;\n" + "	}\n"
				+ "" + "\n" + "    @Valid\n" + "    @NotNull\n"
				+ "    private DataSourceFactory database = new DataSourceFactory();\n" + "    \n"
				+ "    @JsonProperty(\"swagger\")\n"
				+ "    public SwaggerBundleConfiguration swaggerBundleConfiguration;\n" + "\n" + "\n"
				+ "	public SwaggerBundleConfiguration getSwaggerBundleConfiguration() {\n"
				+ "		return swaggerBundleConfiguration;\n" + "	}\n" + "\n"
				+ "	public void setSwaggerBundleConfiguration(SwaggerBundleConfiguration swaggerBundleConfiguration) {\n"
				+ "		this.swaggerBundleConfiguration = swaggerBundleConfiguration;\n" + "	}\n" + "\n"
				+ "	@NotNull\n"
				+ "    private Map<String, Map<String, String>> viewRendererConfiguration = Collections.emptyMap();\n"
				+ "\n" + "    @JsonProperty\n" + "    public String getDefaultName() {\n"
				+ "        return defaultName;\n" + "    }\n" + "\n" + "    @JsonProperty\n"
				+ "    public void setDefaultName(String defaultName) {\n" + "        this.defaultName = defaultName;\n"
				+ "    }\n" + "\n" + "    @JsonProperty(\"database\")\n"
				+ "    public DataSourceFactory getDataSourceFactory() {\n" + "        return database;\n" + "    }\n"
				+ "\n" + "    @JsonProperty(\"database\")\n"
				+ "    public void setDataSourceFactory(DataSourceFactory dataSourceFactory) {\n"
				+ "        this.database = dataSourceFactory;\n" + "    }\n" + "\n"
				+ "    @JsonProperty(\"viewRendererConfiguration\")\n"
				+ "    public Map<String, Map<String, String>> getViewRendererConfiguration() {\n"
				+ "        return viewRendererConfiguration;\n" + "    }\n" + "\n"
				+ "    @JsonProperty(\"viewRendererConfiguration\")\n"
				+ "    public void setViewRendererConfiguration(Map<String, Map<String, String>> viewRendererConfiguration) {\n"
				+ "        final ImmutableMap.Builder<String, Map<String, String>> builder = ImmutableMap.builder();\n"
				+ "        for (Map.Entry<String, Map<String, String>> entry : viewRendererConfiguration.entrySet()) {\n"
				+ "            builder.put(entry.getKey(), ImmutableMap.copyOf(entry.getValue()));\n" + "        }\n"
				+ "        this.viewRendererConfiguration = builder.build();\n" + "    }\n" + "   \n" + "}\n" + "");
		return buffer.toString();
	}

}
