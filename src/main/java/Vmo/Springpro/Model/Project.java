package Vmo.Springpro.Model;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Project {
	private int project_id;
	
	private String projetc_name;
	
	private String project_code;
	
	private Center center_id;
	
	private String manager;
	
	private Date start_date;
	
	private Date end_date;
	
	private String programming_language;
	
	private String status;
	
	
}
