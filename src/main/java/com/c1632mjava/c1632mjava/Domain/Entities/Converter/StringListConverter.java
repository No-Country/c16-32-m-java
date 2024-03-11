package com.c1632mjava.c1632mjava.Domain.Entities.Converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.ArrayList;
import java.util.Arrays;

@Converter
public class StringListConverter implements AttributeConverter<ArrayList<String>, byte[]> {
    @Override
    public byte[] convertToDatabaseColumn(ArrayList<String> attribute) {
        if(attribute != null){
            return String.join(",", attribute).getBytes();
        } else {
            return null;
        }
    }

    @Override
    public ArrayList<String> convertToEntityAttribute(byte[] dbData) {
        if(dbData != null && dbData.length > 1){
            return new ArrayList<>(Arrays.asList(new String(dbData).split(",")));
        } else {
            return null;
        }
    }
}