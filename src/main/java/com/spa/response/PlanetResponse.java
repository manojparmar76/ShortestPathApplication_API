/**
 * 
 */
package com.spa.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author Manoj
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PlanetResponse {

	private int planetId;
	private String planetNode;
	private String planetName;
	private List<RouteResponse> routes;
	private List<TrafficResponse> traffics;
	private String message;

	public int getPlanetId() {
		return planetId;
	}

	public void setPlanetId(int planetId) {
		this.planetId = planetId;
	}

	public String getPlanetNode() {
		return planetNode;
	}

	public void setPlanetNode(String planetNode) {
		this.planetNode = planetNode;
	}

	public String getPlanetName() {
		return planetName;
	}

	public void setPlanetName(String planetName) {
		this.planetName = planetName;
	}

	public List<RouteResponse> getRoutes() {
		return routes;
	}

	public void setRoutes(List<RouteResponse> routes) {
		this.routes = routes;
	}

	public List<TrafficResponse> getTraffics() {
		return traffics;
	}

	public void setTraffics(List<TrafficResponse> traffics) {
		this.traffics = traffics;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
