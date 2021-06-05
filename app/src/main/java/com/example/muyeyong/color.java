package com.example.muyeyong;

public class color { // 자바빈
    String first = ""; // 첫번째색깔
    String second = ""; // 2번째색깔
    String third = ""; // 3번째색깔
    String fourth = ""; // 4번째색깔

    int img; // 색 이미지
    String tag = ""; // 태그목록
    public color(String first, String second, String third, String fourth,int img, String tag) {
        super();
        this.first = first;
        this.second = second;
        this.third = third;
        this.fourth= fourth;
        this.img = img;
        this.tag = tag;

    }
}
