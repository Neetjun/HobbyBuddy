package io.github.hobbybuddy.domain;

import java.util.Date;

public class CommentDTO
{
    private Integer cno, tcno, c_uno, c_bno;
    private String c_content;
    private String c_reg_date;
    private String nickname;



    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "CommentDTO{" +
                "cno=" + cno +
                ", tcno=" + tcno +
                ", c_uno=" + c_uno +
                ", c_bno=" + c_bno +
                ", c_content='" + c_content + '\'' +
                ", c_reg_date=" + c_reg_date +
                '}';
    }

    public CommentDTO()
    {
    }

    public CommentDTO(Integer c_uno, Integer c_bno, String c_content) {
        this.c_uno = c_uno;
        this.c_bno = c_bno;
        this.c_content = c_content;
    }

    public Integer getCno() {
        return cno;
    }

    public void setCno(Integer cno) {
        this.cno = cno;
    }

    public Integer getTcno() {
        return tcno;
    }

    public void setTcno(Integer tcno) {
        this.tcno = tcno;
    }

    public Integer getC_uno() {
        return c_uno;
    }

    public void setC_uno(Integer c_uno) {
        this.c_uno = c_uno;
    }

    public Integer getC_bno() {
        return c_bno;
    }

    public void setC_bno(Integer c_bno) {
        this.c_bno = c_bno;
    }

    public String getC_content() {
        return c_content;
    }

    public void setC_content(String c_content) {
        this.c_content = c_content;
    }

    public String getC_reg_date() {
        return c_reg_date;
    }

    public void setC_reg_date(String c_reg_date) {
        this.c_reg_date = c_reg_date;
    }
}
