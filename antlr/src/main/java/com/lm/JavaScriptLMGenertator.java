package com.lm;

import com.ngram.model;
import com.wxj.demo.NodeUtis;
import com.wxj.demo.Seed;
import com.wxj.demo.TreeNode;

import java.util.List;
import java.util.Random;

public class JavaScriptLMGenertator implements Generator {

    public Seed fuzztree;
    private Seed croptree;
    private model Model;
    private int n;


    public JavaScriptLMGenertator(model Model,int n){
        this.Model=Model;
        this.n = n;

    }

    public void init(Seed fuzztree, Seed croptree){
        this.fuzztree = fuzztree;
        this.croptree = croptree;

    }
    @Override
    public String generate() {
        TreeNode fuzztreeroot = fuzztree.getRoot();
        TreeNode croproot = croptree.getRoot();
        Random r = new Random();
        List<TreeNode> fuzznode = NodeUtis.getMaxProbabilitynode(fuzztreeroot,Model,n);


        TreeNode first =fuzznode.get(0);
        List<TreeNode> replacenode = croproot.getstatemetnodelist(first.getStatement());


        NodeUtis.replace(fuzznode.get(r.nextInt(fuzznode.size())),replacenode.get(r.nextInt(replacenode.size())));

        return fuzztreeroot.gettext();
    }


}
