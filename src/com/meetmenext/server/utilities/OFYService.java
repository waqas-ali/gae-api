package com.meetmenext.server.utilities;

/**
 * @author Zeeshan A. Syed
 *
 * Unless required by applicable law or agreed to in writing, software
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS
 * OF ANY KIND, either express or implied.
 *
 */

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;
import com.meetmenext.server.entities.Events;
import com.meetmenext.server.entities.Favourites;
import com.meetmenext.server.entities.Speaker;
import com.meetmenext.server.entities.User;
import com.meetmenext.server.entities.UsersEvents;

public class OFYService {
	static {
        factory().register(User.class);
        factory().register(Events.class);
        factory().register(Speaker.class);
        factory().register(Favourites.class);
        factory().register(UsersEvents.class);
    }

    public static Objectify ofy() {
        return ObjectifyService.ofy();
    }

    public static ObjectifyFactory factory() {
        return ObjectifyService.factory();
    }
}
