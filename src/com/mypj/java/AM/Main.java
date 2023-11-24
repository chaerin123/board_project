package com.mypj.java.AM;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    System.out.println("==프로그램 시작==");

    Scanner sc = new Scanner(System.in); //입력 받는 부분

    int lastArticleId = 0;

    List<Article> articles = new ArrayList<>();
    //articles는 Article클래스의 인스턴스를 담는 리스트(혹은 배열)
    //Article클래스의 객체를 담기 위한 리스트를 생성한다


    while (true){
      System.out.print("명령어 입력>>");
      //String cmd = sc.nextLine();
      //스캐너 객체인 sc를 사용해 사용자의 입력을 받는다. 'sc.next()'는 다음에 입력된 문자열을 읽어들인다. 이를 cmd라는 변수에 저장
      String cmd = sc.nextLine().trim();
      //trim() : 문자열의 앞뒤 공백을 제거하여 정제된 문자열을 만든다

      if(cmd.length() == 0){ //명령어를 입력하지 않은 경우
        continue;
      }
      if (cmd.equals("system exit")){ //cmd로 입력받은 문장이 다음에 오는 문장과 같을 경우
        break; //반복문을 나감, 프로그램 종료
      }

      if(cmd.equals("article write")){
        int id = lastArticleId + 1; //게시물 번호 부여
        lastArticleId = id;

        System.out.printf("제목: ");
        String title = sc.nextLine(); //nextLine() = 사용자가 엔터키를 입력할 때까지의 한 줄을 읽어들임

        System.out.printf("내용: ");
        String body = sc.nextLine();

        Article article = new Article(id, title, body);
        articles.add(article);
        System.out.printf("%d번 글이 생성되었습니다", id);

      } else if (cmd.equals("article list")) {
        if(articles.size() == 0){
          System.out.println("게시글이 없습니다.");
        } else{
          System.out.println("게시글 존재");
        }
      } else {
        System.out.println("존재하지 않는 명령어 입니다.");
      }
    }

    sc.close(); //스캐너 입력 닫아줌
    System.out.println("프로그램 종료");
  }
}

// Article : 클래스로 객체를 생성하기 위한 템플릿, 특정 객체 유형에 대한 속성과 메서드를 정의함
// 글에 관한 속성(id, title, body)과 생성자를 가지고 있음, 새로운 글을 생성하기 위한 템플릿으로 사용
class Article{
  int id;
  String title;
  String body;

  public Article(int id, String title, String body){
    this.id = id;
    this.title = title;
    this.body =  body;
  }
}