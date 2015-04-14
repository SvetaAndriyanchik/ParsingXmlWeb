package by.epam.parsing.controller;

import by.epam.parsing.model.businesslogic.builder.AbstractPlanesBuilder;
import by.epam.parsing.model.businesslogic.builder.PlaneBuilderFactory;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.lf5.viewer.configure.ConfigurationManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;


@WebServlet(name = "ParserManager", urlPatterns = {"/ParserManager"})
public class ParserManager extends javax.servlet.http.HttpServlet {

    private static Logger log = Logger.getLogger(ParserManager.class);

    @Override
    public void init() throws ServletException {
        String prefix = getServletContext().getRealPath("/");
        System.out.println(prefix);
        String filename = getInitParameter("logFileName");
        if (filename != null) {
            PropertyConfigurator.configure(prefix + filename);
        }
        log.info("init");
    }

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        performParsing(request, response);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        performParsing(request, response);
    }

    protected void performParsing(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws ServletException, IOException {
        String userInput = request.getParameter("input");

        PlaneBuilderFactory pFactory = new PlaneBuilderFactory();
        AbstractPlanesBuilder builder = pFactory.createPlaneBuilder(userInput);
        builder.buildSetPlanes(getServletContext().getRealPath("/data/plane.xml"));
        request.setAttribute("res", builder.getPlanes());
        request.getRequestDispatcher("jsp/result.jsp").forward(request, response);
    }


}
