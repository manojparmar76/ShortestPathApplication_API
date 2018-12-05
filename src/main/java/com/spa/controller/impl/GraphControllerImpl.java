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
import org.springframework.web.multipart.MultipartFile;

import com.spa.controller.GraphController;
import com.spa.response.GraphResponse;
import com.spa.service.GraphService;

/**
 * @author Manoj
 *
 */
@RestController
@RequestMapping(value = "/graph")
@CrossOrigin
public class GraphControllerImpl implements GraphController {

	@Autowired
	private GraphService graphService;

	/* (non-Javadoc)
	 * @see com.spa.controller.GraphController#createGraph(org.springframework.web.multipart.MultipartFile)
	 * Controller for store the given graph data into to in-memory database
	 */
	@RequestMapping(value = "", method = RequestMethod.POST)
	public GraphResponse createGraph(@RequestBody MultipartFile file) {
		return graphService.createGraph(file);
	}
	
	
	@RequestMapping(value = "", method = RequestMethod.DELETE)
	public GraphResponse deletePlanet() {
		return graphService.deleteGraph();
	}
}
