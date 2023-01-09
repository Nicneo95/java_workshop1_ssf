package com.example.workshop1;

import java.util.Collection;
import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Workshop1Application {

	// HOW TO IMPLEMENT LOGGER
	// Type of log level - FATAL, ERROR, WARN, INFO, DEBUG OR TRACE
	// FATAL - very severe error events that will presumably lead the application to abort
	// ERROR - when the application hits an issue preventing one or more functionalities from properly functioning
	// WARN - a problem, or a situation that might disturb one of the processes
	// INFO - a piece of information telling how important a given log message is
	// DEBUG - diagnosing issues and troubleshooting
	// TRACE - captures all the details about the behavior of the application
	private static final Logger logger = LoggerFactory
			.getLogger(Workshop1Application.class);

	private static String portNumber = null;
	private static final String DEFAULT_PORT = "8080";

	// Entry point of our application
	// command line argument is an array of string
	public static void main(String[] args) {
		// READ COMMAND LINE ARGUMENT
		// interate through array of string
		// print each element in the console
		for (String arg : args) {
			logger.debug("arg: " + arg);
			System.out.println(arg);
			if (arg.contains("--port=")) {
				portNumber = arg.substring(arg.length() - 4, arg.length());
				logger.debug("portNumber: " + portNumber);
			}
		}

		if (portNumber == null) {
			portNumber = System.getenv("APP_PORT");
			logger.debug("Sys ENV portNumber: " + portNumber);
		}

		if (portNumber == null) {
			portNumber = DEFAULT_PORT;
		}

		// ACCESS COMMAND LINE ARGUMENT INDEX 0
		if (args.length > 0) {
			System.out.println("First argument in the list: " + args[0]);
		}

		SpringApplication app = new SpringApplication(Workshop1Application.class);
			app.setDefaultProperties(Collections.singletonMap("sever.port", portNumber));
			app.run(args);
	}
}