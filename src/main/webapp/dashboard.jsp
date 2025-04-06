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
                            <%-- Only Managers can view Fuel Report --%>
            <% if ("Manager".equals(user.getRole())) { %>
                <a href="fuelReport.jsp">Fuel & Energy Report</a> |
            <% } %>
                <a href="logGps.jsp">Log GPS Location</a> |
                <a href="gpsReport.jsp">View GPS Logs</a> |
                <form action="logout" method="get" style="display:inline;">
                    <button type="submit">Logout</button>
                </form>
            </div>
        </div>
    </body>
</html>
