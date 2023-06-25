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

import java.util.List;

@Controller
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("")
    public String postComment(CommentDTO commentDTO, Model m)
    {

        System.out.println("commentDTO = " + commentDTO);

        // 덧글 등록
        commentService.postComment(commentDTO);
        
        // 덧글을 달았던 게시글로 이동
        return "redirect:/board/"+commentDTO.getC_bno();
    }

    @GetMapping("")
    @ResponseBody
    public List<CommentDTO> getComment(Integer bno, Model m)
    {
        System.out.println("bno = " + bno);

        List<CommentDTO> cmtList = commentService.getComment(bno);

        System.out.println("cmtList = " + cmtList);

        return cmtList;
    }


}
