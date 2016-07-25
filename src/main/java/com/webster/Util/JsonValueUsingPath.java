package com.webster.Util;


import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import java.util.Arrays;
import java.util.regex.Pattern;
import static com.webster.Util.JsonValueUsingPath.VALUE_TYPE.JSONARRAY;
import static com.webster.Util.JsonValueUsingPath.VALUE_TYPE.JSONOBJECT;
import static com.webster.Util.JsonValueUsingPath.VALUE_TYPE.RAWVALUE;

/**
 * Created by amit on 23/7/16.
 */
@Component
public class JsonValueUsingPath {

    final String indexRegex = "\\w[\\d]";
    Pattern regex = Pattern.compile(indexRegex);
    enum VALUE_TYPE {
            JSONOBJECT,JSONARRAY,RAWVALUE;
    }


    public Object getGenericValue(String json,String path) throws Exception {

        JSONObject jsonObj = null;
        String[] paths = path.split("\\.");
        boolean valueFound = false;
        for (String key : paths){
            VALUE_TYPE value_type = valueTYpe(json);
            switch (value_type){
                case JSONARRAY:
                    throw new Exception("JSOn Path Format not supported");

                case JSONOBJECT:
                    jsonObj = new JSONObject(json);
                    Integer index = null;
                    if(isPointingArray(key)){
                         index= Integer.parseInt(key.substring(key.length()-2,key.length()-1));
                        key = key.substring(0,key.length()-3);
                    }
                    json = jsonObj.getString(key);
                    if(index!=null){
                        json = new JSONArray(json).getString(index);
                    }
                    break;
                case RAWVALUE:
                    valueFound = true;
                    break;
            }
            if(valueFound)break;
        }

        switch (valueTYpe(json)){
            case JSONOBJECT:
                return new JSONObject(json);
            case JSONARRAY:
                return new JSONArray(json);
            case RAWVALUE:
                return json;
        }

        return null;
    }

    private boolean isPointingArray(String key) {
            return key.lastIndexOf("]")==key.length()-1 && key.contains("[");
    }

    private VALUE_TYPE valueTYpe(String indexRegex){
        if(indexRegex.indexOf("{")==0 && indexRegex.lastIndexOf("}")==indexRegex.length()-1){
            return JSONOBJECT;
        }
        if(indexRegex.indexOf("[")==0 && indexRegex.lastIndexOf("]")==indexRegex.length()-1){
            return JSONARRAY;
        }
        return RAWVALUE;
    }

    public static void main(String[] args) throws Exception {
        String dummyJson = "{\n" +
                "  \"_id\": \"5793534c25f98212a73ad920\",\n" +
                "  \"index\": 0,\n" +
                "  \"guid\": \"94634667-f199-47cd-ae91-d53d7fafe840\",\n" +
                "  \"isActive\": false,\n" +
                "  \"balance\": \"$3,019.37\",\n" +
                "  \"picture\": \"http://placehold.it/32x32\",\n" +
                "  \"age\": 36,\n" +
                "  \"eyeColor\": \"blue\",\n" +
                "  \"name\": {\n" +
                "    \"first\": \"Lauren\",\n" +
                "    \"last\": \"Osborne\"\n" +
                "  },\n" +
                "  \"company\": \"ENOMEN\",\n" +
                "  \"email\": \"lauren.osborne@enomen.me\",\n" +
                "  \"phone\": \"+1 (955) 552-2056\",\n" +
                "  \"address\": \"340 Barlow Drive, Graball, Kansas, 2505\",\n" +
                "  \"latitude\": \"29.805464\",\n" +
                "  \"longitude\": \"-87.702762\",\n" +
                "  \"tags\": [\n" +
                "    \"occaecat\",\n" +
                "    \"nostrud\",\n" +
                "    \"amet\",\n" +
                "    \"sit\",\n" +
                "    \"incididunt\"\n" +
                "  ],\n" +
                "  \"range\": [\n" +
                "    0,\n" +
                "    1,\n" +
                "    2,\n" +
                "    3,\n" +
                "    4,\n" +
                "    5,\n" +
                "    6,\n" +
                "    7,\n" +
                "    8,\n" +
                "    9\n" +
                "  ],\n" +
                "  \"friends\": [\n" +
                "    {\n" +
                "      \"id\": 0,\n" +
                "      \"name\": \"Mae Nelson\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 1,\n" +
                "      \"name\": \"Keri Houston\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 2,\n" +
                "      \"name\": \"Sue Nunez\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"greeting\": \"Hello, Lauren! You have 5 unread messages.\",\n" +
                "  \"favoriteFruit\": \"strawberry\"\n" +
                "}";

        JsonValueUsingPath jsonValueUsingPath = new JsonValueUsingPath();
        System.out.println(jsonValueUsingPath.getGenericValue(dummyJson,"name.first"));
    }
}
