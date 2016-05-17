<#-- @ftlvariable name="tableSettings" type="ru.ainurminibaev.db.model.TableSettings"-->
<#-- @ftlvariable name="tableViewName" type="java.lang.String"-->
<#-- @ftlvariable name="tableName" type="java.lang.String"-->
<#include "main_template.ftl"/>
<#macro body_macro>
<div class="">
    <div class="row">

        <div class="col-md-12 col-sm-12 col-xs-12">
            <div class="x_panel">
                <div class="x_title">
                    <h2> Table settings for ${tableViewName}[${tableName}]
                        <small>Set correct visibility, String part for references and correct name</small>
                    </h2>
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                        </li>
                        <li><a class="close-link"><i class="fa fa-close"></i></a>
                        </li>
                    </ul>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <p class="text-muted font-13 m-b-30">
                        Edit settings for the table
                    </p>
                    <div class="col-md-6" style="padding-left: 0px;">
                        <table id="table-outside-settings" class="table table-striped table-bordered dataTable no-footer dtr-inline">
                            <thead>
                            <tr role="row">
                                <th>Table view name</th>
                                <th>Is table visible?</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td><input id="table-view-name-input" type="text" value="${tableSettings.printable}"/></td>
                                <td><input id="table-visible-input" type="checkbox" name="visible-checkbox"<#if tableSettings.visible> checked="checked"</#if>/></td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                    <div class="clearfix"></div>
                    <p class="text-muted font-13 m-b-30">
                        Edit settings for columns of table and click <code>Save</code> button
                    </p>
                    <table id="columnSettingsTable" data-table="${tableSettings.tableName}" class="table table-striped table-bordered dataTable no-footer dtr-inline">
                        <thead>
                        <tr role="row">
                            <th>Column name</th>
                            <th>Column view name</th>
                            <th>Is visible?</th>
                            <th>Represent record?</th>
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
                            <#list tableSettings.columns as columnSettings>
                            <tr class="js-column-settings" data-column="${columnSettings.name}">
                                <td>${columnSettings.name}</td>
                                <td><input type="text" value="${columnSettings.printable}"/></td>
                                <td><input type="checkbox" name="visible-checkbox${columnSettings.name}"<#if columnSettings.visible> checked="checked"</#if>/></td>
                                <td><input type="checkbox" name="str-enable-checkbox${columnSettings.name}" <#if columnSettings.strEnable> checked="checked"</#if>/></td>
                            </tr>
                            </#list>
                        </tbody>
                    </table>
                    <div class="form-group">
                        <div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
                            <a href="/table/view/${tableName}">
                                <button class="btn btn-primary">Return to table</button>
                            </a>
                            <button id="save-btn" class="btn btn-success">Save</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</#macro>
<#macro footer_script_macro>
<script type="text/javascript">
    $(document).ready(function () {
        $("#save-btn").click(function (e) {
            e.preventDefault();
            var settings = {};
            settings.table_name = $("#columnSettingsTable").data('table');
            settings.printable = $("#table-view-name-input").val();
            settings.visible = $("#table-visible-input").is(':checked');
            settings.str_cols = [];
            settings.columns = [];
            $(".js-column-settings").each(function (i, val) {
                var columnSettings = {};
                columnSettings.name = $($(val).find('td')[0]).text();
                columnSettings.is_str_enable = $($(val).find('td input')[2]).is(':checked');
                columnSettings.visible = $($(val).find('td input')[1]).is(':checked');
                columnSettings.printable = $($(val).find('td input')[0]).val();
                settings.columns.push(columnSettings);
                if (columnSettings.is_str_enable) {
                    settings.str_cols.push(columnSettings.name);
                }
            });
            console.log(settings);
            $.ajax({
                type: "POST",
                url: "/table/settings/${tableName}",
                data: JSON.stringify(settings),
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                success: function (data) {
                    showSavedNotify();
                },
                error: function (data) {
                }
            });
        });
    });

    function showSavedNotify() {
        new PNotify({
            title: "System",
            type: "dark",
            text: "OK, table and column settings saved. Try to return to view page",
            animate_speed: 'fast',
            before_close: function (PNotify) {
                // You can access the notice's options with this. It is read only.
                //PNotify.options.text;

                // You can change the notice's options after the timer like this:
                PNotify.update({
                    title: PNotify.options.title + " - Enjoy your Stay",
                    before_close: null
                });
                PNotify.queueRemove();
                return false;
            }
        });

    }
</script>
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
"/js/notify/pnotify.core.js"
]
customHeaderStyles=[
"/js/datatables/jquery.dataTables.min.css",
"/js/datatables/buttons.bootstrap.min.css",
"/js/datatables/fixedHeader.bootstrap.min.css",
"/js/datatables/responsive.bootstrap.min.css",
"/js/datatables/scroller.bootstrap.min.css"
]
/>