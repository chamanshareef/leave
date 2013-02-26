<%@page import="leavemanagement.LeaveApplied"%>
<%@page import="leavemanagement.LeaveStatusType"%>
<%@page import="leavemanagement.LossOfPay"%>
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
Login as : ${session.user.employee.name} | <g:link controller="User" action="logout">Logout</g:link>
</div>
		<g:link controller="LeaveCalendar" action="getLeaves">My Account</g:link>
</g:if>
<br /><br />

<div  style="font-weight: bold;">Leaves Requests</div>
<table>
<tbody>
<tr>
<th>Name</th>
<th>Balance</th>
<th>Leave Type</th>
<th>Start Date</th>
<th>End Date</th>
<%--<th>Reason</th>--%>
<th>Status</th>
</tr>
<tr>
<td>${leave.user.employee.name}</td>
<td>${leave.user.employee.leaveCount}</td>
<td>${leave.leaveType}</td>
<td><g:formatDate format="yyyy-MM-dd" date="${leave.startDate}" /></td>
<td><g:formatDate format="yyyy-MM-dd" date="${leave.endDate}" /></td>
<%--<td>${leave.comment}</td>--%>
<td>${leave.status}</td>
</tr>
</tbody>
</table>
<br />
<table>
<tbody>
<tr>
<th>Reason</th>
<th>Start Date</th>
<th>End Date</th>
<th>Manager Comment</th>
</tr>
<g:each var="it" in="${leave.leaveTransactions}">
<tr>
<td>${it.userComment}</td>
<td><g:formatDate format="yyyy-MM-dd" date="${it.startDate}" /></td>
<td><g:formatDate format="yyyy-MM-dd" date="${it.endDate}" /></td>
<td>${it.managerComment}</td>
</tr>
</g:each>
</tbody>
</table>
<br /><br />
<g:form controller="leaveApplied" action="updateLeave" style="position:relative;float:left;width:550px">
<span>Manager Comment</span>
<%--<textarea rows="20" cols="90" name="manager_comment" placeholder="Please Enter Comment to Employee">${leave.manager_comment}</textarea>--%>
<textarea rows="20" cols="90" name="manager_comment" placeholder="Please Enter Comment to Employee"></textarea>
<br /><br />
<span>Status: </span>
<select name="status">
<g:if test="${leave.status.equals(LeaveStatusType.PENDING ) }">
<option value="PENDING" selected="selected">PENDING</option>
<option value="ACCEPTED">ACCEPTED</option>
<option value="REJECTED">REJECTED</option>
</g:if>
<g:else>
<g:if test="${leave.status.equals(LeaveStatusType.ACCEPTED ) }">
	<option value="PENDING">PENDING</option>
	<option value="ACCEPTED" selected="selected">ACCEPTED</option>
	<option value="REJECTED">REJECTED</option>
</g:if>
<g:else>
	<g:if test="${leave.status.equals(LeaveStatusType.REJECTED ) }">
	<option value="PENDING">PENDING</option>
	<option value="ACCEPTED">ACCEPTED</option>
	<option value="REJECTED" selected="selected">REJECTED</option>
	</g:if>
</g:else>
</g:else>
</select>
<!-- <span>Loss Of Pay</span>
<select name="LOP">
<g:if test="leave.LOP.equals(LossOfPay.N) }">
<option value="N" selected="selected">NO</option>
<option value="Y">YES</option>
</g:if>
<g:else>
<option value="N">NO</option>
<option value="Y" selected="selected">YES</option>
</g:else>
</select>
 -->
<input type="hidden" value="${leave.id}" name="id"/>
<label style="width: 100px;">&nbsp;</label><input type="submit" value="Update"><br /><br />
</g:form>
</body>
</html>