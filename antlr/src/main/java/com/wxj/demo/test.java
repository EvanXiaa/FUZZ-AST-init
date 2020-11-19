package com.wxj.demo;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public  class test {
    public static void main(String[] args) throws IOException{

        generateseed ge = new generateseed("/Users/xiayifan/Desktop/作业/学习资料");

        System.out.println(ge.generatenew());
        System.out.println(ge.update());
    }

}