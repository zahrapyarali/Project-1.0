<%
    if (session == null || !"Manager".equals(session.getAttribute("role"))) {
        response.sendRedirect("unauthorized.jsp");
        return;
    }
%>

<%@ page import="datalayer.GPSTracking, datalayer.GPSTrackingDAO, java.util.List, java.sql.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Transit Station Logs</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <h2>Transit Station Arrival & Departure Report</h2>

    <%
        List<GPSTracking> gpsLogs = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/public_transit_db", "cst8288", "cst8288");
            GPSTrackingDAO gpsDao = new GPSTrackingDAO(conn);
            gpsLogs = gpsDao.findAll(); // no latitude/longitude, just location, status, timestamp
        } catch (Exception e) {
            out.println("<p style='color:red;'>Error fetching data: " + e.getMessage() + "</p>");
        }
    %>

    <table border="1">
        <tr>
            <th>Vehicle ID</th>
            <th>Station</th>
            <th>Status</th>
            <th>Timestamp</th>
        </tr>
        <%
            if (gpsLogs != null && !gpsLogs.isEmpty()) {
                for (GPSTracking log : gpsLogs) {
        %>
        <tr>
            <td><%= log.getVehicleId() %></td>
            <td><%= log.getLocation() %></td>
            <td><%= log.getStatus() %></td>
            <td><%= log.getTimestamp() %></td>
        </tr>
        <%
                }
            } else {
        %>
        <tr>
            <td colspan="4">No data available.</td>
        </tr>
        <%
            }
        %>
    </table>

    <p><a href="dashboard.jsp">← Back to Dashboard</a></p>
</body>
</html>
