<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Log GPS Location</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <h2>Log GPS Location</h2>

    <form method="post" action="GPSTrackerServlet" accept-charset="UTF-8">
        <label>Vehicle ID:</label>
        <input type="number" name="vehicleId" required><br><br>

        <label>Location:</label>
        <input type="text" name="location" required><br><br>

        <label>Status:</label>
        <select name="status" required>
            <option value="Arrival">Arrival</option>
            <option value="Departure">Departure</option>
        </select><br><br>

        <input type="submit" value="Submit">
    </form>

    <p><a href="dashboard.jsp">← Back to Dashboard</a></p>
</body>
</html>
