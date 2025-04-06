<%@ page import="java.sql.*, java.time.*, datalayer.BreakLogDAO, datalayer.BreakLog" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Log Break</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f6f9fc;
            padding: 30px;
        }
        h2 {
            color: #2c3e50;
        }
        form {
            background-color: #fff;
            padding: 20px;
            width: 400px;
            box-shadow: 0 0 10px #ccc;
        }
        label {
            display: block;
            margin: 12px 0 6px;
        }
        input[type="text"], input[type="datetime-local"] {
            width: 100%;
            padding: 8px;
            margin-bottom: 10px;
        }
        button {
            background-color: #3498db;
            color: white;
            border: none;
            padding: 10px 20px;
            cursor: pointer;
        }
        .message {
            margin-top: 20px;
            color: green;
        }
        .error {
            color: red;
        }
        .back-link {
            display: inline-block;
            margin-top: 20px;
            color: #3498db;
            text-decoration: none;
        }
    </style>
</head>
<body>

<%
    
    if (session == null || session.getAttribute("role") == null || !session.getAttribute("role").equals("Operator")) {
        response.sendRedirect("unauthorized.jsp");
        return;
    }

    int operatorId = (int) session.getAttribute("userId");

    String message = "";

    if ("POST".equalsIgnoreCase(request.getMethod())) {
        try {
            int vehicleId = Integer.parseInt(request.getParameter("vehicleId"));
            LocalDateTime breakStart = LocalDateTime.parse(request.getParameter("breakStart"));
            LocalDateTime breakEnd = LocalDateTime.parse(request.getParameter("breakEnd"));

            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/public_transit_db", "cst8288", "cst8288");

            BreakLogDAO dao = new BreakLogDAO(conn);
            BreakLog log = new BreakLog(0, operatorId, vehicleId, breakStart, breakEnd);
            dao.save(log);
            conn.close();

            message = "Break log submitted successfully.";
        } catch (Exception e) {
            message = "Error logging break: " + e.getMessage();
            e.printStackTrace();
        }
    }
%>

<h2>Log Break / Out-of-Service</h2>

<form method="post">
    <label for="vehicleId">Vehicle ID:</label>
    <input type="text" name="vehicleId" id="vehicleId" required>

    <label for="breakStart">Break Start (DateTime):</label>
    <input type="datetime-local" name="breakStart" id="breakStart" required>

    <label for="breakEnd">Break End (DateTime):</label>
    <input type="datetime-local" name="breakEnd" id="breakEnd" required>

    <button type="submit">Submit Break Log</button>
</form>

<% if (!message.isEmpty()) { %>
    <p class="<%= message.contains("Error") ? "error" : "message" %>"><%= message %></p>
<% } %>

<a class="back-link" href="dashboard.jsp">&larr; Back to Dashboard</a>

</body>
</html>
