package com.tolgaozgun.meettime.annotations;

import com.tolgaozgun.meettime.entity.RoomSchedule;
import com.tolgaozgun.meettime.entity.enums.ScheduleType;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class RoomDatesValidator implements ConstraintValidator<ValidRoomDates, RoomSchedule> {

    @Override
    public boolean isValid(RoomSchedule roomSchedule, ConstraintValidatorContext context) {
        if (roomSchedule.getScheduleType() == ScheduleType.ONCE) {
            return roomSchedule.getStartDate() != null
                    && roomSchedule.getEndDate() != null;
        } else {
            return roomSchedule.getStartDate() == null
                    && roomSchedule.getEndDate() == null;
        }
    }
}