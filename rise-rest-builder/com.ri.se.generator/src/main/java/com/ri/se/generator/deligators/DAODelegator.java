package com.ri.se.generator.deligators;

import java.util.HashMap;
import java.util.Objects;

import com.ri.se.generator.Generate;
import com.ri.se.generator.entities.Entry;
import com.ri.se.generator.entities.Schema;

public class DAODelegator implements Generate {

	@Override
	public String create(Schema schema) {
		return produceDAO(schema);
	}

	private String produceDAO(Schema schema) {
		HashMap<Integer, Entry> column = schema.getColumn();
		// Iterator iterator = column.keySet().iterator();
		StringBuffer buffer = new StringBuffer();
		String col = "";
		String values = "";
		String update = "";

		for (int i = 0; i < column.size(); i++) {
			String variable = column.get(i).getVariable();
			col += variable + ", ";
			values += ":" + variable + ", ";
			update += variable += "=:" + variable + ", ";
		}

		if (col.lastIndexOf(",") > 0) {
			col = col.substring(0, col.lastIndexOf(","));
			values = values.substring(0, values.lastIndexOf(","));
			update = update.substring(0, update.lastIndexOf(","));
		}

		buffer.append("");

		if (!Objects.isNull(schema.getMavenGroupId())) {
			buffer.append("package " + schema.getMavenGroupId()+".persistance"  + ";" + ln);
			buffer.append(ln);
		}

		buffer.append("import java.util.List;" + ln);
		buffer.append("import java.util.Date;" + ln);
		buffer.append("import org.skife.jdbi.v2.sqlobject.Bind;" + ln);
		buffer.append("import org.skife.jdbi.v2.sqlobject.BindBean;" + ln);
		buffer.append("import org.skife.jdbi.v2.sqlobject.SqlQuery;" + ln);
		buffer.append("import org.skife.jdbi.v2.sqlobject.SqlUpdate;" + ln);
		buffer.append("import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;" + ln + ln);

		buffer.append("@RegisterMapper(" + schema.getClassName() + "Mapper.class)" + ln);

		buffer.append(attHeaderPublic + " interface " + schema.getClassName() + "DAO" + bs + ln);

		buffer.append("\t");
		try {
			buffer.append("@SqlUpdate(\"" + createTableQuery(schema) + "\")" + ln);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(0);
		}
		buffer.append("\t");
		buffer.append(attHeaderPublic + " void create();" + ln + ln);

		buffer.append("\t");
		buffer.append("@SqlUpdate(\"insert into " + schema.getClassName());
		buffer.append("(" + col + ") values(" + values + ")\")" + ln);
		buffer.append("\t");
		buffer.append(attHeaderPublic + " int post(@BindBean " + schema.getClassName() + " "
				+ schema.getClassName().toLowerCase() + "_);" + ln + ln);

		// Handling update query
		buffer.append("\t");
		buffer.append("@SqlUpdate(\"UPDATE " + schema.getClassName() + " SET " + update + " WHERE "
				+ schema.getIdColumn() + "=:" + schema.getIdColumn() + "\")" + ln);
		buffer.append("\t");
		buffer.append(attHeaderPublic + " int put(@BindBean " + schema.getClassName() + " "
				+ schema.getClassName().toLowerCase() + "_);" + ln + ln);

		buffer.append("\t");
		buffer.append("@SqlQuery(\"select * from " + schema.getClassName() + ";\")" + ln);
		buffer.append("\t");
		buffer.append(attHeaderPublic + " List<" + schema.getClassName() + "> list();" + ln + ln);

		buffer.append("\t");
		buffer.append("@SqlQuery(\"select * from " + schema.getClassName() + " where " + schema.getIdColumn() + "=:"
				+ schema.getIdColumn() + ";\")" + ln);
		buffer.append("\t");
		buffer.append(attHeaderPublic + " " + schema.getClassName() + " get(@Bind(\"" + schema.getIdColumn() + "\") "
				+ column.get(0).getDatatype() + " " + schema.getIdColumn() + ");" + ln + ln);

		buffer.append("\t");
		buffer.append("@SqlUpdate(\"Delete from " + schema.getClassName() + " where " + schema.getIdColumn() + "=:"
				+ schema.getIdColumn() + ";\")" + ln);
		buffer.append("\t");
		buffer.append(attHeaderPublic + " int delete(@Bind(\"" + schema.getIdColumn() + "\") "
				+ column.get(0).getDatatype() + " " + schema.getIdColumn() + ");" + ln + ln);

		buffer.append(be + ln);
		return buffer.toString();
	}
	
	public String createTableQuery(Schema schema) throws Exception {
		StringBuffer buffer = new StringBuffer();
		buffer.append("CREATE TABLE IF NOT EXISTS `"+schema.getClassName()+"` ( ");
		HashMap<Integer, Entry> column = schema.getColumn();
		String params = "";

		params = "`" + column.get(0).getVariable() + "` " + returnSQLDatatype(column.get(0).getDatatype())
				+ " NOT NULL,";

		for (int i = 1; i < schema.getColumn().size(); i++) {
			String variable = schema.getColumn().get(i).getVariable();
			String datatype = schema.getColumn().get(i).getDatatype();
			datatype = datatype.trim();
			params += "`" + column.get(i).getVariable() + "` " + returnSQLDatatype(column.get(i).getDatatype())
					+ " NULL DEFAULT NULL,";
		}

		buffer.append(params);

		buffer.append(" PRIMARY KEY (`" + column.get(0).getVariable() + "`)");
		buffer.append(") ENGINE=InnoDB DEFAULT CHARSET=latin1;");

		return buffer.toString();
	}
	
	private String returnSQLDatatype(String datatype) throws Exception {

		datatype = datatype.trim();
		if (datatype.equalsIgnoreCase("byte")) {
			return "varchar(1)";
		} else if (datatype.equalsIgnoreCase("short")) {
			return "bigint(5)";
		} else if (datatype.equalsIgnoreCase("int")) {
			return "bigint(10)";
		} else if (datatype.equalsIgnoreCase("long")) {
			return "bigint(20)";
		} else if (datatype.equalsIgnoreCase("float")) {
			return "float";
		} else if (datatype.equalsIgnoreCase("double")) {
			return "double";
		} else if (datatype.equalsIgnoreCase("boolean")) {
			return "tinyint(1)";
		} else if (datatype.equalsIgnoreCase("char")) {
			return "varchar(1)";
		} else if (datatype.equalsIgnoreCase("String")) {
			return "varchar(100)";
		} else if (datatype.equalsIgnoreCase("Date")) {
			return "timestamp";
		} else if (datatype.equalsIgnoreCase("DateTime")) {
			return "timestamp";
		}
		throw new Exception(datatype + " not supported !");
	}

}
