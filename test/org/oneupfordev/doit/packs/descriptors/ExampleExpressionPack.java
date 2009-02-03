package org.oneupfordev.doit.packs.descriptors;

import java.util.List;

import org.oneupfordev.doit.CallableExpression;
import org.oneupfordev.doit.ExpressionPack;

public class ExampleExpressionPack implements ExpressionPack {
	String name;
	List<Class<? extends CallableExpression>> exampleList;
	public ExampleExpressionPack(String name, List<Class<? extends CallableExpression>> exampleList) {
		this.name = name;
		this.exampleList = exampleList;
	}
	public List<Class<? extends CallableExpression>> getExpressions() { return exampleList; }
	public String getName() { return name; }
}
