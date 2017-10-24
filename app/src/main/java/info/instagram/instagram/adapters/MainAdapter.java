package info.instagram.instagram.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

import info.instagram.instagram.R;
import info.instagram.instagram.entities.Post;

/**
 * Created by Naser on 10/24/2017.
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private ArrayList<Post> arrayList;
    private Context mContext;

    public MainAdapter(Context mContext, ArrayList<Post> posts) {
        this.arrayList = posts;
        this.mContext = mContext;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mProfileName.setText(arrayList.get(position).getProfileName());
        holder.mProfileName2.setText(arrayList.get(position).getProfileName());
        holder.mPostText.setText(arrayList.get(position).getPostText());
        holder.mLikeCount.setText(String.valueOf(arrayList.get(position).getLikeCount()) + " Likes ");

        holder.imgProfile.setImageResource(arrayList.get(position).getProfileImage());
        holder.imgPost.setImageResource(arrayList.get(position).getPostImage());

        if (arrayList.get(position).isLiked()) {
            holder.imgLike.setImageResource(R.drawable.ic_favorite_black_24dp);
        } else {
            holder.imgLike.setImageResource(R.drawable.ic_favorite_border_black_24dp);
        }

        if (arrayList.get(position).isCommented()) {
            holder.imgComment.setImageResource(R.drawable.ic_chat_black_24dp);
        } else {
            holder.imgComment.setImageResource(R.drawable.ic_chat_bubble_outline_black_24dp);
        }

        Random r = new Random();
        int red=r.nextInt(255 - 0 + 1)+0;
        int green=r.nextInt(255 - 0 + 1)+0;
        int blue=r.nextInt(255 - 0 + 1)+0;

        GradientDrawable draw = new GradientDrawable();
        draw.setShape(GradientDrawable.RECTANGLE);
        draw.setColor(Color.rgb(red,green,blue));
        draw.setCornerRadius(2.0f);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            holder.layout.setBackground(draw);
        }


        holder.imgOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);

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
                dialog.show();
            }
        });

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_row_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout layout;
        private TextView mProfileName, mProfileName2, mPostText, mLikeCount;
        private ImageView imgProfile, imgPost, imgLike, imgComment, imgShare, imgFavorite, imgOptions;

        public ViewHolder(View itemView) {
            super(itemView);

            layout = (LinearLayout) itemView.findViewById(R.id.layout_top);
            mProfileName = (TextView) itemView.findViewById(R.id.txt_profile_name);
            mProfileName2 = (TextView) itemView.findViewById(R.id.txt_profile_name_2);
            mPostText = (TextView) itemView.findViewById(R.id.txt_post_text);
            mLikeCount = (TextView) itemView.findViewById(R.id.txt_like_count);

            imgProfile = (ImageView) itemView.findViewById(R.id.img_profile_pic);
            imgPost = (ImageView) itemView.findViewById(R.id.img_post_picture);
            imgLike = (ImageView) itemView.findViewById(R.id.img_like);
            imgComment = (ImageView) itemView.findViewById(R.id.img_comment);
            imgShare = (ImageView) itemView.findViewById(R.id.img_share);
            imgFavorite = (ImageView) itemView.findViewById(R.id.img_favorite);
            imgOptions = (ImageView) itemView.findViewById(R.id.img_options);
        }
    }

}
