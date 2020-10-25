<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login and Sign Up</title>
</head>
<body>
    <div id="content-grid">

        <div id="signup-pane">
            <p>No account?! No problem! <br /> Sign up below and receive access to my site!</p>
            <form name="register" action="register" method="post">
                <label for="s-first">First name</label>
                <input type="text" required="required" id="s-first" name="s-first"> <br />
                <label for="s-last">Last name</label>
                <input type="text" required="required" id="s-last" name="s-last"> <br />

                <label for="s-username">Username</label>
                <input type="text" required="required" id="s-username" name="s-username"> <br />
                <label for="s-password">Password</label>
                <input type="password" required="required" id="s-password" name="s-password"> <br /><br />
                <button type="submit" id="signup" text="Sign up">Sign Up</button> <br />
            </form>
        </div>

        <div id="login-pane">
            <p>Login to my secure site! <br />
            Are you one of the following users: ${users}? Feel right at home! <br />
            </p>
            <form name="login" action="login" method="post">
                <label for="l-username">Username</label>
                <input type="text" id="l-username" name="l-username"> <br />
                <label for="l-password">Password</label>
                <input type="password" id="l-password" name="l-password"> <br /><br />
                <button type="submit" class="submit" id="submit">Login</button> <br />
            </form>
        </div>
    </div>


    <style>
        #content-grid {
            display: grid;
            grid-template-columns: repeat(2, 1fr);
            gap: 10px;
        }
    </style>

</body>
</html>
