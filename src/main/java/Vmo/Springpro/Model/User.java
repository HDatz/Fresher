package Vmo.Springpro.Model;

import java.util.Set;

import jakarta.persistence.*;
import lombok.*;

@MappedSuperclass
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    
    @Column(nullable = false, unique = true, length = 100)
    private String username;

    @Column(nullable = false, length = 255)
    private String password;
    
    @Column(name = "role")
    private Set<String> roles; // Ví dụ: ROLE_ADMIN, ROLE_FRESHER
}
