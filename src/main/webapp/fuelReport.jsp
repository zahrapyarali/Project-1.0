<%@ page import="java.util.List" %>
<%@ page import="businesslayer.FuelConsumptionReportService" %>
<%@ page import="businesslayer.FuelReportEntry" %>
<%@ page import="datalayer.DataSource" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Fuel & Energy Usage Report</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f6f9fc;
            padding: 20px;
        }
        h2 {
            color: #2c3e50;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 16px;
        }
        th, td {
            padding: 12px;
            border: 1px solid #ccc;
            text-align: center;
        }
        th {
            background-color: #3498db;
            color: white;
        }
        tr:nth-child(even) {
            background-color: #eef2f5;
        }
        .error {
            color: red;
            margin-top: 10px;
        }
        .back-link {
            margin-top: 20px;
            display: inline-block;
            color: #3498db;
            text-decoration: none;
        }
    </style>
</head>
<body>

    <h2>Fuel & Energy Usage Report</h2>

<%
    try {
        DataSource ds = DataSource.getInstance();
        FuelConsumptionReportService service = new FuelConsumptionReportService(ds.createConnection());
        List<FuelReportEntry> report = service.generateReport();

        if (report.isEmpty()) {
%>
        <p>No data available for the report.</p>
<%
        } else {
%>
        <table>
            <tr>
                <th>Vehicle Number</th>
                <th>Vehicle Type</th>
                <th>Total Distance (km)</th>
                <th>Fuel/Energy Used</th>
            </tr>
<%
            for (FuelReportEntry entry : report) {
%>
            <tr>
                <td><%= entry.getVehicleNumber() %></td>
                <td><%= entry.getVehicleType() %></td>
                <td><%= java.lang.String.format("%.2f", entry.getDistanceTravelled()) %></td>
                <td><%= java.lang.String.format("%.2f", entry.getFuelOrEnergyUsed()) %></td>

            </tr>
<%
            }
%>
        </table>
<%
        }
    } catch (Exception e) {
%>
        <p class="error">Error generating report: <%= e.getMessage() %></p>
<%
        e.printStackTrace();
    }
%>

    <br>
    <a class="back-link" href="dashboard.jsp">&larr; Back to Dashboard</a>

</body>
</html>
