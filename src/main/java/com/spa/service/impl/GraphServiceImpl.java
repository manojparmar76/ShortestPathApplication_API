/**
 * 
 */
package com.spa.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.spa.dao.PlanetRepository;
import com.spa.dao.RouteRepository;
import com.spa.dao.TrafficRepository;
import com.spa.entity.Planet;
import com.spa.entity.Route;
import com.spa.entity.Traffic;
import com.spa.response.GraphResponse;
import com.spa.service.GraphService;

/**
 * @author Manoj
 *
 */
@Service
public class GraphServiceImpl implements GraphService {

	@Autowired
	private PlanetRepository planetRepository;

	@Autowired
	private RouteRepository routeRepository;

	@Autowired
	private TrafficRepository trafficRepository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.spa.service.GraphService#createGraph(org.springframework.web.
	 * multipart.MultipartFile) This method is responsible to read the excel
	 * file and process it.
	 */
	@SuppressWarnings({ "resource", "unused" })
	@Override
	public GraphResponse createGraph(MultipartFile file) {
		GraphResponse graphResponse = new GraphResponse();
		long planetInDatabase = planetRepository.count();
		if(planetInDatabase == 0) {
			if (file != null) {
				try {
					InputStream excelFile = file.getInputStream();
					Workbook workbook = new XSSFWorkbook(excelFile);

					for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
						Sheet currentSheet = workbook.getSheetAt(i);
						Iterator<Row> rows = currentSheet.iterator();
						Row headerRow = rows.next();
						while (rows.hasNext()) {
							Row currentRow = rows.next();
							saveData(currentSheet.getSheetName(), currentRow);
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				graphResponse.setMessage("File uploaded successfully.");
			} else {
				graphResponse.setMessage("File is not present.");
			}	
		} else {
			graphResponse.setMessage("Grpah already created.");
		}
		return graphResponse;
	}
	
	/* (non-Javadoc)
	 * @see com.spa.service.GraphService#deleteGraph()
	 * Method to delete the complete graph from the database
	 */
	@Override
	public GraphResponse deleteGraph() {
		GraphResponse graphResponse = new GraphResponse();
		planetRepository.deleteAll();
		graphResponse.setMessage("Graph deleted successfully.");
		return graphResponse;
	}

	/**
	 * Method is responsible for the save the excel file data into the database.
	 * 
	 * @param sheetName
	 * @param currentRow
	 */
	private void saveData(String sheetName, Row currentRow) {
		Iterator<Cell> cells = currentRow.iterator();
		Cell currentCell = null;
		if ("Planet Names".equalsIgnoreCase(sheetName)) {
			Planet planet = new Planet();
			currentCell = cells.next();
			planet.setPlanetNode(currentCell.getStringCellValue());
			currentCell = cells.next();
			planet.setPlanetName(currentCell.getStringCellValue());
			planetRepository.save(planet);
		} else if ("Routes".equalsIgnoreCase(sheetName)) {
			Route route = new Route();
			currentCell = cells.next();
			currentCell = cells.next();
			Planet planetOrigin = planetRepository.findByPlanetNode(currentCell.getStringCellValue());
			route.setPlanetOrigin(planetOrigin);
			currentCell = cells.next();
			Planet planetDestination = planetRepository.findByPlanetNode(currentCell.getStringCellValue());
			route.setPlanetDestination(planetDestination);
			currentCell = cells.next();
			route.setDistance(currentCell.getNumericCellValue());
			if (planetOrigin != null && planetDestination != null) {
				routeRepository.save(route);
			}
		} else if ("Traffic".equalsIgnoreCase(sheetName)) {
			Traffic traffic = new Traffic();
			currentCell = cells.next();
			currentCell = cells.next();
			Planet planetOrigin = planetRepository.findByPlanetNode(currentCell.getStringCellValue());
			traffic.setPlanetOrigin(planetOrigin);
			currentCell = cells.next();
			Planet planetDestination = planetRepository.findByPlanetNode(currentCell.getStringCellValue());
			traffic.setPlanetDestination(planetDestination);
			currentCell = cells.next();
			traffic.setDelay(currentCell.getNumericCellValue());
			if (planetOrigin != null && planetDestination != null) {
				trafficRepository.save(traffic);
			}
		}
	}

}
