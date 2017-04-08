package edu.hm.bugproducer;

import java.util.Date;

/**
 * Example class for rendering.
 */
public class SomeClass {
    private static final int FOO_PARAM = 3;
    private static final int DATE_PARAM = 123456789;

    @RenderMe
    private int foo;
    @RenderMe(with = "edu.hm.bugproducer.ArrayRenderer")
    private int[] array;
    @RenderMe
    private Date date;


    /**
     * Custom Constructor.
     *
     * @param foo int value
     */
    public SomeClass(int foo) {
        this.foo = foo;
    }

    /**
     * Default Constructor.
     */
    public SomeClass() {
        foo = FOO_PARAM;
        array = new int[]{1, 2, 3};
        date = new Date(DATE_PARAM);
    }

    /**
     * Custom Constructor.
     *
     * @param array int array
     */
    public SomeClass(int[] array) {
        this.array = array;
    }

    /**
     * Custom Constructor with all parameters.
     *
     * @param foo   int value
     * @param array int array
     * @param date  date
     */
    public SomeClass(int foo, int[] array, Date date) {
        this.foo = foo;
        this.array = array;
        this.date = date;
    }

    /**
     * Example for no parameter.
     *
     * @return nonsense string
     */
    @RenderMe
    public String withoutParameter() {
        return "withoutParameter";
    }
}
