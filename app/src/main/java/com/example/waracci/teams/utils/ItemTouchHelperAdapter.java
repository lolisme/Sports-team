package com.example.waracci.teams.utils;

/**
 * Created by waracci on 10/2/17.
 */

public interface ItemTouchHelperAdapter {
    boolean onItemMove(int fromPosition, int toPosition);
    void onItemDismiss(int position);
}