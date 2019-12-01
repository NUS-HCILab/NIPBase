package com.hci.nip.base.util;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import org.json.JSONException;
import org.json.JSONObject;

public final class JsonUtil {

    private static final Gson gson = new Gson();

    private JsonUtil() {
    }

    /**
     * @param object to be serialized
     * @return the json String
     */
    public static String getJsonString(Object object) {
        return gson.toJson(object);
    }

    /**
     * @param jsonString the string from which the object is to be deserialized
     * @param type       class of desired object
     * @param <T>        type of object
     * @return an object of type T from the string.Returns {@code null} if {@code jsonString} is {@code null} or if {@code jsonString} is empty.
     * @throws JsonParseException if json is not a valid representation
     */
    public static <T> T getObjectFromJson(String jsonString, Class<T> type) throws JsonParseException {
        try {
            return gson.fromJson(jsonString, type);
        } catch (JsonSyntaxException e) {
            throw new JsonParseException(e);
        }
    }

    /**
     * @param jsonString json representation of string
     * @return the {@link JSONObject object}
     * @throws JsonParseException if the {@code jsonString} is not valid
     */
    public static JSONObject getJsonObject(String jsonString) throws JsonParseException {
        try {
            return new JSONObject(jsonString);
        } catch (JSONException e) {
            throw new JsonParseException(e);
        }
    }

    /**
     * A RuntimeException of handling json parsing
     */
    public static class JsonParseException extends RuntimeException {
        public JsonParseException(Throwable cause) {
            super(cause);
        }
    }

    /**
     * @param newProperty
     * @param jsonString
     * @return a string representation of a wrapped JSOn string
     * e.g. newProperty: 'data', jsonString: '[ {}, {}]' => '{data: [{}, {}]}'
     */
    public static String getWrappedJsonString(String newProperty, String jsonString) {
        return "{\"" + newProperty + "\":" + jsonString + "}";
    }

}
