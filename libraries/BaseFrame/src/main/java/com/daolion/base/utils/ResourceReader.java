package com.daolion.base.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author : 
 * date    : 2012-7-8
 * 
 */
public class ResourceReader {

	public static String readString(Context context, @StringRes int id) {
		return context.getResources().getString(id);
	}
	public static int readDimen(Context context, @DimenRes int id) {
		return context.getResources().getDimensionPixelOffset(id);
	}
	public static Drawable readDrawable(Context context, @DrawableRes int id) {
		return ContextCompat.getDrawable(context, id);
	}
	public static int readColor(Context context, @ColorRes int id) {
	return 	ContextCompat.getColor(context, id);
	}

	public static  float readDimens(Context context, @DimenRes int id){
		return  context.getResources().getDimension(id);
	}


	public static String readAssertResource(Context context, String strAssertFileName) {
		AssetManager assetManager = context.getAssets();
		String strResponse = "";
		try {
			InputStream ims = assetManager.open(strAssertFileName);
			strResponse = getStringFromInputStream(ims);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return strResponse;
	}

	private static String getStringFromInputStream(InputStream a_is) {
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
		String line;
		try {
			br = new BufferedReader(new InputStreamReader(a_is));
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
				}
			}
		}
		return sb.toString();
	}
}