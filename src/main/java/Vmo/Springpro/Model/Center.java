package Vmo.Springpro.Model;


import jakarta.persistence.*;
import lombok.*;

    
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Center")
public class Center {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "center_id")
    private Integer centerId;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "address")
    private String address;
    
  
   
    

}

    

