<#-- @ftlvariable name="data" type="java.lang.Iterable<ru.ainurminibaev.db.dto.TableViewRow>"-->
<#-- @ftlvariable name="tableViewName" type="java.lang.String"-->
<#-- @ftlvariable name="tableName" type="java.lang.String"-->
<#include "main_template.ftl"/>
<#macro body_macro>
<div class="">

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
                    <#--<li class="dropdown">-->
                    <#--<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><i class="fa fa-wrench"></i></a>-->
                    <#--<ul class="dropdown-menu" role="menu">-->
                    <#--<li><a href="#">Settings 1</a>-->
                    <#--</li>-->
                    <#--<li><a href="#">Settings 2</a>-->
                    <#--</li>-->
                    <#--</ul>-->
                    <#--</li>-->
                        <li><a href="/table/settings/${tableName}"><i class="fa fa-wrench"></i></a></li>
                        <li><a class="close-link"><i class="fa fa-close"></i></a>
                        </li>
                    </ul>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <div class="page-title">
                        <div class="title_left">
                            <br>
                            <p class="text-muted font-13 m-b-30">
                                You can search, sort and make page requests for this table
                            </p>
                        </div>
                        <div class="title_right">
                            <div class="col-md-8 col-sm-5 col-xs-12 form-group pull-right top_search">
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
                    <div style="overflow: auto">
                        <table id="datatable1" class="table table-striped table-bordered dataTable no-footer dtr-inline">
                            <thead>
                            <tr role="row">
                                <#list headers as header>
                                    <#if header.visible>
                                        <th class="sorting" data-order="0" data-column="${header.val}">${header.str}</th>
                                    </#if>
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
                    </div>
                    <div class="clearfix"></div>
                    <div id="bpages-selector"></div>
                </div>
            </div>
        </div>
    </div>
</div>
</#macro>
<#macro footer_script_macro>
<script type="text/javascript">
    $(document).ready(function () {
        var lastData = null;
        var filterObj = {
            page: 0,
            query: "",
            size: 10,
            sort: "",
            order: 0
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

        function initSortControllers() {
            var headers = $("#datatable1 > thead > tr > th");
            $("#datatable1 > thead > tr > th").click(function (e) {
                e.preventDefault();
                var $this = $(this);
                var currentOrder = parseInt($this.attr('data-order'));
                var currentColumn = $this.data('column');
                console.log("Was: " + currentOrder + " " + currentColumn);
                headers.each(function (i, header) {
                    var $header = $(header);
                    $header.attr('data-order', 0);
                    $header.removeClass('sorting sorting_asc sorting_desc');
                    if ($this.data('column') != $header.data('column')) {
                        $header.addClass('sorting');
                    }
                });
                if (currentOrder == 0) {
                    currentOrder = 1;
                    $this.attr('data-order', 1);
                    $this.addClass('sorting_asc');
                } else if (currentOrder == 1) {
                    currentOrder = -1;
                    $this.attr('data-order', -1);
                    $this.addClass('sorting_desc');
                } else {
                    currentOrder = 0;
                    currentColumn = "";
                    $this.attr('data-order', 0);
                    $this.addClass('sorting');
                }
                filterObj.order = currentOrder;
                filterObj.sort = currentColumn;
                filterObj.page = 0;
                console.log("Set: " + currentOrder + " " + currentColumn);
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
//                    renderPagesSelector(data, filterObj.size);
                    var pages = Math.floor(data.recordsTotal / filterObj.size);
                    initPagesSelector(filterObj.page, pages)
                },
                error: function (error) {
                    console.error(error);
                }
            });
        }

        if (lastData == null) {
            loadData();
            initSortControllers();
        }

        function initPagesSelector(current, totalPages) {
            $("#bpages-selector").bs_pagination({
                currentPage: current + 1,
                rowsPerPage: 10,
                maxRowsPerPage: 10,
                totalPages: totalPages,
                totalRows: 0,

                visiblePageLinks: 6,

                showGoToPage: true,
                showRowsPerPage: true,
                showRowsInfo: true,
                showRowsDefaultInfo: true,

                directURL: false, // or a function with current page as argument
                disableTextSelectionInNavPane: true, // disable text selection and double click

                bootstrap_version: "3",

                // bootstrap 3
                containerClass: "well",

                mainWrapperClass: "row",

                navListContainerClass: "col-xs-12 col-sm-12 col-md-6",
                navListWrapperClass: "",
                navListClass: "pagination pagination_custom",
                navListActiveItemClass: "active",

                navGoToPageContainerClass: "col-xs-6 col-sm-4 col-md-2 row-space",
                navGoToPageIconClass: "glyphicon glyphicon-arrow-right",
                navGoToPageClass: "form-control small-input",

                navRowsPerPageContainerClass: "col-xs-6 col-sm-4 col-md-2 row-space",
                navRowsPerPageIconClass: "glyphicon glyphicon-th-list",
                navRowsPerPageClass: "form-control small-input",

                navInfoContainerClass: "col-xs-12 col-sm-4 col-md-2 row-space",
                navInfoClass: "",

                // element IDs
                nav_list_id_prefix: "nav_list_",
                nav_top_id_prefix: "top_",
                nav_prev_id_prefix: "prev_",
                nav_item_id_prefix: "nav_item_",
                nav_next_id_prefix: "next_",
                nav_last_id_prefix: "last_",

                nav_goto_page_id_prefix: "goto_page_",
                nav_rows_per_page_id_prefix: "rows_per_page_",
                nav_rows_info_id_prefix: "rows_info_",

                onChangePage: function (event, data) { // returns page_num and rows_per_page after a link has clicked
                    filterObj.page = data.currentPage - 1;
                    loadData();
                },
                onLoad: function () { // returns page_num and rows_per_page on plugin load
                }
            });
        }
    });

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
"/js/datatables/dataTables.scroller.min.js",
"/js/jquery.bs_pagination.min.js",
"/js/bs_pagination/localization/en.min.js"
]
customHeaderStyles=[
"/js/datatables/jquery.dataTables.min.css",
"/js/datatables/buttons.bootstrap.min.css",
"/js/datatables/fixedHeader.bootstrap.min.css",
"/js/datatables/responsive.bootstrap.min.css",
"/js/datatables/scroller.bootstrap.min.css",
"/css/jquery.bs_pagination.min.css"
]
/>