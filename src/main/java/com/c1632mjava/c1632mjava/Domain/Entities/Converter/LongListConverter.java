package com.c1632mjava.c1632mjava.Domain.Entities.Converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Converter
public class LongListConverter implements AttributeConverter<List<Long>, byte[]> {
    @Override
    public byte[] convertToDatabaseColumn(List<Long> attribute) {
        if (attribute != null) {
            StringBuilder builder = new StringBuilder();

            for (Long number : attribute) {
                builder.append(number).append(",");
            }

            return builder.toString().getBytes();
        }
        return null;
    }

    @Override
    public List<Long> convertToEntityAttribute(byte[] dbData) {
        if (dbData != null) {
            String dataString = new String(dbData);

            if (dataString.startsWith("[") && dataString.endsWith("]")) {
                dataString = dataString.substring(1, dataString.length() - 1);
            }

            ArrayList<String> stringArray = new ArrayList<>(Arrays.asList(dataString.split(",")));

            List<Long> longList = new ArrayList<>();

            for (String str : stringArray) {
                longList.add(Long.parseLong(str.trim()));
            }

            return longList;
        } else {
            return null;
        }
    }
}