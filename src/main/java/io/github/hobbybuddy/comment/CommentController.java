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

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("")
    public String postComment(CommentDTO commentDTO, Model m, HttpServletRequest request)
    {
        // cno가 null이면 댓글등록, 아니면 삭제
        if(commentDTO.getCno() == null)
            commentService.postComment(commentDTO);
        else
            commentService.deleteComment(commentDTO);

        // 덧글을 달았던 게시글로 이동
        return "redirect:"+request.getHeader("referer");
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
