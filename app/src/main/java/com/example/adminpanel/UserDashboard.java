package com.example.adminpanel;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adminpanel.HomeAdapter.ContactAdapter;
import com.example.adminpanel.HomeAdapter.ContactHelperClass;
import com.example.adminpanel.HomeAdapter.OfferAdapter;
import com.example.adminpanel.HomeAdapter.OfferHelperClass;
import com.example.adminpanel.HomeAdapter.ReviewAdapter;
import com.example.adminpanel.HomeAdapter.ReviewHelperClass;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class UserDashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    static final float END_SCALE = 0.7f;


    RecyclerView review_recycler, offer_recycler, contact_recycler;
    RecyclerView.Adapter adapter;
    ImageView menuIcon;
    LinearLayout contentView,dashb_walking_icon,dashb_training_icon,dashb_oc_icon;

    //Drawer Menu
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_userdashboard);

        review_recycler = findViewById(R.id.review_recycler);
        offer_recycler = findViewById(R.id.offer_recycler);
        contact_recycler = findViewById(R.id.contact_recycler);

        menuIcon = findViewById(R.id.menu_icon);
        contentView = findViewById(R.id.content);
        dashb_walking_icon = findViewById(R.id.dashb_walking_icon);
        dashb_training_icon=findViewById(R.id.dashb_training_icon);
        dashb_oc_icon=findViewById(R.id.dashb_oc_icon);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);


        walking_nav();
        oc_nav();
        training_nav();
        
        navigationDrawer();

        review_recycler();
        offer_recycler();
        contact_recycler();
    }

    private void walking_nav() {
        dashb_walking_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),book_walker.class);
                startActivity(intent);
            }
        });
    }

    private void oc_nav() {
        dashb_oc_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),overnight_care.class);
                startActivity(intent);
            }
        });
    }

    private void training_nav() {
        dashb_training_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),training.class);
                startActivity(intent);
            }
        });
    }

    private void navigationDrawer() {



        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);

        menuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(drawerLayout.isDrawerVisible(GravityCompat.START))
                    drawerLayout.closeDrawer(GravityCompat.START);

                else drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        animateNavigationDrawer();
    }

    private void animateNavigationDrawer() {

        drawerLayout.setScrimColor (getResources ().getColor (R.color.scrim));
        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                // Scale the View based on current slide offset
                final float diffScaledOffset = slideOffset * (1 - END_SCALE);
                final float offsetScale = 1 - diffScaledOffset;
                contentView.setScaleX(offsetScale);
                contentView.setScaleY(offsetScale);
                // Translate the View, accounting for the scaled width
                final float xOffset = drawerView.getWidth() * slideOffset;
                final float xOffsetDiff = contentView.getWidth() * diffScaledOffset / 2;
                final float xTranslation = xOffset - xOffsetDiff;
                contentView.setTranslationX(xTranslation);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerVisible(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else
            super.onBackPressed();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch(item.getItemId()){

            case R.id.nav_home:
                Intent intent = new Intent(getApplicationContext(),UserDashboard.class);
                startActivity(intent);
                break;

            case R.id.nav_walking:
                intent = new Intent(getApplicationContext(),book_walker.class);
                startActivity(intent);
                break;

            case R.id.nav_training:
                intent = new Intent(getApplicationContext(),training.class);
                startActivity(intent);
                break;
            case R.id.nav_petcare:
                intent = new Intent(getApplicationContext(),overnight_care.class);
                startActivity(intent);
                break;


            case R.id.nav_logout:
                startActivity(new Intent(UserDashboard.this,LoginScreen.class));
                finish();

        }

        return true;
    }





    private void contact_recycler() {
        contact_recycler.setHasFixedSize(true);
        contact_recycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        ArrayList<ContactHelperClass> contacts = new ArrayList<>();
        contacts.add(new ContactHelperClass(R.drawable.insta,"waggers_india"));
        contacts.add(new ContactHelperClass(R.drawable.twitter,"@waggers_twits"));
        contacts.add(new ContactHelperClass(R.drawable.gmail,"waggers.walkers@gmail.com"));

        adapter = new ContactAdapter(contacts);
        contact_recycler.setAdapter(adapter);
    }

    private void offer_recycler() {
        offer_recycler.setHasFixedSize(true);
        offer_recycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        ArrayList<OfferHelperClass> offers = new ArrayList<>();
        offers.add(new OfferHelperClass(R.drawable.offer_card1));
        offers.add(new OfferHelperClass(R.drawable.offer_card2));
        offers.add(new OfferHelperClass(R.drawable.offer_card3));

        adapter = new OfferAdapter(offers);
        offer_recycler.setAdapter(adapter);
    }

    private void review_recycler() {
        review_recycler.setHasFixedSize(true);
        review_recycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        ArrayList<ReviewHelperClass> reviews = new ArrayList<>();
        reviews.add(new ReviewHelperClass(R.drawable.ariana_w,"Ariana W.", "Waggers is a convenient and reliable app for dog owners who need a trusted walker for their furry friend."));
        reviews.add(new ReviewHelperClass(R.drawable.kevin_p,"Kevin P.", "The app is user-friendly and the walkers are professional and attentive to the dogs needs."));
        reviews.add(new ReviewHelperClass(R.drawable.julie_s,"Julie S.", "Waggers makes dog walking a breeze!. Reliable service provide peace of mind for both dogs and their owners."));
        reviews.add(new ReviewHelperClass(R.drawable.john_c,"John C.", "Their professional and reliable walkers ensure that your furry friend gets the exercise and attention they need"));

        adapter = new ReviewAdapter(reviews);
        review_recycler.setAdapter(adapter);
    }


}
