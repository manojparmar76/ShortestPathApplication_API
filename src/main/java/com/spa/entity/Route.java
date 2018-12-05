/**
 * 
 */
package com.spa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @author Manoj
 *
 */
@Entity(name = "ROUTE")
public class Route {

	@Id
	@GeneratedValue
	private int routeId;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "planetOrigin")
	private Planet planetOrigin;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "planetDestination")
	private Planet planetDestination;
	@Column
	private double distance;

	public int getRouteId() {
		return routeId;
	}

	public void setRouteId(int routeId) {
		this.routeId = routeId;
	}

	public Planet getPlanetOrigin() {
		return planetOrigin;
	}

	public void setPlanetOrigin(Planet planetOrigin) {
		this.planetOrigin = planetOrigin;
	}

	public Planet getPlanetDestination() {
		return planetDestination;
	}

	public void setPlanetDestination(Planet planetDestination) {
		this.planetDestination = planetDestination;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double d) {
		this.distance = d;
	}
}
