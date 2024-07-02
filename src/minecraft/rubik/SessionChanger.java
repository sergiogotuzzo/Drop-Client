package rubik;

import java.util.UUID;

import com.mojang.authlib.Agent;
import com.mojang.authlib.AuthenticationService;
import com.mojang.authlib.UserAuthentication;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import com.mojang.util.UUIDTypeAdapter;

import fr.litarvan.openauth.microsoft.MicrosoftAuthResult;
import fr.litarvan.openauth.microsoft.MicrosoftAuthenticationException;
import fr.litarvan.openauth.microsoft.MicrosoftAuthenticator;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Session;

/** WORKS ONLY FOR OFFLINE SESSIONS */
public class SessionChanger {
	private static SessionChanger instance;
	private final UserAuthentication auth;
	private final Minecraft mc = Minecraft.getMinecraft();

	public static SessionChanger getInstance() {
		if (instance == null) {
			instance = new SessionChanger();
		}

		return instance;
	}

	private SessionChanger() {
		UUID notSureWhyINeedThis = UUID.randomUUID();
		AuthenticationService authService = new YggdrasilAuthenticationService(mc.getProxy(), notSureWhyINeedThis.toString());
		
		auth = authService.createUserAuthentication(Agent.MINECRAFT);
		
		authService.createMinecraftSessionService();
	}

	public boolean setUserMojang(String email, String password) {
		if (!mc.getSession().getUsername().equals(email) || mc.getSession().getToken().equals("0")) {
			this.auth.logOut();
			this.auth.setUsername(email);
			this.auth.setPassword(password);
			
			try {
				this.auth.logIn();
				
				Session session = new Session(this.auth.getSelectedProfile().getName(), UUIDTypeAdapter.fromUUID(auth.getSelectedProfile().getId()), this.auth.getAuthenticatedToken(), this.auth.getUserType().getName());
				
				setSession(session);
				
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return false;
	}

	public boolean setUserMicrosoft(String email, String password) {
		MicrosoftAuthenticator authenticator = new MicrosoftAuthenticator();
		
		try {
			MicrosoftAuthResult acc = authenticator.loginWithCredentials(email, password);
			
			mc.session = new Session(acc.getProfile().getName(), acc.getProfile().getId(), acc.getAccessToken(), "legacy");
			
			return true;
		} catch (MicrosoftAuthenticationException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	public void setUserOffline(String username) {
		this.auth.logOut();
		
		Session session = new Session(username, username, "0", "legacy");
		
		setSession(session);
	}

	private void setSession(Session session) {
		mc.session = session;
	}
}