package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import pojos.Reimbursement;
import services.ReimbursementService;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public class ReimbursementServlet extends HttpServlet {

    private ReimbursementService service;
    private ObjectMapper mapper;

    @Override
    public void init() throws ServletException {
        System.out.println("Reimbursement server initializing...");

        this.service = new ReimbursementService();
        this.mapper = new ObjectMapper();

        System.out.println("Reimbursement server initialized");
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String reimbursementId = req.getParameter("id");
        String reimbursementUserId = req.getParameter("user-id");
        String reimbursementStatusId = req.getParameter("status-id");


        if (reimbursementId == null && reimbursementUserId == null && reimbursementStatusId == null) {
            List<Reimbursement> reimbursementList = service.getAllReimbursement();
            String json = mapper.writeValueAsString(reimbursementList);
            resp.getWriter().println(json);
        } else if (reimbursementUserId != null) {
            List<Reimbursement> reimbursementList = service.getReimbursementByUser(Integer.parseInt(reimbursementUserId));
            String json = mapper.writeValueAsString(reimbursementList);
            resp.getWriter().println(json);
        }else if (reimbursementStatusId != null) {
            List<Reimbursement> reimbursementList = service.getReimbursementByStatusId(Integer.parseInt(reimbursementStatusId));
            String json = mapper.writeValueAsString(reimbursementList);
            resp.getWriter().println(json);
        } else {
            Reimbursement reimbursement = service.getReimbursement(Integer.parseInt(reimbursementId));
            String json = mapper.writeValueAsString(reimbursement);
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

        Reimbursement newReimbursement = mapper.readValue(jsonBuilder.toString(), Reimbursement.class);

        Reimbursement reimbursement = service.saveReimbursement(newReimbursement);

        resp.setStatus(201);
        resp.getWriter().println(mapper.writeValueAsString(reimbursement));
        resp.setContentType("Application/Json; Charset=UTF-8");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuilder builder = new StringBuilder();
        BufferedReader reader = req.getReader();



        while (reader.ready()) {
            builder.append(reader.readLine().toString());
        }
        System.out.println("----------------------------------");
        System.out.println(builder.toString());
        System.out.println("----------------------------------");

        Reimbursement reimbursement = mapper.readValue(builder.toString(), Reimbursement.class);

        System.out.println("----------------------------------");
        System.out.println(reimbursement.getAmount());
        System.out.println("----------------------------------");

        service.updateReimbursement(reimbursement);

        resp.setStatus(200);
        resp.setContentType("Application/Json; Charset=UTF-8");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String reimbursementId = req.getParameter("id");

        service.deleteReimbursement(Integer.parseInt(reimbursementId));

        resp.setStatus(200);
        resp.setContentType("Application/Json; Charset=UTF-8");

    }
}
