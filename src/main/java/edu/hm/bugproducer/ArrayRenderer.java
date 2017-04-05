package edu.hm.bugproducer;

/**
 * Created by Johannes Arzt on 05.04.17.
 */
public class ArrayRenderer {

    public ArrayRenderer(){

    }

    public String render(int[] input){
        String result = "";
        Class<?> array = input.getClass();

        result +=" DAS ist eine Test!";


        return result;
    }
}

