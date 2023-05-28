package web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.configs.SuccessUserHandler;
import web.models.User;
import web.services.RoleService;
import web.services.UserService;

@Controller
public class AdminsController {

    private final SuccessUserHandler successUserHandler;
    private final UserService userService;

    private final RoleService roleService;

    @Autowired
    public AdminsController(SuccessUserHandler successUserHandler, UserService userService, RoleService roleService) {
        this.successUserHandler = successUserHandler;
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostMapping("/admin")
    public String saveUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/admin")
    public String getListUsers(Model model) {
        model.addAttribute("listRoles", roleService.getAllRoles());
        model.addAttribute("listUsers", userService.findAll());
        model.addAttribute("user", new User());
        model.addAttribute("isUser", successUserHandler.isUser());
        return "admin";
    }

    @RequestMapping("/updateInfo")
    public String updateUser(@RequestParam("userId") long id, Model model){
        model.addAttribute("user", userService.findById(id));
        model.addAttribute("listRoles", roleService.getAllRoles());
        model.addAttribute("listUsers", userService.findAll());
        model.addAttribute("isUser", successUserHandler.isUser());
        return "admin";
    }

    @RequestMapping("/deleteUser")
    public String deleteUser(@RequestParam("userId") long id){
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}