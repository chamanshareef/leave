<%@page import="leavemanagement.LeaveApplied"%>
<%@page import="leavemanagement.UserType" %>
<%@page import="leavemanagement.HolidayType"%>
<html>
<head>
<meta name="layout" content="main" />
<title>leave calendar</title>
</head>
<body>
<div class="message">
Welcome
</div>
<g:if test="${session.user}">
<br />
<div style="float: right;padding-right: 15px;">
Login as : ${session.user.employee.name} | <g:link controller="User" action="logout">Logout </g:link>
</div>
<br /><br />
<div class="mainMenu">
<g:if test="${session.user.type.equals(UserType.L2) }">
		<g:link class="leaveRequests" controller="leaveApplied" action="getLeaveRequests"> Leave Requests</g:link>
		<g:link class="holidays" controller="LeaveCalendar" action="showHolidays">Add Holidays</g:link>
</g:if>
</div>
</g:if>
<br /><br />
<div style="width: 220px;">
<label style="font-weight: bold;">Holiday calendar</label>
</div>
<table style="width: 60%;text-align: center;">
<tbody>
<tr>
<th>Holiday Name</th>
<th>Holiday Type</th>
<th>Date</th>
</tr>
<g:each var="it" in="${leaveList}">
<g:if test="${it.holidayType.equals(HolidayType.NATIONAL ) }">
<tr>
<td>${it.holidayName}</td>
<td>${it.holidayType }</td>
<td><g:formatDate format="yyyy-MM-dd" date="${it.leaveDate}"/></td>
</tr> 
</g:if>
<%--<br />--%>
</g:each>
<tr>
<td>OPTIONAL</td>
</tr>
<g:each var="itt" in="${leaveList}">
<g:if test="${itt.holidayType.equals(HolidayType.OPTIONAL) }">
<tr>
<td>${itt.holidayName}</td>
<td>${itt.holidayType }</td>
<td><g:formatDate format="yyyy-MM-dd" date="${itt.leaveDate}"/></td>
</tr>
</g:if>
</g:each>
</tbody>
</table>

<div style="font-weight: bold;">Leaves In hand: ${session.user.employee.leaveCount}</div>
<br />
<div  style="font-weight: bold;">Leaves Applied</div>
<table>
<tbody>
<tr>
<th>Leave Type</th>
<th>Start Date</th>
<th>End Date</th>
<th>Days</th>
<%--<th>Reason</th>--%>
<th>Status</th>
<th>Edit</th>
</tr>
<g:each var="it" in="${leaveApplied}">
<tr>
<td>${it.leaveType}</td>
<td><g:formatDate format="yyyy-MM-dd" date="${it.startDate}" /></td>
<td><g:formatDate format="yyyy-MM-dd" date="${it.endDate}" /></td>
<td>${(it.endDate.getTime() - it.startDate.getTime() ) / (1000 * 60 * 60 * 24)+1}</td>
<%--<td>${it.comment}</td>--%>
<td>${it.status}</td>
<td>
<g:if test="${it.status.equals(leavemanagement.LeaveStatusType.USED) }">
Used
</g:if>
<g:else>
<g:link controller="LeaveTransactions" action="getLeaveTransactions" id='${it.id}'>
Edit
</g:link>
</g:else>
</td>
</tr>
</g:each>
</tbody>
</table>
<br />

<div>Apply a leave</div>

<g:form controller="leaveApplied" action="applyLeave" style="padding-left:100px;">
<div style="width: 100%;">
<label>Start Date:</label><g:datePicker name="start_date" value="${new Date()}" precision="day" years="${2012..2100}"/>
<label> Half Day: </label>
<select name="startDayHalf">
<option>No</option>
<option>Yes</option>
</select>
<br /><br />
<label>End Date:</label><g:datePicker name="end_date" value="${new Date()}" precision="day" years="${2012..2100}"/>
<label> Half Day: </label>
<select name="endDayHalf">
<option>No</option>
<option>Yes</option>
</select>
<br /><br />
<label>Leave Type:</label><select name="leave_type">
<option>Earned</option>
<option>Optional</option>
<option>Comp off</option>
</select>
<label>Reason:</label><input type="text" name="comment"><br /><br />
<label>&nbsp;</label><input type="submit" value="Apply"><br />
</div>
</g:form>

</body>
</html>