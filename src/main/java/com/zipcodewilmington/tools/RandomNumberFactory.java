package com.zipcodewilmington.tools;
import java.awt.*;
import java.util.*;

/**
 * Created by Leon on 2/4/2017.
 */

public abstract class RandomNumberFactory {
    private static final Random random = new Random();

    public static Float createFloat(float min, float max) {
        return random.nextFloat() * (max - min) + min;
    }

    public static Integer createInteger(Integer min, Integer max) {
        return createFloat(min, max).intValue();
    }
}
