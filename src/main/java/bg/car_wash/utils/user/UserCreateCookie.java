package bg.car_wash.utils.user;

import bg.car_wash.areas.user.models.viewModels.user.UserSessionViewModel;

import javax.servlet.http.Cookie;
import java.security.MessageDigest;
import java.util.LinkedList;
import java.util.List;

public class UserCreateCookie {

	private List<Cookie> cookies;

	private UserSessionViewModel userSessionViewModel;

	public UserCreateCookie(UserSessionViewModel userSessionViewModel) {
		this.cookies = new LinkedList<>();
		this.userSessionViewModel = userSessionViewModel;
		this.initCookies();
	}

	private void initCookies() {
		Cookie name = new Cookie("user", this.userSessionViewModel.getFullName());
		name.setMaxAge(3600);
		Cookie email = new Cookie("email", this.userSessionViewModel.getEmail());
		email.setMaxAge(3600);
		Cookie userType = new Cookie("user-type", this.userSessionViewModel.getUserType().toString());
		userType.setMaxAge(3600);
		userType.setPath("/");

		String hash = sha256(this.userSessionViewModel.getFullName().trim() + this.userSessionViewModel.getUserType().toString().trim());

		Cookie user_session_id = new Cookie("USER_SESSION_ID", hash);
		user_session_id.setMaxAge(3600);
		user_session_id.setPath("/");

		this.cookies.add(name);
		this.cookies.add(email);
		this.cookies.add(userType);
		this.cookies.add(user_session_id);
	}

	private String sha256(String base) {
		try{
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(base.getBytes("UTF-8"));
			StringBuffer hexString = new StringBuffer();

			for (int i = 0; i < hash.length; i++) {
				String hex = Integer.toHexString(0xff & hash[i]);
				if(hex.length() == 1) hexString.append('0');
				hexString.append(hex);
			}

			return hexString.toString();
		} catch(Exception ex){
			throw new RuntimeException(ex);
		}
	}

	public List<Cookie> getCookies() {
		return cookies;
	}
}
