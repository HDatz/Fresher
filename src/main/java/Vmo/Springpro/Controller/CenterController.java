package Vmo.Springpro.Controller;

import Vmo.Springpro.Dtorequest.ApiRespone;
import Vmo.Springpro.Dtorequest.CenterCreationRequest;
import Vmo.Springpro.Model.Center;
import Vmo.Springpro.Service.CenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/centers")
public class CenterController {

    @Autowired
    private CenterService centerService;

    @PostMapping
    public ResponseEntity<ApiRespone<Center>> createCenter(@RequestBody CenterCreationRequest request) {
        Center createdCenter = centerService.createCenter(request);
        ApiRespone<Center> apiResponse = new ApiRespone<>(200, "Center created successfully", createdCenter);
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @GetMapping
    public ResponseEntity<ApiRespone<List<Center>>> getAllCenters() {
        List<Center> centers = centerService.getAllCenter();
        ApiRespone<List<Center>> apiResponse = new ApiRespone<>(200, "Fetched all centers successfully", centers);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiRespone<Center>> getCenterById(@PathVariable int id) {
        Center center = centerService.getCenterById(id);
        ApiRespone<Center> apiResponse = new ApiRespone<>(200, "Fetched center successfully", center);
        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiRespone<Center>> updateCenter(@PathVariable int id, @RequestBody CenterCreationRequest request) {
        Center updatedCenter = centerService.updateCenter(id, request);
        ApiRespone<Center> apiResponse = new ApiRespone<>(200, "Center updated successfully", updatedCenter);
        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiRespone<Void>> deleteCenter(@PathVariable int id) {
        centerService.deleteCenter(id);
        ApiRespone<Void> apiResponse = new ApiRespone<>(200, "Center deleted successfully", null);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(apiResponse);
    }
}
