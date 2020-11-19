package com.wxj.demo;

import java.util.List;

public class Seed {
    public  TreeNode root;
    public  TreeNode lastupdate;
    public int fuzzcount;


    Seed(TreeNode root){
        this.root = root;
    }

    public TreeNode getRoot() {
        return root;
    }

    public TreeNode getLastupdate() {
        return lastupdate;
    }

    public int getFuzzcount() {
        return fuzzcount;
    }
}
