package leavemanagement

import java.util.Date;

enum LeaveStatusType {
	PENDING, ACCEPTED, REJECTED,USED,CANCEL
}
/*enum LossOfPay{
	N,Y,
}*/
class LeaveApplied {

    String leaveType
	Date startDate
	Date endDate
//	String comment
	LeaveStatusType status
//	String manager_comment
//	LossOfPay LOP
	int isActive = 1
	static belongsTo = [user:User]
	static hasMany = [leaveTransactions:LeaveTransactions]
	static constraints = {
		leaveType(blank:false,nullable:false)
		startDate(blank:false,nullable:false)
		endDate(black:false,nullable:false)
//		comment(blank:false,nullable:true)
//		manager_comment(blank:false,nullable:true)
		isActive(blank:false,nullable:false)
	}
	
	public String toString() {
		return "leaveType :" + leaveType + "status : " + status
	}
}