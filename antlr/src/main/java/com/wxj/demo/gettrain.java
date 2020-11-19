package com.wxj.demo;
import com.lm.JavaScriptLMGenertator;
import com.ngram.model;
import main.resources.*;


//import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.tree.ParseTree;
//import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import com.wxj.demo.getfilename;

public class gettrain {
    public static void main(String[] args) throws IOException {
        File dir = new File("/Users/xiayifan/Desktop/作业/学习资料/data");
        //深度遍历的目录
        //filter:过滤器
        //list:容器,存放符合条件的file对象
        FilenameFilter filter = new FilenameFilter() {

            @Override
            public boolean accept(File dir, String name) {

                return name.endsWith(".js");
            }
        };
        List<File> list = new ArrayList<File>();
        getfilename.getFiles(dir, filter, list);
        for (File file : list) {
            parse(file);
        }
    }
    private static void parse(File a) throws java.io.IOException{
        CharStream input = CharStreams.fromFileName(a.getPath());
        // 构造词法分析器
        JavaScriptLexer lexer = new JavaScriptLexer(input);

        CommonTokenStream tokens = new CommonTokenStream(lexer);
        // 实例化解析器
        JavaScriptParser parser = new JavaScriptParser(tokens);
//
        ParseTree tree = parser.program();

        TreeNode root =  Treeget.gettree(tree,parser);

        BufferedWriter output = new BufferedWriter(new FileWriter("/Users/xiayifan/Desktop/training_data.txt",true));//true,则追加写入text文本

        root.transallnode(root);
        List<List<TreeNode>> allpath = root.getpath(root);
        for(List<TreeNode> nowpath : allpath)
        {for(int i=0;i<nowpath.size()-1;i++)
            output.write(nowpath.get(i)+" ");
            output.write("\n");
        }
        output.flush();
        output.close();
    }
    }
