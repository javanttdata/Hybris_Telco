/*
 * Copyright (c) 2020 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.training;

import org.apache.log4j.Logger;


/**
 * Simple test class to demonstrate how to include utility classes to your webmodule.
 */
public class TrainingcommercewebservicesWebHelper
{
	/** Edit the local|project.properties to change logging behavior (properties log4j.*). */
	@SuppressWarnings("unused")
	private static final Logger LOG = Logger.getLogger(TrainingcommercewebservicesWebHelper.class.getName());

	private TrainingcommercewebservicesWebHelper()
	{
	}

	public static final String getTestOutput()
	{
		return "testoutput";
	}
}
