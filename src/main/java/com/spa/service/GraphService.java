/**
 * 
 */
package com.spa.service;

import org.springframework.web.multipart.MultipartFile;

import com.spa.response.GraphResponse;

/**
 * @author Manoj
 *
 */
public interface GraphService {

	GraphResponse createGraph(MultipartFile file);

	GraphResponse deleteGraph();

}
