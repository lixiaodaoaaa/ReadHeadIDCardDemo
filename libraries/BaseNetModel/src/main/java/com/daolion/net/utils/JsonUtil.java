package com.daolion.net.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtil {
	public static boolean isEmptyJsonElement(JsonElement element) {
		return element == null || element.isJsonNull();
	}
	
	public static String jsonElementToString(JsonElement element) {
		String str = "";
		if (!isEmptyJsonElement(element)) {
			str = element.getAsString().trim();
		}
		return str;
	}
	
	public static JsonObject jsonElementToJsonObject(JsonElement element) {
		JsonObject jsonObject = new JsonObject();
		if (!isEmptyJsonElement(element)) {
			jsonObject = element.getAsJsonObject();
		}
		return jsonObject;
	}

	public static int jsonElementToInteger(JsonElement element) {
		int i = 0;
		if (!isEmptyJsonElement(element)) {
			i = element.getAsInt();
		}
		return i;
	}
	
	public static int jsonElementToInteger(JsonElement element,int defaultValue) {
		int i = defaultValue;
		if (!isEmptyJsonElement(element)) {
			i = element.getAsInt();
		}
		return i;
	}

	public static boolean jsonElementToBoolean(JsonElement element) {
		boolean b = false;
		if (!isEmptyJsonElement(element)) {
			b = element.getAsBoolean();
		}
		return b;
	}
	
	public static long jsonElementToLong(JsonElement element) {
		return  jsonElementToLong(element , 0);
	}

	public static long jsonElementToLong(JsonElement element ,int defaultValue) {
		long l = defaultValue;
		if (!isEmptyJsonElement(element)) {
			l = element.getAsLong();
		}
		return l;
	}

	public static Double jsonElementToDouble(JsonElement element) {
		double d = 0D;
		if (!isEmptyJsonElement(element)) {
			d = element.getAsDouble();
		}
		return d;
	}

	public static Float jsonElementToFloat(JsonElement element) {
		Float d = 0F;
		if (!isEmptyJsonElement(element)) {
			d = element.getAsFloat();
		}
		return d;
	}

	public static JsonArray jsonElementToArray(JsonElement element) {
		JsonArray array = new JsonArray();
		if (!isEmptyJsonElement(element)) {
			try {
				array = element.getAsJsonArray();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return array;
	}




	///////////////////////////////////////////////////////////////////////////
	// jsonElement跟的是一堆strings 我们变成ListStrs即可////////////////////////
	///////////////////////////////////////////////////////////////////////////
	public static List<String> jsonElemenToListString(JsonElement  element){
		List<String> listStrs  = new ArrayList<>();
		if(!isEmptyJsonElement(element)){
			JsonArray jsonArray = element.getAsJsonArray();
			for(int i=0;i<jsonArray.size();i++){
				listStrs.add(jsonElementToString(jsonArray.get(i)));
			}
		}
		return  listStrs;
	}
}
