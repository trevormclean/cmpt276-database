package a2_276.a2.models;

import java.util.List; 
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer>{
    List<Student> findByHairColor(String hairColor);
}
