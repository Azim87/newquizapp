package com.example.quizapp.data.local.converter;

import androidx.room.TypeConverter;

import com.example.quizapp.models.Question;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class QuestionConverters {
    @TypeConverter
    public static List<Question> fromRaw(String raw) {
        if (raw == null) {
            return null;
        }

        Gson gson = new Gson();
        Type type = new TypeToken<List<Question>>() {}.getType();
        return gson.fromJson(raw, type);
    }

    @TypeConverter
    public static String toRaw(List<Question> questions) {
        if (questions == null) {
            return null;
        }

        Gson gson = new Gson();
        Type type = new TypeToken<List<Question>>() {}.getType();
        return gson.toJson(questions, type);
    }
}
