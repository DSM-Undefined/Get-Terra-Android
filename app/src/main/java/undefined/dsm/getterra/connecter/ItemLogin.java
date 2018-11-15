package undefined.dsm.getterra.connecter;

public class ItemLogin  {
    public String accessTocken;

    public String getAccessToken() {

        return accessTocken;
    }

    public ItemLogin(String accessToken) {
        this.accessTocken = accessToken;
    }
}
