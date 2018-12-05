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
public class ShortestPathRequest {

	private String source;
	private String destination;
	private boolean withTraffic;

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

	public boolean isWithTraffic() {
		return withTraffic;
	}

	public void setWithTraffic(boolean withTraffic) {
		this.withTraffic = withTraffic;
	}

}
