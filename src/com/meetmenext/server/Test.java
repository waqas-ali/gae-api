 /**
 * 
 */
package com.meetmenext.server;

import static com.meetmenext.server.utilities.OFYService.ofy;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.math.RandomUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.VoidWork;
import com.googlecode.objectify.util.Closeable;
import com.meetmenext.server.entities.Events;
import com.meetmenext.server.entities.Speaker;
import com.meetmenext.server.objects.EventsBean;
import com.meetmenext.server.objects.SpeakerBean;

/**
 * @author Zeeshan A. Syed
 *
 * Unless required by applicable law or agreed to in writing, software
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS
 * OF ANY KIND, either express or implied.
 *
 */
public class Test extends HttpServlet{
	private static final String USER_AGENT = "Mozilla/5.0";
	public static void main(String[] args) {
		
		//uploadEventsToDatabase();
		//uploadSpeakersToDatabase();
		try {
			sendPost();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Inside Test Servelt2");
		List<Key<Speaker>> keys1 = ofy().load().type(Speaker.class).keys().list();
		ofy().delete().keys(keys1).now();
		List<Key<Events>> keys2 = ofy().load().type(Events.class).keys().list();
		ofy().delete().keys(keys2).now();
		
		uploadEventsToDatabase();
		
		uploadSpeakersToDatabase();
		
		Enumeration params = req.getParameterNames(); 
		while(params.hasMoreElements()){
		 String paramName = (String)params.nextElement();
		 System.out.println("Attribute Name - "+paramName+", Value - "+req.getParameter(paramName));
		}
		
	}
	
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("Inside Test Servelt2 POST");
		Enumeration params = req.getParameterNames(); 
		while(params.hasMoreElements()){
		 String paramName = (String)params.nextElement();
		 System.out.println("Attribute Name - "+paramName+", Value - "+req.getParameter(paramName));
		}
	}
	
	// HTTP GET request
		private static void sendGet() throws Exception {

			String url = "http://localhost:8888/server/imageupload?u_userid=4615814194000322291";
			
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();

			// optional default is GET
			con.setRequestMethod("GET");

			//add request header
			con.setRequestProperty("User-Agent", USER_AGENT);

			int responseCode = con.getResponseCode();
			System.out.println("\nSending 'GET' request to URL : " + url);
			System.out.println("Response Code : " + responseCode);

			BufferedReader in = new BufferedReader(
			        new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			//print result
			System.out.println(response.toString());

		}
		
		// HTTP POST request
		private static void sendPost() throws Exception {

			//String url = "http://meet-me-next.appspot.com/server";
			String url = "http://localhost:8888/server";
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();

			//add request header
			con.setRequestMethod("POST");
			con.setRequestProperty("User-Agent", USER_AGENT);
			con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

			//String urlParameters = "req-type=signup&u_email=test1@gmail.com&u_password=freedom&u_signuptype=l&u_tosstatus=1&u_zipcode=190001&u_state=texas&u_town=new-jersey&u_biodata=i am a good boy"; //Login
			//String urlParameters = "req-type=search-speaker&query_param=Sarah"; 
					//&u_signuptype=l&u_tosstatus=1&u_zipcode=190001&u_state=texas&u_town=new-jersey&u_biodata=i am a good boy";
			//String urlParameters ="req-type=2&u_userid=4615814194000322291";
			// Send post request
			//String urlParameters ="req-type=login&u_email=test1@gmail.com&u_password=freedom";
			//String urlParameters ="req-type=update-favorite-speaker&query_param_1=5284386384216196834&query_param_2=2976592368517180&query_param_3=1";
			//String urlParameters = "req-type=get-favorite-speakers&query_param=5284386384216196834";
			//String urlParameters = "req-type=subscribe-event&user_id=8683428335461955991&event_id=4890893170017159408";
			String urlParameters = "req-type=unsubscribe-event&user_id=8683428335461955991&event_id=4890893170017159408";
			//String urlParameters = "req-type=fetch-event-list&user_id=8683428335461955991";
			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(urlParameters);
			wr.flush();
			wr.close();

			int responseCode = con.getResponseCode();
			System.out.println("\nSending 'POST' request to URL : " + url);
			System.out.println("Post parameters : " + urlParameters);
			System.out.println("Response Code : " + responseCode);

			BufferedReader in = new BufferedReader(
			        new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			
			//print result
			System.out.println(response.toString());

		}
		
		static void uploadEventsToDatabase(){
			System.out.println("Starting Upload events");
			BufferedReader br = null;
			String line = "";
			String csvSplitBy = ",";
			try {
				FileInputStream is = new FileInputStream(new File("Geography_event.csv"));
				br = new BufferedReader(new InputStreamReader(is,"ISO-8859-1"));
				//List<EventsBean> eventslist = new ArrayList<EventsBean>();
				int recordnum = 1;
				while ((line = br.readLine()) != null) {
					if(line!=null || line.isEmpty()){
						line = line.trim().replace("\"", "");
						
						String[] fields = line.split(csvSplitBy);
						//Sample record Spinosaurus: Lost Giant of the Cretaceous,Nizar Ibrahim,Illinois,Chicago,60601,10/12/2015,7:00 p.m.
						final EventsBean eb = new EventsBean();
						try{
						
						eb.setEvent_id(String.valueOf(RandomUtils.nextLong()));
						eb.setEvent_name(fields[0].trim());
						eb.setEvent_speaker_name(fields[1].trim());
						eb.setEvent_town(fields[2].trim());
						eb.setEvent_state(fields[3].trim());
						eb.setEvent_zip(fields[4].trim());
						eb.setEvent_date(fields[5].trim()+" "+fields[6].trim());
						//eventslist.add(eb);
						ObjectifyService.run(new VoidWork() {
							
							@Override
							public void vrun() {
								ofy().save().entity(new Events(eb)).now();
								
							}
						});
						
						
						recordnum ++;
						}
						catch (Exception e) {
							e.printStackTrace();
							System.out.println("Error in Reading record:"+ recordnum);
						}
					}
					
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Error1");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Error2");
			}
			
		}
		
		static void uploadSpeakersToDatabase(){
			
			BufferedReader br = null;
			String line = "";
			String csvSplitBy = ",";
			try {
				FileInputStream is = new FileInputStream(new File("Speakers_list.txt"));
				br = new BufferedReader(new InputStreamReader(is,"ISO-8859-1"));
				//List<EventsBean> eventslist = new ArrayList<EventsBean>();
				int recordnum = 1;
				//int num_fields = 0;
				while ((line = br.readLine()) != null) {
					if(line!=null || line.isEmpty()){
						line = line.trim().replace("\"", "");
						
						String[] fields = line.split(csvSplitBy);
						//Sample record Spinosaurus: Lost Giant of the Cretaceous,Nizar Ibrahim,Illinois,Chicago,60601,10/12/2015,7:00 p.m.
						final SpeakerBean sb = new SpeakerBean();
						try{
						
						sb.setId(String.valueOf(RandomUtils.nextLong()));
						sb.setFirstName(fields[0].trim());
						sb.setLastname(fields[1].trim());
						sb.setVid_url_title(fields[2].trim());
						sb.setCategory(fields[3].trim());
						sb.setWeb_url(fields[4].trim());
						StringBuilder bio=new StringBuilder();
						if(fields.length>5){
							int i =5;
							while(i<fields.length){
								bio .append(fields[i].trim());
								i++;
							}
						}
						sb.setBio(bio.toString());
						//eventslist.add(eb);
						ObjectifyService.run(new VoidWork() {
							
							@Override
							public void vrun() {
								ofy().save().entity(new Speaker(sb)).now();
								
							}
						});
						
						
						recordnum ++;
						}
						catch (Exception e) {
							e.printStackTrace();
							System.out.println("Error in Reading record:"+ recordnum);
							continue;
						}
					}
					
				}
				System.out.println("records:"+recordnum);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
}
