package com.mypj.java.AM;

import com.mypj.java.AM.dto.Article;
import com.mypj.java.AM.util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
  private List<Article> articles;

  App() {
    articles = new ArrayList<>();
  }
  public void start() {
    System.out.println("==프로그램 시작==");

    Scanner sc = new Scanner(System.in); //입력 받는 부분

    makeTestData();

    while (true) {
      System.out.print("명령어 입력>>");
      //String cmd = sc.nextLine();
      //스캐너 객체인 sc를 사용해 사용자의 입력을 받는다. 'sc.next()'는 다음에 입력된 문자열을 읽어들인다. 이를 cmd라는 변수에 저장
      String cmd = sc.nextLine().trim();
      //trim() : 문자열의 앞뒤 공백을 제거하여 정제된 문자열을 만든다

      if (cmd.length() == 0) { //명령어를 입력하지 않은 경우
        continue;
      }
      if (cmd.equals("system exit")) { //cmd로 입력받은 문장이 다음에 오는 문장과 같을 경우
        break; //반복문을 나감, 프로그램 종료
      }

      if (cmd.equals("article write")) {
        int id = articles.size()+1;

        String regDate = Util.getNowDateStr();
        //게시글 작성 시간을 받음

        System.out.printf("제목: ");
        String title = sc.nextLine(); //nextLine() = 사용자가 엔터키를 입력할 때까지의 한 줄을 읽어들임

        System.out.printf("내용: ");
        String body = sc.nextLine();

        com.mypj.java.AM.dto.Article article = new com.mypj.java.AM.dto.Article(id, regDate, title, body);
        articles.add(article);
        System.out.printf("%d번 글이 생성되었습니다.\n", id);

      } else if (cmd.startsWith("article list")) {
        if (articles.size() == 0) {
          System.out.println("게시글이 없습니다.\n");
          continue;
        }

        String searchKeyword = cmd.substring("article list".length()).trim();
        System.out.printf("검색어: %s\n", searchKeyword);
        List<Article> forPrintArticles = articles;

        //검색어가 존재하는 경우
        if (searchKeyword.length() > 0) {
          forPrintArticles = new ArrayList<>();

          for (Article article : articles) {
            if (article.title.contains(searchKeyword)) {
              forPrintArticles.add(article);
            }
          }

          //검색어는 있지만 검색 결과가 없는 경우
          if (forPrintArticles.size() == 0) {
            System.out.println("검색결과가 없습니다.\n");
            continue;
          }
        }

        System.out.println("번호  |   제목   | 조회");
        for (int i = forPrintArticles.size() - 1; i >= 0; i--) {
          Article article = forPrintArticles.get(i);
          System.out.printf("%d  |   %s    |  %d\n", article.id, article.title, article.viewCnt);
        }
      } else if (cmd.startsWith("article delete")) {
        String[] cmdBits = cmd.split(" ");
        int id = Integer.parseInt(cmdBits[2]);

        int foundIdx = -1; //인덱스가 0부터 시작

        for (int i = 0; i < articles.size(); i++) {
          Article article = articles.get(i);

          if (article.id == id) {
            foundIdx = i;
            break;
          }
        }
        if (foundIdx == -1) {
          System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
          continue;
        }

        articles.remove(foundIdx); //인덱스 부문을 삭제
        System.out.printf("%d번 게시물이 삭제 되었습니다\n", id);

      } else if (cmd.startsWith("article detail")) {
        String[] cmdBits = cmd.split(" ");
        int id = Integer.parseInt(cmdBits[2]);

        Article foundArticle = null;
        for (int i = 0; i < articles.size(); i++) {
          Article article = articles.get(i);

          if (article.id == id) {
            foundArticle = article;
            break;
          }
        }

        if (foundArticle == null) {
          System.out.printf("%d번 게시물은 존재하지 않습니다. \n", id);
          continue;
        }

        foundArticle.increaseViewCnt();
        //검색한 게시글의 조회수를 검색할 때마다 올리기
        System.out.printf("번호: %d\n", foundArticle.id);
        System.out.printf("날짜: %s\n", foundArticle.regDate);
        System.out.printf("제목: %s\n", foundArticle.title);
        System.out.printf("내용: %s\n", foundArticle.body);
        System.out.printf("조회: %s\n", foundArticle.viewCnt);

      } else if (cmd.startsWith("article modify")) {
        String[] cmdBits = cmd.split(" ");
        int id = Integer.parseInt(cmdBits[2]);

        Article foundArticle = null;
        for (int i = 0; i < articles.size(); i++) {
          Article article = articles.get(i);
          if (article.id == id) {
            foundArticle = article;
            break;
          }
        }

        if (foundArticle == null) {
          System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
          continue;
        }


        System.out.print("제목 : ");
        String title = sc.nextLine();
        System.out.print("내용 : ");
        String body = sc.nextLine();

        foundArticle.title = title;
        foundArticle.body = body;
        System.out.printf("%d번 게시물이 수정 되었습니다\n", id);
      } else {
        System.out.println("존재하지 않는 명령어 입니다.\n");
      }
    }


    System.out.println("프로그램 종료\n");
    sc.close(); //스캐너 입력 닫아줌
  }
  private void makeTestData(){
    System.out.println("게시물 테스트 데이더를 생성합니다.");
    articles.add(new Article(1, Util.getNowDateStr(), "title1", "body1", 11));
    articles.add(new Article(2, Util.getNowDateStr(), "title2", "body2", 22));
    articles.add(new Article(3, Util.getNowDateStr(), "title3", "body3", 33));
  }
}

