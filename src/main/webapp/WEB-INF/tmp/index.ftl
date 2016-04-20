<!DOCTYPE html>
<html lang="en">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Gentallela Alela! | </title>

    <!-- Bootstrap core CSS -->

    <link href="css/bootstrap.min.css" rel="stylesheet">

    <link href="fonts/css/font-awesome.min.css" rel="stylesheet">
    <link href="css/animate.min.css" rel="stylesheet">

    <!-- Custom styling plus plugins -->
    <link href="css/custom.css" rel="stylesheet">
    <link href="css/icheck/flat/green.css" rel="stylesheet">


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


<body class="nav-md">

<div class="container body">


    <div class="main_container">

        <div class="col-md-3 left_col">
            <div class="left_col scroll-view">

                <div class="navbar nav_title" style="border: 0;">
                    <a href="index.html" class="site_title"><i class="fa fa-paw"></i> <span>Gentellela Alela!</span></a>
                </div>
                <div class="clearfix"></div>

                <!-- menu prile quick info -->
                <div class="profile">
                    <div class="profile_pic">
                        <img src="images/img.jpg" alt="..." class="img-circle profile_img">
                    </div>
                    <div class="profile_info">
                        <span>Welcome,</span>
                        <h2>John Doe</h2>
                    </div>
                </div>
                <!-- /menu prile quick info -->

                <br/>

                <!-- sidebar menu -->
                <div id="sidebar-menu" class="main_menu_side hidden-print main_menu">

                    <div class="menu_section">
                        <h3>General</h3>
                        <ul class="nav side-menu">
                            <li><a><i class="fa fa-home"></i> Home <span class="fa fa-chevron-down"></span></a>
                                <ul class="nav child_menu" style="display: none">
                                    <li><a href="index.html">Dashboard</a>
                                    </li>
                                    <li><a href="index2.html">Dashboard2</a>
                                    </li>
                                    <li><a href="index3.html">Dashboard3</a>
                                    </li>
                                </ul>
                            </li>
                            <li><a><i class="fa fa-edit"></i> Forms <span class="fa fa-chevron-down"></span></a>
                                <ul class="nav child_menu" style="display: none">
                                    <li><a href="form.html">General Form</a>
                                    </li>
                                    <li><a href="form_advanced.html">Advanced Components</a>
                                    </li>
                                    <li><a href="form_validation.html">Form Validation</a>
                                    </li>
                                    <li><a href="form_wizards.html">Form Wizard</a>
                                    </li>
                                    <li><a href="form_upload.html">Form Upload</a>
                                    </li>
                                    <li><a href="form_buttons.html">Form Buttons</a>
                                    </li>
                                </ul>
                            </li>
                            <li><a><i class="fa fa-desktop"></i> UI Elements <span class="fa fa-chevron-down"></span></a>
                                <ul class="nav child_menu" style="display: none">
                                    <li><a href="general_elements.html">General Elements</a>
                                    </li>
                                    <li><a href="media_gallery.html">Media Gallery</a>
                                    </li>
                                    <li><a href="typography.html">Typography</a>
                                    </li>
                                    <li><a href="icons.html">Icons</a>
                                    </li>
                                    <li><a href="glyphicons.html">Glyphicons</a>
                                    </li>
                                    <li><a href="widgets.html">Widgets</a>
                                    </li>
                                    <li><a href="invoice.html">Invoice</a>
                                    </li>
                                    <li><a href="inbox.html">Inbox</a>
                                    </li>
                                    <li><a href="calendar.html">Calendar</a>
                                    </li>
                                </ul>
                            </li>
                            <li><a><i class="fa fa-table"></i> Tables <span class="fa fa-chevron-down"></span></a>
                                <ul class="nav child_menu" style="display: none">
                                <#list tableNames?keys as tnProp>
                                    <li>
                                        <a href="/table/view/${tnProp}">${tableNames[tnProp]}</a>
                                    </li>
                                </#list>
                                </ul>
                            </li>
                            <li><a><i class="fa fa-bar-chart-o"></i> Data Presentation <span class="fa fa-chevron-down"></span></a>
                                <ul class="nav child_menu" style="display: none">
                                    <li><a href="chartjs.html">Chart JS</a>
                                    </li>
                                    <li><a href="chartjs2.html">Chart JS2</a>
                                    </li>
                                    <li><a href="morisjs.html">Moris JS</a>
                                    </li>
                                    <li><a href="echarts.html">ECharts </a>
                                    </li>
                                    <li><a href="other_charts.html">Other Charts </a>
                                    </li>
                                </ul>
                            </li>
                        </ul>
                    </div>
                    <div class="menu_section">
                        <h3>Live On</h3>
                        <ul class="nav side-menu">
                            <li><a><i class="fa fa-bug"></i> Additional Pages <span class="fa fa-chevron-down"></span></a>
                                <ul class="nav child_menu" style="display: none">
                                    <li><a href="e_commerce.html">E-commerce</a>
                                    </li>
                                    <li><a href="projects.html">Projects</a>
                                    </li>
                                    <li><a href="project_detail.html">Project Detail</a>
                                    </li>
                                    <li><a href="contacts.html">Contacts</a>
                                    </li>
                                    <li><a href="profile.html">Profile</a>
                                    </li>
                                </ul>
                            </li>
                            <li><a><i class="fa fa-windows"></i> Extras <span class="fa fa-chevron-down"></span></a>
                                <ul class="nav child_menu" style="display: none">
                                    <li><a href="page_404.html">404 Error</a>
                                    </li>
                                    <li><a href="page_500.html">500 Error</a>
                                    </li>
                                    <li><a href="plain_page.html">Plain Page</a>
                                    </li>
                                    <li><a href="login.html">Login Page</a>
                                    </li>
                                    <li><a href="pricing_tables.html">Pricing Tables</a>
                                    </li>

                                </ul>
                            </li>
                            <li><a><i class="fa fa-laptop"></i> Landing Page <span class="label label-success pull-right">Coming Soon</span></a>
                            </li>
                        </ul>
                    </div>

                </div>
                <!-- /sidebar menu -->

                <!-- /menu footer buttons -->
                <div class="sidebar-footer hidden-small">
                    <a data-toggle="tooltip" data-placement="top" title="Settings">
                        <span class="glyphicon glyphicon-cog" aria-hidden="true"></span>
                    </a>
                    <a data-toggle="tooltip" data-placement="top" title="FullScreen">
                        <span class="glyphicon glyphicon-fullscreen" aria-hidden="true"></span>
                    </a>
                    <a data-toggle="tooltip" data-placement="top" title="Lock">
                        <span class="glyphicon glyphicon-eye-close" aria-hidden="true"></span>
                    </a>
                    <a data-toggle="tooltip" data-placement="top" title="Logout">
                        <span class="glyphicon glyphicon-off" aria-hidden="true"></span>
                    </a>
                </div>
                <!-- /menu footer buttons -->
            </div>
        </div>

        <!-- top navigation -->
        <div class="top_nav">

            <div class="nav_menu">
                <nav class="" role="navigation">
                    <div class="nav toggle">
                        <a id="menu_toggle"><i class="fa fa-bars"></i></a>
                    </div>

                    <ul class="nav navbar-nav navbar-right">
                        <li class="">
                            <a href="javascript:;" class="user-profile dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                                <img src="images/img.jpg" alt="">John Doe
                                <span class=" fa fa-angle-down"></span>
                            </a>
                            <ul class="dropdown-menu dropdown-usermenu animated fadeInDown pull-right">
                                <li><a href="javascript:;"> Profile</a>
                                </li>
                                <li>
                                    <a href="javascript:;">
                                        <span class="badge bg-red pull-right">50%</span>
                                        <span>Settings</span>
                                    </a>
                                </li>
                                <li>
                                    <a href="javascript:;">Help</a>
                                </li>
                                <li><a href="login.html"><i class="fa fa-sign-out pull-right"></i> Log Out</a>
                                </li>
                            </ul>
                        </li>

                        <li role="presentation" class="dropdown">
                            <a href="javascript:;" class="dropdown-toggle info-number" data-toggle="dropdown" aria-expanded="false">
                                <i class="fa fa-envelope-o"></i>
                                <span class="badge bg-green">6</span>
                            </a>
                            <ul id="menu1" class="dropdown-menu list-unstyled msg_list animated fadeInDown" role="menu">
                                <li>
                                    <a>
                      <span class="image">
                                        <img src="images/img.jpg" alt="Profile Image"/>
                                    </span>
                      <span>
                                        <span>John Smith</span>
                      <span class="time">3 mins ago</span>
                      </span>
                      <span class="message">
                                        Film festivals used to be do-or-die moments for movie makers. They were where...
                                    </span>
                                    </a>
                                </li>
                                <li>
                                    <a>
                      <span class="image">
                                        <img src="images/img.jpg" alt="Profile Image"/>
                                    </span>
                      <span>
                                        <span>John Smith</span>
                      <span class="time">3 mins ago</span>
                      </span>
                      <span class="message">
                                        Film festivals used to be do-or-die moments for movie makers. They were where...
                                    </span>
                                    </a>
                                </li>
                                <li>
                                    <a>
                      <span class="image">
                                        <img src="images/img.jpg" alt="Profile Image"/>
                                    </span>
                      <span>
                                        <span>John Smith</span>
                      <span class="time">3 mins ago</span>
                      </span>
                      <span class="message">
                                        Film festivals used to be do-or-die moments for movie makers. They were where...
                                    </span>
                                    </a>
                                </li>
                                <li>
                                    <a>
                      <span class="image">
                                        <img src="images/img.jpg" alt="Profile Image"/>
                                    </span>
                      <span>
                                        <span>John Smith</span>
                      <span class="time">3 mins ago</span>
                      </span>
                      <span class="message">
                                        Film festivals used to be do-or-die moments for movie makers. They were where...
                                    </span>
                                    </a>
                                </li>
                                <li>
                                    <div class="text-center">
                                        <a>
                                            <strong>See All Alerts</strong>
                                            <i class="fa fa-angle-right"></i>
                                        </a>
                                    </div>
                                </li>
                            </ul>
                        </li>

                    </ul>
                </nav>
            </div>

        </div>
        <!-- /top navigation -->

        <!-- page content -->
        <div class="right_col" role="main">
            <div class="">

                <div class="page-title">
                    <div class="title_left">
                        <h3>
                            Inbox Design
                            <small>
                                Some examples to get you started
                            </small>
                        </h3>
                    </div>

                    <div class="title_right">
                        <div class="col-md-5 col-sm-5 col-xs-12 form-group pull-right top_search">
                            <div class="input-group">
                                <input type="text" class="form-control" placeholder="Search for...">
                  <span class="input-group-btn">
                            <button class="btn btn-default" type="button">Go!</button>
                        </span>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="clearfix"></div>

                <div class="row">

                    <div class="col-md-12">
                        <div class="x_panel">
                            <div class="x_title">
                                <h2> Inbox Design
                                    <small>User Mail</small>
                                </h2>
                                <ul class="nav navbar-right panel_toolbox">
                                    <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                                    </li>
                                    <li class="dropdown">
                                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><i class="fa fa-wrench"></i></a>
                                        <ul class="dropdown-menu" role="menu">
                                            <li><a href="#">Settings 1</a>
                                            </li>
                                            <li><a href="#">Settings 2</a>
                                            </li>
                                        </ul>
                                    </li>
                                    <li><a class="close-link"><i class="fa fa-close"></i></a>
                                    </li>
                                </ul>
                                <div class="clearfix"></div>
                            </div>
                            <div class="x_content">


                                <div class="row">

                                    <div class="col-sm-3 mail_list_column">

                                        <div class="mail_list">
                                            <div class="left">
                                                <i class="fa fa-circle"></i> <i class="fa fa-edit"></i>
                                            </div>
                                            <div class="right">
                                                <h3>Dennis Mugo
                                                    <small>3.00 PM</small>
                                                </h3>
                                                <p>Ut enim ad minim veniam, quis nostrud exercitation enim ad minim veniam, quis nostrud exercitation...</p>
                                            </div>
                                        </div>
                                        <div class="mail_list">
                                            <div class="left">
                                                <i class="fa fa-star"></i>
                                            </div>
                                            <div class="right">
                                                <h3>Jane Nobert
                                                    <small>4.09 PM</small>
                                                </h3>
                                                <p><span class="badge">To</span> Ut enim ad minim veniam, quis nostrud exercitation enim ad minim veniam, quis nostrud exercitation...</p>
                                            </div>
                                        </div>
                                        <div class="mail_list">
                                            <div class="left">
                                                <i class="fa fa-circle-o"></i><i class="fa fa-paperclip"></i>
                                            </div>
                                            <div class="right">
                                                <h3>Musimbi Anne
                                                    <small>4.09 PM</small>
                                                </h3>
                                                <p><span class="badge">CC</span> Ut enim ad minim veniam, quis nostrud exercitation enim ad minim veniam, quis nostrud exercitation...</p>
                                            </div>
                                        </div>
                                        <div class="mail_list">
                                            <div class="left">
                                                <i class="fa fa-paperclip"></i>
                                            </div>
                                            <div class="right">
                                                <h3>Jon Dibbs
                                                    <small>4.09 PM</small>
                                                </h3>
                                                <p>Ut enim ad minim veniam, quis nostrud exercitation enim ad minim veniam, quis nostrud exercitation...</p>
                                            </div>
                                        </div>
                                        <div class="mail_list">
                                            <div class="left">
                                                .
                                            </div>
                                            <div class="right">
                                                <h3>Debbis & Raymond
                                                    <small>4.09 PM</small>
                                                </h3>
                                                <p>Ut enim ad minim veniam, quis nostrud exercitation enim ad minim veniam, quis nostrud exercitation...</p>
                                            </div>
                                        </div>
                                        <div class="mail_list">
                                            <div class="left">
                                                .
                                            </div>
                                            <div class="right">
                                                <h3>Debbis & Raymond
                                                    <small>4.09 PM</small>
                                                </h3>
                                                <p>Ut enim ad minim veniam, quis nostrud exercitation enim ad minim veniam, quis nostrud exercitation...</p>
                                            </div>
                                        </div>
                                        <div class="mail_list">
                                            <div class="left">
                                                <i class="fa fa-circle"></i> <i class="fa fa-edit"></i>
                                            </div>
                                            <div class="right">
                                                <h3>Dennis Mugo
                                                    <small>3.00 PM</small>
                                                </h3>
                                                <p>Ut enim ad minim veniam, quis nostrud exercitation enim ad minim veniam, quis nostrud exercitation...</p>
                                            </div>
                                        </div>
                                        <div class="mail_list">
                                            <div class="left">
                                                <i class="fa fa-star"></i>
                                            </div>
                                            <div class="right">
                                                <h3>Jane Nobert
                                                    <small>4.09 PM</small>
                                                </h3>
                                                <p>Ut enim ad minim veniam, quis nostrud exercitation enim ad minim veniam, quis nostrud exercitation...</p>
                                            </div>
                                        </div>


                                    </div>
                                    <!-- /MAIL LIST -->


                                    <!-- CONTENT MAIL -->
                                    <div class="col-sm-9 mail_view">
                                        <div class="inbox-body">
                                            <div class="mail_heading row">
                                                <div class="col-md-8">
                                                    <div class="compose-btn">
                                                        <a class="btn btn-sm btn-primary" href="mail_compose.html"><i class="fa fa-reply"></i> Reply</a>
                                                        <button title="" data-placement="top" data-toggle="tooltip" type="button" data-original-title="Print" class="btn  btn-sm tooltips"><i class="fa fa-print"></i></button>
                                                        <button title="" data-placement="top" data-toggle="tooltip" data-original-title="Trash" class="btn btn-sm tooltips"><i class="fa fa-trash-o"></i>
                                                        </button>
                                                    </div>

                                                </div>
                                                <div class="col-md-4 text-right">
                                                    <p class="date"> 8:02 PM 12 FEB 2014</p>
                                                </div>
                                                <div class="col-md-12">
                                                    <h4> Donec vitae leo at sem lobortis porttitor eu consequat risus. Mauris sed congue orci. Donec ultrices faucibus rutrum.</h4>
                                                </div>
                                            </div>
                                            <div class="sender-info">
                                                <div class="row">
                                                    <div class="col-md-12">
                                                        <strong>Jon Doe</strong>
                                                        <span>(jon.doe@gmail.com)</span> to
                                                        <strong>me</strong>
                                                        <a class="sender-dropdown"><i class="fa fa-chevron-down"></i></a>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="view-mail">
                                                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut
                                                    aliquip ex ea commodo consequat.
                                                    Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim
                                                    id est laborum. </p>
                                                <p>Riusmod tempor incididunt ut labor erem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud
                                                    exercitation ullamco laboris
                                                    nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt
                                                    in culpa qui officia deserunt
                                                    mollit anim id est laborum.</p>
                                                <p>Modesed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure
                                                    dolor in reprehenderit in voluptate
                                                    velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
                                            </div>
                                            <div class="attachment">
                                                <p>
                                                    <span><i class="fa fa-paperclip"></i> 3 attachments — </span>
                                                    <a href="#">Download all attachments</a> |
                                                    <a href="#">View all images</a>
                                                </p>
                                                <ul>
                                                    <li>
                                                        <a href="#" class="atch-thumb">
                                                            <img src="images/1.png" alt="img"/>
                                                        </a>

                                                        <div class="file-name">
                                                            image-name.jpg
                                                        </div>
                                                        <span>12KB</span>


                                                        <div class="links">
                                                            <a href="#">View</a> -
                                                            <a href="#">Download</a>
                                                        </div>
                                                    </li>

                                                    <li>
                                                        <a href="#" class="atch-thumb">
                                                            <img src="images/1.png" alt="img"/>
                                                        </a>

                                                        <div class="file-name">
                                                            img_name.jpg
                                                        </div>
                                                        <span>40KB</span>

                                                        <div class="links">
                                                            <a href="#">View</a> -
                                                            <a href="#">Download</a>
                                                        </div>
                                                    </li>
                                                    <li>
                                                        <a href="#" class="atch-thumb">
                                                            <img src="images/1.png" alt="img"/>
                                                        </a>

                                                        <div class="file-name">
                                                            img_name.jpg
                                                        </div>
                                                        <span>30KB</span>

                                                        <div class="links">
                                                            <a href="#">View</a> -
                                                            <a href="#">Download</a>
                                                        </div>
                                                    </li>

                                                </ul>
                                            </div>
                                            <div class="compose-btn pull-left">
                                                <a class="btn btn-sm btn-primary" href="mail_compose.html"><i class="fa fa-reply"></i> Reply</a>
                                                <button class="btn btn-sm "><i class="fa fa-arrow-right"></i> Forward</button>
                                                <button title="" data-placement="top" data-toggle="tooltip" type="button" data-original-title="Print" class="btn  btn-sm tooltips"><i class="fa fa-print"></i></button>
                                                <button title="" data-placement="top" data-toggle="tooltip" data-original-title="Trash" class="btn btn-sm tooltips"><i class="fa fa-trash-o"></i>
                                                </button>
                                            </div>
                                        </div>

                                    </div>
                                    <!-- /CONTENT MAIL -->
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <!-- footer content -->
            <footer>
                <div class="pull-right">
                    Gentelella - Bootstrap Admin Template by <a href="https://colorlib.com">Colorlib</a>
                </div>
                <div class="clearfix"></div>
            </footer>
            <!-- /footer content -->

        </div>
        <!-- /page content -->
    </div>

</div>

<div id="custom_notifications" class="custom-notifications dsp_none">
    <ul class="list-unstyled notifications clearfix" data-tabbed_notifications="notif-group">
    </ul>
    <div class="clearfix"></div>
    <div id="notif-group" class="tabbed_notifications"></div>
</div>

<script src="js/bootstrap.min.js"></script>

<!-- bootstrap progress js -->
<script src="js/progressbar/bootstrap-progressbar.min.js"></script>
<!-- icheck -->
<script src="js/icheck/icheck.min.js"></script>
<!-- pace -->
<script src="js/pace/pace.min.js"></script>
<script src="js/custom.js"></script>

</body>

</html>
