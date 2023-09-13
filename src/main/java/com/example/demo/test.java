package com.example.demo;

//import org.grammaticalframework.pgf.*;

import org.grammaticalframework.pgf.PGF;

import java.util.List;

public class test {
    public static void main(String[] args){
        try {
            String gfilename ="/Users/yusuf_budullah/IdeaProjects/GFM/src/main/resources/com/example/demo/test_Letter.pgf";
            PGF pgf = PGF.readPGF(gfilename);
            List<String> categories = pgf.getCategories();
            System.out.println(categories.get(0));
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
