/**
 * 
 */
package com.spa.request;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.spa.entity.Route;
import com.spa.entity.Traffic;

/**
 * @author Manoj
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PlanetRequest {

	private String planetNode;
	private String planetName;
	private List<Route> routes;
	private List<Traffic> traffics;

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

	public List<Route> getRoutes() {
		return routes;
	}

	public void setRoutes(List<Route> routes) {
		this.routes = routes;
	}

	public List<Traffic> getTraffics() {
		return traffics;
	}

	public void setTraffics(List<Traffic> traffics) {
		this.traffics = traffics;
	}

}
