package com.example.questions3_2.Ultilities;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class StupidFunc {
    public static ArrayList<Integer> rss(int quantity, int max) {
        // random subset
        Random generator = new Random(Calendar.getInstance().getTimeInMillis());
        Map<Integer, Integer> map = new HashMap<>();
        ArrayList<Integer> out = new ArrayList<>();

        for (int i = 0; i < quantity; ++i, --max) {
            int now = generator.nextInt(max) + 1;

            if (now == max) {
                if (!map.containsKey(now)) {
                    out.add(now);
                }
                else {
                    out.add(map.get(now));
                }
            }
            else {
                if (!map.containsKey(now)) {
                    out.add(now);
                }
                else {
                    out.add(map.get(now));
                }

                if (!map.containsKey(max)) {
                    map.put(now, max);
                }
                else {
                    map.put(now, map.get(max));
                }
            }
        }

        return out;
    }
}
