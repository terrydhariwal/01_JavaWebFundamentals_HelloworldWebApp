package io.nosqlyessql;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "HelloworldServlet", urlPatterns = {"/helloworld"}, initParams = {@WebInitParam(name = "ProductName", value = "Hello World Application")})
public class HelloworldServlet extends HttpServlet {

    String appLevelParam = "";
    String ProductName = "";

    @Override
    public void init() throws ServletException {
        // getInitParameter gets Servlet level parameters (in annotations or web.xml). Note values in web.xml override the ones in annotations
        ProductName = getInitParameter("ProductName");

        // getServletContext().getInitParameter(...) gets application level parameters (in web.xml nested inside <context-param>)
        appLevelParam =  getServletContext().getInitParameter("AppLevelParam");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //response.getWriter().write("Hello World");
        String name = request.getParameter("name");
        if(name != null && !name.equals("")) {
            String HTTP_METHOD = request.getMethod();
            String response_content_type = request.getParameter("response");
            switch (response_content_type) {
                case "xml":
                    response.setContentType("text/xml");
                    response.getWriter().write("<helloworldapp>");
                    response.getWriter().printf("<greeting>hello %s</greeting>", name);
                    response.getWriter().printf("<http_method>%s</http_method>", HTTP_METHOD);
                    response.getWriter().printf("<product_name>%s</product_name>", ProductName);
                    response.getWriter().printf("<app_parameter>%s</app_parameter>", appLevelParam);
                    response.getWriter().write("</helloworldapp>");
                    break;
                case "json":
                    response.setContentType("application/json");
                    response.getWriter().write("{");
                    response.getWriter().printf("\"greeting\":\"hello %s\",", name);
                    response.getWriter().printf("\"http_method\":\"%s\",", HTTP_METHOD);
                    response.getWriter().printf("\"product_name\":\"%s\",", ProductName);
                    response.getWriter().printf("\"app_parameter\":\"%s\"", appLevelParam);
                    response.getWriter().write("}");
                    break;
                default:
                    response.setContentType("text/html");
                    response.getWriter().printf("hello %s", name);
                    response.getWriter().write("<br/>");
                    response.getWriter().printf("http_method: %s", HTTP_METHOD);
                    response.getWriter().write("<br/>");
                    response.getWriter().printf("product_name: %s", ProductName);
                    response.getWriter().write("<br/>");
                    response.getWriter().printf("app_parameter: %s", appLevelParam);
                    break;
            }
        }
        else {
            response.sendRedirect("/index.jsp");
        }

    }
}
