/**
 * 
 */
package com.spa.response;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author Manoj
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RouteResponse {

	private int routeId;
	private String planetOrigin;
	private String planetDestination;
	private double distance;
	private String message;

	public int getRouteId() {
		return routeId;
	}

	public void setRouteId(int routeId) {
		this.routeId = routeId;
	}

	public String getPlanetOrigin() {
		return planetOrigin;
	}

	public void setPlanetOrigin(String planetOrigin) {
		this.planetOrigin = planetOrigin;
	}

	public String getPlanetDestination() {
		return planetDestination;
	}

	public void setPlanetDestination(String planetDestination) {
		this.planetDestination = planetDestination;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
