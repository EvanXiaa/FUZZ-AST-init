package com.wxj.demo;

import com.ngram.model;

import java.util.ArrayList;


import java.util.List;

public class NodeUtis {


    public static  List<TreeNode> getMaxProbabilitynode(TreeNode root,model Model, int n){

        List<TreeNode> allnode = root.getAllnode();


        allnode.sort((o1, o2) -> Double.compare(o2.getnodeProbability(Model, n), o1.getnodeProbability(Model, n)));

        double maxprobability = allnode.get(0).getnodeProbability(Model,n);
        List<TreeNode> Maxnodelist = new ArrayList<>();
        for (TreeNode i : allnode){
            if(i.getnodeProbability(Model,n)==maxprobability)
                Maxnodelist.add(i);
            else
                break;
        }
        return Maxnodelist;
    }

    public  static  void replace(TreeNode Node1,TreeNode Node2 ){
        TreeNode parent = Node1.getParent();
        for(int i=0;i<parent.getchildrencount();i++){
            if(parent.getChlidren().get(i) == Node1){
                parent.getChlidren().set(i,Node2);
                Node2.setParent(parent);
            }
        }
    }
}
