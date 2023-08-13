
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Role;
import models.User;
import services.RoleService;
import services.UserService;

public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        
        UserService userService = new UserService();
        RoleService roleService = new RoleService();
        String action = request.getParameter("action");

        try {
            List<User> usersArray = userService.getAll(2);
          
            if (usersArray == null) {

                request.setAttribute("error", "No users found");
            }

            request.setAttribute("users", usersArray);

        } catch (Exception ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex); // ???
        }

        if (action != null && action.equals("edit_link")) {

            String userEmail = request.getParameter("users_email");
            request.setAttribute("edit_table", true);
            request.setAttribute("email", userEmail); 
            getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
            return;
        }


        getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
        return;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

      
        UserService userService = new UserService();
        String email = request.getParameter("email");
        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        String password = request.getParameter("password");
        String role = request.getParameter("roles");
        String editButton = request.getParameter(" submit_edits_button");
     
  
         User newUser = new User(email, firstName, lastName, password);
         
        if ("Edit User".equalsIgnoreCase(editButton)) {

            String userName = request.getParameter("user_name");

                if (role.equalsIgnoreCase("option 1")) {
               newUser = new User(email, firstName, lastName, password);
        }
        else if (role.equalsIgnoreCase("option 2")) {
              newUser = new User(email, firstName, lastName, password);
        }
            try {
                userService.update(newUser);
                   List<User> usersArray = userService.getAll(2);
                request.setAttribute("users", usersArray); 
            } catch (Exception ex) {
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            request.setAttribute("message", "updated user: " + userName);
            getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
            return;

        } else {

            try {
                userService.insert(newUser.getRole() , newUser);
                   List<User> usersArray = userService.getAll(2); 
                request.setAttribute("users", usersArray); 
            } catch (Exception ex) {
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.setAttribute("message", "added user: " + firstName + " " + lastName);
            getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
            return;
        }

    }

}
