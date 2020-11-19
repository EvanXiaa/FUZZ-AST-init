package edu.berkeley.cs.jqf.fuzz;
import com.wxj.demo.generateseed;

public class MyGenerator {
    generateseed generator;
    String current_code = "";

    public MyGenerator(){}

    public void init(generateseed n){
       generator = n;

    }

    public String generate(){
        if(current_code=="")
            current_code=generate_new();
        return current_code;
    }

    public String  generate_new() {

        try{  current_code = generator.generatenew(); return current_code;}catch (Exception e){};
       return current_code;
    }

    public String update(){
        try{ current_code = generator.update(); return current_code;}catch (Exception e){};
        return current_code;
    }
}
