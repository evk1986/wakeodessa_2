package com.kravchenko.wakeodessa.controllers;

import com.kravchenko.wakeodessa.domains.GenericResponse;
import com.kravchenko.wakeodessa.domains.Order;
import com.kravchenko.wakeodessa.domains.User;
import com.kravchenko.wakeodessa.exceptions.UserNotFoundException;
import com.kravchenko.wakeodessa.repositories.VerificationTokenRepository;
import com.kravchenko.wakeodessa.services.OrderService;
import com.kravchenko.wakeodessa.services.UserSecurityService;
import com.kravchenko.wakeodessa.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

/**
 * Created by Egor on 30.12.2016.
 */
@Controller
public class UserController {

    @Autowired
    UserService uds;

    @Autowired
    OrderService os;

    @Autowired
    VerificationTokenRepository passwordTokenRepository;

    @Autowired
    private MessageSource messages;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private Environment env;


    @Autowired
    UserSecurityService securityService;

    @RequestMapping(value = "/user/user-profile", method = RequestMethod.GET)
    public String getUserProfile(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        /*User user =
                (User) SecurityContextHolder.getContext()
                        .getAuthentication().getPrincipal();*/
        String name = auth.getName(); //get logged in username
        System.out.println(name);
        User user = uds.findByLogin(name);
        List<Order> orders = new ArrayList<>();

        if (user != null) {
            orders = os.findAllByOrderByUserByLogin(user.getLogin());
            model.addAttribute("orders", orders);
        }
       // System.out.println(orders.toString());
        model.addAttribute("user", user);
        return "user_profile";
    }

    @RequestMapping(value = "/user/user_profile", method = RequestMethod.POST)
    public String updateUserProfile(
            @ModelAttribute(value = "user") User currentUser, Model model) {
        System.out.println("currentUser: " + currentUser.toString());
        User user = uds.findByLogin(currentUser.getLogin());
        System.out.println("user: " + user.toString());
        user.setName(currentUser.getName());
        user.setEmail(currentUser.getEmail());
        user.setDateOfBirth(currentUser.getDateOfBirth());
        user.setGender(currentUser.getGender());
        user.setHomeAdress(currentUser.getHomeAdress());
        user.setSurname(currentUser.getSurname());
        user.setTelNumber(currentUser.getTelNumber());
        System.out.println(user.toString());
        uds.save(user);
        List<Order> orders = os.findAllByOrderByUserByLogin(user.getLogin());
        model.addAttribute("orders", orders);
        model.addAttribute("user", user);
        System.out.println(user.getName());
        return "user_profile";
    }

    @RequestMapping(value = "/user/user_edit/{login}", method = RequestMethod.GET)
    public String getUserEditForm(@PathVariable("login") String login, Model model) {
        User user = uds.findByLogin(login);
        model.addAttribute("user", user);
        model.addAttribute("login", login);
        return "user_edit";
    }

    @RequestMapping(value = "/forget_password", method = RequestMethod.GET)
    public String resetPassword(HttpServletRequest request,
                                Model model) {

        return "forgetPassword";

    }


    @RequestMapping(value = "/user/resetPassword", method = RequestMethod.POST)
    @ResponseBody
    public GenericResponse resetPassword(HttpServletRequest request,
                                         @RequestParam("email") String userEmail) {
        User user = uds.findByEmail(userEmail);
        System.out.println(user.toString());
        if (user == null) {
            throw new UserNotFoundException();
        }
        String token = UUID.randomUUID().toString();
        uds.createPasswordResetTokenForUser(user, token);
        mailSender.send(constructResetTokenEmail(getAppUrl(request),
                request.getLocale(), token, user));
        return new GenericResponse(
                messages.getMessage("message.resetPasswordEmail", null,
                        request.getLocale()));
    }

    @RequestMapping(value = "/user/changePassword", method = RequestMethod.GET)
    public String showChangePasswordPage(Locale locale, Model model,
                                         @RequestParam("id") Long id,
                                         @RequestParam("token") String token
    ) {
        System.out.println("id " + id + "token " + token.toString());
        String result = securityService.validatePasswordResetToken(id, token);

        if (result != null) {
            model.addAttribute("message",
                    messages.getMessage("auth.message." + result, null, locale));
            return "redirect:/login?lang=" + locale.getLanguage();
        }
        User user = uds.findById(id);
        model.addAttribute("user", user);
        return "updatePassword";
    }


    @RequestMapping(value = "/user/savePassword", method = RequestMethod.POST)
    @ResponseBody
    public GenericResponse savePassword(Locale locale,
                                        @RequestParam(value = "newPassword") String newPassword) {
        User user =
                (User) SecurityContextHolder.getContext()
                        .getAuthentication().getPrincipal();

        uds.changeUserPassword(user, newPassword);
        return new GenericResponse(
                messages.getMessage("message.resetPasswordSuc", null, locale));
    }


    private SimpleMailMessage constructResetTokenEmail(
            String contextPath, Locale locale, String token, User user) {
        String url = contextPath + "/user/changePassword?id=" +
                user.getId() + "&token=" + token;
        System.out.println(url.toString());
      /*  String message = messages.getMessage("message.resetPassword",
                null, locale);*/
        String message = "Вы получили етот e-mail, так кто-то совершил " +
                "действие по восстановления пароля на сайте woc.od.ua. " +
                "Если єто не вы, никак не реагируйте на ето письмо. " +
                "Для восстановления пароля перейдите по ссылке:";
        return constructEmail("Reset Password", message + " \r\n" + url, user);
    }

    private SimpleMailMessage constructEmail(String subject, String body,
                                             User user) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setSubject(subject);
        email.setText(body);
        email.setTo(user.getEmail());
        email.setFrom("syntetich@gmail.com");
        return email;
    }

    private String getAppUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }

}
