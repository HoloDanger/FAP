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

            .sidebar button.managecourses {
                background-color: #5D646C;
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

            .delete-container {
                width: 400px;
                height: 220px;
                margin: 100px auto;
                background-color: #fff;
                border-radius: 5px;
                padding: 20px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
                margin-top: 10px;
            }

            .add-container {
                width: 400px;
                height:740px;
                margin: 100px auto;
                background-color: #fff;
                border-radius: 5px;
                padding: 20px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
                margin-top: 10px;
            }

            .print-container {
                width: 400px;
                height: 280px;
                margin: 100px auto;
                background-color: #fff;
                border-radius: 5px;
                padding: 20px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
                margin-top: 10px;
            }

            .delete-container h2,
            .print-container h2,
            .add-container h2 {
                text-align: center;
                color: #333;
                font-size: 36px;
                display: block;
                margin: 0 auto;
                margin-bottom: 20px;
            }

            .delete-form,
            .add-form {
                margin-top: 20px;
            }

            .delete-form input[type="text"],
            .delete-form input[type="password"],
            .print-form input[type="text"],
            .print-form input[type="password"],
            .add-form input[type="text"],
            .add-form input[type="password"] {
                width: 338px;
                padding: 10px;
                margin: 10px 0;
                margin-left: 20px;
                border: 1px solid #ccc;
                border-radius: 20px;
                font-size: 18px;
            }

            .delete-form input[type="submit"],
            .print-form input[type="submit"],
            input[type="submit"],
            .add-form input[type="submit"] {
                width: 150px;
                padding: 10px;
                margin-top: 40px;
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

            .delete-form input[type="submit"],
            .print-form input[type="submit"],
            input[type="submit"]		{
                margin-top: 20px;
            }


            .add-form input[type="submit"]		{
                margin-top: 20px;
            }

            .delete-form input[type="submit"]:hover,
            .print-form input[type="submit"]:hover,
            input[type="submit"]:hover,
            .add-form input[type="submit"]:hover	{
                background-color: #53A768;
            }

            .delete-form button,
            .print-form button,
            .add-form button	{
                background-color: transparent;
                border: none;
                font-size: 16px;
                color: #5FBF77;
            }

            .delete-container p,
            .print-container p,
            .add-container p {
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

        <header>
            <nav>
                <button class="active-learning"><img src="https://activelearning.ph/wp-content/uploads/2021/03/logo-white.png"><a class="login" href="index.jsp"></a></button>
                <button class="nav" href="#">Courses</button>
                <button class="nav" href="#">News</button>
                <button class="nav" href="#">Careers</button>
                <button class="nav" href="#">About</button>
                <button class="nav" href="#">Contact Us</button>
                <button class="login" href="#"><a class="login" href="login.jsp">Logout</a></button>
            </nav>
        </header>

        <div class="header-text">IT and Project Management Training Philippines - ActiveLearning, Inc.</div>

        <div class="body">

            <!-- Sidebar -->
            <div class="sidebar">
                <img src="https://via.placeholder.com/100" alt="Profile Picture" class="profile-pic">
                <div class="username">Juan Dela Cruz</div>
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
            </div>
            <div class="container">
                <div class="add-container">
                    <h2>Add a Course:</h2>
                    <!-- Modified form: Added action attribute to submit form data to the CourseServlet -->
                    <form class="add-form" action="CourseServlet" method="POST">
                        <p>Name:</p><input type="text" placeholder="Name" name="coursename" required>
                        <p>Instructor:</p><input type="text" placeholder="Instructor" name="courseinstructor" required>
                        <p>Start Date:</p><input type="date" placeholder="Start Date" name="coursestartdate" required>
                        <p>End Date:</p><input type="date" placeholder="End Date" name="courseenddate" required>
                        <p>Time:</p><input type="text" placeholder="Time" name="coursetime" required>
                        <p>Banner:</p><input type="text" placeholder="Banner Link" name="coursebanner" required>
                        <input type="submit" value="Add">
                    </form>
                </div>
                <div>
                    <div class="print-container">
                        <h2>Print a Course:</h2>
                        <form class="print-form" action="CourseServlet" method="POST">
                            <p>Name:</p><input type="text" placeholder="Name" name="coursename" required>
                            <input type="submit" value="Print">
                        </form>
                        <input type="submit" value="Print All">
                    </div>
                    <div class="delete-container">
                        <h2>Delete a Course:</h2>
                        <form class="delete-form" action="CourseServlet" method="POST">
                            <input type="hidden" name="action" value="delete">
                            <p>Name:</p><input type="text" placeholder="Name" name="coursename" required>
                            <input type="submit" value="Delete">
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <footer>
            <div class="footer-text">© 2024 ActiveLearning, Inc. All Rights Reserved.</div>
        </footer>

    </body>
</html>