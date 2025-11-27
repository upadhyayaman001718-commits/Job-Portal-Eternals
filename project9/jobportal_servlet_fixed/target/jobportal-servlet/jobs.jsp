<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html><body>
<h1>Jobs</h1>
<a href="${pageContext.request.contextPath}/jobs">Post a job</a>
<table border="1">
  <tr><th>Title</th><th>Location</th><th>Salary</th><th>Action</th></tr>
  <c:forEach items="${jobs}" var="job">
    <tr>
      <td>${job.title}</td>
      <td>${job.location}</td>
      <td>${job.salary}</td>
      <td>
        <form method="post" action="${pageContext.request.contextPath}/apply">
          <input type="hidden" name="jobId" value="${job.id}" />
          Seeker Id: <input name="seekerId" required />
          <input name="cover" placeholder="cover letter" />
          <button type="submit">Apply</button>
        </form>
      </td>
    </tr>
  </c:forEach>
</table>
</body></html>
