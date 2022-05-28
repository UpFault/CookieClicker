package com.upfault.cookieclicker.utilities;

public class StringUtils {

   public static String wrap(String s) {
      int i = 0;
      StringBuilder sb = new StringBuilder();
      while (i < s.length()) {
         if (i % 30 == 0 && i != 0) {
            sb.append("\n");
         }
         sb.append(s.charAt(i));
         i++;
      }
      return sb.toString();
   }

}
