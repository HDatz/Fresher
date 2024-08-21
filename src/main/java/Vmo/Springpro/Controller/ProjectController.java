package Vmo.Springpro.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import Vmo.Springpro.Dtorequest.ApiRespone;
import Vmo.Springpro.Dtorequest.ProjectCreationRequest;
import Vmo.Springpro.Error.AppException;
import Vmo.Springpro.Model.Project;
import Vmo.Springpro.Service.ProjectService;

@RestController
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    // Tạo mới một Project
    @PostMapping
    public ResponseEntity<ApiRespone<Project>> createProject(@RequestBody ProjectCreationRequest request) {
        Project project = projectService.createProject(request);
        return ResponseEntity.status(201)
            .body(new ApiRespone<>(201, "Project created successfully", project));
    }

    // Lấy tất cả các Project
    @GetMapping
    public ResponseEntity<ApiRespone<List<Project>>> getAllProjects() {
        List<Project> projects = projectService.getAllProjects();
        return ResponseEntity.ok(new ApiRespone<>(200, "Fetched all projects", projects));
    }

    // Lấy thông tin Project theo ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiRespone<Project>> getProjectById(@PathVariable int id) {
        Optional<Project> project = Optional.ofNullable(projectService.getProjectById(id));
        return project.map(value -> ResponseEntity.ok(new ApiRespone<>(200, "Project found", value)))
                      .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                            .body(new ApiRespone<>(404, "Project not found", null)));
    }

    // Cập nhật thông tin Project
    @PutMapping("/{id}")
    public ResponseEntity<ApiRespone<Project>> updateProject(@PathVariable int id, @RequestBody ProjectCreationRequest request) {
        Optional<Project> updatedProject = Optional.ofNullable(projectService.updateProject(id, request));
        return updatedProject.map(value -> ResponseEntity.ok(new ApiRespone<>(200, "Project updated successfully", value)))
                             .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                             .body(new ApiRespone<>(404, "Project not found", null)));
    }

    // Xóa Project theo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiRespone<Void>> deleteProject(@PathVariable int id) {
        try {
            projectService.deleteProject(id);
            return ResponseEntity.noContent().build();
        } catch (AppException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiRespone<>(404, e.getMessage(), null));
        }
    }
}
