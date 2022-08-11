package com.project.spring.new_main_project.controller;


import com.project.spring.new_main_project.dto.UserDTO;
import com.project.spring.new_main_project.cart.MainCart;
import com.project.spring.new_main_project.exception.UserNotFoundException;
import com.project.spring.new_main_project.model.Role;
import com.project.spring.new_main_project.model.User;
import com.project.spring.new_main_project.repository.RoleRepository;
import com.project.spring.new_main_project.repository.UserRepository;
import com.project.spring.new_main_project.service.UserService;
import com.project.spring.new_main_project.util.Util;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/login")
@Controller
public class LoginController {

    private PasswordEncoder bCryptPasswordEncoder;
    private UserRepository userRepository;
    private UserService userService;
    private RoleRepository roleRepository;
    private JavaMailSender mailSender;


    @Autowired
    public LoginController(PasswordEncoder bCryptPasswordEncoder, UserRepository userRepository, UserService userService, RoleRepository roleRepository, JavaMailSender mailSender) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRepository = userRepository;
        this.userService = userService;
        this.roleRepository = roleRepository;
        this.mailSender = mailSender;
    }

    @RequestMapping
    public String login(){
        MainCart.cart.clear();
        return "account";
    }


    @GetMapping("/register")
    public String registerGet(Model model)
    {
        model.addAttribute("user", new UserDTO());
        return "account";
    }

    @PostMapping("/register")
    public String registerPost(@ModelAttribute User userModel, HttpServletRequest request) throws ServletException{
        String password = userModel.getPassword();
        userModel.setPassword(bCryptPasswordEncoder.encode(password));
        List<Role> roles = new ArrayList<>();
        roles.add(roleRepository.findById(1).get());
        roles.add(roleRepository.findById(2).get());
        userModel.setRoles(roles);
        userRepository.save(userModel);
        request.login(userModel.getEmail(), password);
        return "redirect:/";
    }



    @GetMapping("/forgot_password")
    public String forgotPasswordForm(Model model){
        model.addAttribute("userDTO", new UserDTO());
        return "forgot_password";
    }

    @PostMapping("/forgot_password")
    public String processForgotPasswordForm(HttpServletRequest request, Model model) {
        String email = request.getParameter("email"); // getParameter берет с шаблона name
        String token = RandomString.make(45);

        try {
            userService.updateResetPassword(token, email);

            String resetPasswordLink = Util.getSiteURL(request) + "/login/reset_password?token=" + token;

            System.out.println(resetPasswordLink);

            sendEmail(email, resetPasswordLink);


            }
        catch(MessagingException | UnsupportedEncodingException exception) {
            model.addAttribute("error", exception.getMessage());
        }
        catch (UserNotFoundException userNotFoundException){
            model.addAttribute("error", "User with this email was not found");
        }
        return "forgot_password";
    }

    private void sendEmail(String email, String resetPasswordLink) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message);

        messageHelper.setFrom("I-Store@gmail.com", "I-Store Support");
        messageHelper.setTo(email);


        String subject = "Here is the link to reset your password";

        String content = "<p>Hello,</p>"
                + "<p>You have requested to reset your password.</p>"
                + "<p>Click the link below to change your password:</p>"
                + "<p><a href=\"" + resetPasswordLink + "\">Change my password</a></p>"
                + "<br>"
                + "<p>Ignore this email if you do remember your password, "
                + "or you have not made the request.</p>";

        messageHelper.setSubject(subject);
        messageHelper.setText(content, true);

        mailSender.send(message);
    }


    @GetMapping("/reset_password")
    public String showResetPassword(@Param(value = "token") String token, Model model){
        User user = userService.get(token);
        model.addAttribute("token", token);

        if (user == null) {
            model.addAttribute("message", "Invalid Token");
            return "message";
        }
        return "reset_password_form";
    }

    @PostMapping("/reset_password")
    public String processResetPassword(HttpServletRequest request, Model model){
        String token = request.getParameter("token");
        String password = request.getParameter("password");

        User user = userService.get(token);
        if (user == null) {
            model.addAttribute("message", "Invalid Token");
        } else{
            userService.updatePassword(user, password);
            model.addAttribute("message", "You have successfully changed your password");
        }
        return "message";

    }
}





