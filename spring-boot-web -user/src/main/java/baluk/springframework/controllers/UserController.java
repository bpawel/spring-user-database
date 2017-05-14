package baluk.springframework.controllers;

import baluk.springframework.model.User;
import baluk.springframework.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Paweł Baluk on 07/05/2017.
 */
@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("users", userService.listAllUsers());
        return "users";
    }

    @RequestMapping("user/{id}")
    public String showProduct(@PathVariable Integer id, Model model){
        model.addAttribute("user", userService.getUserById(id));
        return "usershow";
    }

    @RequestMapping("user/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
        model.addAttribute("user", userService.getUserById(id));
        return "userform";
    }

    @RequestMapping("user/new")
    public String newProduct(Model model){
        model.addAttribute("user", new User());
        return "userform";
    }

    @RequestMapping(value = "user", method = RequestMethod.POST)
    public String saveProduct(User user){
        userService.saveUser(user);

        return "redirect:/user/" + user.getId();
    }

    @RequestMapping("user/delete/{id}")
    public String delete(@PathVariable Integer id){
        userService.deleteUser(id);
        return "redirect:/users";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }
}
