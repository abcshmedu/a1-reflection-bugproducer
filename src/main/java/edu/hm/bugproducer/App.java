package edu.hm.bugproducer;

import java.lang.reflect.InvocationTargetException;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {

        SomeClass someClass = new SomeClass(3);

        Renderer renderer = new Renderer(someClass);

        try {
            renderer.render();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
