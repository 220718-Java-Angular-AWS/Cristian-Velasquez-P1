package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import pojos.ReimbursementStatus;
import services.ReimbursementStatusService;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public class ReimbursementStatusServlet extends HttpServlet {
    private ReimbursementStatusService service;
    private ObjectMapper mapper;

    @Override
    public void init() throws ServletException {
        System.out.println("ReimbursementStatus server initializing...");

        this.service = new ReimbursementStatusService();
        this.mapper = new ObjectMapper();

        System.out.println("ReimbursementStatus server initialized");
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String reimbursementStatusId = req.getParameter("id");

        if (reimbursementStatusId == null) {
            List<ReimbursementStatus> reimbursementStatusList = service.getAllReimbursementStatus();
            String json = mapper.writeValueAsString(reimbursementStatusList);
            resp.getWriter().println(json);
        } else {
            ReimbursementStatus reimbursementStatus = service.getReimbursementStatus(Integer.parseInt(reimbursementStatusId));
            String json = mapper.writeValueAsString(reimbursementStatus);
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

        ReimbursementStatus newReimbursementStatus = mapper.readValue(jsonBuilder.toString(), ReimbursementStatus.class);

        ReimbursementStatus reimbursementStatus = service.saveReimbursementStatus(newReimbursementStatus);

        resp.setStatus(201);
        resp.getWriter().println(mapper.writeValueAsString(reimbursementStatus));
        resp.setContentType("Application/Json; Charset=UTF-8");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuilder builder = new StringBuilder();
        BufferedReader reader = req.getReader();

        while (reader.ready()) {
            builder.append(reader.readLine().toString());
        }

        ReimbursementStatus reimbursementStatus = mapper.readValue(builder.toString(), ReimbursementStatus.class);
        service.updateReimbursementStatus(reimbursementStatus);

        resp.setStatus(200);
        resp.setContentType("Application/Json; Charset=UTF-8");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String reimbursementStatusId = req.getParameter("id");

        service.deleteReimbursementStatus(Integer.parseInt(reimbursementStatusId));

        resp.setStatus(200);
        resp.setContentType("Application/Json; Charset=UTF-8");
    }
}
