package com.sptek.demo2;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Board {

    @Id
    @GeneratedValue     // 자동 번호 증가
    private int bno;   // 게시물 번호

    private String title;

    private String writer;

    private String content;

    private int viewCnt;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date inDate;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date upDate;

    @Override
    public String toString() {
        return "Board{" +
                "bno=" + bno +
                ", title='" + title + '\'' +
                ", writer='" + writer + '\'' +
                ", content='" + content + '\'' +
                ", viewCnt=" + viewCnt +
                ", inDate=" + inDate +
                ", upDate=" + upDate +
                '}';
    }

    public int getBno() {
        return bno;
    }

    public void setBno(int bno) {
        this.bno = bno;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getViewCnt() {
        return viewCnt;
    }

    public void setViewCnt(int viewCnt) {
        this.viewCnt = viewCnt;
    }

    public Date getInDate() {
        return inDate;
    }

    public void setInDate(Date inDate) {
        this.inDate = inDate;
    }

    public Date getUpDate() {
        return upDate;
    }

    public void setUpDate(Date upDate) {
        this.upDate = upDate;
    }
}
