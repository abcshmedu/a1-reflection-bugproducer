package edu.hm.bugproducer;

import java.util.Date;

public class SomeClass {
    @RenderMe
    private int foo;
    @RenderMe(with = "edu.hm.bugproducer.ArrayRenderer")
    int[] array;
    @RenderMe
    private Date date;


    public SomeClass(int foo) {
        this.foo = foo;
    }

    public SomeClass() {
        foo = 3;
        array = new int[]{1, 2, 3};
        date = new Date(123456789);
    }

    public SomeClass(int[] array) {
        this.array = array;
    }

    public SomeClass(int foo, int[] array, Date date) {
        this.foo = foo;
        this.array = array;
        this.date = date;
    }

    @RenderMe
    public String withoutParameter(){
        return "withoutParameter";
    }
}
