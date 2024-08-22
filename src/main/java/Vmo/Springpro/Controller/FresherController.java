package Vmo.Springpro.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import Vmo.Springpro.Dtorequest.ApiRespone;
import Vmo.Springpro.Dtorequest.FresherCreationRequest;
import Vmo.Springpro.Model.Fresher;
import Vmo.Springpro.Service.FresherService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/freshers")
public class FresherController {

	@Autowired
	private FresherService fresherService;

	// Tạo một Fresher mới
	@PostMapping
	public ResponseEntity<ApiRespone<Fresher>> createFresher(@RequestBody FresherCreationRequest request) {
		if (fresherService.existsByEmail(request.getEmail())) {
			return ResponseEntity.badRequest().body(new ApiRespone<>(400, "Email already exists", null));
		}
		Fresher fresher = fresherService.createFresher(request);
		return ResponseEntity.status(201).body(new ApiRespone<>(201, "Fresher created successfully", fresher));
	}

	// Lấy tất cả các Fresher
	@GetMapping
	public ResponseEntity<ApiRespone<List<Fresher>>> getAllFreshers() {
		List<Fresher> freshers = fresherService.getAllFreshers();
		return ResponseEntity.ok(new ApiRespone<>(200, "Fetched all Freshers", freshers));
	}

	// Lấy thông tin Fresher theo ID
	@GetMapping("/{id}")
	public ResponseEntity<ApiRespone<Fresher>> getFresherById(@PathVariable int id) {
		Optional<Fresher> fresher = Optional.ofNullable(fresherService.getFresherById(id));
		return fresher.map(value -> ResponseEntity.ok(new ApiRespone<>(200, "Fresher found", value)))
				.orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body(new ApiRespone<>(404, "Fresher not found", null)));
	}

	// Tìm fresher theo tên
	@GetMapping("/name/{name}")
	public ResponseEntity<ApiRespone<List<Fresher>>> getNameFreshers(@PathVariable String name) {
	    List<Fresher> freshers = fresherService.sreachByName(name);
	    if (freshers.isEmpty()) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                .body(new ApiRespone<>(404, "No Freshers found with the given name", null));
	    }
	    return ResponseEntity.ok(new ApiRespone<>(200, "Freshers found", freshers));
	}
	
	// Tìm theo Email
	@GetMapping("/email/{email}")
    public ResponseEntity<ApiRespone<List<Fresher>>> getFreshersByEmail(@PathVariable String email) {
        List<Fresher> freshers = fresherService.sreachByEmail(email);
        if (freshers.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiRespone<>(404, "No Freshers found with the given email", null));
        }
        return ResponseEntity.ok(new ApiRespone<>(200, "Freshers found", freshers));
    }
	
	@GetMapping("/langue/{language}")
	public ResponseEntity<ApiRespone<List<Fresher>>> getFreshersLangue(@PathVariable String language) {
	    List<Fresher> freshers = fresherService.sreachByLangue(language);
	    if (freshers.isEmpty()) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                .body(new ApiRespone<>(404, "No Freshers found with the given language", null));
	    }
	    return ResponseEntity.ok(new ApiRespone<>(200,"Freshers found", freshers));
	}

	
	// Cập nhật thông tin Fresher
	@PutMapping("/{id}")
	public ResponseEntity<ApiRespone<Fresher>> updateFresher(@PathVariable int id,
			@RequestBody FresherCreationRequest request) {
		Optional<Fresher> updatedFresher = Optional.ofNullable(fresherService.updateFresher(id, request));
		return updatedFresher
				.map(value -> ResponseEntity.ok(new ApiRespone<>(200, "Fresher updated successfully", value)))
				.orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body(new ApiRespone<>(404, "Fresher not found", null)));
	}

	// Xóa một Fresher theo ID
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiRespone<Void>> deleteFresher(@PathVariable int id) {
		try {
			fresherService.deleteFresher(id);
			return ResponseEntity.noContent().build(); // Trả về mã trạng thái 204 No Content nếu thành công
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiRespone<>(404, e.getMessage(), null));
		}
	}
}
