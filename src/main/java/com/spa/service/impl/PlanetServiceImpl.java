/**
 * 
 */
package com.spa.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spa.dao.PlanetRepository;
import com.spa.entity.Planet;
import com.spa.request.PlanetRequest;
import com.spa.response.PlanetResponse;
import com.spa.service.PlanetService;
import com.spa.util.ConverterUtility;

/**
 * @author Manoj
 *
 */
@Service
public class PlanetServiceImpl implements PlanetService {

	@Autowired
	private PlanetRepository planetRepository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.spa.service.PlanetService#getPlanets() Method to fetch the all
	 * the planets from the database.
	 */
	@Override
	public List<PlanetResponse> getPlanets() {
		List<PlanetResponse> planetResponses = new ArrayList<PlanetResponse>();
		Iterable<Planet> planets = planetRepository.findAll();
		for (Planet planet : planets) {
			planetResponses.add(ConverterUtility.getPlanetResponse(planet));
		}
		return planetResponses;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.spa.service.PlanetService#getPlanet(java.lang.String) Get the
	 * planet details from the database.
	 */
	@Override
	public PlanetResponse getPlanet(String planetNode) {
		PlanetResponse planetResponse = new PlanetResponse();
		Planet planet = planetRepository.findByPlanetNode(planetNode);
		if (planet != null) {
			planetResponse = ConverterUtility.getPlanetResponse(planet);
		} else {
			planetResponse.setMessage("Planet Node not found in graph.");
		}
		return planetResponse;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.spa.service.PlanetService#updatePlanet(java.lang.String,
	 * com.spa.request.PlanetRequest) Method to update the planet details in the
	 * database.
	 */
	@Override
	public PlanetResponse updatePlanet(String planetNode, PlanetRequest planetRequest) {
		PlanetResponse planetResponse = new PlanetResponse();
		Planet planet = planetRepository.findByPlanetNode(planetNode);
		if (planet != null) {
			if (planetRequest.getPlanetName() != null && !planetRequest.getPlanetName().equals("")) {
				planet.setPlanetName(planetRequest.getPlanetName());
			}
			planet = planetRepository.save(planet);
			planetResponse = ConverterUtility.getPlanetResponse(planet);
		} else {
			planetResponse.setMessage("Planet Node not found in graph.");
		}
		return planetResponse;
	}

}
