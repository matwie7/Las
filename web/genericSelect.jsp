<%@ page import="las.fill_db.ColumnNamesMap" %>
<%@ page import="las.fill_db.DbHandler" %>
<%@ page import="las.fill_db.models.DbModel" %>
<%@ page import="java.text.MessageFormat" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Development
  Date: 18-May-16
  Time: 12:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<HTML>
<HEAD>
    <TITLE>The tableName Database Table </TITLE>
    <link rel="stylesheet" type="text/css" href="http://codepen.io/alassetter/pen/cyrfB.css">

</HEAD>

<BODY>

<%
    String tableName = request.getParameter("tabName");
    DbHandler dbHandler = new DbHandler();
    List<DbModel> data = null;
    if (request.getParameterMap().size() == 1)
        data = dbHandler.simpleSelect(tableName);
    else
        data = dbHandler.select(request.getParameterMap());

    if (data.isEmpty()) {
        out.print("Niczego nie znaleziono");
        return;
    }
    List<String> keys = data.get(0).getKeysAndValues(true).getLeft();
%>
<form action="genericSelect.jsp" method="GET">
    <%
        for (String key : keys)
            out.print(ColumnNamesMap.getColumnDisplayName(key) + ": <input name=\"" + key + "\" type=''text''/>   ");

        String hiddenExtraInputPattern = "<input name=''tabName'' value=''{0}'' style=''visibility: hidden; width: 0px;''>";
        out.print(MessageFormat.format(hiddenExtraInputPattern, tableName));
    %>
    <input type="submit" value="Szukaj"/>
</form>
<TABLE class="table-fill">
    <TR>
        <%
            for (String key : keys)
                out.println("<th>" + ColumnNamesMap.getColumnDisplayName(key) + "</th>");
            keys = null;
        %>

    </TR>
    <% for (DbModel model : data) { %>
    <TR>
        <%
            for (String value : model.getKeysAndValues(true).getRight())
                out.println("<td>" + value + "</td>");
        %>
    </TR>
    <% } %>
</TABLE>
</BODY>
</HTML>