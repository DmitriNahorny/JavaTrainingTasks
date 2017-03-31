package by.nahorny.web.servlet;

/**
 * Created by Dmitri_Nahorny on 3/23/2017.
 */
import by.nahorny.web.parser.TariffsParser;
import by.nahorny.web.tariff.Tariff;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Part;

@WebServlet("/result")
@MultipartConfig
public class TimeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {processRequest(request, response);
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String selectedParser = request.getParameter("parser");
        Part filePart = request.getPart("file");

        String filePath = this.getServletContext().getRealPath("") + "resource\\tariffs.xml";
        filePart.write(filePath);
        Set<Tariff> importedTariffs = null;

        switch (selectedParser.toUpperCase()) {
            case "DOM":
                TariffsParser parserInst = new TariffsParser();
                parserInst.buildSetTariffs(filePath);
                importedTariffs = parserInst.getTariffs();
                break;
            case "SAX":
                break;
            case "STAX":
                break;
            default: request.setAttribute("error", "Incorrect XML parser type");
        }

        request.setAttribute("text_content", importedTariffs);
        request.getRequestDispatcher("pages/timeaction.jsp").forward(request, response);
    }
}
