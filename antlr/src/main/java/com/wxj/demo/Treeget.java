package com.wxj.demo;

import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.Trees;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Treeget {

    //public  TreeNode root = new TreeNode("start",null,null);



    public static TreeNode gettree(ParseTree t, Parser recog){
        //System.out.println(t.getText());
        String[] ruleNames = recog != null ? recog.getRuleNames() : null;
        List<String> ruleNamesList = ruleNames != null ? Arrays.asList(ruleNames) : null;
        //System.out.println(ruleNamesList);
        return  gettree(t,ruleNamesList);
        //System.out.println(Trees.getNodeText(t,ruleNamesList));



    }

    public  static  TreeNode gettree(ParseTree t ,List<String> ruleNames){
        if(t.getChildCount()==0){
            //TreeNode treeNode = new TreeNode(Trees.getNodeText(t,ruleNames));
            return new TreeNode(Trees.getNodeText(t,ruleNames));
        }
        else{
            TreeNode treeNode = new TreeNode(Trees.getNodeText(t,ruleNames));
            List<TreeNode> children = new ArrayList<>();
            for(int i = 0;i<t.getChildCount();i++){
                TreeNode treeNode1 = gettree(t.getChild(i),ruleNames);
                treeNode1.setParent(treeNode);
                children.add(treeNode1);
            }
            treeNode.setChlidren(children);

            return treeNode;

        }

    }


}
