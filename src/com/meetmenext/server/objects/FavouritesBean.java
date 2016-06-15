/**
 * 
 */
package com.meetmenext.server.objects;

import org.json.simple.JSONObject;

import com.meetmenext.server.entities.Speaker;

/**
 * @author Zeeshan A. Syed
 *
 * Unless required by applicable law or agreed to in writing, software
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS
 * OF ANY KIND, either express or implied.
 *
 */
public class FavouritesBean {
	public String id;
	public String speaker_id;
	private Speaker speaker;
	public String user_id;
	public boolean active;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSpeaker_id() {
		return speaker_id==null?this.speaker.getModelObject().getId():speaker_id;
		//return speaker_id;
	}
	public void setSpeaker_id(String speaker_id) {
		this.speaker_id = speaker_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public Speaker getSpeaker() {
		return speaker;
	}
	public void setSpeaker(Speaker speaker) {
		this.speaker = speaker;
	}
	
	
	
	
}
