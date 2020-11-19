package com.wxj.demo;


import com.lm.JavaScriptLMGenertator;
import com.ngram.model;
import main.resources.*;
import com.wxj.demo.getfilename;

import java.io.*;
import java.util.Random;

//import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.tree.ParseTree;
//import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.util.ArrayList;
import java.util.List;

public  class generateseed {

    Random r ;
    List<File> list ;
    model Model ;
    TreeNode current_root;
    JavaScriptLMGenertator genertator ;

    public  generateseed(String pathname) throws IOException {


        File modelfile = new File("/Users/xiayifan/filex/kenlm/build/bin/test.arpa");

        FileReader fileReader = new FileReader(modelfile);

       this.Model = new model(fileReader);
        genertator = new JavaScriptLMGenertator(Model,3);
        File dir=new File(pathname);
        //深度遍历的目录
        //filter:过滤器
        //list:容器,存放符合条件的file对象
        FilenameFilter filter=new FilenameFilter() {

            @Override
            public boolean accept(File dir, String name) {

                return name.endsWith(".js");
            }
        };
        this.list=new ArrayList<File>();
        getfilename.getFiles(dir,filter,this.list);
        this.r = new Random();}


        public  String generatenew() throws IOException {

        current_root=transtree(CharStreams.fromFileName(this.list.get(this.r.nextInt(list.size()-1)).getAbsolutePath()));

      return current_root.gettext();
    }

    public  String update() throws IOException {

        return mutate(list,Model);
    }


    private static TreeNode transtree(CharStream input) throws IOException {
        JavaScriptLexer lexer = new JavaScriptLexer(input);

        CommonTokenStream tokens = new CommonTokenStream(lexer);
        // 实例化解析器
        JavaScriptParser parser = new JavaScriptParser(tokens);

//

        ParseTree tree = parser.program();

        return Treeget.gettree(tree,parser);
    }

    private  String mutate(List<File> list,model Model) throws IOException {
        TreeNode newroot = transtree(CharStreams.fromFileName(list.get(r.nextInt(list.size()-1)).getAbsolutePath()));

        genertator.init(new Seed(current_root),new Seed(newroot));

        String code = genertator.generate();

        current_root = genertator.fuzztree.getRoot();

        return code;
    }
}
