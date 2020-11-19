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

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class JSMain {
    public static void main(String[] args) throws IOException {


        File modelfile = new File("/Users/xiayifan/filex/kenlm/build/bin/test.arpa");

        FileReader fileReader = new FileReader(modelfile);

        model Model = new model(fileReader);



       CharStream input = CharStreams.fromString("var x =1; var y=x+2;");
       CharStream input2 = CharStreams.fromString("x=true;\n" +
               "Number(x);");
        CharStream input3 = CharStreams.fromFileName("/Users/xiayifan/PycharmProjects/ANTLR/Untitled2.js");
////        CharStream input = CharStreams.fromString("function foo()\n" +
////                "{\n" +
////                "    var o = Error();\n" +
////                "    for(let i in o)\n" +
////                "    {\n" +
////                "        o[i];\n" +
////                "    }\n" +
////                "}\n" +
////                "\n" +
////                "var bb = foo();");
////        CharStream input = CharStreams.fromString("var q;\n" +
////                "function g(){\n" +
////                "\tq = g.caller;\n" +
////                "\treturn 7;\n" +
////                "}\n" +
////                "\n" +
////                "\n" +
////                "var a = [1, 2, 3];\n" +
////                "a.length = 4;\n" +
////                "Object.defineProperty(Array.prototype, \"3\", {get : g});\n" +
////                "[4, 5, 6].concat(a);\n" +
////                "q(0x77777777, 0x77777777, 0);");
//        //CharStream input = CharStreams.fromString("x>=y");
//        //ANTLRInputStream input = new ANTLRInputStream(System.in);
//
//        // 构造词法分析器
////        ECMAScriptLexer lexer=new ECMAScriptLexer(input);
////
////        CommonTokenStream tokens = new CommonTokenStream(lexer);
////
//        // 实例化解析器
//        ECMAScriptParser parser = new ECMAScriptParser(tokens);
        // 构造词法分析器
        JavaScriptLexer lexer = new JavaScriptLexer(input);

        CommonTokenStream tokens = new CommonTokenStream(lexer);
        // 实例化解析器
        JavaScriptParser parser = new JavaScriptParser(tokens);
//
        ParseTree tree = parser.program();

        // 构造词法分析器
        JavaScriptLexer lexer2 = new JavaScriptLexer(input2);

        CommonTokenStream tokens2 = new CommonTokenStream(lexer2);
        // 实例化解析器
        JavaScriptParser parser2 = new JavaScriptParser(tokens2);
//
        ParseTree tree2 = parser2.program();

        JavaScriptLexer lexer3 = new JavaScriptLexer(input3);

        CommonTokenStream tokens3 = new CommonTokenStream(lexer3);
        // 实例化解析器
        JavaScriptParser parser3 = new JavaScriptParser(tokens3);
//
        ParseTree tree3 = parser3.program();
//        HelloBaseVisitor visitor = new HelloBaseVisitor();
//
//        System.out.println(visitor.visit(tree));

//        System.out.println(tree.getText());
//
//        System.out.println(tree.toStringTree(parser));

       //将提取出来的树转化成了自己定义的树
       TreeNode root =  Treeget.gettree(tree,parser);
       TreeNode root2 = Treeget.gettree(tree2,parser2);
        System.out.println(root.gettext());
        //System.out.println(root.getpath(root));
        System.out.println(root2.gettext());

       TreeNode child1 = root.getChlidren().get(0);
        //System.out.println(root.getStatement());
        TreeNode child2 = child1.getChlidren().get(0);
        TreeNode child3 = child2.getChlidren().get(0);
        TreeNode child4 = child3.getChlidren().get(0);

        root.transallnode(root);
        List<List<TreeNode>> allpath = root.getpath(root);
        List<TreeNode> nowpath = allpath.get(0);
        for(TreeNode t :nowpath)
            System.out.print(t+" ");

        System.out.println(child1.getngram(3));
        System.out.println(child2.getngram(3));
        System.out.println(child3.getngram(3));
        System.out.println(child4.getngram(3));

        System.out.println(child1.getnodeProbability(Model,3));
        System.out.println(child2.getnodeProbability(Model,3));
        System.out.println(child3.getnodeProbability(Model,3));
        System.out.println(child4.getnodeProbability(Model,3));

        System.out.println(root.getAllnode().size());

        System.out.println(NodeUtis.getMaxProbabilitynode(root,Model,3));


        System.out.println(root.getstatemetnodelist("sourceElement").size());

        JavaScriptLMGenertator genertator = new JavaScriptLMGenertator(Model,3);
        genertator.init(new Seed(root),new Seed(root2));
        System.out.println(genertator.generate());




//        for(int i =0 ;i<child1.getChlidren().size();i++){
//            System.out.println(child1.getChlidren().get(i).getStatement());
//        }


//        ParseTreeWalker walker = new ParseTreeWalker();
//
//        ECMAScriptBaseListener listener = new ECMAScriptBaseListener();
//
//        walker.walk(listener,tree);




    }
}
