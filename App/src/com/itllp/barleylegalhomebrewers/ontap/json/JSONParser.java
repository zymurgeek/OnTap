package com.itllp.barleylegalhomebrewers.ontap.json;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
 
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
 
import android.util.Log;

public class JSONParser {
	static InputStream is = null;
    static JSONObject jObj = null;
    static JSONArray jArray = null;
    static String json = "";
 
    public JSONParser() {
    }
 
    /**
     * Gets the JSON tree from a given URL.
     * @param url Location to query.  If null, returns an empty object.
     */
    public JSONObject getJSONObjectFromUrl(String url) {

    	if (null == url) {
    		return new JSONObject();
    	}
        try {
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);
 
            HttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            is = httpEntity.getContent();           
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
 
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    is, "UTF-8"), 8000);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
            json = sb.toString();
        } catch (Exception e) {
            Log.e("Buffer Error", "Error converting result " + e.toString());
        }
 
        try {
            jObj = new JSONObject(json);
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data for JSONObject" + e.toString());
        }
 
        return jObj;
    }


    public JSONArray getJSONArrayFromUrl(String url) {
    	 
        try {
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);
 
            HttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            is = httpEntity.getContent();           
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
 
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    is, "UTF-8"), 8000);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
            json = sb.toString();
        } catch (Exception e) {
            Log.e("Buffer Error", "Error converting result " + e.toString());
        }
 
        try {
            jArray = new JSONArray(json);
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data for JSONArray" + e.toString());
        }
 
        return jArray;
    }
}
