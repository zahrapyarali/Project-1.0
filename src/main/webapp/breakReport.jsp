<%@ page import="java.util.List" %>
<%@ page import="datalayer.BreakLog" %>
<%@ page import="datalayer.BreakLogDAO" %>
<%@ page import="datalayer.DataSource" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Break & Out-of-Service Report</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f7faff;
            padding: 20px;
        }
        h2 {
            color: #2c3e50;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th, td {
            padding: 12px;
            border: 1px solid #ddd;
            text-align: center;
        }
        th {
            background-color: #3498db;
            color: white;
        }
        tr:nth-child(even) {
            background-color: #f2f2f2;
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

<h2>Break & Out-of-Service Report</h2>

<%
    try {
        DataSource ds = DataSource.getInstance();
        BreakLogDAO dao = new BreakLogDAO(ds.createConnection());
        List<BreakLog> logs = dao.findAll();

        if (logs.isEmpty()) {
%>
            <p>No break logs found.</p>
<%
        } else {
%>
            <table>
                <tr>
                    <th>Log ID</th>
                    <th>Operator ID</th>
                    <th>Vehicle ID</th>
                    <th>Break Start</th>
                    <th>Break End</th>
                </tr>
<%
            for (BreakLog log : logs) {
%>
                <tr>
                    <td><%= log.getLogId() %></td>
                    <td><%= log.getOperatorId() %></td>
                    <td><%= log.getVehicleId() %></td>
                    <td><%= log.getBreakStart() != null ? log.getBreakStart() : "—" %></td>
                    <td><%= log.getBreakEnd() != null ? log.getBreakEnd() : "—" %></td>
                </tr>
<%
            }
%>
            </table>
<%
        }
    } catch (Exception e) {
        out.println("<p style='color:red;'>Error: " + e.getMessage() + "</p>");
    }
%>

<br>
<a class="back-link" href="dashboard.jsp">&larr; Back to Dashboard</a>

</body>
</html>
