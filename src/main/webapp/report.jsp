<%@page import="businesslayer.VehicleFactory"%>
<%@page import="businesslayer.FuelConsumptionStrategy"%>
<%@page import="businesslayer.MaintenanceLog"%>
<%@page import="businesslayer.strategies.*" %>
<%@page import="datalayer.*" %>
<%@page import="java.util.*" %>
<%@page import="java.text.DecimalFormat" %>
<%@page import="java.sql.Connection" %>
<%@page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Transit Reports</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<div class="container">
    <h1>Transit Reports</h1>

    <!-- Maintenance Dashboard -->
    <h2>Maintenance Dashboard</h2>
    <table class="report-table">
        <tr>
            <th>Vehicle ID</th>
            <th>Component</th>
            <th>Hours Used</th>
            <th>Threshold</th>
            <th>Status</th>
        </tr>
        <%
            Connection conn = DataSource.getInstance().createConnection();
            MaintenanceLogDAO maintenanceDAO = new MaintenanceLogDAO(conn);
            List<MaintenanceLog> maintenanceLogs = maintenanceDAO.findAll();
            for (MaintenanceLog log : maintenanceLogs) {
                boolean exceeded = log.getHoursUsed() >= log.getThreshold();
        %>
        <tr>
            <td><%= log.getVehicleId() %></td>
            <td><%= log.getComponent() %></td>
            <td><%= log.getHoursUsed() %></td>
            <td><%= log.getThreshold() %></td>
            <td style="color: <%= exceeded ? "red" : "green" %>;">
                <%= exceeded ? "Needs Maintenance" : "OK" %>
            </td>
        </tr>
        <% } %>
    </table>

    <!-- Operator Performance Dashboard -->
    <h2>Operator Performance Dashboard</h2>
    <table class="report-table">
        <tr>
            <th>Operator ID</th>
            <th>Breaks Logged</th>
            <th>Out-of-Service Logs</th>
            <th>Total Logged Events</th>
        </tr>
        <%
            BreakLogDAO breakDAO = new BreakLogDAO(conn);
            Map<Integer, Integer> breakCounts = breakDAO.getStatusCount("Check-In");
            Map<Integer, Integer> oosCounts = breakDAO.getStatusCount("Out-of-Service");

            Set<Integer> allOperators = new HashSet<>();
            allOperators.addAll(breakCounts.keySet());
            allOperators.addAll(oosCounts.keySet());

            for (Integer operatorId : allOperators) {
                int breaks = breakCounts.getOrDefault(operatorId, 0);
                int outOfService = oosCounts.getOrDefault(operatorId, 0);
        %>
        <tr>
            <td><%= operatorId %></td>
            <td><%= breaks %></td>
            <td><%= outOfService %></td>
            <td><%= breaks + outOfService %></td>
        </tr>
        <% } %>
    </table>

    <!-- Fuel & Maintenance Cost Report -->
    <h2>Fuel & Maintenance Cost Report</h2>
    <table class="report-table">
        <tr>
            <th>Vehicle ID</th>
            <th>Fuel Type</th>
            <th>Distance Traveled (km)</th>
            <th>Fuel Used (Litres/kWh)</th>
        </tr>
        <%
            VehicleDAO vehicleDAO = new VehicleDAO(conn);
            GPSTrackingDAO gpsDAO = new GPSTrackingDAO(conn);
            List<Vehicle> vehicles = vehicleDAO.findAll();
            DecimalFormat df = new DecimalFormat("0.00");

            for (Vehicle v : vehicles) {
                double distance = gpsDAO.calculateTotalDistance(v.getId());

                FuelConsumptionStrategy strategy = null;
                try {
                    strategy = VehicleFactory.getStrategy(v.getType());
                } catch (Exception e) {
                    strategy = null;
                }

                double used = (strategy != null) ? strategy.calculateConsumption(distance) : 0;
        %>
        <tr>
            <td><%= v.getId() %></td>
            <td><%= v.getFuelType() %></td>
            <td><%= df.format(distance) %></td>
            <td><%= df.format(used) %></td>
        </tr>
        <% } %>
    </table>

    <br>
    <a href="dashboard.jsp">&larr; Back to Dashboard</a>
</div>
</body>
</html>
