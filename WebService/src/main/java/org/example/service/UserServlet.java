package org.example.service;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.SessionFactory;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {

    private UserOperations userService;

    @Override
    public void init() throws ServletException {
        SessionFactory sessionFactory = DatabaseOperations.provideSessionFactory();
        userService = new UserOperations(sessionFactory);
    }
    /*@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");

        if (username != null) {
            User user = userService.getUserByUsername(username);
            if (user != null) {
                req.setAttribute("user", user);
                req.getRequestDispatcher("userDetail.jsp").forward(req, resp);
            } else {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND); // 404
            }
        } else {
            List<User> users = userService.getAllUsers();
            req.setAttribute("users", users);
            req.getRequestDispatcher("").forward(req, resp);
        }
    }*/

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("getUser".equals(action)) {
            String username = req.getParameter("username");
            User user = userService.getUserByUsername(username);
            req.setAttribute("user", user);
        }

        req.getRequestDispatcher("assignmentindex.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("insert".equals(action)) {
            User user = new User();
            user.setUsername(req.getParameter("username"));
            user.setPassword(req.getParameter("password"));
            user.setEmail(req.getParameter("email"));

            String birthdayStr = req.getParameter("birthday");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date birthday = sdf.parse(birthdayStr);
                user.setBirthday(birthday);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            user.setSex(Short.parseShort(req.getParameter("sex")));
            user.setEnabled(req.getParameter("enabled") != null);
            userService.insertUser(user);
            resp.setStatus(HttpServletResponse.SC_OK); // 200

        } else if ("update".equals(action)) {
            User user = new User();
            user.setUsername(req.getParameter("username"));
            user.setPassword(req.getParameter("password"));
            user.setEmail(req.getParameter("email"));
            try {
                userService.updateUser(user);
                resp.setStatus(HttpServletResponse.SC_OK); // 200
            } catch (Exception e) {
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); // 500
                e.printStackTrace();
            }
        } else if ("delete".equals(action)) {

            String username = req.getParameter("username");
            try {
                userService.deleteUser(username);
                resp.setStatus(HttpServletResponse.SC_OK); // 200
            } catch (Exception e) {
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); // 500
                e.printStackTrace();
            }
        } else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST); // 400
        }

        resp.sendRedirect("assignmentindex.jsp");
    }

    /*@Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action2 = req.getParameter("action");

        if ("usernameDelete".equals(action2)) {
            String username = req.getParameter("username");
            User user = userService.getUserByUsername(username);
            req.setAttribute("user", user);
        }

        req.getRequestDispatcher("assignmentindex.jsp").forward(req, resp);
    }*/

   /* @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        try {
        if ("delete".equals(action)) {
            String username = req.getParameter("username");
            try {
                userService.deleteUser(username);
                resp.setStatus(HttpServletResponse.SC_OK); // 200
            } catch (Exception e) {
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); // 500
                e.printStackTrace();
            }
        } else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);// 400

        }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }*/

}
