/**
 * 
 */
package com.meetmenext.server.objects;

import java.util.List;

import org.json.simple.JSONObject;

import com.meetmenext.server.entities.User;



/**
 * @author Zeeshan A. Syed
 *
 * Unless required by applicable law or agreed to in writing, software
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS
 * OF ANY KIND, either express or implied.
 *
 */
public class EventsBean {
	public String event_id;
	public String event_name;	
	public String event_state;
	public String event_town;
	public String event_zip;
	public String event_speaker_name;
	public String event_date;
	public String isSubscribed;
	//private List<User> user_list;
	
	/*
	public List<User> getUser_list() {
		return user_list;
	}
	public void setUser_list(List<User> user_list) {
		this.user_list = user_list;
	}*/
	
	public String getEvent_id() {
		return event_id;
	}
	public String getIsSubscribed() {
		return isSubscribed;
	}
	public void setIsSubscribed(String isSubscribed) {
		this.isSubscribed = isSubscribed;
	}
	public void setEvent_id(String event_id) {
		this.event_id = event_id;
	}
	public String getEvent_name() {
		return event_name;
	}
	public void setEvent_name(String event_name) {
		this.event_name = event_name;
	}
	public String getEvent_state() {
		return event_state;
	}
	public void setEvent_state(String event_state) {
		this.event_state = event_state;
	}
	public String getEvent_town() {
		return event_town;
	}
	public void setEvent_town(String event_town) {
		this.event_town = event_town;
	}
	public String getEvent_zip() {
		return event_zip;
	}
	public void setEvent_zip(String event_zip) {
		this.event_zip = event_zip;
	}
	public String getEvent_speaker_name() {
		return event_speaker_name;
	}
	public void setEvent_speaker_name(String speaker_name) {
		this.event_speaker_name = speaker_name;
	}
	public String getEvent_date() {
		return event_date;
	}
	public void setEvent_date(String event_date) {
		this.event_date = event_date;
	}
	
	/**
	 * 
	 */
	public JSONObject toJsonObject() {
		JSONObject obj = new JSONObject();
		obj.put("event_id", this.event_id);
		obj.put("event_name", this.event_name);
		obj.put("event_state", this.event_state);
		obj.put("event_town", this.event_town);
		obj.put("event_zip", this.event_zip);
		obj.put("event_speaker_name", this.event_speaker_name);
		obj.put("event_date", this.event_date);
		obj.put("subscribed", this.isSubscribed);
		return obj;
		
	}
	
}
