<%--
  Created by IntelliJ IDEA.
  User: Development
  Date: 18-May-16
  Time: 02:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<%@ page import="las.fill_db.DbHandler" %>
<%@ page import="java.text.MessageFormat" %>
<%@ page import="las.fill_db.DbColumn" %>
<%@ page import="las.fill_db.ColumnNamesMap" %>
<html>
<head>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
          integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">

    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css"
          integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r" crossorigin="anonymous">

    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"
            integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS"
            crossorigin="anonymous"></script>
</head>
<body>
<form action="insert_record" method="GET" class="form-horizontal" role="form">
    <%
        String tabName = request.getParameter("tabName");
        out.println("<h1>" + tabName + "</h1>");
        DbHandler dbHandler = new DbHandler();
        List<DbColumn> list = dbHandler.getDbColummnNamesWithTypes(tabName);
        for (DbColumn item : list) {
            out.println("<div class=\"form-group\">");
            out.println("<label for=\"" + item.getName() + "\" class=\"col-sm-2 control-label\">" + ColumnNamesMap.getColumnDisplayName(item.getName()) + "</label>");
            out.println("<div class=\"col-sm-10\">");
            out.println("<input type=\"" + item.getType() + "\" name=\" " + item.getName() + "\" class=\"form-control\" id=\"" + item.getName() + "\" placeholder=\"" + ColumnNamesMap.getColumnDisplayName(item.getName()) + "\">");
            out.println("   </div>\n" + "    </div>\n");
        }
        String hiddenExtraInputPattern = "<input name=''table'' value=''{0}'' style=''visibility: hidden;''><br>";
        out.print(MessageFormat.format(hiddenExtraInputPattern, tabName));
    %>


    <input type="submit" class="btn btn-default" style="margin-left: 235px;"/>
</form>
</body>
</html>
