<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Log GPS Location</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <h2>Log GPS Location</h2>
    <form action="GPSTrackerServlet" method="post">
        <label for="vehicleId">Vehicle ID:</label>
        <input type="number" name="vehicleId" required><br><br>

        <label for="location">Location:</label>
        <input type="text" name="location" required><br><br>

        <button type="submit">Submit GPS Log</button>
    </form>

    <% if (request.getParameter("success") != null) { %>
        <p style="color: green;">GPS Log submitted successfully!</p>
    <% } else if (request.getParameter("error") != null) { %>
        <p style="color: red;">Error logging GPS data. Please try again.</p>
    <% } %>

    <p><a href="dashboard.jsp">← Back to Dashboard</a></p>
</body>
</html>
