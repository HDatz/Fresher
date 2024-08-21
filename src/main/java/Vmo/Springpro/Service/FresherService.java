package Vmo.Springpro.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import Vmo.Springpro.Dtorequest.FresherCreationRequest;
import Vmo.Springpro.Error.AppException;
import Vmo.Springpro.Error.ErrorClass;
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

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);

    // Tạo mới một fresher
    public Fresher createFresher(FresherCreationRequest request) {
        Fresher fresher = new Fresher();
        fresher.setUsername(request.getUsername());
        fresher.setPassword(passwordEncoder.encode(request.getPassword()));
        fresher.setName(request.getName());
        fresher.setEmail(request.getEmail());
        fresher.setPhone(request.getPhone());
        fresher.setProgramming_language(request.getProgrammingLanguage());

        // Liên kết fresher với center
        Center center = centerRepository.findById(request.getCenterId())
                .orElseThrow(() -> new AppException(ErrorClass.CENTER_NOT_FOUND));
        fresher.setCenter_id(center);

        return fresherRepository.save(fresher);
    }

    // Kiểm tra sự tồn tại của email
    public boolean existsByEmail(String email) {
        return fresherRepository.existsByEmail(email);
    }

    // Lấy danh sách tất cả fresher (giữ nguyên mật khẩu mã hóa)
    public List<Fresher> getAllFreshers() {
        return fresherRepository.findAll(); // Giữ nguyên mật khẩu mã hóa
    }

    // Lấy một fresher theo ID (giữ nguyên mật khẩu mã hóa)
    public Fresher getFresherById(int id) {
        return fresherRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorClass.FP_NOT_FOUND));
    }

    // Cập nhật thông tin của fresher
    public Fresher updateFresher(int id, FresherCreationRequest request) {
        Fresher fresher = fresherRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorClass.FP_NOT_FOUND));

        fresher.setUsername(request.getUsername());
        fresher.setPassword(passwordEncoder.encode(request.getPassword())); // Mã hóa mật khẩu
        fresher.setName(request.getName());
        fresher.setEmail(request.getEmail());
        fresher.setPhone(request.getPhone());
        fresher.setProgramming_language(request.getProgrammingLanguage());

        Center center = centerRepository.findById(request.getCenterId())
                .orElseThrow(() -> new AppException(ErrorClass.CENTER_NOT_FOUND));
        fresher.setCenter_id(center);

        return fresherRepository.save(fresher);
    }

    // Xóa một fresher theo ID
    public void deleteFresher(int id) {
        Fresher fresher = fresherRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorClass.FP_NOT_FOUND));
        fresherRepository.delete(fresher);
    }
}
