/**
 * 
 */
package com.spa.controller;

import com.spa.request.ShortestPathRequest;
import com.spa.response.ShortestPathResponse;

/**
 * @author Manoj
 *
 */
public interface ShortestPathController {
	
	ShortestPathResponse findShortestPath(ShortestPathRequest shortestPathRequest);

}
