package data.restfulbooker;

/**
 * Created By Karan Arora on 19-02-2022
 */
public class TokenBuilder {

    public static Tokencreds getToken () {
        return Tokencreds.builder()
            .username ("admin")
            .password ("password123")
            .build ();
    }

}