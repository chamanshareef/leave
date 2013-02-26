package leavemanagement

import java.util.Date;

enum GenderType {
	MALE, FEMALE
}

class Employee {

	String name
	Date dateOfBirth
	String empId
	GenderType gender = GenderType.FEMALE
	int leaveCount=0
	
//	static hasMany = [managers : Manager]
	
	static belongsTo = [user : User]
	static constraints = {
		name(blank:false, nullable:false,unique:false)
		dateOfBirth(unique:false, nullable:false, blank:false)
		empId(blank:false, nullable:false,unique:true)
	}
}