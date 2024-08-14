package Vmo.Springpro.Model;

import java.sql.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="EmailNotifications")
public class EmailNotifications {
	
	@Id
	@Column(name="eamil_id")
	private int email_id;
	
	@Column(name="fresher_id")
	private fresher fresher_id;
	
	@Column(name="action")
	private String action;
	
	@Column(name="email_date")
	private Date email_date;
	
	
	
}
