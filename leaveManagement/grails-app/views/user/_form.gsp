<%@ page import="leavemanagement.User" %>



<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'emailId', 'error')} required">
	<label for="emailId">
		<g:message code="user.emailId.label" default="Email Id" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="email" name="emailId" required="" value="${userInstance?.emailId}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'password', 'error')} required">
	<label for="password">
		<g:message code="user.password.label" default="Password" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="password" required="" value="${userInstance?.password}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'token', 'error')} ">
	<label for="token">
		<g:message code="user.token.label" default="Token" />
		
	</label>
	<g:textField name="token" value="${userInstance?.token}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'validTill', 'error')} ">
	<label for="validTill">
		<g:message code="user.validTill.label" default="Valid Till" />
		
	</label>
	<g:datePicker name="validTill" precision="day"  value="${userInstance?.validTill}" default="none" noSelection="['': '']" />
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'status', 'error')} required">
	<label for="status">
		<g:message code="user.status.label" default="Status" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="status" from="${leavemanagement.UserStatusType?.values()}" keys="${leavemanagement.UserStatusType.values()*.name()}" required="" value="${userInstance?.status?.name()}"/>
</div>

