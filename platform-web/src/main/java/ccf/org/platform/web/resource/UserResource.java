package ccf.org.platform.web.resource;

import ccf.org.platform.logic.exception.DatabaseException;
import ccf.org.platform.logic.service.ManageUserEJB;
import ccf.org.platform.model.core.User;
import ccf.org.platform.web.dto.AbstractDTO;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;

import javax.ejb.EJB;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import java.io.IOException;
import java.security.GeneralSecurityException;

@Path("/user")
public class UserResource extends AbstractResource<User, AbstractDTO> {

	private final String mappingFileBase = "mappings\\user-mapper.xml";

	@EJB
	private ManageUserEJB manageUserEJB;

	/**
	 * Inserts a new {@link User} mapping the given {@link UserDTO}.
	 *
	 * @param idToken The token passed by fe needed to verify google identity
	 * @return <code>true</code> if successfully inserted, <code>false</code> otherwise
	 */
	@POST
	public boolean add(final String idToken) {
		logger.info(getClass().getSimpleName() + "-add");
		boolean added = false;

		try {
			final Payload payload = manageUserEJB.googleSignInVerifier(idToken);
			if (payload != null) {
				
				final User user = new User();
				user.setName((String) payload.get("given_name"));
				manageUserEJB.persist(user);
				added = true;
			}
		} catch (GeneralSecurityException | IOException | DatabaseException e) {
			e.printStackTrace();
		}

		return added;
	}

}
