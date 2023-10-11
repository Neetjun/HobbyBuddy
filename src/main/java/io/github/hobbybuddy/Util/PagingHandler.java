package io.github.hobbybuddy.Util;

import org.springframework.stereotype.Component;

import java.util.Map;

public class PagingHandler
{
    private Map<String,String> map;
    private int page;
    private int startPage;
    private int endPage;
    private int startList;
    private int endList;
    private int nextPageStart;
    private int prevPageStart;
    private int boardCnt;
    private int totPage;

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getStartPage() {
        return startPage;
    }

    public void setStartPage(int startPage) {
        this.startPage = startPage;
    }

    public int getEndPage() {
        return endPage;
    }

    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }

    public int getStartList() {
        return startList;
    }

    public void setStartList(int startList) {
        this.startList = startList;
    }

    public int getEndList() {
        return endList;
    }

    public void setEndList(int endList) {
        this.endList = endList;
    }

    public int getNextPageStart() {
        return nextPageStart;
    }

    public void setNextPageStart(int nextPageStart) {
        this.nextPageStart = nextPageStart;
    }

    public int getPrevPageStart() {
        return prevPageStart;
    }

    public void setPrevPageStart(int prevPageStart) {
        this.prevPageStart = prevPageStart;
    }

    public int getBoardCnt() {
        return boardCnt;
    }

    public void setBoardCnt(int boardCnt) {
        this.boardCnt = boardCnt;
    }

    public int getTotPage() {
        return totPage;
    }

    public void setTotPage(int totPage) {
        this.totPage = totPage;
    }

    private void doPaging()
    {
        page = Integer.parseInt(map.get("page"));
        boardCnt = Integer.parseInt((map.get("boardCnt")));
        totPage = (int)Math.ceil(boardCnt/12.0);
        startPage = page/10 * 10 + 1;
        endPage = startPage + 9;

        if(endPage >= totPage)
            endPage = totPage;

        startList = page == 1 ? 1 : 12 * (page-1) + (page-1);
        endList = page * 12 + (page-1);
        nextPageStart = (page/10 + 1) * 10 + 1;
        prevPageStart = (page/10 - 2) * 10 + 1;

        if(prevPageStart < 0)
            prevPageStart = 1;
    }

    @Override
    public String toString() {
        return "PagingHandler{" +
                "page=" + page +
                ", startPage=" + startPage +
                ", endPage=" + endPage +
                ", startList=" + startList +
                ", endList=" + endList +
                ", nextPageStart=" + nextPageStart +
                ", prevPageStart=" + prevPageStart +
                ", boardCnt=" + boardCnt +
                ", totPage=" + totPage +
                '}';
    }

    /*
     * 1 ~ 12    -- 1  START = 12 * (page-1) + (page-1)
     * 13 ~ 25   -- 2
     * 26 ~ 38   -- 3
     * 39 ~ 51   -- 4  END = page * 12 + (page-1)
     * */

    public PagingHandler(Map<String,String> map)
    {
        this.map = map;

        if(map.get("page") != null)
            doPaging();
    }
}
