<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.sql.*, java.util.ArrayList, java.util.List" %>
<%@ page import="datalayer.DataSource" %>
<%@ page import="datalayer.GPSTracking" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>GPS Data List</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 30px;
            }
            table {
                width: 100%;
                border-collapse: collapse;
                margin-bottom: 20px;
            }
            th, td {
                padding: 10px;
                border: 1px solid #aaa;
                text-align: center;
            }
            .map-container {
                width: 100%;
                height: 400px;
                margin-top: 20px;
            }
            a.button {
                text-decoration: none;
                padding: 6px 12px;
                background-color: #4CAF50;
                color: white;
                border-radius: 4px;
            }
        </style>
        <script>
            /**
             * Updates the iframe's src attribute to display the map for the given coordinates.
             * @param {number} lat - The latitude.
             * @param {number} lng - The longitude.
             */
            function showMap(lat, lng) {
                var mapFrame = document.getElementById("mapFrame");
                mapFrame.src = "https://www.google.com/maps?q=" + lat + "," + lng + "&output=embed";
            }
        </script>
    </head>
    <body>
        <%
            // Retrieve vehicleId from the request parameter
            String vehicleIdParam = request.getParameter("vehicleId");
            int vehicleId = 0;
            try {
                vehicleId = Integer.parseInt(vehicleIdParam);
            } catch (NumberFormatException e) {
                out.println("Invalid vehicle ID.");
                return;
            }

            // Query the database for all GPS records for the given vehicleId
           List<GPSTracking> gpsList = new ArrayList<>();
String sql = "SELECT tracking_id, vehicle_id, latitude, longitude, location, timestamp FROM gps_data WHERE vehicle_id = ? ORDER BY timestamp DESC";
try {
    // Using DataSource to get a connection
    Connection con = DataSource.getInstance().createConnection();
    try (PreparedStatement pstmt = con.prepareStatement(sql)) {
        pstmt.setInt(1, vehicleId);
        try (ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                GPSTracking gps = new GPSTracking();
                gps.setTrackingId(rs.getInt("tracking_id"));
                gps.setVehicleId(rs.getInt("vehicle_id"));
                gps.setLatitude(rs.getDouble("latitude"));
                gps.setLongitude(rs.getDouble("longitude"));
                gps.setLocation(rs.getString("location"));
                gps.setTimestamp(rs.getTimestamp("timestamp").toLocalDateTime());
                gpsList.add(gps);
            }
        }
    }
} catch (SQLException e) {
    out.println("Error retrieving GPS data: " + e.getMessage());
}

        %>

        <h2>GPS Data for Vehicle ID: <%= vehicleId %></h2>
        <table>
            <tr>
                <th>GPS ID</th>
                <th>Location</th>
                <th>Timestamp</th>
                <th>Action</th>
            </tr>
            <%
                if (gpsList.isEmpty()) {
            %>
            <tr>
                <td colspan="4">No GPS data found for this vehicle.</td>
            </tr>
            <%
                } else {
                    for (GPSTracking gps : gpsList) {
            %>
            <tr>
                <td><%= gps.getTrackingId() %></td>
                <td><%= gps.getLocation() %></td>
                <td><%= gps.getTimestamp() %></td>
                <td>
                    <!-- When clicked, this button calls the JavaScript function to show the map based on latitude and longitude -->
                    <button onclick="showMap(<%= gps.getLatitude() %>, <%= gps.getLongitude() %>)">View Map</button>
                </td>
            </tr>
            <%
                    }
                }
            %>
        </table>

        <!-- Map container: the iframe will display the Google Map -->
        <div class="map-container">
            <iframe id="mapFrame" width="100%" height="100%" frameborder="0" style="border:0" allowfullscreen="" aria-hidden="false" tabindex="0"></iframe>
        </div>

        <p><a class="button" href="dashboard.jsp">Back to Dashboard</a></p>
    </body>
</html>
