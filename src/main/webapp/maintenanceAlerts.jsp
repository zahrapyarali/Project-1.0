<%@ page import="businesslayer.*" %>
<%@ page import="datalayer.*" %>
<%@ page import="java.util.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Predictive Maintenance Alerts</title>
        <link rel="stylesheet" href="style.css">
<!--        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f9f9f9;
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
                padding: 10px;
                border: 1px solid #ccc;
                text-align: center;
            }
            .alert {
                color: red;
            }
            .scheduled {
                color: green;
                font-weight: bold;
            }
            .btn {
                padding: 6px 12px;
                font-size: 14px;
            }
            .back-link {
                margin-top: 20px;
                display: inline-block;
                color: inherit;
                text-decoration: none;
            }
        </style>-->
    </head>
    <body>

        <h2>Predictive Maintenance Alerts</h2>

        <%
            try {
                DataSource ds = DataSource.getInstance();
                MaintenanceLogDAO dao = new MaintenanceLogDAO(ds.createConnection());
                List<MaintenanceLog> logs = dao.findOverdueAlerts();
                 User user = (User) session.getAttribute("user"); 

                if (request.getMethod().equalsIgnoreCase("POST")) {
                    String logIdStr = request.getParameter("logId");
                    if (logIdStr != null) {
                        int logId = Integer.parseInt(logIdStr);
                        dao.scheduleMaintenance(logId);
                        response.sendRedirect("maintenanceAlerts.jsp"); // Refresh to show updated status
                        return;
                    }
                }

                if (logs.isEmpty()) {
        %>
        <p>No alerts to display. All vehicles are within safe wear limits.</p>
        <%
        } else {
        %>
        <table>
            <tr>
                <th>Vehicle ID</th>
                <th>Component</th>
                <th>Hours Used</th>
                <th>Threshold</th>
                <th>Status</th>
                <% if ("Manager".equals(user.getRole())) { %>
                <th>Action</th>
                <% } %>
            </tr>
            <%
                for (MaintenanceLog log : logs) {
            %>
            <tr>
                <td><%= log.getVehicleId()%></td>
                <td><%= log.getComponent()%></td>
                <td><%= log.getHoursUsed()%></td>
                <td><%= log.getThreshold()%></td>
                <td class="alert">Maintenance Required</td>
                <% if ("Manager".equals(user.getRole())) { %>
                <td>
                    <%
                    if (log.isScheduled()) {
                        %>
                        <span class="scheduled">Scheduled</span>
                        <%
                    } else {
                        %>
                        <form method="post" style="display:inline;">
                            <input type="hidden" name="logId" value="<%= log.getLogId()%>" />
                            <button type="submit" class="btn">Schedule</button>
                        </form>
                <% } %>
                </td>
                <% } %>
            </tr>
            <%
                }
            %>
        </table>
        <%
            }
        } catch (Exception e) {
        %>
        <p style="color: red;">Error loading maintenance alerts: <%= e.getMessage()%></p>
        <%
                e.printStackTrace();
            }
        %>

        <br>
        <a class="back-link" href="dashboard.jsp">&larr; Back to Dashboard</a>

    </body>
</html>
