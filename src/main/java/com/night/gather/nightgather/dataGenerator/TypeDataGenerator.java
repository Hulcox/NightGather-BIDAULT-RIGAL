package com.night.gather.nightgather.dataGenerator;

import com.night.gather.nightgather.entity.Type;
import com.night.gather.nightgather.entity.TypeEvent;

import java.util.ArrayList;
import java.util.List;

public class TypeDataGenerator {

    public static List<Type> generateTypes() {
        List<Type> types = new ArrayList<>();
        for (TypeEvent typeEvent : TypeEvent.values()) {
            Type type = new Type();
            type.setType(typeEvent);
            types.add(type);
        }
        return types;
    }
}