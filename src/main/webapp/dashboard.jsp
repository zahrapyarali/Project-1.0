<%@ page import="datalayer.User" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Dashboard</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <div class="container">
        <%
            User user = (User) session.getAttribute("user");
            if (user == null) {
                response.sendRedirect("login.html");
                return;
            }
        %>
        <h1>Welcome, <%= user.getName()%>!</h1>
        <p>Your role: <%= user.getRole()%></p>

        <div class="link">
            <a href="vehicles.jsp">Manage Vehicles</a> |
            <a href="report.jsp">View Reports</a> |
            <a href="logGps.jsp">Log GPS Location</a> |
            <a href="gpsReport.jsp">View GPS Logs</a> |
            <form action="logout" method="get" style="display:inline;">
                <button type="submit">Logout</button>
            </form>
        </div>

        <!--Break Logging UI for Operators -->
        <%
            if ("Operator".equals(user.getRole())) {
        %>
        <hr>
        <h2>Log Break / Out-of-Service</h2>
        <form action="breakLog" method="post">
            <label for="vehicleId">Vehicle ID:</label>
            <input type="number" name="vehicleId" id="vehicleId" required />
            <br><br>
            <button type="submit" name="mode" value="start">Start Break</button>
            <button type="submit" name="mode" value="end">End Break</button>
        </form>
        <%
            }
        %>
    </div>
</body>
</html>
