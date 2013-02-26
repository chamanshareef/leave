package leavemanagement

class UserService {

	User user
    ArrayList<LeaveCalendar> leaveList 
	def getUser(String emailId, String password) {
		
		user = User.findByEmailIdAndPassword(emailId, password);
		return user
    }
	
	def addUser(User newUser) {
		
		println "user is " + newUser
		user = newUser.save(flush:true,failOnError:true)
		return user
	}
	
	def updateLeaveCount(User u){
		def user = User.get(u.id)
		user.employee.leaveCount = u.employee.leaveCount
		if(user.save()){
			println "Updated successfully"
			return user	
		}else{
			println "Update failed"
			return user
		}
	}
}
