package edu.hm.bugproducer;


import org.junit.*;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import static org.junit.Assert.assertEquals;

@RunWith(Enclosed.class)
public class RendererTest {

    public static class DefaultConstructor {

        private SomeClass toRender;
        private Renderer renderer;

        @Test
        public void testRenderingDefault() throws Exception {
            toRender = new SomeClass();
            renderer = new Renderer(toRender);

            assertEquals("Instance of edu.hm.bugproducer.SomeClass\n" +
                            "foo (Type int): 3\narray (Type int[]) [1, 2, 3, ]\n" +
                            "date (Type java.util.Date): Fri Jan 02 11:17:36 CET 1970\n",
                    renderer.render());
        }
    }

    @RunWith(Parameterized.class)
    public static class FirstParameterizedPart {

        private SomeClass toRender;
        private Renderer renderer;

        private int foo;
        private int[] intArray;
        private Date date;

        public FirstParameterizedPart(int foo, int[] intArray, Date date) {
            this.foo = foo;
            this.intArray = intArray;
            this.date = date;
        }

        @Parameterized.Parameters
        public static Collection myArray() {
            return Arrays.asList(new Object[][]{
                    {2, new int[]{1, 2, 3}, new Date(123456789)},
                    {Integer.MAX_VALUE, new int[]{1, 2, 3}, new Date(123456789)},
                    {Integer.MIN_VALUE, new int[]{1, 2, 3}, new Date(123456789)}
            });
        }

        @Test
        public void testRenderingAllParameter() throws Exception {
            toRender = new SomeClass(foo, intArray, date);
            renderer = new Renderer(toRender);

            assertEquals("Instance of edu.hm.bugproducer.SomeClass\n" +
                            "foo (Type int): " + foo + "\narray (Type int[]) [" + intArray[0] + ", " + intArray[1] + ", " + intArray[2] + ", ]\n" +
                            "date (Type java.util.Date): " + date + "\n",
                    renderer.render());
        }

    }

    @RunWith(Parameterized.class)
    public static class FooConstructorParameterized {


        private SomeClass toRender;
        private Renderer renderer;

        private int foo;

        public FooConstructorParameterized(int foo) {
            this.foo = foo;
        }

        @Parameterized.Parameters
        public static Collection myArray() {
            return Arrays.asList(new Object[][]{
                    {2}, {Integer.MIN_VALUE}, {Integer.MAX_VALUE}});
        }


        @Test(expected = NullPointerException.class)
        public void testArrayNullPointer() throws InvocationTargetException, NoSuchMethodException, ClassNotFoundException, InstantiationException, IllegalAccessException {

            toRender = new SomeClass(foo);

            assertEquals("Instance of edu.hm.bugproducer.SomeClass\n" +
                            "foo (Type int): " + foo + "\narray (Type int[]) []\n" +
                            "date (Type java.util.Date): Fri Jan 02 11:17:36 CET 1970\n",
                    new Renderer(toRender).render());
        }

    }

    @RunWith(Parameterized.class)
    public static class ArrayParameterizedPart {


        private SomeClass toRender;
        private Renderer renderer;

        private int[] intArray;

        public ArrayParameterizedPart(int[] intArray) {
            this.intArray = intArray;
        }

        @Parameterized.Parameters
        public static Collection myArray() {
            return Arrays.asList(new Object[][]{
                    {null}});
        }


        @Test(expected = NullPointerException.class)
        public void testArrayNullPointer() throws InvocationTargetException, NoSuchMethodException, ClassNotFoundException, InstantiationException, IllegalAccessException {

            toRender = new SomeClass(intArray);
            new Renderer(toRender).render();
        }

        @Test(expected = NullPointerException.class)
        public void testNullPointerRendering() throws Exception {
            renderer = new Renderer(toRender);
            renderer.render();
        }
    }


}