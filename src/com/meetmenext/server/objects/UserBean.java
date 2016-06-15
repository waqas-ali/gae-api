/**
 * 
 */
package com.meetmenext.server.objects;

import java.util.List;

import org.json.simple.JSONObject;

import com.meetmenext.server.entities.Events;

/**
 * @author Zeeshan A. Syed
 *
 * Unless required by applicable law or agreed to in writing, software
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS
 * OF ANY KIND, either express or implied.
 *
 */
public class UserBean {
	
	private String id,createddate,modifieddate,profilepicurl;
	private String email,password,status,createduser,modifieduser,signuptype,tos_status,town,state,zipcode,speakername,numberofalerts,bio_data;
	//private List<Events> events_list;
	
	
//	public List<Events> getEvents_list() {
//		return events_list;
//	}
//	public void setEvents_list(List<Events> events_list) {
//		this.events_list = events_list;
//	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCreateddate() {
		return createddate;
	}
	public void setCreateddate(String createddate) {
		this.createddate = createddate;
	}
	public String getModifieddate() {
		return modifieddate;
	}
	public void setModifieddate(String modifieddate) {
		this.modifieddate = modifieddate;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCreateduser() {
		return createduser;
	}
	public void setCreateduser(String createduser) {
		this.createduser = createduser;
	}
	public String getModifieduser() {
		return modifieduser;
	}
	public void setModifieduser(String modifieduser) {
		this.modifieduser = modifieduser;
	}
	public String getSignuptype() {
		return signuptype;
	}
	public void setSignuptype(String signuptype) {
		this.signuptype = signuptype;
	}
	public String getTos_status() {
		return tos_status;
	}
	public void setTos_status(String tos_status) {
		this.tos_status = tos_status;
	}
	public String getTown() {
		return town;
	}
	public void setTown(String town) {
		this.town = town;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getSpeakername() {
		return speakername;
	}
	public void setSpeakername(String speakername) {
		this.speakername = speakername;
	}
	public String getNumberofalerts() {
		return numberofalerts;
	}
	public void setNumberofalerts(String numberofalerts) {
		this.numberofalerts = numberofalerts;
	}
	public String getBio_data() {
		return bio_data;
	}
	public void setBio_data(String bio_data) {
		this.bio_data = bio_data;
	}
	
	public String getProfilepicurl() {
		return profilepicurl;
	}
	public void setProfilepicurl(String profilepicurl) {
		this.profilepicurl = profilepicurl;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("u_userid", id);
		jsonObject.put("u_email", email);
		jsonObject.put("u_status", status);
		jsonObject.put("u_state", state);
		jsonObject.put("u_zip", zipcode);
		jsonObject.put("u_biodata", bio_data);
		jsonObject.put("u_numberofalerts", numberofalerts);
		jsonObject.put("u_speakername", speakername);
		jsonObject.put("u_profilepicurl", profilepicurl);
		//jsonobject.put
		
		return jsonObject.toString();
	}
	
}
