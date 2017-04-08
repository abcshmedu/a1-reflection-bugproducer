package edu.hm.bugproducer;

/**
 * Created by Johannes Arzt on 05.04.17.
 */
public class ArrayRenderer {

    /**
     * Default Constructor.
     */
    public ArrayRenderer() {

    }

    /**
     * Method to render int arrays.
     *
     * @param input int array
     * @return int arrays values
     */
    public String render(int[] input) {
        String result = "[";

        for (int i = 0; i < input.length; i++) {
            result += input[i] + ", ";
        }
        result += "]";

        return result;
    }

}

