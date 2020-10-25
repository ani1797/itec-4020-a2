<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hello ${name.getFirst()}</title>
</head>
<body>
    <p>
        Hello ${name.getFirst()} ${name.getLast()}, <br/>
        You are seeing this page because you have successfully authenticated! <br />
        Here are couple of your details: <br/>
        Username: ${username}
    </p>
    <p>You can click <a href="/logout">Logout</a> to get back to login page</p>
</body>
</html>
