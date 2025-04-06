<%@ page import="datalayer.GPSTracking, datalayer.GPSTrackingDAO, java.util.List, java.sql.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>GPS Tracking Report</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <h2>GPS Tracking Report</h2>

    <%
        List<GPSTracking> gpsLogs = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/public_transit_db", "root", "root"); // update if needed
            GPSTrackingDAO gpsDao = new GPSTrackingDAO(conn);
            gpsLogs = gpsDao.findAll();
        } catch (Exception e) {
            out.println("<p style='color:red;'>Error fetching data: " + e.getMessage() + "</p>");
        }
    %>

    <table border="1">
        <tr>
            <th>Tracking ID</th>
            <th>Vehicle ID</th>
            <th>Location</th>
            <th>Latitude</th>
            <th>Longitude</th>
            <th>Timestamp</th>
        </tr>
        <%
            if (gpsLogs != null) {
                for (GPSTracking log : gpsLogs) {
        %>
        <tr>
            <td><%= log.getTrackingId() %></td>
            <td><%= log.getVehicleId() %></td>
            <td><%= log.getLocation() %></td>
            <td><%= log.getLatitude() %></td>
            <td><%= log.getLongitude() %></td>
            <td><%= log.getTimestamp() %></td>
        </tr>
        <%
                }
            } else {
        %>
        <tr>
            <td colspan="6">No data available.</td>
        </tr>
        <%
            }
        %>
    </table>

    <p><a href="dashboard.jsp">← Back to Dashboard</a></p>
</body>
</html>
