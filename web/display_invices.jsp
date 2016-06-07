<%@ page import="las.fill_db.ColumnNamesMap" %>
<%@ page import="las.fill_db.DbHandler" %>
<%@ page import="las.fill_db.models.DbModel" %>
<%@ page import="java.text.MessageFormat" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="las.fill_db.InvoiceDbHandler" %>
<%@ page import="las.fill_db.models.Transakcja" %>
<%--
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
    <script src="//code.jquery.com/jquery-1.10.2.js"></script>
    <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
</HEAD>

<BODY>

<%
    String tableName = "transakcja";
    DbHandler dbHandler = new InvoiceDbHandler();
    List<DbModel> data;
    if (request != null && request.getParameterMap().size() > 1)
        data = dbHandler.select(request.getParameterMap());
    else
        data = dbHandler.simpleSelect(tableName);

    if (data != null)
        if (data.isEmpty()) {
            out.print("Niczego nie znaleziono");
            return;
        }

    List<String> keys = InvoiceDbHandler.getFields();
%>
<form action="display_invices.jsp" method="GET">
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
        %>

    </TR>
    <% for (DbModel model : data) {
        String trPattern = "<TR id=''{0}''>";
        out.print(MessageFormat.format(trPattern, String.valueOf(model.getValue(Transakcja.ID_TRANSAKCJI))));%>
    <%
        for (String key : keys)
            out.println("<td>" + model.getValue(key) + "</td>");
    %>
    </TR>
    <% } %>
</TABLE>
<script src="${pageContext.request.contextPath}/invice_handling.js"></script>
</BODY>
</HTML>