package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import pojos.User;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public class UserServlet extends HttpServlet {
    private UserService service;
    private ObjectMapper mapper;

    @Override
    public void init() throws ServletException {
        System.out.println("User server initializing...");

        this.service = new UserService();
        this.mapper = new ObjectMapper();

        System.out.println("User server initialized");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("id");

        if (userId == null) {
            List<User> userList = service.getAllUser();
            String json = mapper.writeValueAsString(userList);
            resp.getWriter().println(json);
        } else {
            User user = service.getUser(Integer.parseInt(userId));
            String json = mapper.writeValueAsString(user);
            resp.getWriter().println(json);
        }
        resp.setStatus(200);
        resp.setContentType("Application/Json; Charset=UTF-8");


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuilder jsonBuilder = new StringBuilder();
        BufferedReader reqReader = req.getReader();
        while (reqReader.ready()) {
            jsonBuilder.append(reqReader.readLine());
        }

        String jsonString = jsonBuilder.toString();
        User newUser = mapper.readValue(jsonString, User.class);

        User user = service.saveUser(newUser);
        String json = mapper.writeValueAsString(user);
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
        User user = mapper.readValue(jsonString, User.class);

        service.updateUser(user);

        resp.setStatus(200);
        resp.setContentType("Application/Json; Charset=UTF-8");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("user-id");

        service.deleteUser(Integer.parseInt(userId));

        resp.setStatus(200);
        resp.setContentType("Application/Json; Charset=UTF-8");
    }

    @Override
    public void destroy() {
        super.destroy();
    }


}
