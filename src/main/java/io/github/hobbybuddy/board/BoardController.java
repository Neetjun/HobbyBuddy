package io.github.hobbybuddy.board;

import io.github.hobbybuddy.Util.PagingHandler;
import io.github.hobbybuddy.Util.UploadHandler;
import io.github.hobbybuddy.domain.BoardDTO;
import io.github.hobbybuddy.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

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
    @Transactional
    public String postBoard(BoardDTO dto, HttpSession session, RedirectAttributes ra) throws IOException
    {

        if(session.getAttribute("id") == null)
        {
            ra.addFlashAttribute("unAuthErr","loginErr");
            return "redirect:/";
        }

        // dto 객체에 유저번호(작성자) 삽입
        Integer uno = (Integer)session.getAttribute("uno");
        dto.setB_uno(uno);

        List<BoardDTO> myBoard = boardService.myBoardList(uno);
        Integer bno = 0;

        // 게시판 DB 입력
        boardService.postBoard(dto);

        // 게시글에 첨부된 이미지 경로 DB에 저장
        int idx = 1;

        for(String fileName : dto.getImgList().split(","))
        {
            HashMap<String, Object> fileMap = new HashMap<String, Object>();

            bno = myBoard.get(0).getBno()+1;

            fileMap.put("fileName", fileName);
            fileMap.put("bno",bno);

            boardService.insertImg(fileMap);
            idx++;
        }

        String goTo = "/board/" + bno;

        System.out.println("이동 전 까진 잘 되는듯?");

        return "redirect:" + goTo;
    }

    // 게시글 읽기
    @GetMapping("/{bno}")
    public String readBoard(@PathVariable Integer bno, BoardDTO dto, Model m, HttpSession session)
    {
        dto = boardService.getBoard(dto.getBno());
        String id = (String)session.getAttribute("id");

        // 조회수 증가
        boardService.updateViewCnt(dto);

        // 비회원 상태라면
        id = id == null ? "" : id;

        // 접속중 id과 게시글의 작성자 id가 일치하면 true 아니면 false.
        // boolean isWriter = id.equals(boardService.isWriter(dto));

        // isWriter 쿼리문이 필요가 없지 않나??
        boolean isWriter = dto.getB_uno() == (Integer)session.getAttribute("uno");

        System.out.println("bno = " + bno);
        System.out.println("boardService.getImg(bno) = " + boardService.getImg(bno));

        m.addAttribute("type", "read");
        m.addAttribute("isWriter",isWriter);
        m.addAttribute("writer",boardService.getWriter(dto.getBno()));
        m.addAttribute("boardDTO", dto);
        m.addAttribute("imgList", boardService.getImg(bno));

        return "boardForm";
    }

    // 게시글 편집(수정 / 삭제)
    @PostMapping("/{bno}")
    public String handleBoard(@PathVariable Integer bno, String mod, BoardDTO dto, HttpSession session, RedirectAttributes ra)
    {
        if(session.getAttribute("id") == null)
        {
            ra.addFlashAttribute("unAuthErr","loginErr");
            return "redirect:/";
        }
        else if (!session.getAttribute("uno").equals(dto.getB_uno()))
        {
            ra.addFlashAttribute("unAuthErr","unoErr");
            return "redirect:/";
        }

        if(mod.equals("delete"))
        {
            boardService.deleteBoard(dto);

            return "redirect:/";
        }
        else
        {
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

        return objMap;
    }

    // 게시글 좋아요
    @Transactional
    @ResponseBody
    @PostMapping("/like")
    public HashMap<String,Object> likeContent(@RequestParam HashMap<String,String> likeMap)
    {
        int cntResult = boardService.likeCnt(likeMap);
        HashMap<String,Object> resultMap = new HashMap<String, Object>();

        if(cntResult == 0)
        {
            boardService.likeContent(likeMap);
            boardService.updateLikeCnt(likeMap);
            Integer likeCnt = boardService.getBoard(Integer.parseInt(likeMap.get("bno"))).getLike_count();

            resultMap.put("result","success");
            resultMap.put("likeCnt",likeCnt);

            return resultMap;
        }
        else
        {
            resultMap.put("result","fail");
            return resultMap;
        }
    }

    // 게시글 첨부파일 등록
    @ResponseBody
    @PostMapping("/upload")
    public ArrayList<String> uploadFile(@RequestParam("files") MultipartFile[] file) throws IOException
    {
        ArrayList<String> uploadList;

        UploadHandler uploadHandler = new UploadHandler();

        uploadList = uploadHandler.uploadFile(file);

        return uploadList;
    }
}
