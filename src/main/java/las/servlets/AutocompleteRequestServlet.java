package las.servlets;

import com.google.gson.Gson;
import las.fill_db.DbHandler;
import las.fill_db.models.DbModel;
import las.fill_db.models.Klient;
import las.fill_db.models.Wycinka;
import las.helpers.ResponseHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/autocomplete")
public class AutocompleteRequestServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DbHandler dbHandler = new DbHandler();
        String tableName = request.getParameter("table");

        List<DbModel> selectResult = dbHandler.simpleSelect(tableName);
        List<String> responseList = new ArrayList<>();

        switch (tableName) {
            case "klient":
                String klientPattern = "{0}, {1} {2} - {3}";
                for (DbModel model : selectResult) {
                    responseList.add(MessageFormat.format(klientPattern, model.getValue(Klient.PESEL), model.getValue(Klient.IMIE), model.getValue(Klient.NAZWISKO), model.getValue(Klient.ADRES)));
                }
                break;
            case "wycinka":
                String wycinkaPattern = "{0} - {1}";
                for (DbModel model : selectResult) {
                    responseList.add(MessageFormat.format(wycinkaPattern, model.getValue(Wycinka.ID_WYCINKI), model.getValue(Wycinka.DATA_WYCINKI)));
                }
                break;
        }

        String responseJson = new Gson().toJson(responseList);

        ResponseHelper.sendJsonResponse(response, responseJson);
    }
}
