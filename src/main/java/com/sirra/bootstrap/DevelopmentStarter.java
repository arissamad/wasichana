package com.sirra.bootstrap;

import com.sirra.server.*;

public class DevelopmentStarter {
	
	public static void main(String[] args)
	throws Exception
	{
		if(false) {
			new Tester();
			return;
		}
		Mode.set(Mode.Development);
		HerokuStarter.main(args);
	}
}