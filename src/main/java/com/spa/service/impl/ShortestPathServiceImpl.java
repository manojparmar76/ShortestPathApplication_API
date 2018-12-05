/**
 * 
 */
package com.spa.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spa.dao.PlanetRepository;
import com.spa.dao.TrafficRepository;
import com.spa.entity.Planet;
import com.spa.entity.Route;
import com.spa.entity.Traffic;
import com.spa.request.ShortestPathRequest;
import com.spa.response.ShortestPathResponse;
import com.spa.service.ShortestPathService;
import com.spa.util.ShortestPath;

/**
 * @author Manoj
 *
 */
@Service
public class ShortestPathServiceImpl implements ShortestPathService {

	@Autowired
	private PlanetRepository planetRepository;
	
	@Autowired
	private TrafficRepository trafficRepository;

	/**
	 * Method to get the planet id which is available in database
	 * @param index
	 * @return
	 */
	private int getActualPlanetId(int index) {
		return (index + 1);
	}

	/**
	 * Method to find the index value for the given planet.
	 * @param planetId
	 * @return
	 */
	private int getPlanetIndex(int planetId) {
		return (planetId - 1);
	}

	/**
	 * Method to create matrix for the all the planets available in the database
	 * @return
	 */
	private double[][] createGraphMatrix() {
		List<Planet> planets = (List<Planet>) planetRepository.findAll();
		double[][] graphMatrix = new double[planets.size()][planets.size()];
		for (Planet planet : planets) {
			int sourceIndex = getPlanetIndex(planet.getPlanetId());
			List<Route> routes = planet.getRoutes();
			for (Route route : routes) {
				int destnationIndex = getPlanetIndex(route.getPlanetDestination().getPlanetId());
				graphMatrix[sourceIndex][destnationIndex] = route.getDistance();
			}
		}
		return graphMatrix;
	}

	/**
	 * Method to find the all the planet node and name which is visited in finding the shortest path.
	 * @param planetIndexes
	 * @return
	 */
	private Map<String, List<String>> getVisitedNodes(List<Integer> planetIndexes) {
		Map<String, List<String>> nodeDetails = new HashMap<String, List<String>>();
		List<String> planetNodes = new ArrayList<String>();
		List<String> planetNames = new ArrayList<String>();
		for (Integer planetIndex : planetIndexes) {
			Optional<Planet> planet = planetRepository.findById(getActualPlanetId(planetIndex));
			if (planet.get() != null) {
				planetNodes.add(planet.get().getPlanetNode());
				planetNames.add(planet.get().getPlanetName());
			}
		}
		nodeDetails.put("nodes", planetNodes);
		nodeDetails.put("names", planetNames);
		return nodeDetails;
	}

	/**
	 * Method to find the traffic between visited nodes.
	 * @param planetIndexes
	 * @return
	 */
	private double calculateTraffic(List<Integer> planetIndexes) {
		double trafficCount = 0;
		Iterator<Integer> planets = planetIndexes.iterator();
		int currentIndex = planets.next();
		Planet currentPlanet = planetRepository.findById(getActualPlanetId(currentIndex)).get();
		
		while(planets.hasNext()) {
			int nextIndex = planets.next();
			Planet nextPlanet = planetRepository.findById(getActualPlanetId(nextIndex)).get();
			Traffic traffic = trafficRepository.findByPlanetOriginAndPlanetDestination(currentPlanet, nextPlanet);
			if(traffic != null) {
				trafficCount = trafficCount + traffic.getDelay();
			}
			currentPlanet = nextPlanet;
		}
		return trafficCount;		
	}

	/* (non-Javadoc)
	 * @see com.spa.service.ShortestPathService#findShortestPath(com.spa.request.ShortestPathRequest)
	 * Method to find the shortest path between nodes and create response object.
	 */
	@Override
	public ShortestPathResponse findShortestPath(ShortestPathRequest shortestPathRequest) {
		ShortestPathResponse shortestPathResponse = new ShortestPathResponse();
		
		if ((shortestPathRequest.getSource() != null && !shortestPathRequest.getSource().equals(""))
				&& (shortestPathRequest.getDestination() != null && !shortestPathRequest.getDestination().equals(""))) {
			
			if(!shortestPathRequest.getSource().equals(shortestPathRequest.getDestination())) {
				
				//find the source and destination planet entity.
				Planet src = planetRepository.findByPlanetNode(shortestPathRequest.getSource());
				Planet dst = planetRepository.findByPlanetNode(shortestPathRequest.getDestination());
				
				if (src != null && dst != null) {
					
					if(getPlanetIndex(src.getPlanetId()) < getPlanetIndex(dst.getPlanetId())) {
						
						//Create matrix using database 
						double[][] graphMatrix = createGraphMatrix();
						
						//ShortestPath shortestPath = new ShortestPath();
						//Find the shortest path in the graph between source and destination.
						double shortestDistance = ShortestPath.findMinimumDistance(graphMatrix,
								getPlanetIndex(src.getPlanetId()), getPlanetIndex(dst.getPlanetId()));
						
						if (shortestDistance != Integer.MAX_VALUE) {
							//Create response
							//Find the visited nodes in the which is involved in shortest path
							List<Integer> pathIds = ShortestPath.findVisitedNodeIndex(getPlanetIndex(dst.getPlanetId()),
									ShortestPath.getShortestPath(), new ArrayList<Integer>());			
							Map<String, List<String>> visitedNodes = getVisitedNodes(pathIds);
							shortestPathResponse.setSource(src.getPlanetNode());
							shortestPathResponse.setDestination(dst.getPlanetNode());
							shortestPathResponse.setDistance(shortestDistance);
							shortestPathResponse.setNodes(visitedNodes.get("nodes"));
							shortestPathResponse.setPlanetNames(visitedNodes.get("names"));
							if (shortestPathRequest.isWithTraffic()) {
								//Find the traffic between visited node.
								double traffic = calculateTraffic(pathIds);
								shortestPathResponse.setTraffic(traffic);
							}
						} else {
							shortestPathResponse.setMessage("There are no path between given nodes");
						}
					} else {
						shortestPathResponse.setMessage("Source node should be smaller than Destination node");
					}					
				} else {
					shortestPathResponse.setMessage("Planet Node not found in graph.");
				}
			} else {
				shortestPathResponse.setMessage("Source and Destination should be different.");
			}
		} else {
			shortestPathResponse.setMessage("Source and Destination both are required to find shortest path.");
		}
		return shortestPathResponse;
	}
}
