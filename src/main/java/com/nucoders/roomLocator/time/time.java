package com.nucoders.roomLocator.time;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;

public class time {
    private ZoneId cairo = ZoneId.of("Africa/Cairo");
    private final String[] slots = {"free", "slot1", "slot2", "slot3", "slot4", "slot5", "slot6",
            "slot7", "slot8", "slot9", "slot10", "slot11", "slot12"};

    public String currentSlot() {
        int slotConst = 7;
        int preSlot = 1;
        int transitionMinute = 30;
        int[] freeTime = {1, 12};

        LocalTime timeNow = LocalTime.now(cairo);

        int hourNow = timeNow.getHour() - slotConst;
        int minuteNow = timeNow.getMinute();

        if (minuteNow < transitionMinute)
        {
            hourNow -= preSlot;
        }
        if (hourNow < freeTime[0]
                || hourNow > freeTime[1])
        {

            return slots[0];
        }
        return slots[hourNow];
    }
    public String currentDay() {
        LocalDate now = LocalDate.now(cairo);
        return now.getDayOfWeek().toString();
    }

}
