package info.instagram.instagram.activities;

import android.os.Handler;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;

import info.instagram.instagram.R;
import info.instagram.instagram.fragments.Explorer;
import info.instagram.instagram.fragments.MainPage;
import info.instagram.instagram.fragments.PostFragment;
import info.instagram.instagram.utilities.BaseActivity;

public class MainActivity extends BaseActivity implements View.OnClickListener {

   // private TextView mTitle;
    private ImageView mHome, mPoeple, mMessage, mNotification, mProfile;

    private Handler mHandler;
    // tags used to attach the fragments
    private static final String TAG_HOME = "home";
    private static final String TAG_POEPLE = "search_poeple";
    private static final String TAG_MESSAGE = "message";
    private static final String TAG_NOTIFICATION = "notification";
    private static final String TAG_PROFILE = "profile";
    public static String CURRENT_TAG = TAG_HOME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        mHandler=  new Handler();

        findView();
        setSelectedMenuItem();

       // initRecycleView();

        loadHomeFragment();

    }

    private void findView() {

        //mTitle = (TextView) findViewById(R.id.txt_title);
        mHome = (ImageView) findViewById(R.id.img_home);
        mPoeple = (ImageView) findViewById(R.id.img_poeple);
        mMessage = (ImageView) findViewById(R.id.img_message);
        mNotification = (ImageView) findViewById(R.id.img_notification);
        mProfile = (ImageView) findViewById(R.id.img_profile);

       // mTitle.setOnClickListener(this);
        mHome.setOnClickListener(this);
        mPoeple.setOnClickListener(this);
        mMessage.setOnClickListener(this);
        mNotification.setOnClickListener(this);
        mProfile.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.img_home:
                mSelectedMenuItem = 0;
                break;
            case R.id.img_poeple:
                mSelectedMenuItem = 1;
                break;
            case R.id.img_message:
                mSelectedMenuItem = 2;
                break;
            case R.id.img_notification:
                mSelectedMenuItem = 3;
                break;
            case R.id.img_profile:
                mSelectedMenuItem = 4;
                break;
            default:
                break;
        }

        setSelectedMenuItem();
    }

    private void setSelectedMenuItem() {
        mHome.setColorFilter(ContextCompat.getColor(getApplicationContext(), android.R.color.darker_gray));
        mPoeple.setColorFilter(ContextCompat.getColor(getApplicationContext(), android.R.color.darker_gray));
        mMessage.setColorFilter(ContextCompat.getColor(getApplicationContext(), android.R.color.darker_gray));
        mNotification.setColorFilter(ContextCompat.getColor(getApplicationContext(), android.R.color.darker_gray));
        mProfile.setColorFilter(ContextCompat.getColor(getApplicationContext(), android.R.color.darker_gray));
        switch (mSelectedMenuItem) {
            case 0:
                mHome.setColorFilter(ContextCompat.getColor(getApplicationContext(), android.R.color.black));
                CURRENT_TAG = TAG_HOME;
                break;
            case 1:
                mPoeple.setColorFilter(ContextCompat.getColor(getApplicationContext(), android.R.color.black));
                CURRENT_TAG = TAG_POEPLE;
                break;
            case 2:
                mMessage.setColorFilter(ContextCompat.getColor(getApplicationContext(), android.R.color.black));
                CURRENT_TAG = TAG_MESSAGE;
                break;
            case 3:
                mNotification.setColorFilter(ContextCompat.getColor(getApplicationContext(), android.R.color.black));
                CURRENT_TAG = TAG_NOTIFICATION;
                return;
            case 4:
                mProfile.setColorFilter(ContextCompat.getColor(getApplicationContext(), android.R.color.black));
                CURRENT_TAG = TAG_PROFILE;
                break;
            default:
                break;
        }


        loadHomeFragment();
    }


    private void loadHomeFragment() {

        // if user select the current navigation menu again, don't do anything
        // just close the navigation drawer
        if (getSupportFragmentManager().findFragmentByTag(CURRENT_TAG) != null) {
            return;
        }

        // Sometimes, when fragment has huge data, screen seems hanging
        // when switching between navigation menus
        // So using runnable, the fragment is loaded with cross fade effect
        // This effect can be seen in GMail app
        Runnable mPendingRunnable = new Runnable() {
            @Override
            public void run() {
                // update the main content by replacing fragments
                Fragment fragment = getHomeFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                        android.R.anim.fade_out);
                fragmentTransaction.replace(R.id.content, fragment, CURRENT_TAG);
                fragmentTransaction.commitAllowingStateLoss();
            }
        };

        // If mPendingRunnable is not null, then add to the message queue
        if (mPendingRunnable != null) {
            mHandler.post(mPendingRunnable);
        }


        // refresh toolbar menu
        invalidateOptionsMenu();
    }

    private Fragment getHomeFragment() {
        switch (mSelectedMenuItem) {
            case 0:
                MainPage mainPage = new MainPage();
                return mainPage;
            case 1:
                Explorer explorer = new Explorer();
                return explorer;
            case 2:
                PostFragment postFragment = new PostFragment();
                return postFragment;
            case 3:
                return null;
            case 4 :
                MainPage mainPage2 = new MainPage();
                return mainPage2;
            default:
                return new MainPage();
        }
    }

}
