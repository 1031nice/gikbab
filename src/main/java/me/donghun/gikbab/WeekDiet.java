package me.donghun.gikbab;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class WeekDiet {

    Map<String, String> weekDiet = new HashMap<>();

    public Map<String, String> getWeekDiet() {
        return weekDiet;
    }

    public void setWeekDiet(Map<String, String> weekDiet) {
        this.weekDiet = weekDiet;
    }
}
