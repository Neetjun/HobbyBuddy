package io.github.hobbybuddy.domain;

import java.util.ArrayList;

public class BoardDTO
{
    private Integer bno, b_uno, like_count, view_count, cmt_count;
    private String title, b_content;
    private String b_reg_date, u_date;
    private String imgList;

    public BoardDTO(Integer b_uno, String title, String b_content) {
        this.b_uno = b_uno;
        this.title = title;
        this.b_content = b_content;
    }

    public String getImgList() {
        return imgList;
    }

    public void setImgList(String imgList) {
        this.imgList = imgList;
    }

    public Integer getCmt_count() {
        return cmt_count;
    }

    public void setCmt_count(Integer cmt_count) {
        this.cmt_count = cmt_count;
    }

    public Integer getLike_count() {
        return like_count;
    }

    public void setLike_count(Integer like_count) {
        this.like_count = like_count;
    }

    public Integer getView_count() {
        return view_count;
    }

    public void setView_count(Integer view_count) {
        this.view_count = view_count;
    }

    public Integer getBno() {
        return bno;
    }

    public void setBno(Integer bno) {
        this.bno = bno;
    }

    public Integer getB_uno() {
        return b_uno;
    }

    public void setB_uno(Integer b_uno) {
        this.b_uno = b_uno;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getB_content() {
        return b_content;
    }

    public void setB_content(String b_content) {
        this.b_content = b_content;
    }

    public String getB_reg_date() {
        return b_reg_date;
    }

    public void setB_reg_date(String b_reg_date) {
        this.b_reg_date = b_reg_date;
    }

    public String getU_date() {
        return u_date;
    }

    public void setU_date(String u_date) {
        this.u_date = u_date;
    }
}
