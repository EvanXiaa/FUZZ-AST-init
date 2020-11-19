package com.wxj.demo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class getfilename {

    public static void main(String[] args) {

        File dir=new File("/Users/xiayifan/Downloads/data");
        //深度遍历的目录
        //filter:过滤器
        //list:容器,存放符合条件的file对象
        FilenameFilter filter=new FilenameFilter() {

            @Override
            public boolean accept(File dir, String name) {

                return name.endsWith(".js");
            }
        };
        List<File> list=new ArrayList<File>();
        getFiles(dir,filter,list);
        System.out.println(list.get(0));

    }

    private static void write2File(List<File> list, File destFile) {
        BufferedWriter bw=null;
        try {
            bw=new BufferedWriter(new FileWriter(destFile));
            for(File file:list){
                bw.write(file.getAbsolutePath());
                bw.newLine();
                bw.flush();
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            //释放资源的代码
            if(bw!=null){
                try {
                    bw.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }


    }

    public static void getFiles(File dir, FilenameFilter filter, List<File> list) {

        File[] files=dir.listFiles();
        for(File file:files){
            if(file.isDirectory()){
                //如果是目录，则递归
                getFiles(file, filter, list);
            }else{
                //文件
                //过滤文件：将符合条件的file对象存储到list集合中
                if(filter.accept(dir, file.getName())){
                    list.add(file);
                }
            }
        }
    }

}


