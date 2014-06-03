package org.d214.whs.wcc.portal;
//package org.d214.whs.wcc.portal;
//
//import java.io.InputStream;
//import java.lang.reflect.Type;
//import java.net.HttpURLConnection;
//import java.net.URL;
//
//import android.os.AsyncTask;
//import android.widget.ArrayAdapter;
//
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import com.google.gson.reflect.TypeToken;
//
//public class GetJSon extends AsyncTask <String, Integer, Long>{
//	
//	private String[] listEvents;
//
//	@Override
//	protected Long doInBackground(String... params) {
//		// TODO Auto-generated method stub
//		
//		try{
//			URL upcomingEvents = new URL("http://1.wccsandbox.appspot.com/wcc_prototype");
//			HttpURLConnection connection = (HttpURLConnection) upcomingEvents.openConnection();
//			
//			connection.connect();
//			InputStream inputStream = connection.getInputStream();
//			
//			byte[] response = new byte[inputStream.available()];
//			
//			inputStream.read(response);
//			String responseJSon = new String(response);
//				
//			Gson gson = new GsonBuilder().create();
//			//gson.fromJson(response, String[].class);
//			Type type = new TypeToken<String[]>(){}.getType();
//			
//			listEvents = gson.fromJson(responseJSon, type);
//			//System.out.println(listEvents.length);
//
//			
//		} catch(Exception e){
//		
//		}	
//		
//		return null;
//		}
//	
//		@Override
//		protected void onPostExecute(Long result) {
//		// TODO Auto-generated method stub
//		super.onPostExecute(result);
//		ArrayAdapter<String> adapter = new ArrayAdapter<String>(Events.this, android.R.layout.simple_list_item_1, listEvents);
//        setListAdapter(adapter);
//		
//	}
//
//	
//}
//
//
//
