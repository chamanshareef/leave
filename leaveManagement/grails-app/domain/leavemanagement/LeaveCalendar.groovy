package leavemanagement

import java.util.Date;
enum HolidayType{
	NATIONAL,OPTIONAL
}
class LeaveCalendar {

    String holidayName
	HolidayType holidayType
	Date leaveDate
	
	static constraints = {
		holidayName(blank:false,nullable:false)
		leaveDate(blank:false,nullable:false)
	}
}