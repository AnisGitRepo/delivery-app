//package com.example.delivery;
//
//import com.fasterxml.jackson.core.JsonParser;
//import com.fasterxml.jackson.databind.DeserializationContext;
//import com.fasterxml.jackson.databind.JsonDeserializer;
//
//import java.io.IOException;
//import java.time.LocalTime;
//import java.time.format.DateTimeFormatter;
//
//import org.springframework.boot.jackson.JsonComponent;
//
//
//import java.util.Arrays;
//import java.util.List;
//
//
//@JsonComponent
//public class CustomLocalTimeDeserializer extends JsonDeserializer<LocalTime> {
//
//    private static final List<DateTimeFormatter> FORMATTERS = Arrays.asList(
//            DateTimeFormatter.ISO_LOCAL_TIME,
//            DateTimeFormatter.ofPattern("HH:mm:ss")
//    );
//
//    @Override
//    public LocalTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
//        String timeStr = p.getValueAsString();
//        if (timeStr == null) {
//            return null; // Handle null values gracefully
//        }
//        for (DateTimeFormatter formatter : FORMATTERS) {
//            try {
//                return LocalTime.parse(timeStr, formatter);
//            } catch (Exception e) {
//                // Try the next format
//            }
//        }
//        throw new IllegalArgumentException("Unsupported time format: " + timeStr);
//    }
//}
//
//
//
