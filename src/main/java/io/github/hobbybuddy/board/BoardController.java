package io.github.hobbybuddy.board;

import io.github.hobbybuddy.domain.BoardDTO;
import io.github.hobbybuddy.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@RequestMapping("/board")
@Controller
public class BoardController {

    @Autowired
    BoardService boardService;

    // 게시판 폼 불러오기 (쓰기)
    @GetMapping("")
    public String getBoardForm()
    {
        return "boardForm";
    }

    // 게시판 등록
    @PostMapping("")
    public String postBoard(BoardDTO dto, Model m, HttpSession session)
    {
        // dto 객체에 유저번호(작성자) 삽입
        Integer uno = (Integer)session.getAttribute("uno");
        dto.setB_uno(uno);
        System.out.println("boardDTO = " + dto);

        // 게시판 DB 입력
        boardService.postBoard(dto);

        return "redirect:/";
    }

    // 게시판 읽기
    @GetMapping("/{bno}")
    public String readBoard(@PathVariable String bno, BoardDTO dto, Model m)
    {
        dto = boardService.getBoard(bno);

        m.addAttribute("type", "read");
        m.addAttribute("writer",boardService.getWriter(bno));
        m.addAttribute("boardDTO", dto);

        return "boardForm";
    }

    //게시판 목록
    @ResponseBody
    @GetMapping("/list")
    public List<BoardDTO> getBoardList(@RequestParam Map<String,String> paging)
    {
        System.out.println("작동확인");
        System.out.println("paging = " + paging);
        List<BoardDTO> list = boardService.getBoardList();

        return list;
    }
}
