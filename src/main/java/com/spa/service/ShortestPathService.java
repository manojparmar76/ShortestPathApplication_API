/**
 * 
 */
package com.spa.service;

import com.spa.request.ShortestPathRequest;
import com.spa.response.ShortestPathResponse;

/**
 * @author Manoj
 *
 */
public interface ShortestPathService {

	ShortestPathResponse findShortestPath(ShortestPathRequest shortestPathRequest);

}
