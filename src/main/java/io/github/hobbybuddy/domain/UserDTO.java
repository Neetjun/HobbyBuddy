package io.github.hobbybuddy.domain;

import java.util.Date;

public class UserDTO
{
    Integer uno;
    String id, pw, nickname;
    Date u_reg_date;

    public UserDTO(Integer uno, String id, String pw, String nickname, Date u_reg_date) {
        this.uno = uno;
        this.id = id;
        this.pw = pw;
        this.nickname = nickname;
        this.u_reg_date = u_reg_date;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "uno=" + uno +
                ", id='" + id + '\'' +
                ", pw='" + pw + '\'' +
                ", nickname='" + nickname + '\'' +
                ", u_reg_date=" + u_reg_date +
                '}';
    }

    public Integer getUno() {
        return uno;
    }

    public void setUno(Integer uno) {
        this.uno = uno;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Date getU_reg_date() {
        return u_reg_date;
    }

    public void setU_reg_date(Date u_reg_date) {
        this.u_reg_date = u_reg_date;
    }
}
