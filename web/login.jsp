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
      margin-left: -48px;
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
	
	.login-container {
      width: 400px;
      height: 600px;
      margin: 100px auto;
      background-color: #fff;
      border-radius: 5px;
      padding: 20px;
      box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
    }

    .login-container h2 {
      text-align: center;
      color: #333;
	  font-size: 36px;
    }

    .login-form {
      margin-top: 20px;
    }

    .login-form input[type="text"],
    .login-form input[type="password"] {
      width: 338px;
      padding: 10px;
      margin: 10px 0;
	  margin-left: 20px;
      border: 1px solid #ccc;
      border-radius: 20px;
	  font-size: 18px;
    }

    .login-form input[type="submit"] {
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

    .login-form input[type="submit"]:hover {
      background-color: #53A768;
    }

	.login-form input[name="captcha"] {
		margin-top: 150px;
	}

	.login-form input[name="username"] {
		margin-top: 20px;
	}

    .login-form .message {
      text-align: center;
      margin-top: 10px;
	  margin-left: 5px;
    }

    .login-form .message a {
      color: #5FBF77;
    }

    .login-form button {
      background-color: transparent;
      border: none;
	  font-size: 16px;
      color: #5FBF77;
    }

    .login a {
      color: #fff;
    }
  </style>
</head>
<body>

<header>
  <nav>
    <button class="active-learning"><img src="https://activelearning.ph/wp-content/uploads/2021/03/logo-white.png"></button>
    <button class="nav" href="#">Courses</button>
    <button class="nav" href="#">News</button>
    <button class="nav" href="#">Careers</button>
    <button class="nav" href="#">About</button>
    <button class="nav" href="#">Contact Us</button>
    <button class="login" href="#"><a class="login" href="login.html">Login</a></button>
  </nav>
</header>

<div class="header-text">IT and Project Management Training Philippines - ActiveLearning, Inc.</div>

<div class="body">
<div class="login-container">
  <h2>Login</h2>
  <form class="login-form" action="LoginServlet" method="POST">
    <input type="text" placeholder="Username" name="username" required>
    <input type="password" placeholder="Password" name="password" required>
    <input type="text" placeholder="Captcha" name="captcha" required>
    <!-- Add the CAPTCHA image here -->
    <img src="CaptchaServlet" alt="CAPTCHA Image" id="captchaImage">
<!--     Optionally, add a link or button to refresh the CAPTCHA image 
    <button type="button" onclick="refreshCaptcha()">Refresh CAPTCHA</button>-->
    <input type="submit" value="Login">
    <div class="message">Don't have an account?<button><a href="login.jsp">Sign Up</a></button></div>
  </form>
</div>
</div>

<footer>
  <!-- Your existing footer content -->
</footer>

</body>
</html>