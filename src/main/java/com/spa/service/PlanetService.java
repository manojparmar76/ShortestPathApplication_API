/**
 * 
 */
package com.spa.service;

import java.util.List;

import com.spa.request.PlanetRequest;
import com.spa.response.PlanetResponse;

/**
 * @author Manoj
 *
 */
public interface PlanetService {

	List<PlanetResponse> getPlanets();

	PlanetResponse getPlanet(String planetNode);

	PlanetResponse updatePlanet(String planetNode, PlanetRequest planetRequest);

}
