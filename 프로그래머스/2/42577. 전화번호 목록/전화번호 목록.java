import java.util.*;
import java.io.*;

class Solution {
    public boolean solution(String[] phone_book) {
        Set<String> set=new HashSet<>();
        for(int i=0;i<phone_book.length;i++) set.add(phone_book[i]);
        for(int i=0;i<phone_book.length;i++){
            for(int j=0;j<phone_book[i].length()-1;j++){
                String sub= phone_book[i].substring(0,j+1);
                // System.out.println(sub);
                if(set.contains(sub)) return false;
            }
        }
        // System.out.println(set);
        return true;
    }
}