/**
 * 
 */
package com.meetmenext.server.service;
import static com.meetmenext.server.utilities.OFYService.ofy;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang.math.RandomUtils;
import org.json.simple.JSONObject;

import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.meetmenext.server.entities.User;
import com.meetmenext.server.objects.UserBean;
import com.meetmenext.server.utilities.Constants;
import com.meetmenext.server.utilities.Utils;


/**
 * @author Zeeshan A. Syed
 *
 * Unless required by applicable law or agreed to in writing, software
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS
 * OF ANY KIND, either express or implied.
 *
 */
public class UserService {
	private BlobstoreService blobservice = BlobstoreServiceFactory.getBlobstoreService();
	String handleProfilePictureUpload(){
		return null;
	}
	
	public static String handleUserSignUp(UserBean ub){
			JSONObject jsonobj = null;
			try{
				String id = String.valueOf(RandomUtils.nextLong());
				ub.setId(id);
				ub.setCreateddate(Utils.longToString(Utils.millis()));
				ub.setModifieddate(Utils.longToString(Utils.millis()));
				ub.setCreateduser(Constants.ADMIN);
				ub.setModifieduser(Constants.ADMIN);
				ofy().save().entity(new User(ub)).now();
				jsonobj = new JSONObject();
				jsonobj.put("u_userid", ub.getId());
				jsonobj.put("u_email", ub.getEmail());
				//jsonobj.put("u_password", ub.getPassword());
			}
			catch(Exception e){
				return null;
			}
		return jsonobj.toString();
	}
	public static UserBean getUserByEmail(String email){
		UserBean ub = null;
		List<User> userlist = ofy().load().type(User.class).filter("email",email.trim()).list();
		if(userlist!=null && userlist.size()>0){
			ub = userlist.get(0).getModelObject();
		}
		return ub;
	}
	//returns ProfilePic Upload-Url to requesting client
	
	public String getPictureUploadUrl()
	{
		String blobUploadUrl = blobservice.createUploadUrl("/server/imageupload");
		blobUploadUrl = blobUploadUrl.replace("\\/", "/");
		JSONObject jsonresp = new JSONObject();
		jsonresp.put("imageuploadurl", blobUploadUrl);
		return jsonresp.toString();	
	}
	
	public static String updateUserProfile(UserBean ub){
		ub.setModifieddate(Utils.longToString(Utils.millis()));
		ofy().save().entity(new User(ub)).now();
		return ub.toString();
		
	}
	
	public static UserBean getUserById(String id){
		User user = ofy().load().type(User.class).id(Utils.stringToLong(id)).now();
		return user.getModelObject();
	}
	
	public static UserBean getUserByLoginCredentials(String email,String password){
		List<User> userlist= ofy().load().type(User.class).filter("email",email.trim()).list();
		UserBean ub = null;
		User user;
		if(userlist!=null && userlist.size()>0 && (user= userlist.get(0))!=null){
			ub = user.getModelObject();
			if(!password.equalsIgnoreCase(ub.getPassword())){
				ub = null;
			}
		}
		return ub;
	}
}
