package Vmo.Springpro.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Vmo.Springpro.Dtorequest.ScoresCreationRequest;
import Vmo.Springpro.Error.AppException;
import Vmo.Springpro.Error.ErrorClass;
import Vmo.Springpro.Model.Fresher;
import Vmo.Springpro.Model.Scores;
import Vmo.Springpro.repository.FresherRepository;
import Vmo.Springpro.repository.ScoresRepository;

@Service
public class ScoresService {

    @Autowired
    private ScoresRepository scoresRepository;

    @Autowired
    private FresherRepository fresherRepository;

    // Tính toán điểm cuối cùng
    private void calculateFinalScore(Scores scores) {
        float finalScore = (scores.getAssignment1() + scores.getAssignment2() + scores.getAssignment3()) / 3;
        scores.setFinalScore(finalScore);
    }

    // Tạo điểm cho fresher 
    public Scores createScores(ScoresCreationRequest request) {
        Fresher fresher = fresherRepository.findById(request.getFresher_id())
                .orElseThrow(() -> new AppException(ErrorClass.USER_EXISTED));

        Scores scores = new Scores();
        scores.setFresher(fresher);
        scores.setAssignment1(request.getAssignment1());
        scores.setAssignment2(request.getAssignment2());
        scores.setAssignment3(request.getAssignment3());

        // Tính toán finalScore trước khi lưu
        calculateFinalScore(scores);

        return scoresRepository.save(scores);
    }

    // Tìm điểm tốt (finalScore >= 8)
    public List<Scores> getGoodScores() {
        return scoresRepository.findByFinalScoreGreaterThanEqual(8.1f);
    }

    // Tìm điểm trung bình (6.5 <= finalScore < 8)
    public List<Scores> getMidScores() {
        return scoresRepository.findByFinalScoreBetween(6.5f, 8f);
    }

    // Tìm điểm kém (finalScore < 6.5)
    public List<Scores> getLowScores() {
        return scoresRepository.findByFinalScoreLessThan(6.5f);
    }

    // Lấy ra tất cả điểm 
    public List<Scores> getAllScores(){
        return scoresRepository.findAll();
    }

    // Lấy ra điểm bảng theo id 
    public Scores getScoresById(int id) {
        return scoresRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorClass.SCORE_NOT_FOUND));
    }

    // Cập nhật bảng điểm 
    public Scores updateScores(int id, ScoresCreationRequest request) {
        Scores scores = scoresRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorClass.SCORE_NOT_FOUND));

        scores.setAssignment1(request.getAssignment1());
        scores.setAssignment2(request.getAssignment2());
        scores.setAssignment3(request.getAssignment3());

        // Tính toán finalScore trước khi lưu
        calculateFinalScore(scores);

        return scoresRepository.save(scores);
    }
    
    //Delete
    public void deleteScores(int id) {
        Scores scores = scoresRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorClass.SCORE_NOT_FOUND));
        scoresRepository.delete(scores);
    }
}
