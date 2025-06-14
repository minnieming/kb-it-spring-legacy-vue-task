package org.scoula.ex05.domain;

public class Member {
    private String name;
    private String userid;

    public Member() {}

    public Member(String name, String userid){
        this.name = name;
        this.userid = userid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUserid (String userid) {
        this.userid = userid;
    }

    public String getName(){
        return name;
    }

    public String getUserid(){
        return userid;
    }

}
