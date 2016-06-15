/**
 * 
 */
package com.meetmenext.server.objects;

import org.json.simple.JSONObject;

/**
 * @author Zeeshan A. Syed
 *
 * Unless required by applicable law or agreed to in writing, software
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS
 * OF ANY KIND, either express or implied.
 *
 */
public class SpeakerBean {
	public String id;
	public String firstName;
	public String lastname;
	public String vid_url_title;
	public String category;
	public String web_url;
	public String bio;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getVid_url_title() {
		return vid_url_title;
	}
	public void setVid_url_title(String vid_url_title) {
		this.vid_url_title = vid_url_title;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getWeb_url() {
		return web_url;
	}
	public void setWeb_url(String web_url) {
		this.web_url = web_url;
	}
	public String getBio() {
		return bio;
	}
	public void setBio(String bio) {
		this.bio = bio;
	}

	public JSONObject toJsonObject() {
		JSONObject obj = new JSONObject();
		obj.put("speaker_id", this.id);
		obj.put("speaker_firstName", this.firstName);
		obj.put("speaker_lastname", this.lastname);
		obj.put("speaker_vid_url_title", this.vid_url_title);
		obj.put("speaker_category", this.category);
		obj.put("speaker_web_url", this.web_url);
		obj.put("speaker_bio", this.bio);
		return obj;
		
	}
}
