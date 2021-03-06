package edu.hm.bugproducer;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Renderclass.
 */
public class Renderer {
    private Object input;


    /**
     * Costum constructor.
     *
     * @param input object to render
     */
    public Renderer(Object input) {

        this.input = input;
    }

    /**
     * Method for rendering.
     *
     * @return result string of the object.
     * @throws NoSuchMethodException     method doesnt exist
     * @throws IllegalAccessException    illegal access
     * @throws InvocationTargetException cant invoke method
     * @throws InstantiationException    cant create instance
     * @throws ClassNotFoundException    class not found
     */
    public String render() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, ClassNotFoundException {

        Class< ? > classToRender = input.getClass();


        Field[] attributes = classToRender.getDeclaredFields();

        StringBuilder result = new StringBuilder("Instance of " + classToRender.getCanonicalName() + "\n");

        for (Field attribute : attributes) {


            if (attribute.getAnnotation(RenderMe.class) != null) {
                attribute.setAccessible(true);

                if (attribute.getAnnotation(RenderMe.class).with().equals("")) {
                    result.append(attribute.getName()).append(" (Type ").append(attribute.getType().getCanonicalName()).append("): ").append(attribute.get(input));

                } else {
                    //Change this => crash e.g. for String
                    //Perhaps move into ArrayRenderer
                    result.append(attribute.getName()).append(" (Type ").append(attribute.getType().getCanonicalName()).append(") ");

                    String className = attribute.getAnnotation(RenderMe.class).with();
                    Object toRendernArray = attribute.get(input);
                    Class< ? > specialRenderClass = Class.forName(className);
                    Object specialRenderObject = specialRenderClass.getConstructor().newInstance();
                    Method method = specialRenderClass.getMethod("render", toRendernArray.getClass());
                    Object resultObj = method.invoke(specialRenderObject, toRendernArray);
                    result.append((String) resultObj);

                }
            }

            result.append("\n");

        }

        Method[] methods = classToRender.getMethods();

        for (Method method : methods) {
            if (method.getAnnotation(RenderMe.class) != null) {
                Object ott = classToRender.getConstructor().newInstance();
                try {
                    result.append((String) method.invoke(ott));
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }

        return result.toString();

    }
}
