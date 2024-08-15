package Vmo.Springpro.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Vmo.Springpro.Dtorequest.FresherCreationRequest;
import Vmo.Springpro.Model.Center;
import Vmo.Springpro.Model.Fresher;
import Vmo.Springpro.repository.CenterRepository;
import Vmo.Springpro.repository.FresherRepository;

@Service
public class FresherService {

    @Autowired
    private FresherRepository fresherRepository;

    @Autowired
    private CenterRepository centerRepository;

    // Tạo mới một fresher
    public Fresher createFresher(FresherCreationRequest request) {
        Fresher fresher = new Fresher();
        fresher.setName(request.getName());
        fresher.setEmail(request.getEmail());
        fresher.setPhone(request.getPhone());
        fresher.setProgramming_language(request.getProgrammingLanguage());

        // Liên kết fresher với center
        Center center = centerRepository.findById(request.getCenterId())
                .orElseThrow(() -> new RuntimeException("Center not found"));
        fresher.setCenter_id(center);

        return fresherRepository.save(fresher);
    }

    // Lấy danh sách tất cả fresher
    public List<Fresher> getAllFreshers() {
        return fresherRepository.findAll();
    }

    // Lấy một fresher theo ID
    public Fresher getFresherById(int id) {
        return fresherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fresher not found"));
    }

    // Cập nhật thông tin của fresher
    public Fresher updateFresher(int id, FresherCreationRequest request) {
        Fresher fresher = fresherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fresher not found"));
        
        fresher.setName(request.getName());
        fresher.setEmail(request.getEmail());
        fresher.setPhone(request.getPhone());
        fresher.setProgramming_language(request.getProgrammingLanguage());

        Center center = centerRepository.findById(request.getCenterId())
                .orElseThrow(() -> new RuntimeException("Center not found"));
        fresher.setCenter_id(center);

        return fresherRepository.save(fresher);
    }

    // Xóa một fresher theo ID
    public boolean deleteFresher(int id) {
        if (fresherRepository.existsById(id)) {
            fresherRepository.deleteById(id);
            return true; // Trả về true nếu xóa thành công
        } else {
            return false; // Trả về false nếu fresher không tồn tại
        }
    }

}
