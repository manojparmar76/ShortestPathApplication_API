/**
 * 
 */
package com.spa.request;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author Manoj
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TrafficRequest {

	private String planetOrigin;
	private String planetDestination;
	private double delay;

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

}
