<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="datalayer.Vehicle" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Manage Vehicles </title>
        <link rel="stylesheet" href="style.css">
    </head>
    <body>
        <div class="container">
            <h2>Vehicle List</h2>
            <table>
                <tr>
                    <th>ID</th>
                    <th>Type</th>
                    <th>Number</th>
                    <th>Fuel</th>
                    <th>Route</th>
                    <th>Actions</th>
                </tr>
                <c:forEach var="v" items="${vehicleList}">
                    <tr>
                        <td>${v.vehicleId}</td>
                        <td>${v.type}</td>
                        <td>${v.vehicleNumber}</td>
                        <td>${v.fuelType}</td>
                        <td>${v.currentRoute}</td>
                        <td>
                            <a class="button" href="vehicles?action=delete&vehicleId=${v.vehicleId}"
                               onclick="return confirm('Are you sure you want to delete this vehicle?');">Delete</a>
                            <a class="button" href="gpsList.jsp?vehicleId=${v.vehicleId}">View GPS Data</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>

            <h3>Add New Vehicle</h3>
            <form action="RegisterVehicle" method="post">
                <div class="form-group">
                    <label for="type">Type</label>
                    <input type="text" id="type" name="type" required>
                </div>
                <div class="form-group">
                    <label for="vehicleNumber">Vehicle Number</label>
                    <input type="text" id="vehicleNumber" name="vehicleNumber" required>
                </div>
                <div class="form-group">
                    <label for="fuelType">Fuel Type</label>
                    <input type="text" id="fuelType" name="fuelType">
                </div>
                <div class="form-group">
                    <label for="maxPassengers">Max Passengers</label>
                    <input type="number" id="maxPassengers" name="maxPassengers">
                </div>
                <div class="form-group">
                    <label for="currentRoute">Current Route</label>
                    <input type="text" id="currentRoute" name="currentRoute">
                </div>
                <button type="submit">Add Vehicle</button>
            </form>

            <div class="link">
                <a href="dashboard.jsp">Back to Dashboard</a>
            </div>
        </div>
    </body>
</html>
