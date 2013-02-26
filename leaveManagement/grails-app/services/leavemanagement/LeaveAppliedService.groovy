package leavemanagement

import com.sun.org.apache.bcel.internal.generic.RETURN;

class LeaveAppliedService {

	
    def serviceMethod() {

    }
	
	def applyLeave(LeaveApplied leave,leavemanagement.LeaveTransactions leaveTransaction){
		
		println "leaveTransaction: "+leaveTransaction
		

//		def lv = LeaveApplied.get(2)
	
//		println "leave: "+user.leaveApplied
		if(leave.save(flush:true)) {
			println "leave applied: "
			def lv = LeaveApplied.find(leave)
			println "lv: "+lv
			leaveTransaction.leaveApplied=lv
			if(leaveTransaction.save(flush:true)){
				println "leave transation saved"
			}else{
				println "save leave transation failed"
			}
			
		} else {
			println "leave apply failed"
			leave.errors.each {
				println it
		   }
		}
	}
	
	def getLeaveRequests(){
		
		def leaveList = LeaveApplied.findAllByIsActive(1)
		return leaveList
	}
	
	def getLeaveDetail(Id){
//		println "ID: "+Id
		def leave = LeaveApplied.get(Id)
		println "leave: "+leave
		return leave	
	}
	
	def updateLeave(Id,LeaveApplied leave){
		def leaveObj = LeaveApplied.get(Id)
//		leaveObj.manager_comment = leave.manager_comment
		leaveObj.status = leave.status
//		leaveObj.LOP = leave.LOP
		println "leaveObj: "+leaveObj
		if(leaveObj.save()){
			println "Updated successfully"
			return leaveObj	
//			return LeaveApplied.get(Id)
		}else{
			println "Update failed"
			return leave
		}
	}
	
	def reapplay(Id,LeaveApplied leaveObj,LeaveTransactions leaveTransaction){
//		def leaveObj = LeaveApplied.get(Id)
		
		if(leaveTransaction.save(flush:true)){
			println "traction added"
		}else{
			println "traction add failed"
		}
		if(leaveObj.save()){
			println "Updated successfully"
			return leaveObj
//			return LeaveApplied.get(Id)
		}else{
			println "Update failed"
			return leave
		}
	}
	
	def updateTrasanctions(LeaveApplied leaveObj){
		if(leaveObj.save()){
			println "Updated successfully"
			return leaveObj
//			return LeaveApplied.get(Id)
		}else{
			println "Update failed"
			return leave
		}
	}
}