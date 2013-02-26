<html>
<head>
<!-- <g:javascript src="user.js"/> -->

<meta name="layout" content="main" />
<title>Login Page</title>
</head>
<body>
<g:if test="${flash.message}">
<div class="message">
${flash.message}
</div>
</g:if>
<g:if test="${session.user}">
<br />
Login as : ${session.user.emailId} | <g:link action="logout">Logout</g:link>
</g:if>
<g:else>
<g:form controller="user" action="login" style="padding-left:100px;width:250px;position:relative;float:left;">
<br />
<div class="signIn" style="width: 220px;">
<label>Email ID</label><input type="text" name="emailId">
<label>Password</label><input type="password" name="password"><br /><br />
<label>&nbsp;</label><input type="submit" value="login">
</div>
</g:form>
</g:else>

<g:form controller="user" action="signUp" style="position:relative;float:left;width:550px">
<br />
<div class="signUp">
<label style="width: 100px;">Name</label><input type="text" name="name"><br /><br />
<label style="width: 100px;">Date Of Birth</label> <g:datePicker name="dateOfBirth" value="${new Date()}" precision="day" years="${1900..2013}"/><br /><br />
<label style="width: 100px;">Gender</label><select name="gender">
					<option value="MALE">MALE</option>
					<option value="FEMALE">FEMALE</option>
					</select><br /><br />
<label style="width: 100px;">Email ID</label><input type="text" name="emailId"><br /><br />
<label style="width: 100px;">Password</label><input type="password" name="password"><br /><br />
<label style="width: 100px;">&nbsp;</label><input type="submit" value="Sign Up"><br /><br />
</div>
</g:form>
</body>
</html>