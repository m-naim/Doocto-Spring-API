package org.naim.doctoo.mapper;

import org.naim.doctoo.model.AuthProvider;
import org.naim.doctoo.model.User;

public class UserMapper {

	public static User mapObject(UserInterface object) {
		User user= new User();
        user.setName(object.getName());
        user.setEmail(object.getEmail());
        user.setPassword(object.getPassword());
        user.setProvider(AuthProvider.local);
		return user;
	}
}
