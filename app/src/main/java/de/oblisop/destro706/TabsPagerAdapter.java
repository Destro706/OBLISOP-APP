package de.oblisop.destro706;

/**
 * Created by sascha on 11.12.14.
 */
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabsPagerAdapter extends FragmentPagerAdapter{

        private static final String[] titles = {"Startseite", "News", "Termine", "Ranking", "\"Die Liste\"", "Galerie", "Chat"};

        protected TabsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int index) {

            switch (index) {
                case 0:
                    return new Willkommen();
                case 1:
                    return new News();
                case 2:
                    return new Termine();
                case 3:
                    return new Teilnehmer();
                case 4:
                    return new Liste();
                case 5:
                    return new Galerie();
                case 6:
                    return new Chat();
            }

            return null;
        }

        @Override
        public int getCount() {

            return 7;
        }

        @Override
        public CharSequence getPageTitle(int position) {
        return titles[position];
        }

    }


