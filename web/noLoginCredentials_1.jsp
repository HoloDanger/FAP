<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                height: 400px;
                margin: auto;
                margin-top: 20vh;
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
                height: 190px;
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
                margin-top: 10px;
            }

            .details-form {
                margin-top: 20px;
                text-align: center;
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
                margin-top: 30px;
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
            }

            .details-container p {
                font-size: 18px;
                margin-bottom: 0px;
                text-align: center;
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
            </nav>
        </header>

        <div class="header-text">IT and Project Management Training Philippines - ActiveLearning, Inc.</div>

        <div class="body">


            <div class="container">
                <div class="details-container">
                    <h2>Invalid Login</h2>
                    <form class="details-form" action="login.jsp" method="POST">
                        <p>Blank Username & Password</p>
                        <input type="submit" value="Back To Login">
                    </form>
                </div>
            </div>
        </div>

        <footer>
            <div class="footer-text">Â© 2024 ActiveLearning, Inc. All Rights Reserved.</div>
        </footer>

    </body>
</html>