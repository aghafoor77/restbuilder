package com.ri.se.generator;

import com.ri.se.generator.entities.Schema;
/**
 * 
 * @author blockchain
 *
 *
 */
public interface Generate {
	public static String attHeaderPrivate = "private";
	public static String attHeaderPublic = "public";
	public static String attHeaderClass = "class";
	public static String bs = "{";
	public static String be = "}";
	public static String quote = "\"";
	public static String ln = "\n";
	public String create(Schema schema);

}
