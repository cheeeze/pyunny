package com.ssafy.backend.vo;

public class RatingCount {
    private int inlike;
    private int dislike;

    public RatingCount() {
    }

    public RatingCount(int inlike, int dislike) {
        this.inlike = inlike;
        this.dislike = dislike;
    }

    public int getInlike() {
        return inlike;
    }

    public void setInlike(int inlike) {
        this.inlike = inlike;
    }

    public int getDislike() {
        return dislike;
    }

    public void setDislike(int dislike) {
        this.dislike = dislike;
    }

    @Override
    public String toString() {
        return "RatingCount [dislike=" + dislike + ", inlike=" + inlike + "]";
    }

}