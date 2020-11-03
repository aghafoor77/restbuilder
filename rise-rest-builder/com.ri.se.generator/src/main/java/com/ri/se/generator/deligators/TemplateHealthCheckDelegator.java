package com.ri.se.generator.deligators;
import com.ri.se.generator.Generate;
import com.ri.se.generator.entities.Schema;

public class TemplateHealthCheckDelegator implements Generate {

	@Override
	public String create(Schema schema) {
		return produceTemplateHealthCheck(schema);
	}

	private String produceTemplateHealthCheck(Schema schema) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("package " + schema.getMavenGroupId() + ";" + ln + ln);
		buffer.append("import com.codahale.metrics.health.HealthCheck;\n" + 
				"\n" + 
				"public class TemplateHealthCheck extends HealthCheck {\n" + 
				"    private final String template;\n" + 
				"\n" + 
				"    public TemplateHealthCheck(String template) {\n" + 
				"        this.template = template;\n" + 
				"    }\n" + 
				"\n" + 
				"    @Override\n" + 
				"    protected Result check() throws Exception {\n" + 
				"        final String saying = String.format(template, \"TEST\");\n" + 
				"        if (!saying.contains(\"TEST\")) {\n" + 
				"            return Result.unhealthy(\"template doesn't include a name\");\n" + 
				"        }\n" + 
				"        return Result.healthy();\n" + 
				"    }\n" + 
				"}");
		return buffer.toString();
	}

}
