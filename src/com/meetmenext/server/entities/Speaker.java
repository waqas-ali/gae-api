/**
 * 
 */
package com.meetmenext.server.entities;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.meetmenext.server.objects.SpeakerBean;
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
public class Speaker {
	@Id
	private Long id;
	@Index
	private String firstName;
	@Index
	private String lastName;
	private String vid_url_title;
	private String category;
	private String web_url;
	private String bio;
	
	public Speaker(){
		
	}
	
	public Speaker(SpeakerBean sb){
		this.id = Utils.stringToLong(sb.getId());
		this.firstName = sb.getFirstName();
		this.lastName = sb.getLastname();
		this.vid_url_title = sb.getVid_url_title();
		this.web_url = sb.getWeb_url();
		this.bio = sb.getBio();
	}
	
	public SpeakerBean getModelObject(){
		SpeakerBean sb = new SpeakerBean();
		sb.id = Utils.longToString(this.id);
		sb.firstName = this.firstName;
		sb.lastname = this.lastName;
		sb.vid_url_title = this.vid_url_title;
		sb.category = this.category;
		sb.web_url = this.web_url;
		sb.bio = this.bio;
		return sb;
	}
}
