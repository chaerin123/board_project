package com.mypj.java.AM;

import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    System.out.println("==프로그램 시작==");

    Scanner sc = new Scanner(System.in); //입력 받는 부분

    int lastArticleId = 0;

    while (true){
      System.out.print("명령어 입력>>");
      String cmd = sc.next();
      //스캐너 객체인 sc를 사용해 사용자의 입력을 받는다. 'sc.next()'는 다음에 입력된 문자열을 읽어들인다. 이를 cmd라는 변수에 저장

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

        System.out.printf("%d번 글이 생성되었습니다", id);
      } else if (cmd.equals("article list")) {
        System.out.println("목록을 아직 구현하지 못했습니다.");
      } else {
        System.out.println("존재하지 않는 명령어 입니다.");
      }
    }

    sc.close(); //스캐너 입력 닫아줌
    System.out.println("프로그램 종료");
  }
}

class Article{
  int id;

  String title;
  String body;

}