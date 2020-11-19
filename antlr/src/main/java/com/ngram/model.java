package com.ngram;

import java.io.*;
import java.util.HashMap;
//import java.util.List;

public class model {

    public long one_gram_count ;
    public long two_gram_count;
    public long three_gram_count;

    public HashMap<String,Double> onegram = new HashMap<>();
    public HashMap<String,Double> twogram = new HashMap<>();
    public HashMap<String,Double> threegram = new HashMap<>();
    //public HashMap<String, List<String>> thetwo = new HashMap<>();
    public FileReader arpa;

    public model(FileReader fileReader) {
        this.arpa = fileReader;
        this.caclulate();
    }

    public void caclulate(){
        BufferedReader reader = new BufferedReader(this.arpa);

        int linecount = 0;

      try {
          String x;
          while ((x = reader.readLine())!=null){
              linecount++;
              if(linecount>=5&&linecount<=7){
                  //System.out.println(x);
                 String[] result =  x.split("=");
                  switch (linecount){
                      case  5 : one_gram_count = Long.parseLong(result[1]);
                      case  6 : two_gram_count = Long.parseLong(result[1]);
                      case  7:  three_gram_count = Long.parseLong(result[1]);
                  }

              }
              else if(linecount>=10 && linecount<=(10+one_gram_count-1)){
                  //System.out.println(x);

                  String[] result = x.split("\\s+");
                  //System.out.println(result.length);
                  //System.out.println(result[1] + "   " + Double.valueOf(result[0]));
                  onegram.put(result[1],Double.valueOf(result[0]));

              }
              else if(linecount>= (10 + 2 + one_gram_count) && linecount<=(10 + 2 +one_gram_count + two_gram_count -1)){
                  //System.out.println(x);

                  String[] result = x.split("\\s+");
                  String two = result[1] + " " + result[2];
                  twogram.put(two,Double.valueOf(result[0]));
              }
              else if(linecount>=(10 + 2 +one_gram_count + two_gram_count+2) &&linecount<=(10 + 2 +one_gram_count + two_gram_count+2 +three_gram_count-1)){
                  String[] result = x.split("\\s+");
                  String three = result[1] + " " + result[2] + " " +result[3];
                  threegram.put(three,Double.valueOf(result[0]));
              }
              //System.out.println(x);
          }
          System.out.println("one gram " + one_gram_count);
          System.out.println("two gram " + two_gram_count);
          System.out.println("three gram " + three_gram_count);
          System.out.println("one gram hashmap " + onegram.size());
          System.out.println(onegram);
          System.out.println("two gram hashmap " + twogram.size());
          System.out.println(twogram);
          System.out.println("three gram hashmap " + threegram.size());
          System.out.println(threegram);


      }
      catch (Exception e){
          e.printStackTrace();
      }

    }

    public double getProbability(String ngram ,int n){
        switch (n){
            case 1: return onegram.get(ngram)!= null?onegram.get(ngram):Double.MIN_EXPONENT;
            case 2: return twogram.get(ngram)!=null?twogram.get(ngram):Double.MIN_EXPONENT;
            case 3: return threegram.get(ngram)!=null?threegram.get(ngram):Double.MIN_EXPONENT;
            default: return Double.MIN_EXPONENT;
        }
    }
}
