package Vmo.Springpro.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import Vmo.Springpro.Model.Fresher;

public interface FresherRepository extends JpaRepository<Fresher, Integer> {
    
    boolean existsByEmail(String email);
    
    Optional<Fresher> findByName(String Username);  
    
    List<Fresher> findByNameContainingIgnoreCase(String name);
    
    List<Fresher> findByEmailContainingIgnoreCase(String email);
    
    List<Fresher> findByProgrammingLanguage(String programmingLanguage);
    


}
