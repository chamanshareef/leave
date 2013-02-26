package leavemanagement

import org.apache.jasper.compiler.Node.ParamsAction;

class LeaveCalendarController {
	def user
    def index() { }
	
	def getLeaves() {
		
		user = session.user
		def leaveList = LeaveCalendar.getAll()
		def leaveApplied = LeaveApplied.findAllWhere(user:user); 
//		println "leaveList "+leaveList+" leavesApplied: "+leaveApplied
		session.leaveList = leaveList
		render(view:'leaveStatus', model: [leaveList: leaveList,leaveApplied:leaveApplied])
	}
	def showHolidays(){
		def leaveList = LeaveCalendar.getAll()
		render(view:'holidays',model:[leaveList: leaveList])
	}
	
	def addHoliday(){
		
		println "parameters: "+params
		LeaveCalendar holiday = new LeaveCalendar()
		holiday.holidayName = params.holiday_name
		holiday.leaveDate = params.leave_date
		holiday.holidayType = params.holiday_type
		if(holiday.save(flush:true)){
			println "holiday added"
		}else{
			println "add holiday failed"
		}
		def leaveList = LeaveCalendar.getAll()
		render(view:'holidays',model:[leaveList: leaveList])
	}
}
