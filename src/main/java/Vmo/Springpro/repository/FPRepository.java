package Vmo.Springpro.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import Vmo.Springpro.Model.Fresher;
import Vmo.Springpro.Model.Fresher_Project;

public interface FPRepository extends JpaRepository<Fresher_Project, Integer> {
	
    List<Fresher_Project> findByFresher(Fresher fresher);

}
