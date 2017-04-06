package edu.hm.bugproducer;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Renderer {
    private Object input;

    public Renderer(Object input) {

        this.input = input;
    }

    public String render() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, ClassNotFoundException {

        Class<?> cut = input.getClass();

        Field[] attributes = cut.getDeclaredFields();

        String result = "Instance of " + cut.getCanonicalName() + "\n";

        for (Field attribute : attributes) {


            if (attribute.getAnnotation(RenderMe.class) != null) {
                attribute.setAccessible(true);

                if (attribute.getAnnotation(RenderMe.class).with().equals("")) {



                    try {
                        result += attribute.getName() + " (Type " + attribute.getType().getName() + "): " + attribute.get(input);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }


                } else {
                    String ClassName = attribute.getAnnotation(RenderMe.class).with();

                    Class<?> specialRenderClass = Class.forName(ClassName);
                    Object specialRenderObject = specialRenderClass.getConstructor().newInstance();

                    Method[] methods = specialRenderClass.getDeclaredMethods();

                    for (Method method : methods) {


                        method.invoke(specialRenderObject,new int[]{});



                    }


                }
            }

            result += "\n";

        }
        System.out.printf("result: " + result);
        return result;

    }
}
