<%--
  Created by IntelliJ IDEA.
  User: Development
  Date: 17-May-16
  Time: 22:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>

<form action="genericInsert.jsp" method="GET">
    Dodaj:
    <select name="tabName">
        <option value="klient">Klienta</option>
        <option value="pracownik">Pracownika</option>
        <option value="dostawca">Dostawcę</option>
        <option value="dostawa">Dostawę</option>
        <option value="obszar">Obszar</option>
        <option value="przynaleznosc">Przynależność (szkółki do obszaru)</option>
        <option value="szkolka">Szkółkę</option>
        <option value="transakcja">Transakcję</option>
        <option value="wycinka">Wycinkę</option>
        <option value="zerowisko">Żerowisko</option>
        <option value="zlecenie">Zlecenie</option>
        <option value="zwierze">Zwierze</option>
    </select>
    <input type="submit"/>
</form>

<form action="genericSelect.jsp" method="GET">
    Pokaż zawartość tabeli:
    <select name="tabName">
        <option value="klient">Klient</option>
        <option value="pracownik">Pracownik</option>
        <option value="dostawca">Dostawca</option>
        <option value="dostawa">Dostawa</option>
        <option value="obszar">Obszar</option>
        <option value="przynaleznosc">Przynależność</option>
        <option value="szkolka">Szkółka</option>
        <option value="transakcja">Transakcja</option>
        <option value="wycinka">Wycinka</option>
        <option value="zerowisko">Żerowisko</option>
        <option value="zlecenie">Zlecenie</option>
        <option value="zwierze">Zwierze</option>
    </select>
    <input type="submit"/>
</form>

<a href="sellWood.jsp">Sprzedaj drewno</a>  <a href="display_invices.jsp">Pokaż faktury</a>

</body>
</html>