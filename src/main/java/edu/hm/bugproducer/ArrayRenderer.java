package edu.hm.bugproducer;

/**
 * Created by Johannes Arzt on 05.04.17.
 */
public class ArrayRenderer {

    public ArrayRenderer(){

    }

    public String render(int[] input){
        String result = "[";

        for(int i=0; i<input.length;i++){
        result+=input[i]+", ";

        }

        result+="]";




        return result;
    }
}

