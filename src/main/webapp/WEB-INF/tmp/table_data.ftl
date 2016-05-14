<#-- @ftlvariable name="data" type="java.lang.Iterable<ru.ainurminibaev.db.dto.TableViewRow>"-->
<#-- @ftlvariable name="tableViewName" type="java.lang.String"-->
<#-- @ftlvariable name="tableName" type="java.lang.String"-->
<#include "main_template.ftl"/>
<#macro body_macro>
<div class="">
    <div class="page-title">
        <div class="title_left">
            <h3>
                Users
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

        <div class="col-md-12 col-sm-12 col-xs-12">
            <div class="x_panel">
                <div class="x_title">
                    <h2> ${tableViewName}[${tableName}]
                        <small>Table Data</small>
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
                    <p class="text-muted font-13 m-b-30">
                        You can search, sort and make page requests for this table
                    </p>
                    <table id="datatable1" class="table table-striped table-bordered">
                        <thead>
                        <tr>
                            <#list headers as header>
                                <th>${header}</th>
                            </#list>
                        </tr>
                        </thead>
                        <tbody>
                        <#--<#list data as row>-->
                            <#--<tr>-->
                                <#--<#list row.columns as column>-->
                                    <#--<td>${column.strVal!""}</td>-->
                                <#--</#list>-->
                            <#--</tr>-->
                            <#--</#list>-->
                        </tbody>
                    </table>
                    <div class="btn-group" id="pages-selector">
                        <button class="btn btn-success" type="button">5</button>
                        <button class="btn btn-success" type="button">6</button>
                        <button class="btn btn-success" type="button">7</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</#macro>
<#macro footer_script_macro>
<script>
    var handleDataTableButtons = function () {
                "use strict";
                0 !== $("#datatable-buttons").length && $("#datatable-buttons").DataTable({
                    dom: "Bfrtip",
                    buttons: [{
                        extend: "copy",
                        className: "btn-sm"
                    }, {
                        extend: "csv",
                        className: "btn-sm"
                    }, {
                        extend: "excel",
                        className: "btn-sm"
                    }, {
                        extend: "pdf",
                        className: "btn-sm"
                    }, {
                        extend: "print",
                        className: "btn-sm"
                    }],
                    responsive: !0
                })
            },
            TableManageButtons = function () {
                "use strict";
                return {
                    init: function () {
                        handleDataTableButtons()
                    }
                }
            }();
</script>
<script type="text/javascript">
    $(document).ready(function () {
        var lastData = null;
        var filterObj = {
            page: 0,
            query: "",
            size: 10
        };

        function generateRows(data) {
            var content = '';
            var rows = data.data;
            for (var rowI in rows) {
                var row = rows[rowI];
                content += '<tr>';
                for (var colI in row.columns) {
                    var col = row.columns[colI];
                    content += '<td>' + col.strVal + '</td>';
                }
                content += '</tr>';
            }
            return content;
        }

        function renderPagesSelector(data, size) {
            var pages = data.recordsTotal / size;
            if (pages < 1) {
                pages = 1;
            } else if (data.recordsTotal % size != 0) {
                pages += 1;
            }
            var content = '';
            for (var i = 1; i <= pages; i++) {
                content += "\<button class=\"btn btn-success js-page-select\" type=\"button\"\>" + i + "\</button>";
            }
            $("#pages-selector").html(content);
            $(".js-page-select").click(function (e) {
                filterObj.page = parseInt($(this).text()) - 1;
                loadData();
            });
        }

        function loadData() {
            $.ajax({
                type: "GET",
                url: '${tableName}/data?' + $.param(filterObj),
                success: function (data) {
                    lastData = data;
                    $("#datatable1 > tbody ").html(generateRows(data));
                    renderPagesSelector(data, filterObj.size);
                },
                error: function (error) {
                    console.error(error);
                }
            });
        }

        if (lastData == null) {
            loadData();
        }
//        $('#datatable').dataTable();
    });
    TableManageButtons.init();
</script>
</#macro>

<@main
customFooterScripts=[
"/js/datatables/jquery.dataTables.min.js",
"/js/datatables/dataTables.bootstrap.js",
"/js/datatables/dataTables.buttons.min.js",
"/js/datatables/buttons.bootstrap.min.js",
"/js/datatables/jszip.min.js",
"/js/datatables/pdfmake.min.js",
"/js/datatables/vfs_fonts.js",
"/js/datatables/buttons.html5.min.js",
"/js/datatables/buttons.print.min.js",
"/js/datatables/dataTables.fixedHeader.min.js",
"/js/datatables/dataTables.keyTable.min.js",
"/js/datatables/dataTables.responsive.min.js",
"/js/datatables/responsive.bootstrap.min.js",
"/js/datatables/dataTables.scroller.min.js"
]
customHeaderStyles=[
"/js/datatables/jquery.dataTables.min.css",
"/js/datatables/buttons.bootstrap.min.css",
"/js/datatables/fixedHeader.bootstrap.min.css",
"/js/datatables/responsive.bootstrap.min.css",
"/js/datatables/scroller.bootstrap.min.css"
]
/>