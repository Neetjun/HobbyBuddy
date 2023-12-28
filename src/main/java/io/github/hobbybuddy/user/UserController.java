package io.github.hobbybuddy.user;

import io.github.hobbybuddy.domain.UserDTO;
import io.github.hobbybuddy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController
{
    @Autowired
    private UserService userService;

    // 회원가입
    @PostMapping("")
    public String registration(UserDTO user, Model m, String mod, RedirectAttributes ra, HttpSession session, HttpServletRequest request)
    {
        String goTo = request.getHeader("Referer");

        if(mod == null)
            mod = "";

        // PATCH 메서드 활용법 알아낼 때까지 임시 방편
        // 닉네임 수정
        if(mod.equals("modify"))
        {
            // 닉네임 수정시 수정 대상 유저 id값을 임의로 조작하는 경우 방지
            if(!session.getAttribute("id").equals(user.getId()))
            {
                ra.addFlashAttribute("unAuthErr","unoErr");
                return "redirect:/";
            }

            userService.modNickname(user);
            session.setAttribute("nickname",user.getNickname());

            return "redirect:" + goTo;
        }

        // ID 중복방지 로직을 악의적으로 통과하는 경우 방지
        if(userService.idDupCheck(user.getId()).equals("1"))
        {
            ra.addFlashAttribute("unAuthErr","unoErr");
            return "redirect:/";
        }

        // 서비스 계층에서 user insert문 불러와서 실행
        int result = userService.registerUser(user);

        // 성공 실패 확인
        ra.addFlashAttribute("regResult", result);

        return "redirect:" + goTo;
    }

    // 로그인
    @GetMapping("")
    public String login(UserDTO user, Model m, RedirectAttributes ra, HttpSession session, HttpServletRequest request)
    {
        // 입력받은 id, pw를 통해 유저 정보 입력
        user = userService.loginUser(user);

        // 제대로 유저 정보가 입력이 됐다면 세션에 id와 닉네임 저장
        if (user != null)
        {
            session.setAttribute("id", user.getId());
            session.setAttribute("nickname", user.getNickname());
            session.setAttribute("uno", user.getUno());
        }
        else
            ra.addFlashAttribute("loginCheck","fail");



        String goTo = request.getHeader("Referer");

        return "redirect:" + goTo;
    }

    // 로그아웃
    @GetMapping("/logout")
    public String logout(HttpSession session, HttpServletRequest request)
    {
        session.invalidate();
        String goTo = (String)request.getHeader("Referer");
        return "redirect:" + goTo;
    }

    // 아이디 중복검사
    @GetMapping("/dupCheck")
    @ResponseBody
    public String idDuplication(@RequestParam Map<String,String> id)
    {
        String result = "ok";

        if(userService.idDupCheck(id.get("id")).equals("1"))
            result = "duplicated";

        return result;
    }

    // 닉네임 수정
    @PatchMapping("")
    public String modNickname(Model m, UserDTO user)
    {
        return "";
    }
}
