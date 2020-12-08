package com.ri.se.generator.deligators;

import java.util.HashMap;
import java.util.Objects;

import com.ri.se.generator.Generate;
import com.ri.se.generator.entities.Entry;
import com.ri.se.generator.entities.Schema;

public class EntityDelegator implements Generate {

	@Override
	public String create(Schema schema) {
		return produceEntity(schema);
	}

	private String produceEntity(Schema schema) {

		HashMap<Integer, Entry> column = schema.getColumn();
		// column.get(
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("");

		if (!Objects.isNull(schema.getMavenGroupId())) {
			stringBuffer.append("package " + schema.getMavenGroupId() + ";" + ln);
			stringBuffer.append(ln);
		}

		stringBuffer.append("import java.util.Date;" + ln);

		stringBuffer.append("import javax.persistence.Column;" + ln);
		stringBuffer.append("import javax.persistence.Entity;" + ln);
		stringBuffer.append("import javax.persistence.Id;" + ln);
		stringBuffer.append("import javax.persistence.Table;" + ln);
		stringBuffer.append("import javax.xml.bind.annotation.XmlRootElement;" + ln);

		stringBuffer.append("@Entity" + ln);
		stringBuffer.append("@Table(name = \"" + schema.getClassName() + "\")" + ln);
		stringBuffer.append("@XmlRootElement" + ln);

		stringBuffer.append(attHeaderPublic + " " + attHeaderClass + " " + schema.getClassName() + bs + ln);
		stringBuffer.append("\t@Id" + ln);
		stringBuffer.append("\t@Column(name = \"" + schema.getIdColumn() + "\")" + ln);
		// stringBuffer.append("\t" + attHeaderPrivate + " " + id + ";" + ln);
		String constrcutor = "";
		constrcutor = " ";
		for (int i = 0; i < column.size(); i++) {
			String variable = column.get(i).getVariable();
			String dataType = column.get(i).getDatatype();
			constrcutor += dataType + " " + variable + ",";
			variable = variable.trim();
			stringBuffer.append("\t" + attHeaderPrivate + " " + dataType + " " + variable + ";" + ln);

		}
		if (constrcutor.lastIndexOf(",") > 0) {
			constrcutor = constrcutor.substring(0, constrcutor.lastIndexOf(","));

		}
		stringBuffer
				.append("\t" + attHeaderPublic + " " + schema.getClassName() + " ()" + bs + ln + ln + "\t" + be + ln);

		stringBuffer.append("\t" + attHeaderPublic + " " + schema.getClassName() + " (" + constrcutor + ")" + bs + ln);

		for (int i = 0; i < column.size(); i++) {
			String variable = column.get(i).getVariable();
			String dataType = column.get(i).getDatatype();
			stringBuffer.append("\t\tthis." + variable + " = " + variable + "; " + ln);

		}
		stringBuffer.append("\t" + be + ln);

		stringBuffer.append(ln);

		for (int i = 0; i < column.size(); i++) {
			String variable = column.get(i).getVariable();
			String dataType = column.get(i).getDatatype();

			String getset = variable;
			getset = getset.substring(0, 1).toUpperCase() + getset.substring(1);
			stringBuffer.append("\t" + attHeaderPublic + " " + dataType + " get" + getset + "()" + bs + ln);

			stringBuffer.append("\t\treturn this." + variable + ";" + ln);
			stringBuffer.append("\t" + be + ln);

			stringBuffer.append(
					"\t" + attHeaderPublic + " void set" + getset + "(" + dataType + " " + variable + ") " + bs + ln);
			stringBuffer.append("\t\tthis." + variable + " = " + variable + "; " + ln);
			stringBuffer.append("\t" + be + ln);
		}

		stringBuffer.append(be + ln);
		return stringBuffer.toString();
	}
}
