package leavemanagement

class LeaveTransactionsController {

	leavemanagement.LeaveAppliedService leaveAppliedService   
    def index() { }
	
	def getLeaveTransactions(){
		def leaveObj = leaveAppliedService.getLeaveDetail(params.id)
		println "leave transaction: "+leaveObj.leaveTransactions
		render(view:'respond', model: [leave:leaveObj])
	}
}
