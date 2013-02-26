<%@page import="leavemanagement.LeaveApplied"%>
<%@page import="leavemanagement.UserType" %>
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
<br />
<g:link controller="LeaveCalendar" action="getLeaves">My Account</g:link>
<br />
<g:if test="${session.user.type.equals(UserType.L2) }">
		<g:link controller="leaveApplied" action="getLeaveRequests"> Leave Requests</g:link>
</g:if>
</g:if>
<br /><br />
<div style="width: 220px;">
<label style="font-weight: bold;">leave calendar</label>
</div>
<table style="width: 60%;text-align: center;">
<tbody>
<tr>
<th>Holiday Name</th>
<th>Holiday Type</th>
<th>Date</th>
</tr>
<g:each var="it" in="${leaveList}">
<tr>
<td>${it.holidayName}</td>
<td>${it.holidayType }</td>
<td>${it.leaveDate}</td>
</tr>
</g:each>
</tbody>
</table>
<br />

<div>Add Holidays</div>

<g:form controller="leaveCalendar" action="addHoliday" style="padding-left:100px;">
<div style="width: 100%;">
<label>Holiday name: </label><input type="text" name="holiday_name">
<label>Date:</label><g:datePicker name="leave_date" value="${new Date()}" precision="day" years="${2012..2100}"/><br /><br />
<label>Holiday Type:</label><select name="holiday_type">
<option>NATIONAL</option>
<option>OPTIONAL</option>
</select>
<label>&nbsp;</label><input type="submit" value="Add"><br />
</div>
</g:form>

</body>
</html>