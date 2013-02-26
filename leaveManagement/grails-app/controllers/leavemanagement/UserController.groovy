package leavemanagement

import org.apache.commons.lang.RandomStringUtils;
import org.codehaus.groovy.util.StringUtil;

class UserController {
	
	def userService
    def index() { }
	
	def login(){
		
		User user = userService.getUser(params.emailId, params.password) 
		if( user!= null ){
			println "Login success"
			flash.message="Login success"
			session.user=user
			redirect(controller: "LeaveCalendar", action: "getLeaves")
		}else{
			flash.message="Login Failed"
			redirect(action:"index")
		}
		
	}
	
	def logout(){
		session.user=null
		redirect(action:"index")
	}
	
	def signUp(){
		
		println "params: "+params.dateOfBirth
		String UUID = RandomStringUtils.randomAlphanumeric(8)
		
		Employee employee = new Employee()
		employee.name = params.name
		employee.dateOfBirth = params.dateOfBirth
		employee.empId = UUID
		employee.gender = params.gender
		User user = new User(emailId:params.emailId,password:params.password,token:'adghg',validTill:new Date(),status:'ACTIVE',type:'L1')
		println "user.emailId: "+user.emailId
		user.employee = employee
		def newuser = userService.addUser(user)
		//redirect(action:"index")
		if( newuser != null ){
			println "Signup success"
			flash.message="Signup success"
			session.user=newuser
			redirect(controller: "LeaveCalendar", action: "getLeaves")
		}else{
			flash.message="Login Failed"
			redirect(action:"index")
		}
	}
	
}
