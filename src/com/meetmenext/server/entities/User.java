/**
 * 
 */
package com.meetmenext.server.entities;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Load;
import com.meetmenext.server.objects.UserBean;
import com.meetmenext.server.utilities.Utils;

/**
 * @author Zeeshan A. Syed
 *
 * Unless required by applicable law or agreed to in writing, software
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS
 * OF ANY KIND, either express or implied.
 *
 */
@Entity
public class User {
	@Id
	private Long id;
	@Index
	private String email;
	private String password;
	private String status;
	@Index
	private String signuptype;
	private String tos_status;
	private String town;
	private String state;
	private String zipcode;
	private String speakername;
	private String numberofalerts;
	private String bio_data;
	private String createduser;
	private String modifieduser;
	private Long createddate;
	private Long modifeddate;
	private String profilepicurl;
//	@Load
//	private List<Ref<Events>> event_list;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
	public User() {
		
	}
	
	public User(UserBean ub){
		this.id=Utils.stringToLong(ub.getId());
		this.email=ub.getEmail();
		this.password=ub.getPassword();
		this.status=ub.getStatus();
		this.createduser=ub.getCreateduser();
		this.modifieduser=ub.getModifieduser();
		this.createddate=Utils.stringToLong(ub.getCreateddate());
		this.modifeddate=Utils.stringToLong(ub.getModifieddate());
		
		this.bio_data =ub.getBio_data();
		this.signuptype = ub.getSignuptype();
		this.speakername=ub.getSpeakername();
		this.state= ub.getState();
		this.zipcode = ub.getZipcode();
		this.town = ub.getTown();
		this.tos_status = ub.getTos_status();
		this.numberofalerts = ub.getNumberofalerts();
		this.profilepicurl = ub.getProfilepicurl();
		//this.event_list = getEventsListFromBean(ub.getEvents_list());
	}
	
	public UserBean getModelObject(){
		UserBean ub = new UserBean();
		ub.setId(Utils.longToString(this.id));
		ub.setEmail(this.email);
		ub.setPassword(this.password);
		ub.setStatus(this.status);
		ub.setCreateduser(this.createduser);
		ub.setModifieduser(this.modifieduser);
		ub.setCreateddate(Utils.longToString(this.createddate));
		ub.setModifieddate(Utils.longToString(this.modifeddate));
		ub.setBio_data(this.bio_data);
		ub.setSignuptype(this.signuptype);
		ub.setSpeakername(this.speakername);
		ub.setState(this.state);
		ub.setZipcode(this.zipcode);
		ub.setTown(this.town);
		ub.setTos_status(this.tos_status);
		ub.setNumberofalerts(this.numberofalerts);
		ub.setProfilepicurl(this.profilepicurl);
		//ub.setEvents_list(this.getEventsListFromModel());
		return ub;
	}
	/*
	private List<Events> getEventsListFromModel() {
		List<Events> list = new ArrayList<>();
		for ( Ref<Events> event : this.event_list ) {
			list.add(event.get());
		}
		return list;
	}
	private List<Ref<Events>> getEventsListFromBean(List<Events> events) {
		List<Ref<Events>> list = new ArrayList<Ref<Events>>();
		for ( Events event : events) {
			list.add(Ref.create(Key.create(Events.class,Utils.stringToLong(event.getModelObject().getEvent_id()))));
		}
		return list;
	}
	*/
}
