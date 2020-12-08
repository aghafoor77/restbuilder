package com.ri.se.generator.deligators;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Objects;

import com.ri.se.generator.Generate;
import com.ri.se.generator.entities.Entry;
import com.ri.se.generator.entities.Schema;

public class ServiceDelegator implements Generate {

	@Override
	public String create(Schema schema) {
		return produceService(schema);
	}

	private String produceService(Schema schema) {
		HashMap<Integer, Entry> column = schema.getColumn();
		Iterator iterator = column.keySet().iterator();
		StringBuffer buffer = new StringBuffer();

		if (!Objects.isNull(schema.getMavenGroupId())) {
			buffer.append("package " + schema.getMavenGroupId()+".persistance" + ";" + ln);
			buffer.append(ln);
		}

		buffer.append("import java.util.List;" + ln);
		buffer.append("import java.util.Objects;" + ln);
		buffer.append("import java.util.Date;" + ln);
		buffer.append("import org.skife.jdbi.v2.exceptions.UnableToExecuteStatementException;" + ln);
		buffer.append("import org.skife.jdbi.v2.exceptions.UnableToObtainConnectionException;" + ln);
		buffer.append("import org.skife.jdbi.v2.sqlobject.CreateSqlObject;" + ln);
		buffer.append(ln);

		buffer.append(attHeaderPublic + " abstract " + attHeaderClass + " " + schema.getClassName() + "Service " + bs
				+ ln + ln);
		buffer.append(ln);
		buffer.append("\t");
		buffer.append(
				"private static final String DATABASE_REACH_ERROR = \"Could not reach the MySQL database. The database may be down or there may be network connectivity issues. Details: \";\n"
						+ "	private static final String DATABASE_CONNECTION_ERROR = \"Could not create a connection to the MySQL database. The database configurations are likely incorrect. Details: \";\n"
						+ "	private static final String DATABASE_UNEXPECTED_ERROR = \"Unexpected error occurred while attempting to reach the database. Details: \";\n"
						+ "	");

		buffer.append(ln);

		buffer.append("\t");
		buffer.append("@CreateSqlObject" + ln);
		buffer.append("\t");
		buffer.append("abstract " + schema.getClassName() + "DAO dao();" + ln);
		buffer.append(ln);

		buffer.append("\t");
		buffer.append(attHeaderPublic + " void create() " + bs + ln);
		buffer.append("\t\t");
		buffer.append("dao().create();");
		buffer.append(ln + "\t");
		buffer.append(be + ln + ln);

		buffer.append("\t");
		buffer.append(attHeaderPublic + " int post(" + schema.getClassName() + " " + schema.getClassName().toLowerCase()
				+ "_)" + bs + ln + ln);
		buffer.append("\t\t");
		buffer.append("return dao().post(" + schema.getClassName().toLowerCase() + "_);" + ln);
		buffer.append("\t");
		buffer.append(be + ln);
		buffer.append("\t");

		buffer.append(attHeaderPublic + " int put(" + schema.getClassName() + " " + schema.getClassName().toLowerCase()
				+ "_)" + bs + ln + ln);
		buffer.append("\t\t");
		buffer.append("return dao().put(" + schema.getClassName().toLowerCase() + "_);" + ln);

		buffer.append("\t");
		buffer.append(be + ln);

		buffer.append("\t");
		buffer.append(attHeaderPublic + " List<" + schema.getClassName() + "> list()" + bs + ln + ln);
		buffer.append("\t\t");
		buffer.append("return dao().list();" + ln);
		buffer.append("\t");
		buffer.append(be + ln);

		buffer.append("\t");
		buffer.append(attHeaderPublic + " " + schema.getClassName() + " get(" + column.get(0).getDatatype() + " "
				+ schema.getIdColumn() + ")" + bs + ln + ln);
		buffer.append("\t\t");
		buffer.append("return dao().get(" + schema.getIdColumn() + ");" + ln);

		buffer.append("\t");
		buffer.append(be + ln);

		buffer.append("\t");
		buffer.append(attHeaderPublic + " int delete(" + column.get(0).getDatatype() + " " + schema.getIdColumn() + ")"
				+ bs + ln + ln);
		buffer.append("\t\t");
		buffer.append("return dao().delete(" + schema.getIdColumn() + ");" + ln);

		buffer.append("\t");
		buffer.append(be + ln);

		buffer.append("\t");
		buffer.append("public String performHealthCheck() {" + ln);
		buffer.append("\t\t");
		buffer.append("try {" + ln);
		buffer.append("\t\t\t");
		buffer.append("dao().list();" + ln);
		buffer.append("\t\t");
		buffer.append("} catch (UnableToObtainConnectionException ex) {" + ln);
		buffer.append("\t\t\t");
		buffer.append("return checkUnableToObtainConnectionException(ex);" + ln);
		buffer.append("\t\t");
		buffer.append("} catch (UnableToExecuteStatementException ex) {" + ln);
		buffer.append("\t\t\t");
		buffer.append("return checkUnableToExecuteStatementException(ex);" + ln);
		buffer.append("\t\t");
		buffer.append("} catch (Exception ex) {" + ln);
		buffer.append("\t\t\t");
		buffer.append("return DATABASE_UNEXPECTED_ERROR + ex.getCause().getLocalizedMessage();" + ln);
		buffer.append("\t\t");
		buffer.append("}" + ln);
		buffer.append("\t\t");
		buffer.append("return null;" + ln);
		buffer.append("\t");
		buffer.append("}" + ln);

		buffer.append("\t\t");
		buffer.append(
				"private String checkUnableToObtainConnectionException(UnableToObtainConnectionException ex) {" + ln);
		buffer.append("\t\t\t");
		buffer.append("if (ex.getCause() instanceof java.sql.SQLNonTransientConnectionException) {" + ln);
		buffer.append("\t\t\t\t");
		buffer.append("return DATABASE_REACH_ERROR + ex.getCause().getLocalizedMessage();" + ln);
		buffer.append("\t\t");
		buffer.append("} else if (ex.getCause() instanceof java.sql.SQLException) {" + ln);
		buffer.append("\t\t\t");
		buffer.append("return DATABASE_CONNECTION_ERROR + ex.getCause().getLocalizedMessage();" + ln);
		buffer.append("\t\t");
		buffer.append("} else {" + ln);
		buffer.append("\t\t\t");
		buffer.append("return DATABASE_UNEXPECTED_ERROR + ex.getCause().getLocalizedMessage();" + ln);
		buffer.append("\t\t");
		buffer.append("}" + ln);
		buffer.append("\t");
		buffer.append("}" + ln);

		buffer.append("\t");
		buffer.append(
				"private String checkUnableToExecuteStatementException(UnableToExecuteStatementException ex) {" + ln);
		buffer.append("\t\t");
		buffer.append("if (ex.getCause() instanceof java.sql.SQLSyntaxErrorException) {" + ln);
		buffer.append("\t\t\t");
		buffer.append("return DATABASE_CONNECTION_ERROR + ex.getCause().getLocalizedMessage();" + ln);
		buffer.append("\t\t");
		buffer.append("} else {" + ln);
		buffer.append("\t\t\t");
		buffer.append("return DATABASE_UNEXPECTED_ERROR + ex.getCause().getLocalizedMessage();" + ln);
		buffer.append("\t\t");
		buffer.append("}" + ln);
		buffer.append("\t");
		buffer.append("}" + ln);

		buffer.append(be + ln);
		return buffer.toString();
	}
}
