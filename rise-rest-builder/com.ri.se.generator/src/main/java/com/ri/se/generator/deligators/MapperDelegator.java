package com.ri.se.generator.deligators;

import java.util.Objects;

import com.ri.se.generator.Generate;
import com.ri.se.generator.entities.Schema;

public class MapperDelegator implements Generate {

	@Override
	public String create(Schema schema) {
		return produceMapper(schema);
	}

	private String produceMapper(Schema schema) {

		StringBuffer stringBuffer = new StringBuffer();

		if (!Objects.isNull(schema.getMavenGroupId())) {
			stringBuffer.append("package " + schema.getMavenGroupId()+".persistance"  + ";" + ln);
			stringBuffer.append(ln);
		}
		stringBuffer.append(ln);
		stringBuffer.append("import java.sql.ResultSet;" + ln);
		stringBuffer.append("import java.sql.SQLException;" + ln);
		stringBuffer.append("import java.util.Date;" + ln);

		stringBuffer.append("import org.skife.jdbi.v2.StatementContext;" + ln);
		stringBuffer.append("import org.skife.jdbi.v2.tweak.ResultSetMapper;" + ln + ln);

		stringBuffer.append("public class " + schema.getClassName() + "Mapper implements ResultSetMapper<"
				+ schema.getClassName() + "> {" + ln + ln);

		for (int i = 0; i < schema.getColumn().size(); i++) {
			String variable = schema.getColumn().get(i).getVariable();

			stringBuffer.append("\tprivate static final String  " + variable + " = \"" + variable + "\";" + ln);
		}
		stringBuffer.append(ln);
		stringBuffer.append("\tpublic " + schema.getClassName()
				+ " map(int i, ResultSet resultSet, StatementContext statementContext) throws SQLException {" + ln);

		stringBuffer.append("\t\treturn new " + schema.getClassName() + "(");

		String params = "";
		for (int i = 0; i < schema.getColumn().size(); i++) {
			String variable = schema.getColumn().get(i).getVariable();
			String datatype = schema.getColumn().get(i).getDatatype();
			datatype = datatype.trim();

			if (datatype.equalsIgnoreCase("byte")) {
				params += ("resultSet.getByte(" + variable + "), ");
			} else if (datatype.equalsIgnoreCase("short")) {

				params += ("resultSet.getShort(" + variable + "), ");
			} else if (datatype.equalsIgnoreCase("int")) {

				params += ("resultSet.getInt(" + variable + "), ");
			} else if (datatype.equalsIgnoreCase("long")) {

				params += ("resultSet.getLong(" + variable + "), ");
			} else if (datatype.equalsIgnoreCase("float")) {

				params += ("resultSet.getFloat(" + variable + "), ");
			} else if (datatype.equalsIgnoreCase("double")) {

				params += ("resultSet.getDouble(" + variable + "), ");
			} else if (datatype.equalsIgnoreCase("boolean")) {

				params += ("resultSet.getBoolean(" + variable + "), ");
			} else if (datatype.equalsIgnoreCase("char")) {

				params += ("resultSet.getString(" + variable + "), ");
			} else if (datatype.equalsIgnoreCase("String")) {

				params += ("resultSet.getString(" + variable + "), ");
			} else if (datatype.equalsIgnoreCase("Date")) {

				params += ("resultSet.getTimestamp(" + variable + "), ");
			} else if (datatype.equalsIgnoreCase("DateTime")) {

				params += ("resultSet.getTimestamp(" + variable + "), ");
			}
		}
		if (params.lastIndexOf(",") > 0) {
			params = params.substring(0, params.lastIndexOf(","));
		}

		stringBuffer.append(params + ");" + ln + ln);
		stringBuffer.append("\t" + be + ln);
		stringBuffer.append(be + ln);
		return stringBuffer.toString();
	}
}
