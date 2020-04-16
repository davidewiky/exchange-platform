package ccf.org.platform.logic.service;

import ccf.org.platform.model.core.User;
import ccf.org.platform.util.file.FileHandler;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;

import javax.ejb.*;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

@Stateless
public class ManageUserEJB extends GenericDataAccessEJB<User> {

	private static final String RESOURCE_FILE = "/res.properties";
	private static final String CLIENT_PROPERTY = "clientId";

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Payload googleSignInVerifier(final String idToken) throws GeneralSecurityException, IOException {
		final NetHttpTransport transport = GoogleNetHttpTransport.newTrustedTransport();
		final JacksonFactory jsonFactory = JacksonFactory.getDefaultInstance();
		GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory).setAudience(
				Collections.singletonList(FileHandler.getInstance().getPropertyValue(CLIENT_PROPERTY, RESOURCE_FILE)
						.orElse(""))).build();
		final GoogleIdToken token = verifier.verify(idToken);
		// token verified.
		if (token != null) {
			final Payload payload = token.getPayload();
			logger.info(getClass().getSimpleName() + "-googleSignInVerifier[token " + idToken + " verified]");
			return payload;
		}
		// token not valid. Return error to front end
		else {
			return null;
		}

	}

}
