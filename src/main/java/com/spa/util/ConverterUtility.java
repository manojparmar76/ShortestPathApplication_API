/**
 * 
 */
package com.spa.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.spa.dao.PlanetRepository;
import com.spa.entity.Planet;
import com.spa.entity.Route;
import com.spa.entity.Traffic;
import com.spa.request.PlanetRequest;
import com.spa.request.RouteRequest;
import com.spa.request.TrafficRequest;
import com.spa.response.PlanetResponse;
import com.spa.response.RouteResponse;
import com.spa.response.TrafficResponse;

/**
 * @author Manoj
 *
 */
public class ConverterUtility {

	@Autowired
	private static PlanetRepository planetRepository;

	/**
	 * Utility to convert planet request into planet entity
	 * @param planetRequest
	 * @return
	 */
	public static Planet getPlanetEntity(PlanetRequest planetRequest) {
		Planet planet = new Planet();
		if (planetRequest.getPlanetNode() != null && !planetRequest.getPlanetNode().equals("")) {
			planet.setPlanetNode(planetRequest.getPlanetNode());
		}
		if (planetRequest.getPlanetName() != null && !planetRequest.getPlanetName().equals("")) {
			planet.setPlanetName(planetRequest.getPlanetName());
		}
		return planet;
	}

	/**
	 * Utility to convert route request into route entity
	 * @param routeRequest
	 * @return
	 */
	public static Route getRouteEntity(RouteRequest routeRequest) {
		Route route = new Route();
		if (routeRequest.getPlanetOrigin() != null && !routeRequest.getPlanetOrigin().equals("")) {
			Planet planet = planetRepository.findByPlanetNode(routeRequest.getPlanetOrigin());
			route.setPlanetOrigin(planet);
		}
		if (routeRequest.getPlanetDestination() != null && !routeRequest.getPlanetDestination().equals("")) {
			Planet planet = planetRepository.findByPlanetNode(routeRequest.getPlanetDestination());
			route.setPlanetDestination(planet);
		}
		route.setDistance(routeRequest.getDistance());
		return route;
	}

	/**
	 * Utility to convert traffic request into traffic entity
	 * @param trafficRequest
	 * @return
	 */
	public static Traffic getTrafficEntity(TrafficRequest trafficRequest) {
		Traffic traffic = new Traffic();
		if (trafficRequest.getPlanetOrigin() != null && !trafficRequest.getPlanetOrigin().equals("")) {
			Planet planet = planetRepository.findByPlanetNode(trafficRequest.getPlanetOrigin());
			traffic.setPlanetOrigin(planet);
		}
		if (trafficRequest.getPlanetDestination() != null && !trafficRequest.getPlanetDestination().equals("")) {
			Planet planet = planetRepository.findByPlanetNode(trafficRequest.getPlanetDestination());
			traffic.setPlanetDestination(planet);
		}
		traffic.setDelay(trafficRequest.getDelay());
		return traffic;
	}

	/**
	 * Utility to convert the planet entity into planet response
	 * @param planet
	 * @return
	 */
	public static PlanetResponse getPlanetResponse(Planet planet) {
		PlanetResponse planetResponse = new PlanetResponse();
		planetResponse.setPlanetId(planet.getPlanetId());
		planetResponse.setPlanetNode(planet.getPlanetNode());
		planetResponse.setPlanetName(planet.getPlanetName());
		
		List<RouteResponse> routeResponses = new ArrayList<RouteResponse>();
		if(planet.getRoutes() != null && planet.getRoutes().size() > 0) {
			for (Route route : planet.getRoutes()) {
				routeResponses.add(ConverterUtility.getRouteResponse(route));
			}
		}
		planetResponse.setRoutes(routeResponses);
		
		List<TrafficResponse> trafficResponses = new ArrayList<TrafficResponse>();
		if(planet.getTraffics() != null && planet.getTraffics().size() > 0) {
			for (Traffic traffic : planet.getTraffics()) {
				trafficResponses.add(ConverterUtility.getTrafficResponse(traffic));
			}
		}
		planetResponse.setTraffics(trafficResponses);
		
		return planetResponse;
	}

	/**
	 * Utility to convert the route entity into route response
	 * @param route
	 * @return
	 */
	public static RouteResponse getRouteResponse(Route route) {
		RouteResponse routeResponse = new RouteResponse();
		routeResponse.setRouteId(route.getRouteId());
		routeResponse.setPlanetOrigin(route.getPlanetOrigin().getPlanetNode());
		routeResponse.setPlanetDestination(route.getPlanetDestination().getPlanetNode());
		routeResponse.setDistance(route.getDistance());
		return routeResponse;
	}

	/**
	 * Utility to convert the traffic entity into traffic response
	 * @param traffic
	 * @return
	 */
	public static TrafficResponse getTrafficResponse(Traffic traffic) {
		TrafficResponse trafficResponse = new TrafficResponse();
		trafficResponse.setTrafficId(traffic.getTrafficId());
		trafficResponse.setPlanetOrigin(traffic.getPlanetOrigin().getPlanetNode());
		trafficResponse.setPlanetDestination(traffic.getPlanetDestination().getPlanetNode());
		trafficResponse.setDelay(traffic.getDelay());
		return trafficResponse;
	}
}
