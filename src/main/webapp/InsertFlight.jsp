<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<center>
		<h1>Insert New Flight Details</h1>

		<div
			style="border: 3px solid black; width: 25%; border-radius: 20px; padding: 20px"
			align="center">
			<form action=InsertFlight method=post>
				<label for=name>Name :-</label> <input type="text" name=name id=name /><br>
				<br> <label for=fromf>From :-</label> <input type="text"
					name=fromf id=fromf/><br>
				<br> <label for=tof>To :-</label> <input type="text" name=tof
					id=tof /><br>
				<br> <label for=datef>Departure :-</label> <input
					type="date" name=datef id=datef /><br>
				<br> <label for=timef>Time :-</label> <input type="time"
					name=timef id=timef /><br>
				<br> <label for=price>Price :-</label> <input type="text"
					name=price id=price /><br>
				<br> <input type=submit value=submit /> <input type=reset />
			</form>
		</div>
	</center>
	<%
	String message = (String) session.getAttribute("message");
	if (message != null) {
	%>
	<p style="color: silver;"><%=message %></p>
	<%
		session.setAttribute("message", null);
	}
%>

</body>
</html>