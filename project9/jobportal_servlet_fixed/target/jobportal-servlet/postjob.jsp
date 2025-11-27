<%@ page contentType="text/html;charset=UTF-8" %>
<html><body>
<h1>Post Job</h1>
<form method="post" action="${pageContext.request.contextPath}/jobs">
 Employer Id: <input name="employerId"/> <br/>
 Title: <input name="title"/> <br/>
 Location: <input name="location"/> <br/>
 Type: <input name="type"/> <br/>
 Salary: <input name="salary"/> <br/>
 Description: <textarea name="description"></textarea><br/>
 <button type="submit">Post</button>
</form>
</body></html>
