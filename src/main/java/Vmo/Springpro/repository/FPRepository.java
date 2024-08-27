package Vmo.Springpro.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import Vmo.Springpro.Model.Fresher;
import Vmo.Springpro.Model.Fresher_Project;
import Vmo.Springpro.Model.Project;

public interface FPRepository extends JpaRepository<Fresher_Project, Integer> {
	
    List<Fresher_Project> findByFresher(Fresher fresher);
    
    Optional<Fresher_Project> findByFresherAndProject(Fresher fresher, Project project);
    
    boolean existsByFresherAndProject(Fresher fresher, Project project);


}


