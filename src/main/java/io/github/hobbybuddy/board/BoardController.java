package io.github.hobbybuddy.board;

import io.github.hobbybuddy.Util.PagingHandler;
import io.github.hobbybuddy.domain.BoardDTO;
import io.github.hobbybuddy.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
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
    public String postBoard(BoardDTO dto, HttpSession session)
    {
        // dto 객체에 유저번호(작성자) 삽입
        Integer uno = (Integer)session.getAttribute("uno");
        dto.setB_uno(uno);
        dto.setB_content(dto.getB_content().replaceAll("<div><br></div>","\n")
                .replaceAll("^\\s+|\\s+$",""));

        // 게시판 DB 입력
        boardService.postBoard(dto);

        int bno = 0;

        List<BoardDTO> myBoard = boardService.myBoardList(uno);

        // 내 작성글의 마지막 글(방금 작성한글)의 bno 얻기
        bno = myBoard.get(0).getBno();

        String goTo = "/board/" + bno;

        return "redirect:" + goTo;
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

        dto.setB_content(dto.getB_content().replaceAll("\n","<div><br></div>"));

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
            dto.setB_content(dto.getB_content().replaceAll("<div><br></div>","\n")
                    .replaceAll("^\\s+|\\s+$",""));

            boardService.updateBoard(dto);

            return "redirect:/board/"+bno;
        }

    }

    //게시판 목록
    @ResponseBody
    @GetMapping("/list")
    public HashMap<String,Object> getBoardList(@RequestParam HashMap<String,String> paging)
    {
        List<BoardDTO> list = boardService.getBoardList(paging);
        paging.put("boardCnt",String.valueOf(list.size()));

        PagingHandler ph = new PagingHandler(paging);
        HashMap<String, Object> objMap = new HashMap<String, Object>();

        objMap.put("list",list);
        objMap.put("ph", ph);

        System.out.println("ph = " + ph);

        return objMap;
    }

    // 게시글 좋아요
    @ResponseBody
    @PostMapping("/like")
    public String likeContent(@RequestParam HashMap<String,String> likeMap)
    {
        int cntResult = boardService.likeCnt(likeMap);

        if(cntResult == 0)
        {
            boardService.likeContent(likeMap);
            boardService.updateLikeCnt(likeMap);
            return "success";
        }
        else
            return "fail";
    }
}
