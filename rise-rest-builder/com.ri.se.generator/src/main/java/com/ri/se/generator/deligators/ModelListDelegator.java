package com.ri.se.generator.deligators;

import com.ri.se.generator.Generate;
import com.ri.se.generator.entities.Schema;

public class ModelListDelegator implements Generate {

	@Override
	public String create(Schema schema) {
		return produceList(schema);
	}

	private String produceList(Schema schema) {

		StringBuffer buffer = new StringBuffer();

		buffer.append("package " + schema.getMavenGroupId() + ";" + ln + ln);

		buffer.append("import java.util.ArrayList;\n" + "\n" + "public class " + schema.getClassName()
				+ "List extends ArrayList<" + schema.getClassName() + ">{\n" + "\n" + "}\n" + "");

		return buffer.toString();
	}
}
