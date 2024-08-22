package Vmo.Springpro.repository;



import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;

import Vmo.Springpro.Model.Center;


public interface CenterRepository extends JpaRepository<Center, Integer> {
	
	
	boolean existsByNameAndAddress(String name, String address);
	
	
}
