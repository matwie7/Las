package las.servlets;

import las.helpers.RequestHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;


/**
 * Created by M on 17-May-16.
 */
@WebServlet("/insert_record")
public class InsertHandler extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map map = request.getParameterMap();
        String tabName = ((String[]) map.get("table"))[0];

        boolean success = RequestHelper.addToDatabase(map);

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html>");
        out.println("<head>");
        out.println("<title>Hola</title>");
        out.println("</head>");
        out.println("<body bgcolor=\"white\">");
        if (success)
            out.println("Nowy rekord zostal dodany pomyslnie do tabeli " + tabName);
        else
            out.println("Nie udało sie dodac nowego rekordu");
        out.println("</body>");
        out.println("</html>");
    }
}
