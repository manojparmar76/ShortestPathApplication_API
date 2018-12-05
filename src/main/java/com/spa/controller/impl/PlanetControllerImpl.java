/**
 * 
 */
package com.spa.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spa.controller.PlanetController;
import com.spa.request.PlanetRequest;
import com.spa.response.PlanetResponse;
import com.spa.service.PlanetService;

/**
 * @author Manoj
 *
 */
@RestController
@RequestMapping(value = "/planets")
@CrossOrigin
public class PlanetControllerImpl implements PlanetController {

	@Autowired
	private PlanetService planetService;

	/* (non-Javadoc)
	 * @see com.spa.controller.PlanetController#getPlanets()
	 * Controller to fetch all the planets
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public List<PlanetResponse> getPlanets() {
		return planetService.getPlanets();
	}

	/* (non-Javadoc)
	 * @see com.spa.controller.PlanetController#getPlanet(java.lang.String)
	 * Controller to fetch the details of the planet
	 */
	@RequestMapping(value = "/{planetNode}", method = RequestMethod.GET)
	public PlanetResponse getPlanet(@PathVariable String planetNode) {
		return planetService.getPlanet(planetNode);
	}

	/* (non-Javadoc)
	 * @see com.spa.controller.PlanetController#updatePlanet(java.lang.String, com.spa.request.PlanetRequest)
	 * Controller to update the details of the planet
	 */
	@RequestMapping(value = "/{planetNode}", method = RequestMethod.PATCH)
	public PlanetResponse updatePlanet(@PathVariable String planetNode, @RequestBody PlanetRequest planetRequest) {
		return planetService.updatePlanet(planetNode, planetRequest);
	}
}