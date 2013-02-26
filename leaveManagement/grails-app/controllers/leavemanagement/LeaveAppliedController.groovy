package leavemanagement

class LeaveAppliedController {
	
	User user
	LeaveApplied leaveApplied
	ArrayList<leavemanagement.LeaveCalendar> leaveList
    def index() { }
	def leaveAppliedService
	def userService
	def applyLeave(){
		user = session.user
//		println "user: "+user.emailId
		def leave = new LeaveApplied(leaveType:params.leave_type,startDate:params.start_date,endDate:params.end_date,status:'PENDING',user:user)
		def lv = LeaveApplied.get(2)
		
		leavemanagement.LeaveTransactions leaveTransaction = new LeaveTransactions(startDate:params.start_date,endDate:params.end_date,userComment:params.comment,leaveApplied:leave)

//		leave.leaveTransactions = leaveTransaction
		if(params.startDayHalf.equalsIgnoreCase("Yes")){
			leave.getStartDate().setHours(12)
		}
		if(params.endDayHalf.equalsIgnoreCase("Yes")){
			leave.getEndDate().setHours(12)
		}
		
		
		user = session.user
		leaveList = session.leaveList
//		println "user leave count="+user.employee.leaveCount
		float noOfDays = (leave.endDate.getTime() - leave.startDate.getTime())/(1000*60*60*24)
		noOfDays++
		int iteration = noOfDays
		Date day = leave.startDate
		for(int i = 0; i <iteration;i++ ){
			day.setDate((leave.startDate.getDate()+i))
			if(day.getDay()==0 || day.getDay()==6){
				noOfDays--
			}
		}
		
		
		for(LeaveCalendar holiday : leaveList){
			if(leave.startDate.getTime() < holiday.leaveDate.getTime() && leave.endDate.getTime() > holiday.leaveDate.getTime()){
				noOfDays--
			}
		}
		
//		println "noOfDays= "+noOfDays
		user.employee.leaveCount = user.employee.leaveCount-noOfDays
		user = userService.updateLeaveCount(user)
		session.user = user
//		println "user leave count after update="+user.employee.leaveCount
		
		
		leaveAppliedService.applyLeave(leave,leaveTransaction)
		redirect(controller: "LeaveCalendar", action: "getLeaves")
	}
	
	def getLeaveRequests(){
		def leaveList = leaveAppliedService.getLeaveRequests()
		render(view:'leaveList', model: [leaveList: leaveList])
	}
	
	def getLeaveDetail(){
		def leaveObj = leaveAppliedService.getLeaveDetail(params.id)
		println "leave: "+leaveObj
		render(view:'respond', model: [leave:leaveObj])
		redirect(controller: "LeaveApplied", action: "getLeaveDetails",params:[id:params.id])
	}
	
	def getLeaveDetails(){
		def leaveObj = leaveAppliedService.getLeaveDetail(params.id)
		println "leave: "+leaveObj
		render(view:'respond', model: [leave:leaveObj])
	}
	
	def updateLeave(){
		println "parameters: "+params
//		println "LOP: "+params.LOP
		println "status: " + params.status
		LeaveApplied leave = new LeaveApplied();
		
		/*if(params.LOP.equals(leavemanagement.LossOfPay.N)){
			leave.setLOP(leavemanagement.LossOfPay.N)
		}else{
		leave.setLOP(leavemanagement.LossOfPay.Y)
		}*/
		if(params.status.equals(leavemanagement.LeaveStatusType.PENDING.toString())){
			leave.setStatus(leavemanagement.LeaveStatusType.PENDING)
		}
		if(params.status.equals(leavemanagement.LeaveStatusType.ACCEPTED.toString())){
			leave.setStatus(leavemanagement.LeaveStatusType.ACCEPTED)
		}
		if(params.status.equals(leavemanagement.LeaveStatusType.REJECTED.toString())){
			leave.setStatus(leavemanagement.LeaveStatusType.REJECTED)
		}
		def leaveObj = leaveAppliedService.updateLeave(params.id,leave)
		println "leave transaction: "+leaveObj.leaveTransactions
		def transactions = leaveObj.leaveTransactions
		for(LeaveTransactions tran : transactions){
			if(tran.isActive == 1){
				tran.setManagerComment(params.manager_comment)
			}
		}
		
		leaveAppliedService.updateTrasanctions(leaveObj)
		render(view:'respond', model: [leave:leaveObj])
		
	}
	
	def reapply(){
		println "parameters: "+params+ "leaveId: "+params.id
		
		def leaveObj = leaveAppliedService.getLeaveDetail(params.id)
		println "obj.start: "+leaveObj.startDate+" obj.endDate: "+leaveObj.endDate
		println "params.start: "+params.start_date+" params.endDate: "+params.end_date
		if(leaveObj.startDate != params.start_date || leaveObj.endDate != params.end_date){
			println "dates are not equale"
			leaveObj.setStartDate(params.start_date)
			leaveObj.setEndDate(params.end_date)
			leaveObj.setStatus(leavemanagement.LeaveStatusType.PENDING)
			leavemanagement.LeaveTransactions leaveTransaction = new LeaveTransactions(startDate:params.start_date,endDate:params.end_date,userComment:params.comment,leaveApplied:leaveObj)

			def transactions = leaveObj.leaveTransactions
			for(LeaveTransactions tran : transactions){
				tran.setIsActive(0)
			}

//			leaveObj.leaveTransactions = leaveTransaction
			leaveAppliedService.reapplay(params.id,leaveObj,leaveTransaction)
		}else{
			println "start dates are equale"  
		}
		
		redirect(controller: "LeaveTransactions", action: "getLeaveTransactions",params:[id:params.id])
	}
}
