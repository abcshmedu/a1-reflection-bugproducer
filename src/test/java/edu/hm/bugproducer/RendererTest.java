package edu.hm.bugproducer;


import org.junit.*;

import static org.junit.Assert.assertEquals;

public class RendererTest {
    private SomeClass toRender;
    private Renderer renderer;
   // private ArrayRenderer arrayRenderer;



    @Test
    public void testRendering() throws Exception {
        toRender = new SomeClass(5);
        renderer = new Renderer(toRender);

        assertEquals("Instance of edu.hm.bugproducer.SomeClass\n" +
                        "foo (Type int): 5\narray (Type int[]) [1, 2, 3, ]\n" +
                        "date (Type java.util.Date): Fri Jan 02 11:17:36 CET 1970\n",
                renderer.render());
    }
    @Test (expected=NullPointerException.class)
    public void testNullPointerRendering() throws Exception {
        //toRender = new SomeClass(5);
        renderer = new Renderer(toRender);
        renderer.render();
    }
    @Test
    public void template()throws Exception
    {


    }

}