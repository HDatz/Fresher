package Vmo.Springpro.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import Vmo.Springpro.Dtorequest.ScoresCreationRequest;
import Vmo.Springpro.Error.AppException;
import Vmo.Springpro.Model.Scores;
import Vmo.Springpro.Service.ScoresService;

import java.util.List;

@RestController
@RequestMapping("/scores")
public class ScoresController {

    @Autowired
    private ScoresService scoresService;

    // Tạo điểm cho fresher
    @PostMapping
    public ResponseEntity<Scores> createScores(@RequestBody ScoresCreationRequest request) {
        try {
            Scores createdScores = scoresService.createScores(request);
            return new ResponseEntity<>(createdScores, HttpStatus.CREATED);
        } catch (AppException ex) {
            // Xử lý lỗi và trả về mã lỗi tương ứng
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    // Lấy điểm theo id
    @GetMapping("/{id}")
    public ResponseEntity<Scores> getScoresById(@PathVariable int id) {
        try {
            Scores scores = scoresService.getScoresById(id);
            return new ResponseEntity<>(scores, HttpStatus.OK);
        } catch (AppException ex) {
            // Xử lý lỗi và trả về mã lỗi tương ứng
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Cập nhật điểm theo id
    @PutMapping("/{id}")
    public ResponseEntity<Scores> updateScores(@PathVariable int id, @RequestBody ScoresCreationRequest request) {
        try {
            Scores updatedScores = scoresService.updateScores(id, request);
            return new ResponseEntity<>(updatedScores, HttpStatus.OK);
        } catch (AppException ex) {
            // Xử lý lỗi và trả về mã lỗi tương ứng
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Xóa điểm theo id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteScores(@PathVariable int id) {
        try {
            scoresService.deleteScores(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (AppException ex) {
            // Xử lý lỗi và trả về mã lỗi tương ứng
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Lấy tất cả điểm
    @GetMapping
    public ResponseEntity<List<Scores>> getAllScores() {
        List<Scores> scoresList = scoresService.getAllScores();
        return new ResponseEntity<>(scoresList, HttpStatus.OK);
    }

    // Lấy điểm tốt
    @GetMapping("/good")
    public ResponseEntity<List<Scores>> getGoodScores() {
        List<Scores> goodScores = scoresService.getGoodScores();
        return new ResponseEntity<>(goodScores, HttpStatus.OK);
    }

    // Lấy điểm trung bình
    @GetMapping("/mid")
    public ResponseEntity<List<Scores>> getMidScores() {
        List<Scores> midScores = scoresService.getMidScores();
        return new ResponseEntity<>(midScores, HttpStatus.OK);
    }

    // Lấy điểm kém
    @GetMapping("/low")
    public ResponseEntity<List<Scores>> getLowScores() {
        List<Scores> lowScores = scoresService.getLowScores();
        return new ResponseEntity<>(lowScores, HttpStatus.OK);
    }
}
