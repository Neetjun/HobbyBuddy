package io.github.hobbybuddy.user;

import io.github.hobbybuddy.domain.UserDTO;
import io.github.hobbybuddy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.Map;

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
        int result = userService.registerUser(user);

        // 성공 실패 확인
        System.out.println("result = " + result);
        ra.addFlashAttribute("regResult", result);

        return "redirect:/";
    }

    // 로그인
    @GetMapping("")
    public String login(UserDTO user, Model m, RedirectAttributes ra, HttpSession session)
    {
        // 입력받은 id, pw를 통해 유저 정보 입력
        user = userService.loginUser(user);

        System.out.println("user = " + user);

        // 제대로 유저 정보가 입력이 됐다면 세션에 id와 닉네임 저장
        if (user != null)
        {
            session.setAttribute("id", user.getId());
            session.setAttribute("nickname", user.getNickname());
        }
        else
        {
            ra.addFlashAttribute("loginCheck","fail");
        }

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

    // 아이디 중복검사
    @GetMapping("/dupCheck")
    @ResponseBody
    public String idDuplication(@RequestParam Map<String,String> id)
    {
        String result = "ok";

        System.out.println("id = " + id);

        if(userService.idDupCheck(id.get("id")).equals("1"))
            result = "duplicated";
        System.out.println("result = " + result);
        return result;
    }

}
