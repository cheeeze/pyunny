package com.ssafy.backend.vo;

public class User {
    private int id;
    private String name;
    private String nickname;
    private String email;
    private String password;
    private String kakao;
    private String profileImage;

    public User() {

    }

    /*
     * public User(int id, String name, String nickname, String password, String
     * kakao, String profileImage) { this.id = id; this.name = name; this.nickname =
     * nickname; this.password = password; this.kakao = kakao; this.profileImage =
     * profileImage; }
     */

    public User(int id, String name, String nickname, String email, String password, String kakao,
            String profileImage) {
        this.id = id;
        this.name = name;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.kakao = kakao;
        this.profileImage = profileImage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getKakao() {
        return kakao;
    }

    public void setKakao(String kakao) {
        this.kakao = kakao;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    @Override
    public String toString() {
        return "User [email=" + email + ", id=" + id + ", kakao=" + kakao + ", name=" + name + ", nickname=" + nickname
                + ", password=" + password + ", profileImage=" + profileImage + "]";
    }

}