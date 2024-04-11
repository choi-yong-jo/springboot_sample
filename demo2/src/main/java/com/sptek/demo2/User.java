package com.sptek.demo2;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="Account")
public class User {

    @Id
    @Column(name = "user_id")
    private String id;

    private String password;

    private String name;

    private String email;

    private Date inDate;

    private Date upDate;

    // FatchType.EAGER - 두 엔티티의 정보를 같이 가져오는 것(join)
    // FatchType.LAZY - 따로 가져오는 것. 나중에 getList(), default
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)      // 하나의 User의 여러 개 Board
    List<Board> list = new ArrayList<>();

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", inDate=" + inDate +
                ", upDate=" + upDate +
                ", list=" + list +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public List<Board> getList() {
        return list;
    }

    public void setList(List<Board> list) {
        this.list = list;
    }
}
