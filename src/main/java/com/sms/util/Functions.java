package com.sms.util;

public class Functions {
  public static String intToString(int num) {
    if(num<10) {
      return "0" + num;
    }else {
      return "" + num;
    }
  }
}
