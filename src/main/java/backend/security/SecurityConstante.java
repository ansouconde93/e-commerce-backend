package backend.security;

import java.math.BigInteger;
import java.security.SecureRandom;

public class SecurityConstante {
    private final static String getSecret() {
        SecureRandom rnd = new SecureRandom();
        rnd.setSeed(1234);
        byte[] token = new byte[1000];
        rnd.nextBytes(token);
        return new BigInteger(1, token).toString(16);
    }
    public static final String SECRET = getSecret();
    public static final long EXPIRATION_TIME = 60*1000;// access token expire après 1 minute de sa création
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING ="Authorization";
    public static final String HEADER_REFRESH_TOKEN ="RefreshToken";
    public static final long EXPIRATION_TIME_REFRESH_TOKEN = 864_000_000;// refresh token espire après 10 jours de sa creation
}
