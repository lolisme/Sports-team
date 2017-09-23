//package com.example.waracci.teams;
//
//import android.content.Context;
//import android.widget.ArrayAdapter;
//
///**
// * Created by waracci on 9/19/17.
// */
//
//public class MyPlayersAdapter extends ArrayAdapter {
//    private Context mContext;
//    private String[] mPlayers;
//    private String[] mTeams;
//    public MyPlayersAdapter(Context mContext, int resource, String[] mPlayers, String[] mTeams) {
//        super(mContext, resource);
//        this.mContext = mContext;
//        this.mPlayers = mPlayers;
//        this.mTeams = mTeams;
//    }
//
//    @Override
//    public Object getItem(int position ) {
//        String player = mPlayers[position];
//        String team = mTeams[position];
//        return String.format("%s belongs to %s", player, team);
//    }
//
//    @Override
//    public int getCount (){
//        return mPlayers.length;
//    }
//}
