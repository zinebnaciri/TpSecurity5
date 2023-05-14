package ma.projet.demo.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ma.projet.demo.config.EazyBankUsernamePwdAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @Autowired
    private EazyBankUsernamePwdAuthenticationProvider eazyBankUsernamePwdAuthenticationProvider;
    @RequestMapping("/login")
    public String LoginPage(){
        return "login";
    }
    @PostMapping("/login")
    public String LoginResponse(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password);
        try {
            eazyBankUsernamePwdAuthenticationProvider.authenticate(authToken);
        } catch (Exception e) {
            return "redirect:/login?error";
        }

        return "redirect:/myAccount";
    }
}
