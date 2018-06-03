<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@	taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/views/jspf/header.jspf"%>
</head>
<body>
	<%@ include file="/WEB-INF/views/jspf/navbar.jspf"%>

	<div class="container">

		<div class="form-row justify-content-center">

			<div class="form-group col-md-6">

				<div class="container-box-child">
					<form:form action="/MyTwitter/user/update" method="post" modelAttribute="user">

						<div class="form-row">
							<div class="form-group col-md-12">
								<label for="email">Username</label>
								<form:input path="userName" id="userName" type="text" class="form-control is-valid" name="userName" readonly="true"/>
								<form:errors path="userName" cssClass="valid-feedback"/>
								<form:input path="id" hidden="true" value="${user.id }"/>
							</div>
						</div>

						<div class="form-row">
							<div class="form-group col-md-12">
								<label for="email">First name</label>
								<form:input path="firstName" id="firstName" type="text" class="form-control is-valid" name="firstName" required="true"/>
								<form:errors path="firstName" cssClass="valid-feedback" />
							</div>
						</div>
						
						<div class="form-row">
							<div class="form-group col-md-12">
								<label for="email">Last name</label>
								<form:input path="lastName" id="lastName" type="text" class="form-control is-valid" name="lastName" required="true" />
								<form:errors path="lastName" cssClass="valid-feedback" />
							</div>
						</div>

						<div class="form-row">
							<div class="form-group col-md-12">
								<label for="email">Email</label>
								<form:input path="email" id="emailReg" type="email" class="form-control is-valid" name="email" required="true"/>
								<form:errors path="email" cssClass="valid-feedback"/>
							</div>
						</div>

						<div class="form-row">
							<div class="form-group col-md-12">
								<label for="email">Password</label>
								<form:password path="password" id="passwordReg" class="form-control is-valid" name="password"
									placeholder="Password must be 8-20 characters long." required="true"/>
								<form:errors path="password" cssClass="valid-feedback"/>
							</div>
						</div>

						<div class="row justify-content-center">
							<div class="col-sm-12">
								<input class="btn btn-secondary btn-block" type="submit" value="Save changes" />
							</div>
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="/WEB-INF/views/jspf/footer.jspf"%>
</body>
</html>