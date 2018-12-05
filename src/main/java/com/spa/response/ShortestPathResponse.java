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
public class ShortestPathResponse {

	private String source;
	private String destination;
	private Double distance;
	private Double traffic;
	private List<String> nodes;
	private List<String> planetNames;
	private String message;
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public Double getDistance() {
		return distance;
	}
	public void setDistance(Double distance) {
		this.distance = distance;
	}
	public Double getTraffic() {
		return traffic;
	}
	public void setTraffic(Double traffic) {
		this.traffic = traffic;
	}
	public List<String> getNodes() {
		return nodes;
	}
	public void setNodes(List<String> nodes) {
		this.nodes = nodes;
	}
	public List<String> getPlanetNames() {
		return planetNames;
	}
	public void setPlanetNames(List<String> planetNames) {
		this.planetNames = planetNames;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
