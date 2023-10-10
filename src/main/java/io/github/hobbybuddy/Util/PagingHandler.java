package io.github.hobbybuddy.Util;

import java.util.Map;


public class PagingHandler
{
    Map<String,String> map;
    int page;
    int startPage;
    int endPage;
    int startList;
    int endList;
    int nextPageStart;
    int prevPageStart;

    private void doPaging()
    {
        page = Integer.parseInt(map.get("page"));
        startPage = page/10 + 1;
        endPage = startPage + 9;
        startList = page == 1 ? 1 : 12 * (page-1) + (page-1);
        endList = page * 12 + (page-1);
        nextPageStart = (page/10 + 1) * 10 + 1;
        prevPageStart = (page/10 - 1) * 10 + 1;
    }

    @Override
    public String toString() {
        return "PagingHandler{" +
                "map=" + map +
                ", page=" + page +
                ", startPage=" + startPage +
                ", endPage=" + endPage +
                ", startList=" + startList +
                ", endList=" + endList +
                ", nextPageStart=" + nextPageStart +
                ", prevPageStart=" + prevPageStart +
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
