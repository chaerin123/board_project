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
          System.out.println("번호   |   제목");
          for(int i = articles.size() - 1; i >= 0 ; i--){
            Article article = articles.get(i);
            //get() : list에 담긴 값을 가져올 때
            //현재 인덱스 i에 해당하는 articles 리스트의 글을 가져와서 article 변수에 할당
            System.out.printf("%d   |    %s \n", article.id, article.title);
          }
        }
      } else if (cmd.startsWith("article detail")) {
        String[] cmdBits = cmd.split(" ");
        int id = Integer.parseInt(cmdBits[2]);

        Article foundArticle = null;
        for(int i = 0; i<articles.size(); i++){
          Article article = articles.get(i);

          if(article.id == id){
            foundArticle = article;
            break;
          }
        }

        if(foundArticle == null){
          System.out.printf("%d번 게시물은 존재하지 않습니다. \n", id);
        } else {
          System.out.printf("번호: %d\n", foundArticle.id);
          System.out.println("날짜: 2023-11-27 12::12:12\n" );
          System.out.printf("제목: %s\n", foundArticle.title);
          System.out.printf("내용: %s\n", foundArticle.body);

        }

      } else {
        System.out.println("존재하지 않는 명령어 입니다.");
      }
    }


    System.out.println("프로그램 종료");
    sc.close(); //스캐너 입력 닫아줌
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