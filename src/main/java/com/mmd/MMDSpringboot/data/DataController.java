package com.mmd.MMDSpringboot.data;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mmd.MMDSpringboot.column.ColumnService;
import com.mmd.MMDSpringboot.project.ProjectService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("data")
public class DataController {
	
	private DataService dataService;

	public DataController(DataService dataService, ColumnService columnService, ProjectService projectService) {
		super();
		this.dataService = dataService;
	}

	//GET /data
	@GetMapping
	public List<Data> getAllData(){
		return dataService.getAllData();
	}
	
	@GetMapping("/projectData/{projectId}")
	public List<Data> getDataByProjectId(@PathVariable Long projectId) {
		List<Data> projectData = dataService.getDataByProjectId(projectId);
		return projectData;
	}
	
//	@GetMapping("/projectCSV/{projectId}")
//	public Map<Integer, Map<String,String>> getProjectAsCSV(@PathVariable Long projectId) {
//		return dataService.getProjectAsCSV(projectId);
//	}
	
	
//	@RequestMapping(value = "/projectCSV/{projectId}", method = RequestMethod.GET, produces = "application/json")
	@GetMapping("/projectCSV/{projectId}")
	public List<Map<String,String>> getProjectAsCSV(@PathVariable Long projectId) {
		return dataService.getProjectAsCSV(projectId);
	}
	
	@PostMapping("/addData/projectId/{projId}/columnId/{colId}")
	public Long addData(@PathVariable Long projId, @PathVariable Long colId, @RequestBody Data data, HttpServletResponse httpResponse) {
		Long id = dataService.addData(projId, colId, data);
		httpResponse.setStatus(HttpServletResponse.SC_CREATED);
		return id;
	}
	
	@PutMapping("/updateData/{dataId}")
	public Long updateData(@PathVariable Long dataId, @Valid @RequestBody Data data) {
		return dataService.updateData(dataId, data);
	}

}
