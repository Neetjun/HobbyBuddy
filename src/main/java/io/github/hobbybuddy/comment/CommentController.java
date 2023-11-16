package io.github.hobbybuddy.comment;

import io.github.hobbybuddy.domain.CommentDTO;
import io.github.hobbybuddy.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("")
    @ResponseBody
    public String postComment(CommentDTO commentDTO, Model m, HttpServletRequest request, HttpSession session, RedirectAttributes ra)
    {
        if(session.getAttribute("id") == null)
        {
            ra.addFlashAttribute("unAuthErr","loginErr");
            return "loginErr";
        }
        else if (!session.getAttribute("uno").equals(commentDTO.getC_uno()))
        {
            ra.addFlashAttribute("unAuthErr","unoErr");
            return "unoErr";
        }

        // cno가 null이면 댓글등록, 아니면 삭제
        if(commentDTO.getCno() == null)
        {
            // tcno가 null이면 신규 덧글
            if(commentDTO.getTcno() == null)
                commentService.postComment(commentDTO);
            else // null이 아니면 대댓글
                commentService.postReply(commentDTO);
        }
        else
            commentService.deleteComment(commentDTO);

        return "success";
    }

    @GetMapping("")
    @ResponseBody
    public List<CommentDTO> getComment(Integer bno, Model m)
    {
        if(bno == null)
            bno = -1;

        List<CommentDTO> cmtList = commentService.getComment(bno);

        for(CommentDTO dto : cmtList)
            dto.setC_content(dto.getC_content().replaceAll("\r\n","<br/>"));

        return cmtList;
    }
}
