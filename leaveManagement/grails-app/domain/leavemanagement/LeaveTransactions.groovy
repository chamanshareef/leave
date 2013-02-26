package leavemanagement

import java.util.Date;

class LeaveTransactions {
	Date startDate
	Date endDate
	String userComment
	String managerComment
	int isActive = 1 
	static belongsTo = [leaveApplied:LeaveApplied]
	
	static constraints =  {
		startDate(blank:false,nullable:false,unique:false)
		endDate(blank:false,nullable:false,unique:false)
		userComment(blank:false,nullable:true)
		managerComment(blank:false,nullable:true)
		isActive(blank:false,nullable:false)
		
		
	}
	/*public String toString(){
		println "startDate:"+startDate+" endDate:"+endDate+" userComment:"+userComment+" managerComment:"+managerComment
	}*/
}
