package com.ri.se.generator.deligators;

import java.util.Objects;

import com.ri.se.generator.Generate;
import com.ri.se.generator.entities.Schema;

public class ResourceDelegator implements Generate {

	@Override
	public String create(Schema schema) {
		return produceResource(schema);
	}

	private String produceResource(Schema schema) {
		StringBuffer buffer = new StringBuffer();
		if (!Objects.isNull(schema.getMavenGroupId())) {
			buffer.append("package " + schema.getMavenGroupId()+".utils" + ";" + ln);
			buffer.append(ln);
		}
		buffer.append("import "+schema.getMavenGroupId()+".persistance.*;" + ln); 
		buffer.append("import java.util.Date;" + ln);
		buffer.append("import java.util.List;" + ln);
		buffer.append("import javax.validation.Valid;" + ln);
		buffer.append("import javax.ws.rs.Consumes;" + ln);
		buffer.append("import javax.ws.rs.DELETE;" + ln);
		buffer.append("import javax.ws.rs.GET;" + ln);
		buffer.append("import javax.ws.rs.POST;" + ln);
		buffer.append("import javax.ws.rs.PUT;" + ln);
		buffer.append("import javax.ws.rs.Path;" + ln);
		buffer.append("import javax.ws.rs.PathParam;" + ln);
		buffer.append("import javax.ws.rs.Produces;" + ln);
		buffer.append("import javax.ws.rs.core.MediaType;" + ln);
		buffer.append("import javax.ws.rs.core.Response;" + ln);
		buffer.append("import io.swagger.annotations.Api;" + ln);
		buffer.append("import io.swagger.annotations.ApiOperation;" + ln);
		buffer.append("import io.swagger.annotations.ApiResponse;" + ln);
		buffer.append("import io.swagger.annotations.ApiResponses;" + ln);
		buffer.append(ln);
		buffer.append(createBody(schema));
		return buffer.toString();
	}

	private String createBody(Schema schema) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("@Path(\"/v1/" + schema.getClassName().toLowerCase() + "\")"+ln);
		buffer.append("@Api(value = \""+schema.getClassName()+" by RRB\", description = \"API used to provide CRUD operations for "
				+ schema.getClassName() + " service \")"+ln);
		buffer.append(
				attHeaderPublic + " " + attHeaderClass + " " + schema.getClassName() + "Resource " + bs + ln + ln);
		buffer.append(ln+"\t");
		buffer.append(attHeaderPrivate + " " + schema.getClassName() + "Service " +schema.getClassName().toLowerCase() + "Service;"+ ln);
		buffer.append(ln+"\t");
		buffer.append(attHeaderPublic + " " + schema.getClassName() + "Resource(" + schema.getClassName() + "Service "
				+ schema.getClassName().toLowerCase() + "Service)" + bs + ln);
		buffer.append("\t\t");
		buffer.append("this." + schema.getClassName().toLowerCase() + "Service" + " = "
				+ schema.getClassName().toLowerCase() + "Service;"+ln);
		buffer.append("\t");
		buffer.append(be + ln);
		buffer.append(ln);
		buffer.append(createGet(schema));
		buffer.append(ln+ln);
		buffer.append(createList(schema));
		buffer.append(ln+ln);
		buffer.append(createDelete(schema));
		buffer.append(ln+ln);
		buffer.append(createPost(schema));
		buffer.append(ln+ln);
		buffer.append(createPut(schema));
		buffer.append(ln);
		buffer.append(be + ln);
		return buffer.toString();
	}
	
	private String createGet(Schema schema) {
		StringBuffer buffer = new StringBuffer();
		String callService = "this." + schema.getClassName().toLowerCase() + "Service";
		buffer.append("\t");
		buffer.append("@GET\n" + 
				"	@Path(\"/{"+schema.getColumn().get(0).getVariable()+"}\")\n" + 
				"	@Consumes(MediaType.APPLICATION_JSON)\n" + 
				"	@Produces(MediaType.APPLICATION_JSON)\n" + 
				"	@ApiOperation(value = \"Returns instance stored against given identity ["+schema.getClassName()+"]!\", notes = \"Returns instance of "+schema.getClassName()+" stored against given identity !\", response = String.class)\n" + 
				"	@ApiResponses({ @ApiResponse(code = 200, message = \"OK\"),\n" + 
				"			@ApiResponse(code = 500, message = \"Internal Server error !\") })\n" + 
				"	public Response get(@PathParam(\""+schema.getColumn().get(0).getVariable()+"\") final String "+schema.getColumn().get(0).getVariable()+") {\n" + 
				"		"+schema.getClassName()+" "+schema.getClassName().toLowerCase()+" = "+callService+".get("+schema.getColumn().get(0).getVariable()+");\n" + 
				"		if (java.util.Objects.isNull("+schema.getClassName().toLowerCase()+")) {\n" + 
				"			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();\n" + 
				"		}\n" + 
				"		return Response.status(Response.Status.OK).entity("+schema.getClassName().toLowerCase()+").build();\n" + 
				"	}");
		return buffer.toString();
	}
	
	private String createList(Schema schema) {
		StringBuffer buffer = new StringBuffer();
		String callService = "this." + schema.getClassName().toLowerCase() + "Service";
		buffer.append("\t");
		buffer.append("@GET\n" + 
				"	@Consumes(MediaType.APPLICATION_JSON)\n" + 
				"	@Produces(MediaType.APPLICATION_JSON)\n" + 
				"	@ApiOperation(value = \"Returns list of all stored "+schema.getClassName()+"!\", notes = \"Get list of all stored "+schema.getClassName()+"!\", response = String.class)\n" + 
				"	@ApiResponses({ @ApiResponse(code = 200, message = \"OK\"),\n" + 
				"			@ApiResponse(code = 500, message = \"Internal Server error !\") })\n" + 
				"	public Response list() {\n" + 
				"		List<"+schema.getClassName()+"> list = "+callService+".list();\n" + 
				"		if (java.util.Objects.isNull(list)) {\n" + 
				"			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();\n" + 
				"		}\n" + 
				"		"+schema.getClassName()+"List "+schema.getClassName().toLowerCase()+"List = new "+schema.getClassName()+"List();\n" + 
				"		for ("+schema.getClassName()+" "+schema.getClassName().toLowerCase()+" : list) {\n" + 
				"			"+schema.getClassName().toLowerCase()+"List.add("+schema.getClassName().toLowerCase()+");\n" + 
				"		}\n" + 
				"		return Response.status(Response.Status.OK).entity("+schema.getClassName().toLowerCase()+"List).build();\n" + 
				"	}");
		return buffer.toString();
	}
	
	private String createDelete(Schema schema) {
		StringBuffer buffer = new StringBuffer();
		String callService = "this." + schema.getClassName().toLowerCase() + "Service";
		buffer.append("\t");
		buffer.append("@DELETE\n" + 
				"	@Path(\"/{"+schema.getColumn().get(0).getVariable()+"}\")\n" + 
				"	@Consumes(MediaType.APPLICATION_JSON)\n" + 
				"	@Produces(MediaType.APPLICATION_JSON)\n" + 
				"	@ApiOperation(value = \"Returns instance stored against given identity !\", notes = \"Returns instance stored against given identity !\", response = String.class)\n" + 
				"	@ApiResponses({ @ApiResponse(code = 200, message = \"OK\"),\n" + 
				"			@ApiResponse(code = 500, message = \"Internal Server error !\") })\n" + 
				"	public Response delete(@PathParam(\""+schema.getColumn().get(0).getVariable()+"\") final "+schema.getColumn().get(0).getDatatype()+" "+schema.getColumn().get(0).getVariable()+") {\n" + 
				"		int result = "+callService+".delete("+schema.getColumn().get(0).getVariable()+");\n" + 
				"		if (result < 1) {\n" + 
				"			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();\n" + 
				"		}\n" + 
				"		return Response.status(Response.Status.OK).build();\n" + 
				"	}\n" + 
				"	");
		return buffer.toString();
	}
	
	private String createPost(Schema schema) {
		StringBuffer buffer = new StringBuffer();
		String callService = "this." + schema.getClassName().toLowerCase() + "Service";
		buffer.append("\t");
		buffer.append("@POST\n" + 
				"	@Consumes(MediaType.APPLICATION_JSON)\n" + 
				"	@Produces(MediaType.APPLICATION_JSON)\n" + 
				"	@ApiOperation(value = \"Create "+schema.getClassName()+"!\", notes = \"Creating an instance of "+schema.getClassName()+"!\", response = Response.class)\n" + 
				"	@ApiResponses({ @ApiResponse(code = 200, message = \"OK\"),\n" + 
				"			@ApiResponse(code = 500, message = \"Internal Server error !\") })\n" + 
				"	public Response post(@Valid "+schema.getClassName()+" "+schema.getClassName().toLowerCase()+") {\n" + 
				"		int i = "+callService+".post("+schema.getClassName().toLowerCase()+");\n" + 
				"		if (i > 0) {\n" + 
				"			return Response.status(Response.Status.OK).build();\n" + 
				"		} else {\n" + 
				"			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();\n" + 
				"		}\n" + 
				"	}");
		
		return buffer.toString();
	}
	private String createPut(Schema schema) {
		StringBuffer buffer = new StringBuffer();
		String callService = "this." + schema.getClassName().toLowerCase() + "Service";
		buffer.append("\t");
		buffer.append("@PUT\n" + 
				"	@Path(\"/{"+schema.getColumn().get(0).getVariable()+"}\")\n" + 
				"	@Consumes(MediaType.APPLICATION_JSON)\n" + 
				"	@Produces(MediaType.APPLICATION_JSON)\n" + 
				"	@ApiOperation(value = \"Update "+schema.getClassName()+"!\", notes = \"Updating an instance of "+schema.getClassName()+" !\", response = String.class)\n" + 
				"	@ApiResponses({ @ApiResponse(code = 200, message = \"OK\"),\n" + 
				"			@ApiResponse(code = 500, message = \"Internal Server error !\") })\n" + 
				"	public Response put(@PathParam(\""+schema.getColumn().get(0).getVariable()+"\") final "+schema.getColumn().get(0).getDatatype()+" "+schema.getColumn().get(0).getVariable()+", @Valid "+schema.getClassName()+" "+schema.getClassName().toLowerCase()+") {\n" ); 
		
		
		String getset = schema.getColumn().get(0).getVariable();
		getset = getset.substring(0, 1).toUpperCase() + getset.substring(1);
		buffer.append("		"+schema.getClassName().toLowerCase()+".set"+getset+"("+schema.getColumn().get(0).getVariable()+");\n" + 
				"		int i = "+callService+".put("+schema.getClassName().toLowerCase()+");\n" + 
				"		if (i > 0) {\n" + 
				"			return Response.status(Response.Status.OK).build();\n" + 
				"		} else {\n" + 
				"			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();\n" + 
				"		}\n" + 
				"	}");
		
		return buffer.toString();
	}
}