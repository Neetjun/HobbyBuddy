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
    private BoardService boardService;

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

        // 게시판 DB 입력
        boardService.postBoard(dto);

        return "redirect:/";
    }

    // 게시글 읽기
    @GetMapping("/{bno}")
    public String readBoard(@PathVariable Integer bno, BoardDTO dto, Model m, HttpSession session)
    {
        dto = boardService.getBoard(dto.getBno());
        String id = (String)session.getAttribute("id");

        // 비회원 상태라면
        id = id == null ? "" : id;

        // 접속중 id과 게시글의 작성자 id가 일치하면 true 아니면 false.
        // boolean isWriter = id.equals(boardService.isWriter(dto));

        // isWriter 쿼리문이 필요가 없지 않나??
           boolean isWriter = dto.getB_uno() == (Integer)session.getAttribute("uno");

        m.addAttribute("type", "read");
        m.addAttribute("isWriter",isWriter);
        m.addAttribute("writer",boardService.getWriter(dto.getBno()));
        m.addAttribute("boardDTO", dto);

        return "boardForm";
    }

    // 게시글 편집(수정 / 삭제)
    @PostMapping("/{bno}")
    public String handleBoard(@PathVariable Integer bno, String mod, BoardDTO dto)
    {
        if(mod.equals("delete"))
        {
            boardService.deleteBoard(dto);
            return "redirect:/";
        }
        else
        {
            System.out.println("dto = " + dto);
            boardService.updateBoard(dto);
            return "redirect:/board/"+bno;
        }

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
