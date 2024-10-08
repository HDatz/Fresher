package Vmo.Springpro.Model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)  // To handle Lombok's equals/hashCode with inheritance
@ToString(callSuper = true)           // To include User's fields in the toString method
@Table(name = "fresher")
public class Fresher extends User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int fresher_id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(length = 20)
    private String phone;

    @Column(name = "programming_language")
    private String programmingLanguage;

    @ManyToOne
    @JoinColumn(name = "center_id")
    private Center center;
}
