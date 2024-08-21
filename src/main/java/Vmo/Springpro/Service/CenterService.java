package Vmo.Springpro.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Vmo.Springpro.Dtorequest.CenterCreationRequest;
import Vmo.Springpro.Error.AppException;
import Vmo.Springpro.Error.ErrorClass;
import Vmo.Springpro.Model.Center;
import Vmo.Springpro.repository.CenterRepository;

@Service
public class CenterService {

    @Autowired
    private CenterRepository centerRepository;
   

    // Tạo mới một Center
    @Transactional
    public Center createCenter(CenterCreationRequest request) {
        // Kiểm tra nếu center đã tồn tại với tên và địa chỉ này
        if (centerRepository.existsByNameAndAddress(request.getName(), request.getAddress())) {
            throw new AppException(ErrorClass.USER_EXISTED);  // Sử dụng USER_EXISTED hoặc một mã lỗi phù hợp khác
        }

        Center center = new Center();
       
        return centerRepository.save(center);
    }

    // Lấy tất cả center
    public List<Center> getAllCenter() {
        return centerRepository.findAll();
    }

    // Lấy Center theo ID
    public Center getCenterById(int id) {
        return centerRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorClass.CENTER_NOT_FOUND));
    }

    // Cập nhật thông tin Center
    @Transactional
    public Center updateCenter(int id, CenterCreationRequest request) {
        Center center = centerRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorClass.CENTER_NOT_FOUND));

        center.setName(request.getName());
        center.setAddress(request.getAddress());

        return centerRepository.save(center);
    }

    // Xóa Center theo ID
    @Transactional
    public void deleteCenter(int id) {
        Center center = centerRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorClass.CENTER_NOT_FOUND));
        centerRepository.delete(center);
    }
}
