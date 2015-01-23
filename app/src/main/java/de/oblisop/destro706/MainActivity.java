package de.oblisop.destro706;

import android.app.ActionBar;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;



public class MainActivity extends FragmentActivity {
    protected DrawerLayout drawerLayout;
    protected ActionBarDrawerToggle drawerToggle;
    protected ListView drawerList;
    protected String[] listItems;

    protected ViewPager viewPager;
    protected PagerAdapter mAdapter;

	protected PackageManager pm;
	protected Intent wa;
	protected String packageName = "com.whatsapp";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setOffscreenPageLimit(6);

        mAdapter = new TabsPagerAdapter(getSupportFragmentManager()) ;

        viewPager.setAdapter(mAdapter);

        listItems = new String[]{"Startseite", "News", "Termine", "Ranking", "\"Die Liste\"", "Galerie", "Chat", "", "zum Austragungsort"};

        drawerLayout = (DrawerLayout) findViewById(R.id.ganzesLayout);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.opendrawer, R.string.closedrawer);
        drawerLayout.setDrawerListener(drawerToggle);

        ActionBar mainActionbar = getActionBar();
        if (mainActionbar != null) {
            mainActionbar.setHomeButtonEnabled(true);
        }
        if (mainActionbar != null) {
            mainActionbar.setDisplayHomeAsUpEnabled(true);
        }

        drawerList = (ListView) findViewById(R.id.drawerList);

        drawerList.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listItems));
        drawerList.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                switch (drawerList.getPositionForView(view)) {
                    case 0:
                        drawerLayout.closeDrawers();
                        viewPager.setCurrentItem(0);
                        break;

                    case 1:
                        drawerLayout.closeDrawers();
                        viewPager.setCurrentItem(1);
                        break;

                    case 2:
                        drawerLayout.closeDrawers();
                        viewPager.setCurrentItem(2);
                        break;

                    case 3:
                        drawerLayout.closeDrawers();
                        viewPager.setCurrentItem(3);
                        break;

                    case 4:
                        drawerLayout.closeDrawers();
                        viewPager.setCurrentItem(4);
                        break;

                    case 5:
                        drawerLayout.closeDrawers();
                        viewPager.setCurrentItem(5);
                        break;

                    case 6:
                        drawerLayout.closeDrawers();
                        viewPager.setCurrentItem(6);
                        break;


                    case 8:

                        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("google.navigation:q=*******")); //Add Adress instead of *******
                        startActivity(i);
                        drawerLayout.closeDrawers();
                        break;

                    default:
                        break;

                }

            }
        });

    }

    @Override
	protected void onPostCreate(Bundle savedInstanceState) {
		drawerToggle.syncState();
		super.onPostCreate(savedInstanceState);
	}


	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		drawerToggle.onConfigurationChanged(newConfig);
		super.onConfigurationChanged(newConfig);
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        switch (item.getItemId()) {
            case R.id.action_settings:
                Toast.makeText(getApplicationContext(), "Created by: \"Destro706\"\nVersion: \"1.9\"", Toast.LENGTH_SHORT).show();
                break;
        }

        switch (item.getItemId()) {
            case R.id.action_settings2:
                this.recreate();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void zumChat(View v) {
        pm = getPackageManager();

        try {
            wa = pm.getLaunchIntentForPackage(packageName);
            startActivity(wa);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "APP ist nicht installiert", Toast.LENGTH_SHORT).show();
        }
    }


}
