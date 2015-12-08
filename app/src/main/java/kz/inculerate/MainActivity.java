package kz.inculerate;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    ArrayList<News> newsArrayList = new ArrayList<News>();

    RequestQueue queue;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    SwipeRefreshLayout mSwipeRefreshLayout;
    RelativeLayout progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        queue = Volley.newRequestQueue(this);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        progressBar = (RelativeLayout) findViewById(R.id.progressBar);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                requestNews();
            }
        });
        if (savedInstanceState!=null){
            newsArrayList = savedInstanceState.getParcelableArrayList("newsArrayList");
            setAdapter();
        }else
            requestNews();
    }


    public void requestNews(){


        String url = "http://api.royal.kz/soc/news";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {


            @Override
            public void onResponse(JSONObject response) {

                Log.e("response", response.toString());

                try {

                        parseJSON(response.getJSONArray("models"));
                        setAdapter();

                } catch (JSONException e) {

                    e.printStackTrace();

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        );


        queue.add(jsonObjectRequest);

    }


    public void parseJSON(JSONArray jsonArray) throws JSONException {


        if (jsonArray.length()>0){
            newsArrayList.clear();
            for (int i=0; i<jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                News news = new News();
                news.setNewsCat(jsonObject.getString("news_cat"));
                news.setAccountFullname(jsonObject.getString("account_fullname"));
                news.setNewsTitle(jsonObject.getString("news_title"));
                news.setNewsImageFile(jsonObject.getString("news_image_file"));
                news.setNewsCreatedDate(jsonObject.getString("news_created_date"));
                news.setNewsSite(jsonObject.getInt("news_site"));
                newsArrayList.add(news);
            }
        }

    }

    public void setAdapter(){


        if (mAdapter==null) {
            mRecyclerView = (RecyclerView) findViewById(R.id.list);

            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            mRecyclerView.setHasFixedSize(true);

            // use a linear layout manager
            mLayoutManager = new LinearLayoutManager(this);
            mRecyclerView.setLayoutManager(mLayoutManager);

            // specify an adapter (see also next example)
            mAdapter = new AdapterNewsList(this,newsArrayList);
            mRecyclerView.setAdapter(mAdapter);

        }else mAdapter.notifyDataSetChanged();

        mSwipeRefreshLayout.setRefreshing(false);
        mSwipeRefreshLayout.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelableArrayList("newsArrayList", newsArrayList);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.


        return super.onOptionsItemSelected(item);
    }
}
