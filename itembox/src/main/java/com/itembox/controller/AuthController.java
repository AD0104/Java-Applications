package com.itembox.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.RedirectView;

import com.itembox.business.mapper.UserInfoMapper;
import com.itembox.dao.UserInfoJpaRepository;
import com.itembox.dto.forms.SignupDto;
import com.itembox.dto.UserInfoDto;
import com.itembox.entities.UserInfo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;

@EnableWebMvc
@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    protected UserInfoJpaRepository userInfoJpaRepository;

    @Autowired
    protected UserInfoMapper userInfoMapper;

    @GetMapping("/login")
    public String getLoginTemplate(Model model) {
        return "login_template";
    }

    @GetMapping("/signup")
    public String getSingupTemplate(Model model) {
        SignupDto signupDto = new SignupDto();
        model.addAttribute(signupDto);
        return "signup";
    }

    @PostMapping("/signup")
    public String postSignupTemplate(
            Model model,
            @Validated @ModelAttribute SignupDto signupDto,
            BindingResult result) {

        if (!signupDto.getPassword().equals(signupDto.getConfirmPassword())) {
            result.addError(new FieldError("signupDto", "password", "Las contraseñas no coinciden"));
            result.addError(new FieldError("signupDto", "confirmPassword", "Las contraseñas no coinciden"));
        }

        UserInfoDto userInfoDto = userInfoMapper.toDto(userInfoJpaRepository.findByUserName(signupDto.getUserName()));
        if (userInfoDto != null) {
            result.addError(new FieldError("signupDto", "userName", "El nombre de usuario ya se encuentra en uso"));
        }

        if (!result.hasErrors()) {
            try {
                BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
                UserInfo userInfo = new UserInfo();
                userInfo.setUserName(signupDto.getUserName());
                userInfo.setUserPassword(bcrypt.encode(signupDto.getPassword()));
                userInfo.setRoles("CLIENT");
                userInfoJpaRepository.save(userInfo);
            } catch (Exception e) {
                result.addError(new FieldError("signupDto", "userName", e.getMessage()));
            }
        }
        return result.hasErrors() ? "signup" : "redirect:/menu";
    }

    @PostMapping("/logout")
    public String postLogout() {
        return "";
    }

}
