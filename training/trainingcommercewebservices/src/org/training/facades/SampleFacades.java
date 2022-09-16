/*
 * Copyright (c) 2020 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.training.facades;

import de.hybris.platform.core.servicelayer.data.SearchPageData;

import java.util.List;

import org.training.data.UserData;
import org.training.dto.SampleWsDTO;
import org.training.dto.TestMapWsDTO;


public interface SampleFacades
{
	SampleWsDTO getSampleWsDTO(final String value);

	UserData getUser(String id);

	List<UserData> getUsers();

	SearchPageData<UserData> getUsers(SearchPageData<?> params);

	TestMapWsDTO getMap();
}
