package leavemanagement


import java.util.Date;

enum UserStatusType {
	CREATED, ACTIVE, INACTIVE
}

enum UserType {
	L1, L2, L3 
}

class User {
	
	String emailId
	String password
	String token
	Date validTill
	UserStatusType status
	UserType type
	static hasOne = [employee:Employee]
	
	static hasMany = [leaveApplied:LeaveApplied, managers:User, reportees:User]
	
    static constraints = {
		emailId(blank:false,unique:true, nullable:false, email:true)
		password(length:5..20, nullable:false, blank:false)
		token(length:6..20, nullable:true)
		validTill(nullable:true)
    }
}