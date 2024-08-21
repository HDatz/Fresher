package Vmo.Springpro.repository;

import java.util.Optional;

import org.mapstruct.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;
import Vmo.Springpro.Model.Fresher;

@Mapper(componentModel = "spring")
public interface FresherRepository extends JpaRepository<Fresher, Integer> {
    boolean existsByEmail(String email);
    Optional<Fresher> findByUsername(String name);
}

