package com.sirra.bootstrap;

import org.json.simple.*;

public class Tester {

	public Tester() {
		String s = "[0,{\"1\":{\"2\":{\"3\":{\"4\":[5,{\"6\":7}]}}}}]";
		Object obj = JSONValue.parse(s);
		JSONArray array = (JSONArray) obj;
		System.out.println("======the 2nd element of array======");
		System.out.println(array.get(1));
		System.out.println(array.get(1).getClass().getName());
		System.out.println();
	}
}
