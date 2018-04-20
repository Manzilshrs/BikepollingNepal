package com.manzil.bikepollingnepal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class jsonParsingActivity extends AppCompatActivity {

    AQuery aquery;
    String fetchurl="http://10.0.2.2/BikePollingNepal/select.php";
    String fetchurl1="http://192.168.100.2/BikePollingNepal/select.php";
    String posturl="http://192.168.100.2/BikePollingNepal/insert.php";
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json_parsing);
        aquery=new AQuery(this);
        listView= (ListView) findViewById(R.id.listview);
        postData();
        fetchData();
    }

    public void fetchData(){
        aquery.ajax(fetchurl1,JSONArray.class,new AjaxCallback<JSONArray>()
                {

                    @Override
                    public void callback(String url, JSONArray array, AjaxStatus status) {
                        super.callback(url, array, status);
                        Log.i("response", url+"response:"+array);
                        ArrayList<UserInfo>list = new ArrayList<UserInfo>();
                        for (int i=0; i<array.length();i++){
                            try {
                                JSONObject object=array.getJSONObject(i);


//                                        "id": "1",
//                                        "1": "Manzil",
//                                        "username": "Manzil",
//                                        "2": "manzil.shrs665@gmail.com",
//                                        "email": "manzil.shrs665@gmail.com",
//                                        "3": "9843562101",
//                                        "phone": "9843562101",
//                                        "4": "jorpati",
//                                        "address": "jorpati",
//                                        "5": "manjil",
//                                        "password": "manjil",
//                                        "6": "male",
//                                        "gender": "male",
//                                        "7": "nov12",
//                                        "dob": "nov12",
//                                        "8": "",
//                                        "image": ""
//
                         UserInfo info= new UserInfo();
                           info.id=object.getString("id");
                           info.username=object.getString("username");
                           info.email=object.getString("email");
                           info.phone=object.getString("phone");
                           info.address=object.getString("address");
                           info.password=object.getString("password");
                           info.gender=object.getString("gender");
                           info.dob=object.getString("dob");
//                           info.image=object.getString("image");
                                list.add(info);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
listView.setAdapter(new UserListAdapter(jsonParsingActivity.this,list));
                    }
                }
        );

    }

public void postData(){

    HashMap<String,Object>param= new HashMap<String,Object>();
    param.put("username","username ko vlaue");
    param.put("password","username ko vlaue");
    param.put("address","username ko vlaue");
    param.put("phone","username ko vlaue");
    param.put("email","username ko vlaue");
    aquery.ajax(posturl,param,JSONArray.class,new AjaxCallback<JSONArray>()
            {
                @Override
                public void callback(String url, JSONArray object, AjaxStatus status) {
                    super.callback(url, object, status);
                  Log.i("response",url+"response"+object);
                }
            }
    );
}
}
