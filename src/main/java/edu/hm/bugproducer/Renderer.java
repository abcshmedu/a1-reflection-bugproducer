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
                    //Change this => crash e.g. for String
                    //Perhaps move into ArrayRenderer
                    result += attribute.getName() + " (Type " + attribute.getType().getCanonicalName() + ") ";

                    String ClassName = attribute.getAnnotation(RenderMe.class).with();
                    Object  toRendernArray= attribute.get(input);
                    Class<?> specialRenderClass = Class.forName(ClassName);
                    Object specialRenderObject = specialRenderClass.getConstructor().newInstance();
                    Method method =specialRenderClass.getMethod("render",toRendernArray.getClass());
                    Object resultObj=method.invoke(specialRenderObject,toRendernArray);
                    result+=(String) resultObj;

                }
            }

            result += "\n";

        }
        System.out.printf("result: " + result);
        return result;

    }
}
