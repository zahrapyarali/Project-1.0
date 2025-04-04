<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Vehicle Control Reports</title>
        <link rel="stylesheet" href="style.css">
    </head>
    <body>
        <div class="container">
            <h2>Report & Analytic</h2>

            <h3>Average Energy Usage per Vehicle</h3>
            <table>
                <tr>
                    <th>Vehicle ID</th>
                    <th>Average Usage</th>
                </tr>
                <c:forEach var="entry" items="${avgUsageMap}">
                    <tr>
                        <td>${entry.key}</td>
                        <td>${entry.value}</td>
                    </tr>
                </c:forEach>
            </table>

            <h3>Total Usage by Energy Type</h3>
            <table>
                <tr>
                    <th>Energy Type</th>
                    <th>Total Consumption</th>
                </tr>
                <c:forEach var="entry" items="${typeTotals}">
                    <tr>
                        <td>${entry.key}</td>
                        <td>${entry.value}</td>
                    </tr>
                </c:forEach>
            </table>

            <h3>Maintenance Tasks</h3>
            <p>Pending tasks: ${pendingCount}</p>
            <table>
                <tr>
                    <th>Alert ID</th>
                    <th>Vehicle ID</th>
                    <th>Message</th>
                    <th>Time</th>
                    <th>Status</th>
                </tr>
                <c:forEach var="task" items="${taskList}">
                    <tr>
                        <td>${task.taskId}</td>
                        <td>${task.vehicleId}</td>
                        <td>${task.alertMessage}</td>
                        <td>${task.alertTime}</td>
                        <td><c:choose>
                                <c:when test="${task.completed}">Completed</c:when>
                                <c:otherwise>Pending</c:otherwise>
                            </c:choose></td>
                    </tr>
                </c:forEach>
            </table>

            <div class="link">
                <a href="dashboard.jsp">Back to Dashboard</a>
            </div>
        </div>
    </body>
</html>
