package com.example.waracci.teams.ui;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ToxicBakery.viewpager.transforms.CubeOutTransformer;
import com.example.waracci.teams.R;
import com.example.waracci.teams.adapters.PlayerPageAdapter;
import com.example.waracci.teams.models.Player;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PlayerActivityDetail extends AppCompatActivity {


    @Bind(R.id.viewPager)
    ViewPager mViewPager;

    private PlayerPageAdapter adapterViewPager;

    ArrayList<Player> mPlayers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_detail);
        ButterKnife.bind(this);

        mPlayers = Parcels.unwrap(getIntent().getParcelableExtra("players"));
        int startingPosition = getIntent().getIntExtra("position", 0);



        adapterViewPager = new PlayerPageAdapter(getSupportFragmentManager(), mPlayers);

        mViewPager.setAdapter(adapterViewPager);
        mViewPager.setCurrentItem(startingPosition);
        mViewPager.setPageTransformer(true, new CubeOutTransformer());
    }
}
