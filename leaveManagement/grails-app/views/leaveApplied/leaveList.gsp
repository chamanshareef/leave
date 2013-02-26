<%@page import="leavemanagement.LeaveApplied"%>
<%@page import="leavemanagement.LeaveStatusType"%>
<html>
<head>
<link type="text/css" href="../script/leaveList.js" />
<link href="/leavemanagement/static/css/leaveList.css" rel="stylesheet" type="text/css" />
<g:createLinkTo dir="css" file="leaveList.css" />
<meta name="layout" content="main" />
<g:javascript library="jquery" plugin="jquery"/>
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
<th>Edit</th>
</tr>
<g:each var="it" in="${leaveList}">
<br />
<tr onmouseover="ChangeColor(this, true);" onmouseout="ChangeColor(this, false);" onclick="<g:remoteFunction controller="LeaveApplied" action='getLeaveDetail' id='${it.id}'/>"> 
<td>${it.user.employee.name}</td>
<td>${it.user.employee.leaveCount}</td>
<td>${it.leaveType}</td>
<td><g:formatDate format="yyyy-MM-dd" date="${it.startDate}" /></td>
<td><g:formatDate format="yyyy-MM-dd" date="${it.endDate}" /></td>
<%--<td>${it.comment}</td>--%>
<td>${it.status}</td>
<td>
<g:link controller="LeaveApplied" action="getLeaveDetail" id='${it.id}'>
Edit
</g:link>
</td>
<tr/>

</g:each>
</tbody>
</table>
<br />

</body>
</html>