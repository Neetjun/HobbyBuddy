package io.github.hobbybuddy.user;

import io.github.hobbybuddy.domain.UserDTO;
import io.github.hobbybuddy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController
{
    @Autowired
    UserService userService;

    // 회원가입
    @PostMapping("")
    public String registration(UserDTO user, Model m, RedirectAttributes ra)
    {
        // 서비스 계층에서 user insert문 불러와서 실행
        int result = userService.registUser(user);

        // 성공 실패 확인
        System.out.println("result = " + result);
        ra.addFlashAttribute("regResult", result);

        return "redirect:/";
    }

    // 로그인
    @GetMapping("")
    public String login(UserDTO user, Model m, RedirectAttributes ra, HttpSession session)
    {
        user = userService.loginUser(user);

        session.setAttribute("id", user.getId());
        session.setAttribute("nickname", user.getNickname());

        System.out.println("user = " + user);

        return "redirect:/";
    }

    // 로그아웃
    @GetMapping("/logout")
    public String logout(HttpSession session)
    {
        session.invalidate();
        return "redirect:/";
    }

}
