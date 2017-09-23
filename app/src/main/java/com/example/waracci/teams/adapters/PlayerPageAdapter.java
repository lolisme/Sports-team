package com.example.waracci.teams.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.waracci.teams.models.Player;
import com.example.waracci.teams.ui.PlayerDetailsFragment;

import java.util.ArrayList;

/**
 * Created by waracci on 9/20/17.
 */

public class PlayerPageAdapter extends FragmentPagerAdapter {
    private ArrayList<Player> mPlayers;

    public PlayerPageAdapter(FragmentManager fm, ArrayList<Player> players) {
        super(fm);
        mPlayers = players;
    }

    @Override
    public Fragment getItem(int position) {
        return PlayerDetailsFragment.newInstance(mPlayers.get(position));
    }

    @Override
    public int getCount() {
        return mPlayers.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mPlayers.get(position).getName();
    }
}
