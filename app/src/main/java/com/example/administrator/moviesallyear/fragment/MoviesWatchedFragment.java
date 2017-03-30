package com.example.administrator.moviesallyear.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.moviesallyear.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoviesWatchedFragment extends Fragment {


    public MoviesWatchedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movies_watched, container, false);
    }

}
