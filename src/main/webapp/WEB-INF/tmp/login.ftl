<!DOCTYPE html>
<html lang="en">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>DataSchema - Modern database administration tool</title>

    <!-- Bootstrap core CSS -->

    <link href="css/bootstrap.min.css" rel="stylesheet">

    <link href="fonts/css/font-awesome.min.css" rel="stylesheet">
    <link href="css/animate.min.css" rel="stylesheet">

    <!-- Custom styling plus plugins -->
    <link href="css/custom.css" rel="stylesheet">
    <link href="css/icheck/flat/green.css" rel="stylesheet">

    <link rel="apple-touch-icon" sizes="57x57" href="/favs/apple-icon-57x57.png">
    <link rel="apple-touch-icon" sizes="60x60" href="/favs/apple-icon-60x60.png">
    <link rel="apple-touch-icon" sizes="72x72" href="/favs/apple-icon-72x72.png">
    <link rel="apple-touch-icon" sizes="76x76" href="/favs/apple-icon-76x76.png">
    <link rel="apple-touch-icon" sizes="114x114" href="/favs/apple-icon-114x114.png">
    <link rel="apple-touch-icon" sizes="120x120" href="/favs/apple-icon-120x120.png">
    <link rel="apple-touch-icon" sizes="144x144" href="/favs/apple-icon-144x144.png">
    <link rel="apple-touch-icon" sizes="152x152" href="/favs/apple-icon-152x152.png">
    <link rel="apple-touch-icon" sizes="180x180" href="/favs/apple-icon-180x180.png">
    <link rel="icon" type="image/png" sizes="192x192" href="/favs/android-icon-192x192.png">
    <link rel="icon" type="image/png" sizes="32x32" href="/favs/favicon-32x32.png">
    <link rel="icon" type="image/png" sizes="96x96" href="/favs/favicon-96x96.png">
    <link rel="icon" type="image/png" sizes="16x16" href="/favs/favicon-16x16.png">
    <link rel="manifest" href="/favs/manifest.json">
    <meta name="msapplication-TileColor" content="#ffffff">
    <meta name="msapplication-TileImage" content="/favs/ms-icon-144x144.png">
    <meta name="theme-color" content="#ffffff">


    <script src="js/jquery.min.js"></script>

    <!--[if lt IE 9]>
    <script src="../assets/js/ie8-responsive-file-warning.js"></script>
    <![endif]-->

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body style="background:#F7F7F7;">

<div class="">
    <a class="hiddenanchor" id="toregister"></a>
    <a class="hiddenanchor" id="tologin"></a>

    <div id="wrapper">
        <div id="login" class="animate form">
            <section class="login_content">
                <form method="post" action="/login">
                    <h1>Login Form</h1>
                    <div>
                        <input type="text" class="form-control" placeholder="DB host" required="" name="db_url" value="jdbc:postgresql://localhost:5432/sample"/>
                    </div>
                    <div>
                        <input type="text" class="form-control" placeholder="DB username" required="" name="username" value="postgres"/>
                    </div>
                    <div>
                        <input type="password" class="form-control" placeholder="DB password" required="" name="password" value="postgres"/>
                    </div>
                    <div>
                        <input type="submit" class="btn btn-default submit" value="Log in"/>
                    <#--<a class="btn btn-default submit" href="index.html">Log in</a>-->
                    <#--<a class="reset_pass" href="#">Lost your password?</a>-->
                    </div>
                    <div class="clearfix"></div>
                    <div class="separator">

                    <#--<p class="change_link">New to site?-->
                    <#--<a href="#toregister" class="to_register"> Create Account </a>-->
                    <#--</p>-->
                        <div class="clearfix"></div>
                        <br/>
                        <div>
                            <h1>
                                <img src="https://cdn3.iconfinder.com/data/icons/databases-2-2/512/data_cube-512.png" height="40" style="padding-left: 5px;"> DataSchema
                            </h1>

                            <p>©2016 All Rights Reserved.</p>
                        </div>
                    </div>
                </form>
                <!-- form -->
            </section>
            <!-- content -->
        </div>
    </div>
</div>

</body>

</html>
