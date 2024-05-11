<%@page import="java.util.List"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="test.Course"%>
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
                margin-left: -18px;
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

            .schedule-container {
                width: 400px;
                height: 460px;
                margin: 0 auto;
                margin-top: 20px;
                margin-bottom: 40px;
                background-color: #fff;
                border-radius: 5px;
                padding: 20px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
            }

            .schedule-container img {
                max-width: 360px;
                max-height: 244px;
                min-width: 360px;
                margin-bottom: 20px;
                margin-top: -10px;
                margin-left: 20px;
                border-radius: 0%;
            }

            .schedule-container h2 {
                text-align: center;
                color: #333;
                font-size: 30px;
                margin-top: 0px;
            }
            .schedule-container p {
                color: #333;
                font-size: 18px;
                margin-top: -10px;
                margin-left: 20px;
            }
            .schedule-container button .banner {
                background-color: transparent;
                border: none;
            }
            .banner {
                background-color: transparent;
                border: none;
            }

            .unenroll  {
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

            .unenroll:hover {
                background-color: #53A768;
            }

            .body {
                display: flex;
                justify-content: center;
                align-items: flex-start;
                flex-wrap: wrap;
                margin-left: 220px;
                margin-right: 220px;
                margin-bottom: 240px;
                margin-top: 60px;
            }

            button a {
                text-decoration: none;
            }

        </style>
    </head>
    <body>

        <header>
            <nav>
                <button class="active-learning"><img src="https://activelearning.ph/wp-content/uploads/2021/03/logo-white.png"><a class="login" href="login.jsp"></a></button>
                <a href="courses.jsp" class="nav"><button>Courses</button></a>
                <a href="https://activelearning.ph/news/" class="nav"><button class="nav" href="#">News</button></a>
                <a href="https://activelearning.ph/careers/" class="nav"><button class="nav" href="#">Careers</button></a>
                <a href="https://activelearning.ph/about/" class="nav"><button class="nav" href="#">About</button></a>
                <a href="https://activelearning.ph/contact/" class="nav"><button class="nav" href="#">Contact Us</button></a>
                <button class="login" href="#"><a class="login" href="instructor_myaccount.jsp">Account</a></button>
            </nav>
        </header>

        <div class="header-text">${applicationScope.header}</div>

        <div class="body">
            <h1>Course List</h1>
            <form action="CourseServlet" method="get">
                <input type="submit" value="View Courses" id="viewCoursesButton">
            </form>
            <script>
                document.getElementById('viewCoursesButton').addEventListener('click', function (event) {
                    setTimeout(() => {
                        this.style.display = 'none';
                    }, 10);
                });
            </script>
            <%
                List<Course> courses = (List<Course>) request.getAttribute("courses");
                if (courses != null && !courses.isEmpty()) {
                    for (Course course : courses) {
            %>
            <div class="schedule-container">
                <h2><%= course.getCourseName()%></h2>
                <button class="banner"><img src="<%= course.getBanner()%>" alt="Course Banner" class="profile-pic"></button>
                <p>Course Instructor: <%= course.getInstructor()%></p>
                <p>Course Start Date: <%= new SimpleDateFormat("yyyy-MM-dd").format(course.getStartDate())%></p>
                <p>Course End Date: <%= new SimpleDateFormat("yyyy-MM-dd").format(course.getEndDate())%></p>
                <p>Course Time: <%= course.getCourseTime()%></p>
                <button class="unenroll">Enroll</button>
            </div>
            <%
                }
            } else {
            %>
            <p>No courses available.</p>
            <%
                }
            %>
        </div>

        <footer>
            <div class="footer-text">${applicationScope.footer}</div>
        </footer>

    </body>
</html>
