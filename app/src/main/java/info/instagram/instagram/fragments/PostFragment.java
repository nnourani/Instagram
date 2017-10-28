package info.instagram.instagram.fragments;


import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import info.instagram.instagram.R;
import info.instagram.instagram.adapters.MainAdapter;
import info.instagram.instagram.adapters.PostAdapter;
import info.instagram.instagram.entities.ImagePost;
import info.instagram.instagram.entities.Post;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PostFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PostFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    PostAdapter mainAdapter;
    RecyclerView mRecyclerView;
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    public PostFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PostFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PostFragment newInstance(String param1, String param2) {
        PostFragment fragment = new PostFragment();
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
        View view = inflater.inflate(R.layout.fragment_post, container, false);


        initRecycleView(view);

        GetPosts getPosts = new GetPosts();
        getPosts.execute();

        return  view;
    }

    private void initRecycleView(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rc_posts);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        /*ArrayList<Post> av = new ArrayList<>();
        mainAdapter = new PostAdapter(getContext(), av);
        mRecyclerView.setAdapter(mainAdapter);
        mainAdapter.notifyDataSetChanged();*/

    }


    private class GetPosts extends AsyncTask<String, Void, String> {

        private ProgressDialog progressDialog = new ProgressDialog(getActivity());
        InputStream inputStream = null;
        String result = "";

        protected void onPreExecute() {
            progressDialog.setMessage("Downloading your data...");
            progressDialog.show();
            progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                public void onCancel(DialogInterface arg0) {
                    GetPosts.this.cancel(true);
                }
            });
        }

        @Override
        protected String doInBackground(String... params) {

            String str="http://192.168.10.55:8282/api/PostApi/Posts";
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(str)
                    .build();
            try {
                Response response = client.newCall(request).execute();
                return   response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (progressDialog.isShowing()){
                progressDialog.dismiss();
            }

            if (result != null){
                try{
                    List<Post> posts = new Gson().fromJson(result, new TypeToken<List<Post>>(){}.getType());

                    mainAdapter = new PostAdapter(getContext(), posts);
                    mRecyclerView.setAdapter(mainAdapter);
                    mainAdapter.notifyDataSetChanged();

                }catch (Exception e){
                    Log.e("EXP",e.getMessage());
                }

            }

        }


        @Override
        protected void onProgressUpdate(Void... values) {}
    }

}
