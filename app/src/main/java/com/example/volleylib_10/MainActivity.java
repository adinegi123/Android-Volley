package com.example.volleylib_10;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private TextView mTextView;
    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView=findViewById(R.id.text);
        Button btn_parse=findViewById(R.id.btn_parse);
        mQueue= Volley.newRequestQueue(this);//instance of request queue
        btn_parse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jsonParse();
            }
        });


        }
    private void jsonParse(){
        String url="https://jsonplaceholder.typicode.com/posts";
        JsonArrayRequest request= new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                      //JSONArray jsonArray=new JSONArray();
                      for(int i=0;i<response.length();i++)
                      {
                          try {
                              JSONObject jsonObject=response.getJSONObject(i);
                               int userId=jsonObject.getInt("userId");
                               int id=jsonObject.getInt("id");
                               String title=jsonObject.getString("title");
                               String body=jsonObject.getString("body");
                               mTextView.append(String.valueOf(userId)+", "+String.valueOf(id)+", "+title+", "+body+"\n\n");
                          } catch (JSONException e) {
                              e.printStackTrace();
                          }
                      }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();

            }
        });

        mQueue.add(request);

    }
}
