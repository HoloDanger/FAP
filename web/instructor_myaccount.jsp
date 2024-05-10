<%@ page import="test.SQLUser" %>
<%@ page import="test.UserDetailServlet" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>IT and Project Management Training Philippines - ActiveLearning, Inc.</title>
        <style>
            body {
                font-family: Tahoma, Arial;
                margin: 0;
                padding: 0;
                margin-top: 10px;
            }

            header {
                background-color: #1E1E25;
                padding: 5px;
                text-align: center;
                height: 95px;
                margin-top: -10px;
                margin-left: -34px;
                display: flex;
                justify-content: center;
                align-items: center;
            }

            header button {
                color: #fff;
                text-decoration: none;
                padding: 10px;
                transition: background-color 0.3s, color 0.3s;
                border-radius: 50px;
                border: none;
                background-color: transparent;
                font-size: 18px;
                font-weight: bold;
                margin: 0px 5px;
                white-space: nowrap;
            }

            header button:hover {
                background-color: #5FBF77;
                color: #fff;
            }

            button.active-learning {
                background-color: transparent;
                border: none;
                color: white;
                padding: 0;
                text-align: center;
                text-decoration: none;
                display: inline-block;
                font-weight: bold;
            }

            button.login {
                color: #fff;
                font-weight: bold;
                font-size: 22px;
            }

            @media (min-width: 1600px) {
                button.active-learning {
                    margin: 0 230px;
                }
                button.login {
                    margin: 0 230px;
                }
            }

            @media (max-width: 800px) {
                button.active-learning img {
                    transform: scale(0.60);
                    margin-right: -20px;
                }
            }

            button.active-learning:hover {
                background-color: transparent;
            }

            button.active-learning img {
                margin-top: -20px;
                margin-bottom: -32px;
            }

            .header-text {
                text-align: center;
                color: #A2A2A2;
                background-color: #464E58;
            }

            .footer-text {
                position: fixed;
                bottom: 0;
                width: 100%;
                text-align: center;
                color: #fff;
                background-color: #1E1E25;
            }

            .login a {
                color: #fff;
            }

            .sidebar {
                width: 200px;
                height: 100vh;
                background-color: #464E58;
                margin-left: -220px;
                border-bottom-left-radius: 20px;
                border-bottom-right-radius: 20px;
            }

            .profile-pic {
                max-width: 100px;
                max-height: 100px;
                border-radius: 50%;
                margin-bottom: 20px;
                margin-top: 30px;
                margin-left: 50px;
                min-width: 100px;
                min-height: 100px;
                max-width: 100px;
                max-height: 100px;
            }

            .username {
                font-size: 20px;
                margin-bottom: 20px;
                display: flex;
                justify-content: center;
                align-items: center;
                font-weight: bold;
                color: #fff;
                text-decoration: underline;
            }

            .sidebar button {
                color: #fff;
                text-decoration: none;
                padding: 10px 20px;
                width: 100%;
                margin-top: 5px;
                margin-bottom: 5px;
                border: none;
                background-color: #464E58;
                cursor: pointer;
                font-size: 16px;
                transition: background-color 0.3s, color 0.3s;
            }

            .sidebar button:hover {
                background-color: #5D646C;
            }

            .sidebar button.myaccount {
                background-color: #5D646C;
            }

            .sidebar button.printdetails {
                border: 1px solid #ccc;
                border-radius: 20px;
                width: 80%;
                margin-left: auto;
                margin-right: auto;
                display: block;
            }
            .container {
                width: 400px;
                height: 1200px;
                margin: 0 auto;
                margin-top: 20px;
                background-color: #fff;
                border-radius: 5px;
                padding: 20px;
                display: flex;
                justify-content: center;
                align-items: flex-start;
                flex-wrap: wrap;
            }



            @media (min-width: 1600px) {
                .container {
                    width: 1000px;
                }
            }

            .details-container {
                width: 400px;
                height: 970px;
                margin: 100px auto;
                background-color: #fff;
                border-radius: 5px;
                padding: 20px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
                margin-top: 10px;
            }

            .details-container h2 {
                text-align: center;
                color: #333;
                font-size: 36px;
                display: block;
                margin: 0 auto;
                margin-bottom: 20px;
            }

            .details-form {
                margin-top: 20px;
            }

            .details-form input[type="text"],
            .details-form input[type="password"] {
                width: 338px;
                padding: 10px;
                margin: 10px 0;
                margin-left: 20px;
                border: 1px solid #ccc;
                border-radius: 20px;
                font-size: 18px;
            }

            .details-form input[type="submit"] {
                width: 150px;
                padding: 10px;
                margin-top: 20px;
                margin-left: 125px;
                background-color: #5FBF77;
                color: #fff;
                border: none;
                border-radius: 20px;
                cursor: pointer;
                font-size: 18px;
                font-weight: bold;
                transition: background-color 0.3s, color 0.3s;
            }

            .details-form input[type="submit"]:hover {
                background-color: #53A768;
            }

            .details-form input[name="captcha"] {
                margin-top: 150px;
            }

            .details-form input[name="username"] {
                margin-top: 20px;
            }

            .details-form button {
                background-color: transparent;
                border: none;
                font-size: 16px;
                color: #5FBF77;
            }

            .details-container img {
                display: block;
                margin: 0 auto;
                min-width: 100px;
                min-height: 100px;
                max-width: 100px;
                max-height: 100px;
            }

            .details-container p {
                font-size: 18px;
                margin-bottom: 0px;
            }

            .body {
                display: flex;
                justify-content: center;
                align-items: flex-start;
                flex-wrap: wrap;
                margin-left: 220px;
                margin-right: 220px;
            }

        </style>
    </head>
    <body>
        <%
            response.setHeader("Cache-Control", "no-cache, no-store,must - revalidate"); // HTTP 1.1. 
            response.setDateHeader("Expires", 0); // Proxies. 
            String username = null;
            String firstname = null;
            String lastname = null;
            String email = null;
            String password = null;
            String link = null;
            String role = null;
            if (session == null || session.getAttribute("username") == null) {
                response.sendRedirect("error_session.jsp");
            } else {
                username = (String) session.getAttribute("username");
                firstname = (String) session.getAttribute("firstname");
                lastname = (String) session.getAttribute("lastname");
                email = (String) session.getAttribute("email");
                password = (String) session.getAttribute("password");
                link = (String) session.getAttribute("link");
                role = (String) session.getAttribute("role");

                if (link == null) {
                    link = "https://via.placeholder.com/100";
                }

                if (role != null && role.equals("Student")) {
                    response.sendRedirect("student_myaccount.jsp");
                }
            }
        %>

        <header>
            <nav>
                <button class="active-learning"><img src="https://activelearning.ph/wp-content/uploads/2021/03/logo-white.png"><a class="login" href="login.jsp"></a></button>
                <a href="courses.jsp" class="nav"><button>Courses</button></a>
                <a href="https://activelearning.ph/news/" class="nav"><button class="nav" href="#">News</button></a>
                <a href="https://activelearning.ph/careers/" class="nav"><button class="nav" href="#">Careers</button></a>
                <a href="https://activelearning.ph/about/" class="nav"><button class="nav" href="#">About</button></a>
                <a href="https://activelearning.ph/contact/" class="nav"><button class="nav" href="#">Contact Us</button></a>
                <form action="LogoutServlet" method="GET" style="display: inline;"><button class="login" type="submit">Logout</button></form>
            </nav>
        </header>

        <div class="header-text">${applicationScope.header}</div>

        <div class="body">

            <!-- Sidebar -->
            <div class="sidebar">
                <img src="<%= link%>" alt="Profile Picture" class="profile-pic">
                <div class="username"><%= firstname%> <%= lastname%></div>
                <form action="instructor_myaccount.jsp" method="GET">
                    <button type="submit" class="myaccount">My Account</button>
                </form>
                <form action="instructor_myschedule.jsp" method="GET">
                    <button class="myschedule">My Schedule</button>
                </form>
                <form action="instructor_managecourses.jsp" method="GET">
                    <button class="managecourses">Manage Courses</button>
                </form>
                <form action="instructor_manageaccounts.jsp" method="GET">
                    <button class="manageaccounts">Manage Accounts</button>
                </form>
                <button class="printdetails">Print Details</button>
            </div>
            <div class="container">
                <div class="details-container">
                    <h2>Account Details</h2>
                    <img src="<%= link%>" alt="Profile Picture" class="profile-pic">
                    <form class="details-form" action="UserDetailServlet" method="POST">
                        <p>First Name:</p><input type="text" placeholder="First Name" name="firstname" value="<%= firstname%>" required>
                        <p>Last Name:</p><input type="text" placeholder="Last Name" name="lastname" value="<%= lastname%>" required>
                        <p>Username:</p><input type="text" placeholder="Username" name="username" value="<%= username%>" required>
                        <p>Email:</p><input type="text" placeholder="Email" name="email" value="<%= email%>" required>
                        <p>Password:</p><input type="password" placeholder="Password" name="password" required>
                        <p>Role:</p><input type="text" placeholder="Role" name="role" value="<%= role%>" readonly required>
                        <p>Profile Picture Link:</p><input type="text" placeholder="Link" value="<%= link%>" name="link">
                        <input type="submit" value="Save">
                    </form>
                </div>
            </div>
        </div>

        <footer>
            <div class="footer-text">${applicationScope.footer}</div>
        </footer>

    </body>
</html>
