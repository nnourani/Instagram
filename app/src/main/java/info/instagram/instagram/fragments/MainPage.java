package info.instagram.instagram.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import info.instagram.instagram.R;
import info.instagram.instagram.adapters.MainAdapter;
import info.instagram.instagram.entities.Post;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainPage#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainPage extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public MainPage() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainPage.
     */
    // TODO: Rename and change types and number of parameters
    public static MainPage newInstance(String param1, String param2) {
        MainPage fragment = new MainPage();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main_page, container, false);

        initRecycleView(view);

        return view;
    }

    private void initRecycleView(View view) {
        RecyclerView mRecyclerView = (RecyclerView) view.findViewById(R.id.rc_posts);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        ArrayList<Post> av = prepareDate();

        MainAdapter mainAdapter = new MainAdapter(getContext(), av);
        mRecyclerView.setAdapter(mainAdapter);


    }

    private ArrayList<Post> prepareDate() {
        ArrayList<Post> av = new ArrayList<>();
        Post p = new Post(1, 1, "Naser Nourani", R.drawable.a, R.drawable.b1,
                " Post 1 , Natural 1", 12, 10, false, false, false);

        av.add(p);

        p = new Post(2, 2, "Saed za", R.drawable.aa, R.drawable.b2,
                " Post 2 , Natural 2", 130000, 300, true, true, false);
        av.add(p);

        p = new Post(3, 1, "Naser Nourani", R.drawable.a, R.drawable.b3,
                " Post 3 , Natural 3", 370, 26, false, true, false);
        av.add(p);

        p = new Post(4, 1, "Naser Nourani", R.drawable.a, R.drawable.b4,
                " Post 4 , Natural 4", 3345, 544, false, false, false);
        av.add(p);

        p = new Post(5, 1, "Naser Nourani", R.drawable.a, R.drawable.b5,
                " Post5 , Natural 5", 0, 0, false, false, false);
        av.add(p);

        p = new Post(6, 2, "Saed za", R.drawable.a, R.drawable.b6,
                " Post 6 , Natural 6", 334223, 5434, false, false, false);
        av.add(p);

        p = new Post(7, 2, "Saed za", R.drawable.a, R.drawable.b7,
                " Post 7 , Natural 7", 3, 2, false, false, false);
        av.add(p);

        p = new Post(8, 1, "Naser Nourani", R.drawable.a, R.drawable.b1,
                " Post 8 , Natural 8", 786, 354, true, false, false);
        av.add(p);


        return av;
    }

}
