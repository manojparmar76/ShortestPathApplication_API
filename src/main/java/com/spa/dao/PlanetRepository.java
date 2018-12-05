/**
 * 
 */
package com.spa.dao;

import org.springframework.data.repository.CrudRepository;

import com.spa.entity.Planet;

/**
 * @author Manoj
 *
 */
public interface PlanetRepository extends CrudRepository<Planet, Integer> {

	/**
	 * find the Planet using the planet node.
	 * @param stringCellValue
	 * @return Planet
	 */
	Planet findByPlanetNode(String planetNode);

}
