package edu.hm.bugproducer;

import java.lang.reflect.Field;

public class Renderer {
    private Object input;

    public Renderer(Object input) {

        this.input = input;
    }

    public String render() throws NoSuchMethodException {

        Class<?> cut = input.getClass();

        Field[] attributes = cut.getDeclaredFields();

        String result = "Instance of " + cut.getCanonicalName() + "\n";

        for (Field attribute : attributes) {

            if (attribute.getAnnotation(RenderMe.class) != null) {
                attribute.setAccessible(true);

                try {
                    result += attribute.getName() + " (Type " + attribute.getType().getName() + "): " + attribute.get(input);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

            }

            result += "\n";

        }
        System.out.printf("result: " + result);
        return result;
    }
}
