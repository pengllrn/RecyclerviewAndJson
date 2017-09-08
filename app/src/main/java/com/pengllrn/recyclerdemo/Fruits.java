package com.pengllrn.recyclerdemo;

/**
 * @author Administrator
 * @version $Rev$
 * @des ${UTODO}
 * @updateAuthor ${Author}$
 * @updateDate2017/9/6.
 */

public class Fruits {
    private String id="";
    private String fruitname="";

    public Fruits(String id, String fruitname) {
        this.id = id;
        this.fruitname = fruitname;
    }

    public String getId() {
        return id;
    }


    public String getFruitname() {
        return fruitname;
    }

}
