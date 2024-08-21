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

    public Scores createScores(ScoresCreationRequest request) {
        Fresher fresher = fresherRepository.findById(request.getFresher_id())
                .orElseThrow(() -> new AppException(ErrorClass.USER_EXISTED));

        Scores scores = new Scores();
        scores.setFresher(fresher);
        scores.setAssignment1(request.getAssignment1());
        scores.setAssignment2(request.getAssignment2());
        scores.setAssignment3(request.getAssignment3());

        // finalScore sẽ tự động được tính khi entity được tải từ database
        return scoresRepository.save(scores);
    }
    
    public List<Scores> getAllScores(){
    	return scoresRepository.findAll();
    }

    public Scores getScoresById(int id) {
        return scoresRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorClass.SCORE_NOT_FOUND));
    }

    public Scores updateScores(int id, ScoresCreationRequest request) {
        Scores scores = scoresRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorClass.SCORE_NOT_FOUND));

        scores.setAssignment1(request.getAssignment1());
        scores.setAssignment2(request.getAssignment2());
        scores.setAssignment3(request.getAssignment3());

        // finalScore sẽ tự động được cập nhật khi entity được tải từ database
        return scoresRepository.save(scores);
    }
}
