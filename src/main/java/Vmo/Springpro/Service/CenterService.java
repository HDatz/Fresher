package Vmo.Springpro.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Vmo.Springpro.Dtorequest.CenterCreationRequest;
import Vmo.Springpro.Error.CenterNotFoundException;
import Vmo.Springpro.Model.Center;
import Vmo.Springpro.repository.CenterRepository;
import jakarta.validation.Valid;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CenterService {

	@Autowired
	private CenterRepository centerRepository;

	// Tạo mới một Center
	@Transactional
	public Center createCenter(@Valid CenterCreationRequest request) {
		Center center = new Center();
		
		if (centerRepository.existsByNameAndAddress(request.getName(), request.getAddress())) {
            throw new RuntimeException("Da ton tai."); // In ra lỗi nếu trùng cả tên và địa chỉ
        }
		center.setName(request.getName());
		center.setAddress(request.getAddress());
		return centerRepository.save(center);
	}

	// Lấy tất cả center
	public List<Center> getAllCenter() {
		return centerRepository.findAll();
	}

	// Lấy Center theo ID
	public Center getCenterById(int id) {
		return centerRepository.findById(id).orElseThrow(() -> new CenterNotFoundException(id));
	}

	// Cập nhật thông tin Center
	@Transactional
	public Center updateCenter(int id, @Valid CenterCreationRequest request) {
		Center center = centerRepository.findById(id).orElseThrow(() -> new CenterNotFoundException(id));

		center.setName(request.getName());
		center.setAddress(request.getAddress());

		return centerRepository.save(center);
	}

	// Xóa Center theo ID
	@Transactional
	public void deleteCenter(int id) {
		Center center = centerRepository.findById(id).orElseThrow(() -> new CenterNotFoundException(id));
		centerRepository.delete(center);
	}
}
