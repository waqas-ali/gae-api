/**
 * 
 */
package com.meetmenext.server;

import static com.meetmenext.server.utilities.OFYService.ofy;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.images.ImagesService;
import com.google.appengine.api.images.ImagesServiceFactory;
import com.google.appengine.api.images.ServingUrlOptions;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.meetmenext.server.entities.User;
import com.meetmenext.server.objects.UserBean;
import com.meetmenext.server.service.UserService;
import com.meetmenext.server.utilities.Utils;

/**
 * @author Zeeshan A. Syed
 *
 * Unless required by applicable law or agreed to in writing, software
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS
 * OF ANY KIND, either express or implied.
 *
 */
public class UploadPicture extends HttpServlet{
	
	private BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
	
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String userid = req.getParameter("u_userid");
		UserBean ub = null;
		
		if(Utils.isNotNullEmpty(userid) && (ub=UserService.getUserById(userid))!=null)
		{
			try {	
				Map<String, List<BlobKey>> blobs = blobstoreService.getUploads(req);
				 Iterator<String> names = blobs.keySet().iterator();
				String blobName = names.next();
		        List<BlobKey> blobKeys = blobs.get(blobName);
		
		        if (blobKeys == null || blobKeys.isEmpty()) 
		        {
		            resp.sendRedirect("/");
		        } 
		        else 
		        {
		        	//Prepare Public Url Of Image Uploaded
		        	ImagesService imagesService = ImagesServiceFactory.getImagesService();
		            ServingUrlOptions servingOptions = ServingUrlOptions.Builder.withBlobKey(blobKeys.get(0));
		            String servingUrl = imagesService.getServingUrl(servingOptions);
		            servingUrl = servingUrl.replace("\\/", "/");
		            //Save The Url in Userbean and persist to DB
		            ub.setProfilepicurl(servingUrl);
		            ofy().save().entity(new User(ub)).now();
		                        
		            resp.setStatus(HttpServletResponse.SC_OK);
		            resp.setContentType("application/json");
		            resp.getWriter().print(ub.toString());
		        }
			} 
			catch (Exception e) {
				resp.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
	            resp.setContentType("text/plain");
	            resp.getWriter().print("Error: Image Not Found/Invalid Format");
	        }
		}
		else{
			resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
            resp.setContentType("text/plain");
            resp.getWriter().print("Error: Missing Mandatory Paramss");
		}
		
	}
}
