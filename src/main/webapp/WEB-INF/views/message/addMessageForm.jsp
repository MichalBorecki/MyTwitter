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

			<div class="form-group col-md-8">

				<div class="container-box-child">

					<div>
						<h6>Send message to: <b>${message.receiver.fullName }</b></h6>
					</div>

					<form:form action="/MyTwitter/message/add" method="POST" modelAttribute="message">

						<div class="form-row">
							<div class="form-group col-md-12">
								<form:textarea class="form-control is-valid" path="messageText" rows="5" id="messageText" type="text"
									name="messageText" required="true" />
								<form:errors path="messageText" cssClass="valid-feedback" />
								<form:hidden path="receiver"  name="receiver" value="${message.receiver.id }" />
								<form:hidden path="sender" name="sender" value="${message.sender.id }"  />
							</div>
						</div>

						<div class="row justify-content-center">
							<div class="col-sm-12">
								<input class="btn btn-secondary btn-sm btn-block" type="submit" value="Send" />
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