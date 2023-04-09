package com.robertoalv.repaso.model.entities;

import java.util.Calendar;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
	private String id;
	private String name;
	private String lastName;
	private Date contractDate;
	private Boolean active;
	private String role;
	private String password;
	
	
	private static boolean isRecentlyHired(Date contractDate) {
        Calendar limitDate = Calendar.getInstance();
        limitDate.add(Calendar.DAY_OF_MONTH, -30);
        return contractDate.after(limitDate.getTime());
    }
	
	public boolean isRecentlyHired() {
        return isRecentlyHired(this.contractDate);
    }
	

}



