package Vmo.Springpro.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="Center")
public class Center {
	
	@Id
	@Column (name="center_id")
	private int center_id;
	
	@Column (name="center_name")
	private String center_name;
	
	@Column (name = "location")
	private String location;
	
}
