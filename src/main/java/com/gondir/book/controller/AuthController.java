package com.gondir.book.controller;

import com.gondir.book.dto.UserDto;
import com.gondir.book.entity.Role;
import com.gondir.book.entity.User;
import com.gondir.book.service.RoleService;
import com.gondir.book.service.UserService;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AuthController {

    private final UserService userService;
    private final RoleService roleService;

    public AuthController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    // Redirects logged-in users
    private static String redirectByPrincipal() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getPrincipal() != "anonymousUser") return "redirect:users";
        return null;
    }

    @GetMapping("/")
    public String home() {
        String redirect = redirectByPrincipal();
        return (redirect == null ? "index" : redirect);
    }

    @GetMapping("/login")
    public String loginForm() {
        String redirect = redirectByPrincipal();
        return (redirect == null ? "login" : redirect);
    }

    // handler method to handle user registration request
    @GetMapping("register")
    public String showRegistrationForm(Model model) {
        String redirect = redirectByPrincipal();
        if (redirect != null) return redirect;

        UserDto user = new UserDto();
        model.addAttribute("user", user);

        return "register";
    }

    // handler method to handle register user form submit request
    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDto user, BindingResult result, Model model) {
        User existing = userService.findByEmail(user.getEmail());
        if (existing != null) {
            result.rejectValue("email", null, "There is already an account registered with that email");
        }
        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "register";
        }
        userService.saveUser(user);
        return "redirect:/register?success";
    }

    @GetMapping("/userDto")
    public String listRegisteredUserDto(Model model) {
        List<UserDto> users = userService.findAllUserDto();
        model.addAttribute("users", users);
        for (UserDto user : users) {
            System.out.println(user);
        }
        return "users";
    }

    @GetMapping("/users")
    public String listRegisteredUsers(Model model) {
        List<User> users = userService.findAllUser();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/roles")
    public String listRoles(Model model) {
        List<Role> roles = roleService.findAllRole();
        model.addAttribute("roles", roles);
        return "roles";
    }

    @GetMapping("/deleteUser/{userId}")
    public String deleteUser(@PathVariable Long userId) {
        userService.deleteUserById(userId);
        return "redirect:/users?success"; // Redirect to the user list page after deletion
    }

    @GetMapping("/deleteRole/{roleId}")
    public String deleteRole(@PathVariable Long roleId) {
        roleService.deleteRoleById(roleId);
        return "redirect:/roles?success"; // Redirect to the role list page after deletion
    }

    @GetMapping("/book")
    public String getBook() {
        return "game/book";
    }
}
