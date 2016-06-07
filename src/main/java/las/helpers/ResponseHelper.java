package las.helpers;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by M on 23-May-16.
 */
public class ResponseHelper {
    public static void sendJsonResponse(HttpServletResponse response, String json) {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try {
            if (json != null)
                response.getWriter().write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
