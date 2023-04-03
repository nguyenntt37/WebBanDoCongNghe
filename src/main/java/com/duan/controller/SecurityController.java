package com.duan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SecurityController {
    /* Đường dẫn form login */
    @RequestMapping("/security/login/form")
    public String loginForm(Model model) {
        model.addAttribute("message", "Vui lòng đăng nhập !");
        return "security/login";
    }

    /* Đường dẫn địa chỉ thành công khi đăng nhập đưa ra thông báo */
    @RequestMapping("/security/login/success")
    public String loginSuccess(Model model) {
        return "redirect:/";
    }

    /* Đường dẫn địa chỉ lỗi khi đăng nhập đưa ra thông báo */
    @RequestMapping("/security/login/error")
    public String loginError(Model model) {
        model.addAttribute("message", "Sai thông tin đăng nhập !");
        return "security/login";
    }

    /* Đường dẫn địa chỉ lỗi khi đăng nhập đưa ra thông báo */
    @RequestMapping("/security/unauthoried")
    public String unauthoried(Model model) {
        model.addAttribute("message", "Bạn không có quyền truy xuất đến trang này !");
        return "security/login";
    }

    /* Đường dẫn địa chỉ đăng xuất thành công */
    @RequestMapping("/security/logoff/success")
    public String logoffSuccess(Model model) {
        model.addAttribute("message", "Bạn đã đăng xuất thành công !");
        return "redirect:/";
    }
}
