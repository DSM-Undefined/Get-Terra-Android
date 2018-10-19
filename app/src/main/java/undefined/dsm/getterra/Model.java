package undefined.dsm.getterra;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Model {
    @SerializedName("map")
    List<String> booth = new ArrayList<String>();
    // ``
    public List<String> getBooth() {return booth;}
}
