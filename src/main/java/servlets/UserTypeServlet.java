package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import pojos.UserType;
import services.UserTypeService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public class UserTypeServlet extends HttpServlet {
    private UserTypeService service;
    private ObjectMapper mapper;

    @Override
    public void init() throws ServletException {
        System.out.println("UserType server initializing...");

        this.service = new UserTypeService();
        this.mapper = new ObjectMapper();

        System.out.println("UserType server initialized");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userTypeId = req.getParameter("id");

        if (userTypeId == null) {
            List<UserType> userTypeList = service.getAllUserType();
            String json = mapper.writeValueAsString(userTypeList);
            resp.getWriter().println(json);
        } else {
            UserType userType = service.getUserType(Integer.parseInt(userTypeId));
            String json = mapper.writeValueAsString(userType);
            resp.getWriter().println(json);
        }
        resp.setStatus(200);
        resp.setContentType("Application/Json; Charset=UTF-8");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        StringBuilder builder = new StringBuilder();
        BufferedReader reqReader = req.getReader();
        while (reqReader.ready()) {
            builder.append(reqReader.readLine());
        }

        String jsonString = builder.toString();
        UserType newUser = mapper.readValue(jsonString, UserType.class);

        UserType userType = service.saveUserType(newUser);
        String json = mapper.writeValueAsString(userType);
        resp.getWriter().println(json);
        resp.setStatus(201);
        resp.setContentType("Application/Json; Charset=UTF-8");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuilder jsonBuilder = new StringBuilder();
        BufferedReader reqReader = req.getReader();
        while (reqReader.ready()) {
            jsonBuilder.append(reqReader.readLine());
        }
        String jsonString = jsonBuilder.toString();
        System.out.println(jsonString);
        UserType userType = mapper.readValue(jsonString, UserType.class);
        System.out.println(userType);
        service.updateUserType(userType);

        resp.setStatus(200);
        resp.setContentType("Application/Json; Charset=UTF-8");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");

        service.deleteUserType(Integer.parseInt(id));

        resp.setStatus(200);
        resp.setContentType("Application/Json; Charset=UTF-8");
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
