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
@Entity(name = "TRAFFIC")
public class Traffic {

	@Id
	@GeneratedValue
	private int trafficId;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "planetOrigin")
	private Planet planetOrigin;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "planetDestination")
	private Planet planetDestination;
	@Column
	private double delay;

	public int getTrafficId() {
		return trafficId;
	}

	public void setTrafficId(int trafficId) {
		this.trafficId = trafficId;
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

	public double getDelay() {
		return delay;
	}

	public void setDelay(double delay) {
		this.delay = delay;
	}
}
