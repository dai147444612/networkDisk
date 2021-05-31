package com.inet.code;

import cn.hutool.core.text.csv.CsvUtil;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.mvc.method.annotation.HttpEntityMethodProcessor;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import javax.swing.*;
import java.lang.reflect.AnnotatedArrayType;
import java.lang.reflect.Array;
import java.sql.ClientInfoStatus;
import java.util.*;

public class test {

//    @Test
//    public void test1(){
//        String a="ABC123";
//        List<Integer> list=new ArrayList();
//        for (int i=0;i< a.length();i++){
//            list.add(Integer.valueOf(a.charAt(i)));
//        }
//        List<Integer> list1=new ArrayList<>();
//        list1.add(0);
//        int temp=2;
//        for (int i=0;i<list.size();i++){
//            List<Integer> list2 = tenToTwo(list.get(i));
//            List<Integer> listFi =new ArrayList<>();
//            if (list2.size()<7){
//                listFi.add(0);
//                listFi.addAll(list2);
//            }else {
//                listFi.addAll(list2);
//            }
////            System.out.println(listFi);
//            for (int j=0;j<listFi.size();j++){
//                if (isTwo(temp)){
//                    list1.add(0);
//                    temp++;
////                    System.out.println(temp);
//                }
//                list1.add(listFi.get(j));
//                temp++;
//            }
//        }
//
//        int i1 = list1.lastIndexOf(1);
////        System.out.println(i1);
//        int x=i1+1;
//
//
//        for (int i=i1-1;i>=0;i--){
//            if (list1.get(i)==1){
//                x = x ^ (i+1);
//            }
//        }
//        int m=0;
//        List<Integer> ar = tenToTwo1(x);
//        ar.add(0);
//        for (int i=0;i<list1.size();i++){
//           if (isTwo(i+1)){
//            list1.set(i,ar.get(m));
//            m++;
//           }
//       }
//        System.out.println(list1);
//    }
//



    public static int toInt(List<Integer> list){
        String s="";
        for (int i=0;i<list.size();i++){
            s+=String.valueOf(list.get(i));
        }
        return Integer.parseInt(s);
    }
    public static boolean isTwo(int number){
        List<Integer> list = tenToTwo(number);
        if (number==0){
            list.add(1);
        }
        if (list.size()==1 && list.get(0)==1){
            return true;
        }
        if (list.get(0)!=1){
            return false;
        }
        for (int i=1;i<list.size();i++){
            if (list.get(i)==1){
                return false;
            }
        }
        return true;
    }




    //十六进制转二进制
    public static List<Integer> parseHexStr2Byte(String hexStr) {
        //字符串形式十进制--作为桥梁!
        int sint=Integer.valueOf(hexStr, 16);
        List<Integer> list = tenToTwo(sint);
        return list;
    }
    public static String twoToHex(List<Integer> list){
        int i = twoToTen(list);
        String s = Integer.toString(i, 16);
        return s;
    }

    public static List<Integer>  tenToTwo(int number){
        List<Integer> a=new ArrayList<>();
        while(number>0){
            a.add(number%2);
            number=number/2;
        }
        List<Integer> b=new ArrayList<>();
        for (int i=a.size()-1;i>=0;i--){
            b.add(a.get(i));
        }
        return b;
    }


    public static int twoToTen(List<Integer> list){
        int a=0;
        for (int i=0;i<list.size();i++){
             a+=(Math.pow(2,list.size()-1-i)*list.get(i));
        }
        return a;
    }

    public static List<Integer>  tenToTwo1(int number){
        List<Integer> a=new ArrayList<>();
        while(number>0){
            a.add(number%2);
            number=number/2;
        }
        while(a.size()<8){
            a.add(0);
        }
        List<Integer> b=new ArrayList<>();
        for (int i=a.size()-1;i>=0;i--){
            b.add(a.get(i));
        }
        return b;
    }

    public static int twoToTen1(String s){
        List<Integer> list=new ArrayList<>();

        int a=0;
        for (int i=0;i<list.size();i++){
            a+=(Math.pow(2,list.size()-1-i)*list.get(i));
        }
        return a;
    }
    @Test
    public void test5(){
        String s1="A1B2";
        int[] s2=new int[s1.length()];
        for (int i=0;i<s1.length();i++) {
            s2[i] = s1.charAt(i);
        }
        List<Integer> two=new ArrayList();
        for (int i=0;i<s2.length;i++){
            List<Integer> list = tenToTwo1(s2[i]);
            two.addAll(list);
        }
        while(two.size()%6!=0){
            two.add(0);
        }

        List<Integer> eight=new ArrayList<>();

        for (int i=0;i<two.size();i++){
            if (i%6==0){
                eight.add(0);
                eight.add(0);
            }
            eight.add(two.get(i));
        }



       List<Integer> S=new ArrayList<>();
      List<Integer> arrayList=new ArrayList<>();
        for (int i=0;i<eight.size();i++){
            if (i%8==0){
//                System.out.println(arrayList);
                int i1 = twoToTen(arrayList);
                S.add(i1);
                arrayList.clear();
            }
            arrayList.add(eight.get(i));
        }
        int i1 = twoToTen(arrayList);
        S.add(i1);
        S.remove(0);
//        System.out.println(S);

        List<String> list=new ArrayList<>();
        for (int i=0;i<S.size();i++){
            String eco = getEco(S.get(i));
            list.add(eco);
        }
        while(list.size()%4!=0){
            list.add("=");
        }
        System.out.println(list);
        for (int i=0;i<list.size()-1;i++){
            for (int j=i+1;j<list.size();j++){
                if (list.get(j).charAt(0)>list.get(i).charAt(0)){
                    String s=new String();
                    s=list.get(j);
                    list.set(j,list.get(i));
                    list.set(i,s);
                }
            }
        }

    }

    public String getEco(int i){
        if (i==0){
            return "Q";
        }
        if (i==1){
            return "j";
        }
        if (i==2){
            return "6";
        }
        if (i==3){
            return "y";
        }
        if (i==4){
            return "q";
        }
        if (i==5){
            return "B";
        }
        if (i==6){
            return "H";
        }
        if (i==7){
            return "l";
        }
        if (i==8){ return "b"; }
        if (i==9){ return "i"; }
        if (i==10){ return "7"; }
        if (i==11){ return "z"; }
        if (i==12){ return "r"; }
        if (i==13){ return "V"; }
        if (i==14){ return "G"; }
        if (i==15){ return "U"; }
        if (i==16){ return "d"; }
        if (i==17){ return "I"; }
        if (i==18){ return "O"; }
        if (i==19){ return "1"; }
        if (i==20){ return "9"; }
        if (i==21){ return "X"; }
        if (i==22){ return "D"; }
        if (i==23){ return "T"; }
        if (i==24){ return "c"; }
        if (i==25){ return "k"; }
        if (i==26){ return "s"; }
        if (i==27){ return "t"; }
        if (i==28){ return "8"; }
        if (i==29){ return "C"; }
        if (i==30){ return "F"; }
        if (i==31){ return "Y"; }
        if (i==32){ return "2"; }
        if (i==33){ return "m"; }
        if (i==34){ return "u"; }
        if (i==35){ return "e"; }
        if (i==36){ return "Z"; }
        if (i==37){ return "+"; }
        if (i==38){ return "o"; }
        if (i==389){ return "R"; }
        if (i==40){ return "f"; }
        if (i==41){ return "n"; }
        if (i==42){ return "v"; }
        if (i==43){ return "3"; }
        if (i==44){ return "/"; }
        if (i==45){ return "L"; }
        if (i==46){ return "A"; }
        if (i==47){return "E";}
        if (i==48){return "g";}
        if (i==49){return "S";}
        if (i==50){return "w"; }
        if (i==51){ return "M"; }
        if (i==52){ return "4"; }
        if (i==53){ return "K"; }
        if (i==54){return "P";}
        if (i==55){ return "W"; }
        if (i==56){return "h";}
        if (i==57){return "p";}
        if (i==58){return "x";}
        if (i==59){return "5";}
        if (i==60){return "N";}
        if (i==61){return "J";}
        if (i==62){return "O";}
        if (i==63){return "a";}
        return null;
    }

}
