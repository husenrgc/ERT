package muhammadhusen.com.ert.api;



import com.google.gson.annotations.SerializedName;

import java.util.List;

import muhammadhusen.com.ert.result.ResultBerita;
import muhammadhusen.com.ert.result.ResultUser;


public class Value {
    @SerializedName("value")
    String value;
    @SerializedName("message")
    String message;
    List<ResultUser> result;
    List<ResultBerita> results;


    public String getValue() { return value; }

    public String getMessage() {
        return message;
    }

    public List<ResultUser> getResultUsers() {
        return result;
    }

    public List<ResultBerita> getResultBerita(){ return results; }
}
