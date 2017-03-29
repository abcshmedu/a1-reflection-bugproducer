package edu.hm.bugproducer;

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
        }
    }
}
