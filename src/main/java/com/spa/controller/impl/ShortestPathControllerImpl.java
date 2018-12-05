/**
 * 
 */
package com.spa.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spa.controller.ShortestPathController;
import com.spa.request.ShortestPathRequest;
import com.spa.response.ShortestPathResponse;
import com.spa.service.ShortestPathService;

/**
 * @author Manoj
 *
 */
@RestController
@RequestMapping(value = "/shortestPath")
@CrossOrigin
public class ShortestPathControllerImpl implements ShortestPathController {

	@Autowired
	private ShortestPathService shortestPathService;
	
	/* (non-Javadoc)
	 * @see com.spa.controller.ShortestPathController#findShortestPath(com.spa.request.ShortestPathRequest)
	 * Controller to find the shortest path between the given nodes
	 */
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ShortestPathResponse findShortestPath(@RequestBody ShortestPathRequest shortestPathRequest) {
		return shortestPathService.findShortestPath(shortestPathRequest);
	}

}
