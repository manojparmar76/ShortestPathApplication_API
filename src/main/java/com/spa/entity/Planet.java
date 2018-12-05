/**
 * 
 */
package com.spa.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * @author Manoj
 *
 */

@Entity(name = "PLANET")
public class Planet {

	@Id
	@GeneratedValue
	private int planetId;
	@Column
	private String planetNode;
	@Column
	private String planetName;
	@OneToMany(mappedBy = "planetOrigin", cascade = CascadeType.ALL)
	private List<Route> routes;
	@OneToMany(mappedBy = "planetOrigin", cascade = CascadeType.ALL)
	private List<Traffic> traffics;

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