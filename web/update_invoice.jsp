<%@ page import="las.fill_db.InvoiceDbHandler" %>
<%@ page import="las.fill_db.models.Transakcja" %>
<%@ page import="java.text.MessageFormat" %><%--
  Created by IntelliJ IDEA.
  User: Development
  Date: 31-May-16
  Time: 19:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="http://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
    <script src="//code.jquery.com/jquery-1.10.2.js"></script>
    <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
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
    <%--<link rel="stylesheet" href="/resources/demos/style.css">--%>
</head>
<body>
<h1>Sprzedaż drewna</h1>
<%
    int id = Integer.parseInt(request.getParameter("id"));
    InvoiceDbHandler dbHandle = new InvoiceDbHandler();
    Transakcja transakcja = dbHandle.getInvoice(id);
%>

<form class="form-horizontal">
    <div class="form-group" id="klientGroup">
        <label for="klient" class="col-sm-2 control-label">Klient:</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" id="klient"
                   value="<%out.print(transakcja.getValue(Transakcja.PESEL_KLIENTA) + ",");%>">
        </div>
        <button type="button" class="btn btn-link" id="add_client" style="margin-left: 235px;">Dodaj nowego klienta
        </button>
    </div>
    <div class="form-group" id="wycinkaGroup">
        <label for="wycinka" class="col-sm-2 control-label">Wycinka:</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" id="wycinka"
                   value="<%out.print(transakcja.getValue(Transakcja.ID_WYCINKI) + " -");%>">
        </div>
        <button type="button" class="btn btn-link" id="add_treesFelling" style="margin-left: 235px;">Dodaj nową
            wycinke
        </button>
    </div>

    <div class="form-group" id="ilosc_drewnaGroup">
        <label for="ilosc_drewna" class="col-sm-2 control-label">Ilość drewna (m&sup3;):</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" id="ilosc_drewna"
                   value="<%out.print(transakcja.getValue(Transakcja.ILOSC_DREWNA));%>">
        </div>
    </div>

    <div class="form-group" id="cena_za_m3Group">
        <label for="cena_za_m3" class="col-sm-2 control-label">Cena za m&sup3;:</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" id="cena_za_m3"
                   value="<%out.print(transakcja.getValue(Transakcja.CENA_ZA_M3));%>">
        </div>
    </div>

    <div class="form-group">
        <label for="suma_brutto" class="col-sm-2 control-label">Suma brutto:</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" id="suma_brutto"
                   value="<%out.print(transakcja.getValue(Transakcja.SUMA_BRUTTO));%>" disabled>
        </div>
    </div>
    <%
        String hiddenExtraInputPattern = "<input id=''id'' value=''{0}'' style=''visibility: hidden;''><br>";
        out.print(MessageFormat.format(hiddenExtraInputPattern, String.valueOf(id)));
    %>
</form>

<button type="button" class="btn btn-primary" id="save" style="margin-left: 235px;">Aktualizuj</button>

<div id="myModal" class="modal fade" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Wprowadź prawidłowe dane</h4>
            </div>
            <div class="modal-body">

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Zamkij</button>
            </div>
        </div>

    </div>
</div>

<script src="${pageContext.request.contextPath}/validation.js"></script>
<script src="${pageContext.request.contextPath}/invoice_update_handling.js"></script>
</body>
</html>
