package me.arkadii.gumenniy.matrix;

import java.util.Arrays;

/**
 * Created by sebastian on 21.09.16.
 */
public class Algorithm {

    public static int[][] calculateMatrix(int width, int height, int posX, int posY) {
        int[][] result = new int[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                result[i][j] = Math.max(Math.abs(posX - j), Math.abs(posY - i));
            }
        }
        return result;
    }
}
