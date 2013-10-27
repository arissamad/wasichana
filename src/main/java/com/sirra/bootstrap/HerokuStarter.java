package com.sirra.bootstrap;

import org.eclipse.jetty.annotations.*;
import org.eclipse.jetty.plus.webapp.*;
import org.eclipse.jetty.server.*;
import org.eclipse.jetty.webapp.*;

import com.sirra.server.*;
import com.sirra.server.persistence.*;
import com.sirra.server.rest.*;
import com.sirra.appcore.accounts.*;
import com.sirra.appcore.dailyprocess.*;
import com.sirra.appcore.firebase.*;
import com.sirra.appcore.plans.*;
import com.sirra.appcore.sql.*;
import com.sirra.appcore.users.*;

public class HerokuStarter {
	
	public static void main(String[] args)
	throws Exception 
	{
		System.out.println("Starting Wasichana Server... ");
		System.out.println("Mode: " + Mode.get().name());
    	
    	if(args.length > 0 && args[0].equals("nohibernate")) {
    		System.out.println("Not starting hibernate.");
    	} else {
    		HibernateStarter.init("com.sirra");
    	}
    	
    	// Set base package for API classes
    	ApiServlet.setAPIPackageBase("com.sirra");
    	
    	// Initialize Finder
    	Finder.configure(BaseAccount.class, BaseUser.class);
    	
    	// Set firebase location
    	Firebase.setFirebaseInstance("wasichana");
    	
    	String webPort = System.getenv("PORT");
        int port = isBlank(webPort) ? 8080 : Integer.parseInt(webPort);
        
		Server server = new Server(port);

		String wardir = "target/wasichana-1.0/";

		WebAppContext context = new WebAppContext();
		
		context.setResourceBase(wardir);
		context.setDescriptor(wardir + "WEB-INF/web.xml");
		
		context.setConfigurations(new Configuration[] {
				new AnnotationConfiguration(), new WebXmlConfiguration(),
				new WebInfConfiguration(), new TagLibConfiguration(),
				new PlusConfiguration(), new MetaInfConfiguration(),
				new FragmentConfiguration(), new EnvConfiguration() });

		context.setContextPath("/");
		context.setParentLoaderPriority(true);
		
		server.setHandler(context);
		server.start();
		server.join();
		
		System.out.println("Wasichana Server has terminated.");
	}
	
	private static boolean isBlank(String s) {
        return s == null || s.trim().length() == 0;
    }
}
