
package in.jigyasacodes.lh_twitter.data.verify_creds;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Description {

    @SerializedName("urls")
    @Expose
    private List<Object> urls = new ArrayList<Object>();

    /**
     * 
     * @return
     *     The urls
     */
    public List<Object> getUrls() {
        return urls;
    }

    /**
     * 
     * @param urls
     *     The urls
     */
    public void setUrls(List<Object> urls) {
        this.urls = urls;
    }

}
