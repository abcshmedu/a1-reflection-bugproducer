package edu.hm.bugproducer;


import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class RendererTest {
    private SomeClass toRender;
    private Renderer renderer;

    private int foo;
    private int[] intArray;
    private Date date;

    public RendererTest(int foo, int[] intArray, Date date) {
        this.foo = foo;
        this.intArray = intArray;
        this.date = date;
    }

    @Parameterized.Parameters
    public static Collection primeNumbers() {
        return Arrays.asList(new Object[][]{
                {2, new int[]{1, 2, 3}, new Date(123456789)},
                {5, new int[]{1, 2, 3}, new Date(123456788)}
        });
    }

    @Test
    public void testRendering() throws Exception {
        toRender = new SomeClass(foo);
        renderer = new Renderer(toRender);

        assertEquals("Instance of edu.hm.bugproducer.SomeClass\n" +
                        "foo (Type int): " + foo + "\narray (Type int[]) ["+intArray[0]+", " + intArray[1]+", " +intArray[2]+", ]\n" +
                        "date (Type java.util.Date): "+date+"\n",
                renderer.render());
    }

    @Test(expected = NullPointerException.class)
    public void testNullPointerRendering() throws Exception {
        //toRender = new SomeClass(5);
        renderer = new Renderer(toRender);
        renderer.render();
    }

    @Test(expected = NullPointerException.class)
    public void testArray() throws Exception {
        //toRender = new SomeClass(5);
        renderer = new Renderer(toRender);
        renderer.render();
    }
}