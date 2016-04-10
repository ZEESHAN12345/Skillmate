package com.example.zeeshan.skillmate;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;

public class EmployerDetail extends AppCompatActivity {

    NetworkImageView employerPhoto;
    TextView employerName;
    TextView employerDetails;

    String profileId, profilePhotos, name, sex, age, distance, totalTrustScore, skillName, _id, photoGallery, avgRating, maskMyProfilePhotoFlag, showOnlyInitialOfNameFlag, makeMePopularFlag, isFav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employer_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        employerPhoto = (NetworkImageView) findViewById(R.id.employer_photo);
        employerName = (TextView) findViewById(R.id.employer_name);
        employerDetails = (TextView) findViewById(R.id.employer_details);

        /**ArrayList<String> deatils_temp = new ArrayList<String>();
        deatils_temp.add(profileId);
        deatils_temp.add(profilePhotos);
        deatils_temp.add(name);
        deatils_temp.add(sex);
        deatils_temp.add(age);
        deatils_temp.add(distance);
        deatils_temp.add(totalTrustScore);
        deatils_temp.add(skillName);
        deatils_temp.add(_id);
        deatils_temp.add(photoGallery);
        deatils_temp.add(avgRating);
        deatils_temp.add(maskMyProfilePhotoFlag);
        deatils_temp.add(showOnlyInitialOfNameFlag);
        deatils_temp.add(makeMePopularFlag);
        deatils_temp.add(isFav);**/

        Intent i2 = getIntent();
        Bundle b2 =  i2.getExtras();
        ArrayList<String> details_final = (ArrayList<String>) b2.get("details_final");
        //Log.v( "details_final", details_final.toString() );
        for(int i=0; i<details_final.size(); i++) {
            switch (i)
            {

                case 0:
                    profileId = details_final.get(i);
                    break;
                case 1:
                    profilePhotos = details_final.get(i);
                    Log.v("details_final.get(1) = ", profilePhotos);
                    break;
                case 2:
                    name = details_final.get(i);
                break;
                case 3:
                    sex = details_final.get(i);
                break;
                case 4:
                    age = details_final.get(i);
                break;
                case 5:
                    distance= details_final.get(i);
                break;
                case 6:
                    totalTrustScore= details_final.get(i);
                break;
                case 7:
                    skillName = details_final.get(i);
                break;
                case 8:
                    _id = details_final.get(i);
                break;
                case 9:
                    photoGallery = details_final.get(i);
                break;
                case 10:
                    avgRating = details_final.get(i);
                break;
                case 11:
                    maskMyProfilePhotoFlag = details_final.get(i);
                break;
                case 12:
                    showOnlyInitialOfNameFlag = details_final.get(i);
                break;
                case 13:
                    makeMePopularFlag = details_final.get(i);
                break;
                case 14:
                    isFav = details_final.get(i);
                break;

            }
        }

        Log.v("Profile Photo URL = ", profilePhotos);
        ImageLoader imageLoader = AppController.getInstance().getImageLoader();
        employerPhoto.setImageUrl(profilePhotos, imageLoader);

        employerName.setText(name);

        String details = "Profile ID = "+profileId+",\n" +
                         "Sex = "+sex+",\n" +
                         "Age = "+age+",\n" +
                         "Distance = "+distance+",\n" +
                         "Total Trust Score = "+totalTrustScore+",\n" +
                         "Skill Name = "+skillName+",\n" +
                         "Id = "+_id+",\n" +
                         "Photo Gallery = "+photoGallery+",\n" +
                         "Average Rating = "+avgRating+",\n" +
                         "Mask my Profile Photo Flag = "+maskMyProfilePhotoFlag+",\n" +
                         "Show my Initial of Name Flag = "+showOnlyInitialOfNameFlag+",\n" +
                         "Make me Popular Flag = "+makeMePopularFlag+",\n" +
                         "isFav = "+isFav;

        employerDetails.setText(details);


        toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

}
