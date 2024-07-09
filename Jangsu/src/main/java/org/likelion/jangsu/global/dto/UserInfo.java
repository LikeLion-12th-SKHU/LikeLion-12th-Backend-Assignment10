package org.likelion.jangsu.global.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class UserInfo {
    private String id;
    private String email;
    @SerializedName("verified_email")
    private Boolean verifiedEmail;
    private String name;
<<<<<<< HEAD
=======
<<<<<<< HEAD

=======
>>>>>>> 1e17f5d818217f5a48483ca84d9de52866da5fae
    @SerializedName("given_name")
    private String givenName;
    @SerializedName("family_name")
    private String familyName;
    @SerializedName("picture")
    private String pictureUrl;
    private String locale;
<<<<<<< HEAD
=======
>>>>>>> 6672a7d67175708625ee727edab227bf410b422f
>>>>>>> 1e17f5d818217f5a48483ca84d9de52866da5fae
}
