package info.instagram.instagram.adapters;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.internal.Streams;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import info.instagram.instagram.R;
import info.instagram.instagram.entities.ImagePost;
import info.instagram.instagram.entities.Post;
import info.instagram.instagram.fragments.PostFragment;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Naser on 10/28/2017.
 */

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    private List<Post> arrayList;
    private Context mContext;
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    public PostAdapter(Context mContext, List<Post> imagePosts) {
        this.arrayList = imagePosts;
        this.mContext = mContext;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.mProfileName.setText(arrayList.get(position).getTitle());
        holder.mProfileName2.setText(arrayList.get(position).getTitle());

        String content = arrayList.get(position).getContent();
        content = content.replace(" src=\""," src=\"http://192.168.10.55:8282");
        String text = "<html><head><meta charset=\"utf-8\"></head><body>" + content + " <link rel=\"stylesheet\" href=\"192.168.10.55:8282/css/belowthefold.scss?v=bh30g4LDAv9UnKHOoHsIFPf_v7E\"> </body></html>";
        holder.webViewPost.loadData(text ,"text/html; charset=UTF-8" ,null);

       /* if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            holder.mPostContent.setText(Html.fromHtml(arrayList.get(position).getContent(), Html.FROM_HTML_MODE_COMPACT));
        }else{
            holder.mPostContent.setText(Html.fromHtml(arrayList.get(position).getContent()));
        }*/
         holder.mLikeCount.setText(String.valueOf(arrayList.get(position).getComments().size()) + "  Comments ");

       // holder.imgProfile.setImageResource(arrayList.get(position).getProfileImage());

       /* if (arrayList.get(position).isLiked()) {
            holder.imgLike.setImageResource(R.drawable.ic_favorite_black_24dp);
        } else {
            holder.imgLike.setImageResource(R.drawable.ic_favorite_border_black_24dp);
        }

        if (arrayList.get(position).isCommented()) {
            holder.imgComment.setImageResource(R.drawable.ic_chat_black_24dp);
        } else {
            holder.imgComment.setImageResource(R.drawable.ic_chat_bubble_outline_black_24dp);
        }*/

        holder.imgOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                GetPosts getPosts = new GetPosts();
                getPosts.execute(String.valueOf(arrayList.get(position).getId()));


                /*final AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);

                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View dialogView = inflater.inflate(R.layout.post_options, null);
                alertDialog.setView(dialogView);

                alertDialog.setTitle("Your title");
                alertDialog.setMessage("your message ");

                TextView txtReport = (TextView) dialogView.findViewById(R.id.txt_report);
                TextView txtCopyUrl = (TextView) dialogView.findViewById(R.id.txt_copy_url);

                txtReport.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });

                txtCopyUrl.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(mContext , "T2" , Toast.LENGTH_LONG).show();
                    }
                });

                AlertDialog dialog = alertDialog.create();
                dialog.show();*/
            }
        });

    }

    @Override
    public PostAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_row_item, parent, false);
        return new PostAdapter.ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout layout;
        private TextView mProfileName, mProfileName2, mPostText, mLikeCount , mPostContent;
        private ImageView imgProfile, imgLike, imgComment, imgShare, imgFavorite, imgOptions;
        private WebView webViewPost;

        public ViewHolder(View itemView) {
            super(itemView);

            layout = (LinearLayout) itemView.findViewById(R.id.layout_top);
            //mPostContent =  (TextView) itemView.findViewById(R.id.txt_post_content);
            webViewPost= (WebView) itemView.findViewById(R.id.web_posts);
            mProfileName = (TextView) itemView.findViewById(R.id.txt_profile_name);
            mProfileName2 = (TextView) itemView.findViewById(R.id.txt_profile_name_2);
            mPostText = (TextView) itemView.findViewById(R.id.txt_post_text);
            mLikeCount = (TextView) itemView.findViewById(R.id.txt_like_count);

            imgProfile = (ImageView) itemView.findViewById(R.id.img_profile_pic);
            imgLike = (ImageView) itemView.findViewById(R.id.img_like);
            imgComment = (ImageView) itemView.findViewById(R.id.img_comment);
            imgShare = (ImageView) itemView.findViewById(R.id.img_share);
            imgFavorite = (ImageView) itemView.findViewById(R.id.img_favorite);
            imgOptions = (ImageView) itemView.findViewById(R.id.img_options);
        }
    }

    private class GetPosts extends AsyncTask<String, Void, String> {

        private ProgressDialog progressDialog = new ProgressDialog(mContext);
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

            String str="http://192.168.10.55:8282/api/PostApi/blog/comment";
            OkHttpClient client = new OkHttpClient();

            String json = "{" +
                    "'postId' : " + params[0] + ","+
                    "'comment': {" +
                    "'IsAdmin':false," +
                    "'Content': 'Test from android'," +
                    "'Author': 'naser nourani'," +
                    "'Email': 'na@gmail.com'," +
                    "}" +
                    "}";

            RequestBody body = RequestBody.create(JSON, json);
            Request request = new Request.Builder()
                    .url(str)
                    .post(body)
                    .build();
            try {
                Response response = client.newCall(request).execute();
                return   response.body().string();
            } catch (IOException e) {
                Log.d("EXP", "doInBackground: " +  e.getMessage());
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

//
            }

        }


        @Override
        protected void onProgressUpdate(Void... values) {}
    }
}
