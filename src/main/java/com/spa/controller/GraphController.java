/**
 * 
 */
package com.spa.controller;

import org.springframework.web.multipart.MultipartFile;

import com.spa.response.GraphResponse;

/**
 * @author Manoj
 *
 */
public interface GraphController {

	GraphResponse createGraph(MultipartFile file);

	GraphResponse deletePlanet();

}
