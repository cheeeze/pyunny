package com.ssafy.backend.vo;

import java.util.List;

public class MainSearch {

    private String keyword;
    private List<Integer> franchise;

    public MainSearch() {
    }

    public MainSearch(String keyword, List<Integer> franchise) {
        this.keyword = keyword;
        this.franchise = franchise;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public List<Integer> getFranchise() {
        return franchise;
    }

    public void setFranchise(List<Integer> franchise) {
        this.franchise = franchise;
    }

    @Override
    public String toString() {
        return "MainSearch [franchise=" + franchise + ", keyword=" + keyword + "]";
    }

}