package com.example.shareDay.board;

public class Board {

    private String id;
    //private String profile;  //프로필 사진
    private String contents;
    private String name;


    public Board(){

    }
   public Board(String contents, String id, String name){
        this.id=id;
        this.contents=contents;
        this.name=name;
    }
    public Board(String name, String contents){

    }
    public String getContents() {
        return contents;
    }
    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

   // public String getProfile() {
   //     return profile;
   // }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }





   // public void setProfile(String profile) {
   //     this.profile = profile;
   // }
}
