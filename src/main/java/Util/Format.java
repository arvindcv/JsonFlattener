package Util;

import Flattener.Errors;
import Flattener.FormatterValues;
import com.fasterxml.jackson.databind.JsonNode;

public class Format {
    public static String formatError(String reason){
        if(reason == null || reason.isEmpty()){
            return null;
        }
        return FormatterValues.BEGIN_BRACES+
                FormatterValues.NEW_LINE+
                FormatterValues.FIELD_ERROR+
                FormatterValues.FIELD_VALUE_SEPARATOR+
                FormatterValues.QUOTES+
                reason+
                FormatterValues.QUOTES+
                FormatterValues.NEW_LINE+
                FormatterValues.END_BRACES;
    }
    public static String formatJsonFields(String key, JsonNode value){
        if(key==null||value == null){
            return null;
        }
        return FormatterValues.QUOTES+
                key+
                FormatterValues.QUOTES+
                FormatterValues.FIELD_VALUE_SEPARATOR+
                value+
                FormatterValues.FIELD_TERMINATOR+
                FormatterValues.NEW_LINE;
    }
    public static String removeTrailingChar(String Json,String charToRemove){

        if(Json == null || Json.isEmpty()){
            return null;
        }
        if(charToRemove==null || charToRemove.isEmpty()){
            return Json;
        }
        String formattedString="";
        if(charToRemove.charAt(0)== Json.charAt(Json.length()-2)) {
            formattedString = Json.substring(0,Json.length() - 2)+
                    FormatterValues.NEW_LINE+
                    FormatterValues.END_BRACES;
        }else{
            formattedString = Json;
        }
        return formattedString;
    }
    public static String validateFlattendedJson(String flattenedJson){
        if(flattenedJson==null || flattenedJson.isEmpty()){
            return null;
        }
        if(flattenedJson.contains(Errors.ARRAYS_NOT_SUPPORTED)){
            return formatError(Errors.ARRAYS_NOT_SUPPORTED);
        }
        return flattenedJson;
    }
    public static String formatKey(String parentKey,String currentKey){
        if(parentKey==null || currentKey == null){
            return null;
        }
        String key = parentKey.isEmpty() ? (currentKey) :
                (parentKey + FormatterValues.FIELD_DELIMITER + currentKey);
        return key;
    }
}

