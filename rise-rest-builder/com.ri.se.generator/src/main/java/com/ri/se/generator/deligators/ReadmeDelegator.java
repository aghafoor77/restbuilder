package com.ri.se.generator.deligators;

import com.ri.se.generator.Generate;
import com.ri.se.generator.entities.Schema;

public class ReadmeDelegator implements Generate {

	@Override
	public String create(Schema schema) {
		return produceReadme(schema);
	}

	private String produceReadme(Schema schema) {
		StringBuffer buffer = new StringBuffer();
		
		return buffer.toString();
	}
}
