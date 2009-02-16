package org.oneupfordev.doit.packs.descriptors;

import java.util.ArrayList;
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

	@SuppressWarnings("unchecked")
	public ExampleExpressionPack(String name, Class<?>[] classes) {
		List<Class<? extends CallableExpression>> l = new ArrayList<Class<? extends CallableExpression>>();
		for (Class cl : classes) {
			l.add(cl);
		}
		this.name = name;
		this.exampleList = l;
	}

	public ExampleExpressionPack(String name) {
		this.name = name;
		this.exampleList = null;
	}

	public List<Class<? extends CallableExpression>> getExpressions() { return exampleList; }
	public String getName() { return name; }
}
