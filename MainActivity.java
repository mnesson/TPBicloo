package com.example.tpbicloo;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Comparator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends Activity {
	
	private MainActivity self = this;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		RequestQueue queue = Volley.newRequestQueue(this);
		String url ="https://api.jcdecaux.com/vls/v1/stations?contract=Nantes&apiKey=f87d26d699d8b4f2d4bc383acbcd23ee221ca215";
		
		JsonArrayRequest jsObjRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>(){
		
			@Override
			public void onResponse(JSONArray response) {
				// TODO Auto-generated method stub
				Log.d("<<<<", response.toString());
				
				final ArrayList<BikeStation> arrayBikeStation = new ArrayList<BikeStation>();
				ListView arretListView = (ListView) findViewById(R.id.listArrets);
				for (int i = 0; i <response.length() ; ++i){
					try {
						BikeStation bikeStation = new BikeStation(new String(response.getJSONObject(i).getString("name").getBytes("8859-1"), "UTF-8"),
								response.getJSONObject(i).getInt("available_bikes"), 
								response.getJSONObject(i).getInt("bike_stands"));
						arrayBikeStation.add(bikeStation);
						
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				ArrayAdapter<BikeStation> adapter = new ArrayAdapter<BikeStation>(self, android.R.layout.simple_list_item_1, arrayBikeStation);
				adapter.sort(new Comparator<BikeStation>(){
					public int compare(BikeStation args0, BikeStation args1){
						return args0.name.compareTo(args1.name);
					}
				});
				arretListView.setAdapter(adapter);
				
				
				
			}
		}, new Response.ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				// TODO Auto-generated method stub
				Log.d("xxx", error.toString());
			}

		});
		queue.add(jsObjRequest);
	
	}

}
