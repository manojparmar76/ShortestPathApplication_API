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
public class TrafficResponse {

	private int trafficId;
	private String planetOrigin;
	private String planetDestination;
	private double delay;
	private String message;

	public int getTrafficId() {
		return trafficId;
	}

	public void setTrafficId(int trafficId) {
		this.trafficId = trafficId;
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

	public double getDelay() {
		return delay;
	}

	public void setDelay(double delay) {
		this.delay = delay;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
