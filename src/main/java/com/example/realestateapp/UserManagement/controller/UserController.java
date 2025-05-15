//package com.example.realestateapp.UserManagement.controller;
//
//import com.example.realestateapp.PropertyManagement.service.PropertyService;
//import com.example.realestateapp.UserManagement.model.User;
//import com.example.realestateapp.UserManagement.service.UserService;
//import jakarta.servlet.http.HttpSession;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.stream.Collectors;
//
//@Controller
//public class UserController {
//
//    private final UserService userService = new UserService();
//    private final PropertyService propertyService = new PropertyService();
//
//    @GetMapping("/signup")
//    public String signupForm() {
//        return "signup";
//    }
//
//    @PostMapping("/signup")
//    public String signup(@RequestParam String username,
//                         @RequestParam String password,
//                         @RequestParam String gmail,
//                         Model model) {
//        try {
//            if (userService.userExists(username)) {
//                model.addAttribute("message", "Username already exists. Please choose another.");
//            } else if (userService.gmailExists(gmail)) {
//                model.addAttribute("message", "Email already registered. Please use another.");
//            } else {
//                userService.saveUser(new User(username, password, gmail));
//                return "redirect:/login";
//            }
//        } catch (IOException e) {
//            model.addAttribute("message", "Signup failed.");
//        }
//        return "signup";
//    }
//
//    @GetMapping("/login")
//    public String loginForm() {
//        return "login";
//    }
//
//    @Controller
//    public class PageController {
//        @GetMapping("/contact")
//        public String showContactPage() {
//            return "contact"; // Loads contact.html from templates/
//        }
//    }
//
//
//    @PostMapping("/login")
//    public String login(@RequestParam String username,
//                        @RequestParam String password,
//                        HttpSession session,
//                        Model model) {
//        try {
//            if ("admin".equals(username) && "admin123".equals(password)) {
//                session.setAttribute("username", username);
//                return "redirect:/admin-home";
//            }
//
//            if (userService.validateUser(username, password)) {
//                session.setAttribute("username", username);
//                return "redirect:/home";
//            } else {
//                model.addAttribute("message", "Invalid username or password.");
//            }
//        } catch (IOException e) {
//            model.addAttribute("message", "Login error.");
//        }
//        return "login";
//    }
//
//
//    @GetMapping("/admin-home")
//    public String adminHome(HttpSession session, Model model) {
//        String username = (String) session.getAttribute("username");
//        if (!"admin".equals(username)) return "redirect:/login";
//
//        model.addAttribute("username", username);
//
//        try {
//            int userCount = userService.getUserCount();
//            int propertyCount = propertyService.getPropertyCount();
//
//            model.addAttribute("userCount", userCount);
//            model.addAttribute("propertyCount", propertyCount);
//
//            // ✅ Get only last 5 users
//            var allUsers = userService.loadUsers();
//            var lastFiveUsers = allUsers.stream()
//                    .skip(Math.max(0, allUsers.size() - 5))
//                    .collect(Collectors.toList());
//
//            // ✅ Get only last 5 properties
//            var allProperties = propertyService.getAllProperties();
//            var lastFiveProperties = allProperties.stream()
//                    .skip(Math.max(0, allProperties.size() - 5))
//                    .collect(Collectors.toList());
//
//            model.addAttribute("userList", lastFiveUsers);
//            model.addAttribute("propertyList", lastFiveProperties);
//
//        } catch (IOException e) {
//            model.addAttribute("userCount", 0);
//            model.addAttribute("propertyCount", 0);
//            model.addAttribute("userList", new ArrayList<>());
//            model.addAttribute("propertyList", new ArrayList<>());
//        }
//
//        return "admin-home";
//    }
//
//
//    @GetMapping("/index")
//    public String indexPage() {
//        return "index";
//    }
//
//    @GetMapping("/home")
//    public String homePage(HttpSession session, Model model) {
//        String username = (String) session.getAttribute("username");
//        if (username == null) {
//            return "redirect:/login";
//        }
//        model.addAttribute("username", username);
//        return "home";
//    }
//
//    @GetMapping("/logout")
//    public String logout(HttpSession session) {
//        session.invalidate();
//        return "redirect:/login";
//    }
//
//    @GetMapping("/edit")
//    public String showEditPage(HttpSession session, Model model) throws IOException {
//        String username = (String) session.getAttribute("username");
//        if (username == null) return "redirect:/login";
//
//        User user = userService.findUserByUsername(username);
//        model.addAttribute("user", user);
//        return "edit";
//    }
//    @GetMapping("/admin_users")
//    public String viewRegisteredUsers(Model model, HttpSession session) throws IOException {
//        String username = (String) session.getAttribute("username");
//        if (!"admin".equals(username)) return "redirect:/login";
//
//        model.addAttribute("userList", userService.loadUsers());
//        return "admin_users";
//    }
//
//    @PostMapping("/admin_users/delete")
//    public String deleteUserFromAdmin(@RequestParam String username, HttpSession session) throws IOException {
//        String admin = (String) session.getAttribute("username");
//        if (!"admin".equals(admin)) return "redirect:/login";
//
//        userService.deleteUser(username);
//        return "redirect:/admin_users";
//    }
//
//
//    @PostMapping("/edit")
//    public String updateUser(@RequestParam String username,
//                             @RequestParam String password,
//                             @RequestParam String gmail,
//                             HttpSession session) throws IOException {
//        String currentUsername = (String) session.getAttribute("username");
//        if (currentUsername == null) return "redirect:/login";
//
//        userService.updateUser(currentUsername, new User(username, password, gmail));
//        session.setAttribute("username", username);
//        return "redirect:/home";
//    }
//
//    @GetMapping("/delete")
//    public String deleteAccount(HttpSession session) throws IOException {
//        String username = (String) session.getAttribute("username");
//        if (username != null) {
//            userService.deleteUser(username);
//            session.invalidate();
//        }
//        return "redirect:/login";
//    }
//}










//package com.example.realestateapp.UserManagement.controller;
//
//import com.example.realestateapp.PropertyManagement.service.PropertyService;
//import com.example.realestateapp.UserManagement.model.User;
//import com.example.realestateapp.UserManagement.service.UserService;
//import jakarta.servlet.http.HttpSession;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.stream.Collectors;
//
//@Controller
//public class UserController {
//
//    private final UserService userService = new UserService();
//    private final PropertyService propertyService = new PropertyService();
//
//    @GetMapping("/signup")
//    public String signupForm() {
//        return "signup";
//    }
//
//    @PostMapping("/signup")
//    public String signup(@RequestParam String username,
//                         @RequestParam String password,
//                         @RequestParam String gmail,
//                         Model model) {
//        try {
//            if (userService.userExists(username)) {
//                model.addAttribute("message", "Username already exists. Please choose another.");
//            } else if (userService.gmailExists(gmail)) {
//                model.addAttribute("message", "Email already registered. Please use another.");
//            } else {
//                userService.saveUser(new User(username, password, gmail));
//                return "redirect:/login";
//            }
//        } catch (IOException e) {
//            model.addAttribute("message", "Signup failed.");
//        }
//        return "signup";
//    }
//
//    @GetMapping("/login")
//    public String loginForm() {
//        return "login";
//    }
//
//    @PostMapping("/login")
//    public String login(@RequestParam String username,
//                        @RequestParam String password,
//                        HttpSession session,
//                        Model model) {
//        try {
//            if ("admin".equals(username) && "admin123".equals(password)) {
//                session.setAttribute("username", username);
//                return "redirect:/admin-home";
//            }
//
//            if (userService.validateUser(username, password)) {
//                session.setAttribute("username", username);
//                return "redirect:/home";
//            } else {
//                model.addAttribute("message", "Invalid username or password.");
//            }
//        } catch (IOException e) {
//            model.addAttribute("message", "Login error.");
//        }
//        return "login";
//    }
//
//    @GetMapping("/admin-home")
//    public String adminHome(HttpSession session, Model model) {
//        String username = (String) session.getAttribute("username");
//        if (!"admin".equals(username)) return "redirect:/login";
//
//        model.addAttribute("username", username);
//
//        try {
//            int userCount = userService.getUserCount();
//            int propertyCount = propertyService.getPropertyCount();
//
//            model.addAttribute("userCount", userCount);
//            model.addAttribute("propertyCount", propertyCount);
//
//            var allUsers = userService.loadUsers();
//            var lastFiveUsers = allUsers.stream()
//                    .skip(Math.max(0, allUsers.size() - 5))
//                    .collect(Collectors.toList());
//
//            var allProperties = propertyService.getAllProperties();
//            var lastFiveProperties = allProperties.stream()
//                    .skip(Math.max(0, allProperties.size() - 5))
//                    .collect(Collectors.toList());
//
//            model.addAttribute("userList", lastFiveUsers);
//            model.addAttribute("propertyList", lastFiveProperties);
//
//        } catch (IOException e) {
//            model.addAttribute("userCount", 0);
//            model.addAttribute("propertyCount", 0);
//            model.addAttribute("userList", new ArrayList<>());
//            model.addAttribute("propertyList", new ArrayList<>());
//        }
//
//        return "admin-home";
//    }
//
//    @GetMapping("/index")
//    public String indexPage() {
//        return "index";
//    }
//
//    @GetMapping("/home")
//    public String homePage(HttpSession session, Model model) {
//        String username = (String) session.getAttribute("username");
//        if (username == null) {
//            return "redirect:/login";
//        }
//        model.addAttribute("username", username);
//        return "home";
//    }
//
//    @GetMapping("/logout")
//    public String logout(HttpSession session) {
//        session.invalidate();
//        return "redirect:/login";
//    }
//
//    @GetMapping("/edit")
//    public String showEditPage(HttpSession session, Model model) throws IOException {
//        String username = (String) session.getAttribute("username");
//        if (username == null) return "redirect:/login";
//
//        User user = userService.findUserByUsername(username);
//        model.addAttribute("user", user);
//        return "edit";
//    }
//
//    @GetMapping("/admin_users")
//    public String viewRegisteredUsers(Model model, HttpSession session) throws IOException {
//        String username = (String) session.getAttribute("username");
//        if (!"admin".equals(username)) return "redirect:/login";
//
//        model.addAttribute("userList", userService.loadUsers());
//        return "admin_users";
//    }
//
//    @PostMapping("/admin_users/delete")
//    public String deleteUserFromAdmin(@RequestParam String username, HttpSession session) throws IOException {
//        String admin = (String) session.getAttribute("username");
//        if (!"admin".equals(admin)) return "redirect:/login";
//
//        userService.deleteUser(username);
//        return "redirect:/admin_users";
//    }
//
//    @PostMapping("/edit")
//    public String updateUser(@RequestParam String username,
//                             @RequestParam String password,
//                             @RequestParam String gmail,
//                             HttpSession session) throws IOException {
//        String currentUsername = (String) session.getAttribute("username");
//        if (currentUsername == null) return "redirect:/login";
//
//        userService.updateUser(currentUsername, new User(username, password, gmail));
//        session.setAttribute("username", username);
//        return "redirect:/home";
//    }
//
//    @GetMapping("/delete")
//    public String deleteAccount(HttpSession session) throws IOException {
//        String username = (String) session.getAttribute("username");
//        if (username != null) {
//            userService.deleteUser(username);
//            session.invalidate();
//        }
//        return "redirect:/login";
//    }
//
//    @GetMapping("/contact")
//    public String showContactPage(HttpSession session, Model model) {
//        String username = (String) session.getAttribute("username");
//        model.addAttribute("username", username);
//        return "contact"; // Must match templates/contact.html
//    }
//
//
//
//}
















//
//package com.example.realestateapp.UserManagement.controller;
//
//import com.example.realestateapp.PropertyManagement.service.PropertyService;
//import com.example.realestateapp.ReviewManagement.model.Review;
//import com.example.realestateapp.ReviewManagement.service.ReviewService;
//import com.example.realestateapp.UserManagement.model.User;
//import com.example.realestateapp.UserManagement.service.UserService;
//import jakarta.servlet.http.HttpSession;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import com.example.realestateapp.MailManagement.MailService;
//import org.springframework.web.bind.annotation.*;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Controller
//public class UserController {
//
//    private final UserService userService = new UserService();
//    private final PropertyService propertyService = new PropertyService();
//
//    private final ReviewService reviewService = new ReviewService(); // ✅ Added review service
//
//    @GetMapping("/signup")
//    public String signupForm() {
//        return "signup";
//    }
//
//    @PostMapping("/signup")
//    public String signup(@RequestParam String username,
//                         @RequestParam String password,
//                         @RequestParam String gmail,
//                         Model model) {
//        try {
//            if (userService.userExists(username)) {
//                model.addAttribute("message", "Username already exists. Please choose another.");
//            } else if (userService.gmailExists(gmail)) {
//                model.addAttribute("message", "Email already registered. Please use another.");
//            } else {
//                userService.saveUser(new User(username, password, gmail));
//                return "redirect:/login";
//            }
//        } catch (IOException e) {
//            model.addAttribute("message", "Signup failed.");
//        }
//        return "signup";
//    }
//
//    @GetMapping("/login")
//    public String loginForm() {
//        return "login";
//    }
//
//    @PostMapping("/login")
//    public String login(@RequestParam String username,
//                        @RequestParam String password,
//                        HttpSession session,
//                        Model model) {
//        try {
//            if ("admin".equals(username) && "admin123".equals(password)) {
//                session.setAttribute("username", username);
//                return "redirect:/admin-home";
//            }
//
//            if (userService.validateUser(username, password)) {
//                session.setAttribute("username", username);
//                return "redirect:/home";
//            } else {
//                model.addAttribute("message", "Invalid username or password.");
//            }
//        } catch (IOException e) {
//            model.addAttribute("message", "Login error.");
//        }
//        return "login";
//    }
//
//    @GetMapping("/admin-home")
//    public String adminHome(HttpSession session, Model model) {
//        String username = (String) session.getAttribute("username");
//        if (!"admin".equals(username)) return "redirect:/login";
//
//        model.addAttribute("username", username);
//
//        try {
//            int userCount = userService.getUserCount();
//            int propertyCount = propertyService.getPropertyCount();
//
//            model.addAttribute("userCount", userCount);
//            model.addAttribute("propertyCount", propertyCount);
//
//            var allUsers = userService.loadUsers();
//            var lastFiveUsers = allUsers.stream()
//                    .skip(Math.max(0, allUsers.size() - 5))
//                    .collect(Collectors.toList());
//
//            var allProperties = propertyService.getAllProperties();
//            var lastFiveProperties = allProperties.stream()
//                    .skip(Math.max(0, allProperties.size() - 5))
//                    .collect(Collectors.toList());
//
//            model.addAttribute("userList", lastFiveUsers);
//            model.addAttribute("propertyList", lastFiveProperties);
//
//        } catch (IOException e) {
//            model.addAttribute("userCount", 0);
//            model.addAttribute("propertyCount", 0);
//            model.addAttribute("userList", new ArrayList<>());
//            model.addAttribute("propertyList", new ArrayList<>());
//        }
//
//        return "admin-home";
//    }
//
//    @GetMapping("/index")
//    public String indexPage() {
//        return "index";
//    }
//
//    @GetMapping("/home")
//    public String homePage(HttpSession session, Model model) {
//        String username = (String) session.getAttribute("username");
//        if (username == null) {
//            return "redirect:/login";
//        }
//        model.addAttribute("username", username);
//
//        try {
//            List<Review> reviews = reviewService.loadReviews(); // ✅ Load reviews
//            model.addAttribute("reviews", reviews);
//        } catch (IOException e) {
//            model.addAttribute("reviews", List.of());
//        }
//
//        return "home";
//    }
//
//    @GetMapping("/logout")
//    public String logout(HttpSession session) {
//        session.invalidate();
//        return "redirect:/login";
//    }
//
//    @GetMapping("/edit")
//    public String showEditPage(HttpSession session, Model model) throws IOException {
//        String username = (String) session.getAttribute("username");
//        if (username == null) return "redirect:/login";
//
//        User user = userService.findUserByUsername(username);
//        model.addAttribute("user", user);
//        return "edit";
//    }
//
//    @GetMapping("/admin_users")
//    public String viewRegisteredUsers(Model model, HttpSession session) throws IOException {
//        String username = (String) session.getAttribute("username");
//        if (!"admin".equals(username)) return "redirect:/login";
//
//        model.addAttribute("userList", userService.loadUsers());
//        return "admin_users";
//    }
//
//    @PostMapping("/admin_users/delete")
//    public String deleteUserFromAdmin(@RequestParam String username, HttpSession session) throws IOException {
//        String admin = (String) session.getAttribute("username");
//        if (!"admin".equals(admin)) return "redirect:/login";
//
//        userService.deleteUser(username);
//        return "redirect:/admin_users";
//    }
//
//    @PostMapping("/edit")
//    public String updateUser(@RequestParam String username,
//                             @RequestParam String password,
//                             @RequestParam String gmail,
//                             HttpSession session) throws IOException {
//        String currentUsername = (String) session.getAttribute("username");
//        if (currentUsername == null) return "redirect:/login";
//
//        userService.updateUser(currentUsername, new User(username, password, gmail));
//        session.setAttribute("username", username);
//        return "redirect:/home";
//    }
//
//    @GetMapping("/delete")
//    public String deleteAccount(HttpSession session) throws IOException {
//        String username = (String) session.getAttribute("username");
//        if (username != null) {
//            userService.deleteUser(username);
//            session.invalidate();
//        }
//        return "redirect:/login";
//    }
//
//    @GetMapping("/contact")
//    public String showContactPage(HttpSession session, Model model) {
//        String username = (String) session.getAttribute("username");
//        model.addAttribute("username", username);
//        return "contact";
//    }
//}





















package com.example.realestateapp.UserManagement.controller;

import com.example.realestateapp.PropertyManagement.service.PropertyService;
import com.example.realestateapp.UserManagement.model.User;
import com.example.realestateapp.UserManagement.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class UserController {

    private final UserService userService = new UserService();
    private final PropertyService propertyService = new PropertyService();

    @GetMapping("/signup")
    public String signupForm() {
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(@RequestParam String username,
                         @RequestParam String password,
                         @RequestParam String gmail,
                         Model model) {
        try {
            if (userService.userExists(username)) {
                model.addAttribute("message", "Username already exists. Please choose another.");
            } else if (userService.gmailExists(gmail)) {
                model.addAttribute("message", "Email already registered. Please use another.");
            }
        } catch (IOException e) {
            model.addAttribute("message", "Signup failed.");
        }
        return "signup";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        Model model) {
        try {
            if ("admin".equals(username) && "admin123".equals(password)) {
                session.setAttribute("username", username);
                return "redirect:/admin-home";
            }

            if (userService.validateUser(username, password)) {
                session.setAttribute("username", username);
                return "redirect:/home";
            } else {
                model.addAttribute("message", "Invalid username or password.");
            }
        } catch (IOException e) {
            model.addAttribute("message", "Login error.");
        }
        return "login";
    }

    @GetMapping("/admin-home")
    public String adminHome(HttpSession session, Model model) {
        String username = (String) session.getAttribute("username");
        if (!"admin".equals(username)) return "redirect:/login";

        model.addAttribute("username", username);

        try {
            int userCount = userService.getUserCount();
            int propertyCount = propertyService.getPropertyCount();

            model.addAttribute("userCount", userCount);
            model.addAttribute("propertyCount", propertyCount);

            var allUsers = userService.loadUsers();
            var lastFiveUsers = allUsers.stream()
                    .skip(Math.max(0, allUsers.size() - 5))
                    .collect(Collectors.toList());

            var allProperties = propertyService.getAllProperties();
            var lastFiveProperties = allProperties.stream()
                    .skip(Math.max(0, allProperties.size() - 5))
                    .collect(Collectors.toList());

            model.addAttribute("userList", lastFiveUsers);
            model.addAttribute("propertyList", lastFiveProperties);

        } catch (IOException e) {
            model.addAttribute("userCount", 0);
            model.addAttribute("propertyCount", 0);
            model.addAttribute("userList", new ArrayList<>());
            model.addAttribute("propertyList", new ArrayList<>());
        }

        return "admin-home";
    }

    @GetMapping("/index")
    public String indexPage() {
        return "index";
    }

    @GetMapping("/home")
    public String homePage(HttpSession session, Model model) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            return "redirect:/login";
        }
        model.addAttribute("username", username);



        return "home";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    @GetMapping("/edit")
    public String showEditPage(HttpSession session, Model model) throws IOException {
        String username = (String) session.getAttribute("username");
        if (username == null) return "redirect:/login";

        User user = userService.findUserByUsername(username);
        model.addAttribute("user", user);
        return "edit";
    }

    @GetMapping("/admin_users")
    public String viewRegisteredUsers(Model model, HttpSession session) throws IOException {
        String username = (String) session.getAttribute("username");
        if (!"admin".equals(username)) return "redirect:/login";

        model.addAttribute("userList", userService.loadUsers());
        return "admin_users";
    }

    @PostMapping("/admin_users/delete")
    public String deleteUserFromAdmin(@RequestParam String username, HttpSession session) throws IOException {
        String admin = (String) session.getAttribute("username");
        if (!"admin".equals(admin)) return "redirect:/login";

        userService.deleteUser(username);
        return "redirect:/admin_users";
    }

    @PostMapping("/edit")
    public String updateUser(@RequestParam String username,
                             @RequestParam String password,
                             @RequestParam String gmail,
                             HttpSession session) throws IOException {
        String currentUsername = (String) session.getAttribute("username");
        if (currentUsername == null) return "redirect:/login";

        userService.updateUser(currentUsername, new User(username, password, gmail));
        session.setAttribute("username", username);
        return "redirect:/home";
    }

    @GetMapping("/delete")
    public String deleteAccount(HttpSession session) throws IOException {
        String username = (String) session.getAttribute("username");
        if (username != null) {
            userService.deleteUser(username);
            session.invalidate();
        }
        return "redirect:/login";
    }

    @GetMapping("/contact")
    public String showContactPage(HttpSession session, Model model) {
        String username = (String) session.getAttribute("username");
        model.addAttribute("username", username);
        return "contact";
    }
}
