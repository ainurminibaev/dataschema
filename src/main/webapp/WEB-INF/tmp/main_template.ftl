<#macro main  customHeaderScripts=[] customHeaderStyles=[] customFooterScripts=[] customFooterStyles=[]>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Data Schema</title>

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

    <!-- Bootstrap core CSS -->

    <link href="/css/bootstrap.min.css" rel="stylesheet">

    <link href="/fonts/css/font-awesome.min.css" rel="stylesheet">
    <link href="/css/animate.min.css" rel="stylesheet">

    <!-- Custom styling plus plugins -->
    <link href="/css/custom.css" rel="stylesheet">
    <link href="/css/icheck/flat/green.css" rel="stylesheet">


    <script src="/js/jquery.min.js"></script>

    <#list customHeaderStyles as styles>
        <link href="${styles}" rel="stylesheet" type="text/css"/>
    </#list>

</head>


<body class="nav-md">

<div class="container body">


    <div class="main_container">

        <div class="col-md-3 left_col">
            <div class="left_col scroll-view">

                <div class="navbar nav_title" style="border: 0;">
                    <a href="index.html" class="site_title">
                        <img src="https://cdn3.iconfinder.com/data/icons/databases-2-2/512/data_cube-512.png" height="40" style="padding-left: 5px;">
                        <span>Data Schema</span>
                    </a>
                </div>
                <div class="clearfix"></div>

                <!-- menu prile quick info -->
                <div class="profile">
                    <div class="profile_pic">
                        <img src="/images/img.jpg" alt="..." class="img-circle profile_img">
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
                            <li><a><i class="fa fa-home"></i> Home </span></a>
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
                </div>
                <!-- /sidebar menu -->

                <!-- /menu footer buttons -->
                <div class="sidebar-footer hidden-small">
                    <a data-toggle="tooltip" data-placement="top" title="Logout" href="/logout">
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
                </nav>
            </div>

        </div>
        <!-- /top navigation -->

        <!-- page content -->
        <div class="right_col" role="main">

            <@body_macro/>
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

<script src="/js/bootstrap.min.js"></script>

<!-- bootstrap progress js -->
<script src="/js/progressbar/bootstrap-progressbar.min.js"></script>
<!-- icheck -->
<script src="/js/icheck/icheck.min.js"></script>
<!-- pace -->
<script src="/js/pace/pace.min.js"></script>
<script src="/js/custom.js"></script>

    <#list customFooterScripts as script>
    <script src="${script}"></script>
    </#list>

    <#if footer_script_macro?? && footer_script_macro?is_directive>
        <@footer_script_macro/>
    </#if>
</body>

</html>
</#macro>