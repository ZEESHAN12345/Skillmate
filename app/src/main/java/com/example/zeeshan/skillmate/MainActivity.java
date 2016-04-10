package com.example.zeeshan.skillmate;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    public static String JSON_URL = "http://test.skillmate.co.in/v1/user?skillName=aerobics&id=145452999180469&category=learn";
    public static String token;
    public static ListView employers;

    public ArrayList<String> ids = new ArrayList<String>();
    public ArrayList<String> photos = new ArrayList<String>();
    public ArrayList<String> names = new ArrayList<String>();
    public ArrayList<String>  sexs = new ArrayList<String>();
    public ArrayList<String>  ages = new ArrayList<String>();
    public ArrayList<String>  distances = new ArrayList<String>();
    public ArrayList<String>  totalTrustScores = new ArrayList<String>();
    public ArrayList<String> skillNames = new ArrayList<String>();
    public ArrayList<String> _ids = new ArrayList<String>();
    public ArrayList<String> photoGallerys = new ArrayList<String>();
    public ArrayList<String> avgRatings = new ArrayList<String>();
    public ArrayList<String> maskMyProfilePhotoFlags = new ArrayList<String>();
    public ArrayList<String> showOnlyInitialOfNameFlags = new ArrayList<String>();
    public ArrayList<String> makeMePopularFlags = new ArrayList<String>();
    public ArrayList<String> isFavs = new ArrayList<String>();

    public static int len;

    public static ArrayList< ArrayList<String> > details= new ArrayList< ArrayList<String> >();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final Context context = getApplicationContext();

        employers = (ListView) findViewById(R.id.employer);

        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();

        if (!isInternetOn(getApplicationContext())) {

        }

        Log.v("JSON_URL1", JSON_URL);
        JsonObjectRequest request1 = new JsonObjectRequest(JSON_URL, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONObject result = response.getJSONObject("result");
                    token = result.getString("token");
                    Log.v("token", token);
                    SecondRequest(pDialog, context);
                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(), "token:" + token, Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Log.v( "Error: " , error.getMessage() );
                //pDialog.hide();
            }

        }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("x-access-token", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJfaWQiOiJ0ZXN0ZGF0ZSIsImdjbUlkIjoicGFzc3dvcmQiLCJpYXQiOjE0NTk4Mzc0NzMsImV4cCI6MTQ1OTkyMzg3M30.20fMc4QH0rA8NupFCkoC8G3vZKMvLvzTbJnrbUvPR9I");
                return headers;
            }

        };
        AppController.getInstance().addToRequestQueue(request1);

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
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void SecondRequest (final ProgressDialog pDialog, final Context context) {
        JsonObjectRequest request2 = new JsonObjectRequest(JSON_URL, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                pDialog.hide();
                try {
                    JSONObject result = response.getJSONObject("result");

                    //String message = result.getString("message");

                    JSONArray results = result.getJSONArray("results");
                    len = results.length();
                    for(int i=0; i<len; i++) {

                        JSONObject employer = results.getJSONObject(i);

                        String profileId = employer.getString("profileId");
                        ids.add(profileId);

                        JSONArray p_photos = employer.getJSONArray("profilePhotos");
                        /**for(int j=0; j<p_photos.length(); j++) {

                            ArrayList<String> profilePhotos = new ArrayList<>();
                            profilePhotos.add( p_photos.getString(i) );

                        }**/
                        String profilePhotos = p_photos.getString(0);
                        photos.add(profilePhotos);

                        String name = employer.getString("name");
                        names.add(name);
                        String sex = employer.getString("sex");
                        sexs.add(sex);
                        String age = employer.getString("age");
                        ages.add(age);
                        String distance = employer.getString("distance");
                        distances.add(distance);
                        String totalTrustScore = employer.getString("totalTrustScore");
                        totalTrustScores.add(totalTrustScore);

                        JSONObject teach = employer.getJSONObject("teach");
                        String skillName = teach.getString("skillName");
                        skillNames.add(skillName);
                        String _id = teach.getString("_id");
                        _ids.add(_id);
                        //JSONArray photo_gallery = teach.getJSONArray("photoGallery");
                        //String photoGallery = photo_gallery.getString(0);
                        String photoGallery = teach.getString("photoGallery");
                        photoGallerys.add(photoGallery);
                        String avgRating = teach.getString("avgRating");
                        avgRatings.add(avgRating);

                        String maskMyProfilePhotoFlag = employer.getString("maskMyProfilePhotoFlag");
                        maskMyProfilePhotoFlags.add(maskMyProfilePhotoFlag);
                        String showOnlyInitialOfNameFlag = employer.getString("showOnlyInitialOfNameFlag");
                        showOnlyInitialOfNameFlags.add(showOnlyInitialOfNameFlag);
                        String makeMePopularFlag = employer.getString("makeMePopularFlag");
                        makeMePopularFlags.add(makeMePopularFlag);
                        String isFav = employer.getString("isFav");
                        isFavs.add(isFav);

                    }

                    details.add(ids);
                    details.add(photos);
                    details.add(names);
                    details.add(sexs);
                    details.add(ages);
                    details.add(distances);
                    details.add(totalTrustScores);
                    details.add(skillNames);
                    details.add(_ids);
                    details.add(photoGallerys);
                    details.add(avgRatings);
                    details.add(maskMyProfilePhotoFlags);
                    details.add(showOnlyInitialOfNameFlags);
                    details.add(makeMePopularFlags);
                    details.add(isFavs);


                } catch (JSONException e) {
                    e.printStackTrace();
                }

                employers.setAdapter(new CustomAdapter(context, photos, names, details, len));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                System.out.println("JSON_URL2: "+ JSON_URL);
                Log.v("error", error.toString());

            }
        }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                System.out.println("token2 = " + token);
                headers.put("x-access-token", token);
                return headers;
            }

        };
        AppController.getInstance().addToRequestQueue(request2);
    }


    public final boolean isInternetOn(Context context) {

        // get Connectivity Manager object to check connection
        ConnectivityManager connection = (ConnectivityManager) getSystemService(getBaseContext().CONNECTIVITY_SERVICE);
        // Check for network connections
        if ( connection.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTED ||
                connection.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connection.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connection.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTED ) {

            // if connected with internet
            //Toast msg1 = Toast.makeText(context, " Connected ", Toast.LENGTH_LONG);
            //msg1.show();
            return true;


        } else if ( connection.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.DISCONNECTED || connection.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.DISCONNECTED  ) {
            Toast msg2 = Toast.makeText(context, "No available connection!", Toast.LENGTH_LONG);
            msg2.show();
            return false;
        }

        return false;
    }
}

    class CustomAdapter extends BaseAdapter {

        Context context;
        ArrayList<String> profilePhotos;
        ArrayList<String> names;
        ArrayList< ArrayList<String> > _details;
        int _len;
        private static LayoutInflater inflater = null;

        public CustomAdapter(Context context_here, ArrayList<String> profile_photos, ArrayList<String> Names, ArrayList< ArrayList<String> > detail, int len) {
            context = context_here;
            profilePhotos = profile_photos;
            names = Names;
            _details = detail;
            _len = len;
            inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return names.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        public class Holder {
            NetworkImageView photoIv;
            TextView nameTv;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            Holder holder = new Holder();
            View rowView;
            rowView = inflater.inflate(R.layout.row_list, null);

            holder.photoIv = (NetworkImageView) rowView.findViewById(R.id.photo_iv);
            ImageLoader imageLoader = AppController.getInstance().getImageLoader();
            holder.photoIv.setImageUrl(profilePhotos.get(position), imageLoader);

            holder.nameTv = (TextView) rowView.findViewById(R.id.name_tv);
            holder.nameTv.setText(names.get(position));

            rowView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast msg = Toast.makeText(context, "You Clicked: "+names.get(position), Toast.LENGTH_LONG);
                    //msg.show();

                    ArrayList<String> details_final = new ArrayList<String>();

                    for (int i = 0; i < _details.size(); i++) {
                        details_final.add(_details.get(i).get(position));
                        Log.v("check = ", _details.get(i).get(position));
                    }

                    /**details_final.add(details.get(position).get(position));
                     details_final.add(details.get(position).get(position));
                     details_final.add(details.get(position).get(position));
                     details_final.add(details.get(position).get(position));
                     details_final.add(details.get(position).get(position));
                     details_final.add(details.get(position).get(position));
                     details_final.add(details.get(position).get(position));
                     details_final.add(details.get(position).get(position));
                     details_final.add(details.get(position).get(position));
                     details_final.add(details.get(position).get(position));
                     details_final.add(details.get(position).get(position));
                     details_final.add(details.get(position).get(position));
                     details_final.add(details.get(position).get(position));
                     details_final.add(details.get(position).get(position));
                     details_final.add(details.get(position).get(position));**/

                    Intent i1 = new Intent(context, EmployerDetail.class);
                    i1.putExtra("details_final", details_final);
                    i1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(i1);

                }
            });
            return rowView;
        }

    }