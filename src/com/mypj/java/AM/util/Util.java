package com.mypj.java.AM.util;
//해당 클래스가 속한 패키지를 선언
//패키지는 클래스들을 그룹화하는데 사용되며 여기서는 Util클래스가 속한다고 선언되어 있습니다.

import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.Date;
//필요한 외부 클래스를 가져오는 import문 = 날짜 및 시간 처리에 사용됨

public class Util {
  //현재 날짜 및 시간
  public static String getNowDateStr(){
    //getNowDateStr라는 메서드를 정의
    SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    //SimpleDateFormat 객체를 생성
    Date now = new Date();
    return sdf1.format(now);
    //SimpleDataFormat 객체를 사용하여 현재 날짜와 시간을 지정된 형식의 문자열로 변환하고 반환
  }
}
