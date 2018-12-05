/**
 * 
 */
package com.spa.dao;

import org.springframework.data.repository.CrudRepository;

import com.spa.entity.Planet;
import com.spa.entity.Traffic;

/**
 * @author Manoj
 *
 */
public interface TrafficRepository extends CrudRepository<Traffic, Integer> {

	/**
	 * find the traffic using the planet origin and planet destination
	 * @param planetOrigin
	 * @param planetDestination
	 * @return
	 */
	Traffic findByPlanetOriginAndPlanetDestination(Planet planetOrigin, Planet planetDestination);

}
