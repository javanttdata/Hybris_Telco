/*
 * Copyright (c) 2020 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.training.initializer;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.servicelayer.ServicelayerTest;
import de.hybris.platform.webservicescommons.testsupport.client.WsSecuredRequestBuilder;
import de.hybris.platform.webservicescommons.testsupport.server.NeedsEmbeddedServer;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.training.constants.TrainingcommercewebservicesConstants;
import org.training.dto.SampleWsDTO;


@IntegrationTest
@NeedsEmbeddedServer(webExtensions =
{ TrainingcommercewebservicesConstants.EXTENSIONNAME, "oauth2" })
public class HybrisPropertiesWebApplicationContextInitializerTest extends ServicelayerTest
{
	public static final String OAUTH_CLIENT_ID = "mobile_android";
	public static final String OAUTH_CLIENT_PASS = "secret";

	private WsSecuredRequestBuilder wsSecuredRequestBuilder;

	@Before
	public void setUp() throws Exception
	{
		wsSecuredRequestBuilder = new WsSecuredRequestBuilder()//
				.extensionName(TrainingcommercewebservicesConstants.EXTENSIONNAME)//
				.client(OAUTH_CLIENT_ID, OAUTH_CLIENT_PASS);

		createCoreData();
		importCsv("/trainingcommercewebservices/test/democustomer-data.impex", "utf-8");
	}

	@Test
	public void testSpringFileOverride() throws IOException
	{
		final Response response = wsSecuredRequestBuilder.grantClientCredentials().path("/sample/testBean").build()
				.accept(MediaType.APPLICATION_JSON).get();

		Assert.assertEquals(HttpServletResponse.SC_OK, response.getStatus());

		final SampleWsDTO testBean = response.readEntity(SampleWsDTO.class);
		Assert.assertEquals("testText", testBean.getValue());
	}
}
