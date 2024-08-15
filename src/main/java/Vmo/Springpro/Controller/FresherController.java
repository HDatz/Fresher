package Vmo.Springpro.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Fresher> createFresher(@RequestBody FresherCreationRequest request) {
        Fresher fresher = fresherService.createFresher(request);
        return ResponseEntity.status(201).body(fresher); // Trả về mã trạng thái 201 Created
    }

    // Lấy tất cả các Fresher
    @GetMapping
    public ResponseEntity<List<Fresher>> getAllFreshers() {
        List<Fresher> freshers = fresherService.getAllFreshers();
        return ResponseEntity.ok(freshers);
    }

    // Lấy thông tin Fresher theo ID
    @GetMapping("/{id}")
    public ResponseEntity<Fresher> getFresherById(@PathVariable int id) {
        Optional<Fresher> fresher = Optional.ofNullable(fresherService.getFresherById(id));
        return fresher.map(ResponseEntity::ok)
                      .orElseGet(() -> ResponseEntity.notFound().build()); // Trả về mã trạng thái 404 Not Found nếu không tìm thấy
    }

    // Cập nhật thông tin Fresher
    @PutMapping("/{id}")
    public ResponseEntity<Fresher> updateFresher(@PathVariable int id, @RequestBody FresherCreationRequest request) {
        Optional<Fresher> updatedFresher = Optional.ofNullable(fresherService.updateFresher(id, request));
        return updatedFresher.map(ResponseEntity::ok)
                             .orElseGet(() -> ResponseEntity.notFound().build()); // Trả về mã trạng thái 404 Not Found nếu không tìm thấy
    }

    // Xóa một Fresher theo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFresher(@PathVariable int id) {
        boolean isDeleted = fresherService.deleteFresher(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build(); // Trả về mã trạng thái 204 No Content nếu thành công
        } else {
            return ResponseEntity.notFound().build(); // Trả về mã trạng thái 404 Not Found nếu không tìm thấy
        }
    }
}
