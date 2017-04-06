package edu.hm.bugproducer;


import org.junit.*;

import static org.junit.Assert.assertEquals;

public class RendererTest {
    private SomeClass toRender;
    private Renderer renderer;
   // private ArrayRenderer arrayRenderer;


    @Before
    public void setUp() {
        toRender = new SomeClass(5);
        renderer = new Renderer(toRender);
    }

    @Test
    public void testRendering() throws Exception {
        assertEquals("Instance of edu.hm.bugproducer.SomeClass:\n" +
                        "foo (Type int): 5\narray (Type int[]) [1, 2, 3, ]\n" +
                        "date (Type java.util.Date): Fri Jan 02 11:17:36 CET 1970\n ",
                renderer.render());
    }
}