package view;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.RelativeLayout;

import com.example.jasonchen.flyborderviewdemo.R;

import custom.FlyBorderView;

/**
 * Created by JasonChen on 2017/9/26.
 */

public class AttachToViewActivity extends BaseActivity {
    private RelativeLayout rootView;
    private FlyBorderView flyBorderView;

    @Override
    protected void setView() {
        setContentView(R.layout.activity_attachtoview);
        rootView = (RelativeLayout) findViewById(R.id.RootView);
        flyBorderView = (FlyBorderView) findViewById(R.id.FlyBorderView);
    }

    @Override
    protected void setData() {

    }

    @Override
    protected void setListener() {
        rootView.getViewTreeObserver().addOnGlobalFocusChangeListener(new ViewTreeObserver.OnGlobalFocusChangeListener() {
            @Override
            public void onGlobalFocusChanged(View oldFocus, View newFocus) {
                if (newFocus != null) {
//                    newFocus.animate().scaleX(1.20f).scaleY(1.20f).translationZ(1.1f).setDuration(200).start();
                    ObjectAnimator scaleX = ObjectAnimator.ofFloat(newFocus, "scaleX", 1.0f, 1.20f);
                    ObjectAnimator scaleY = ObjectAnimator.ofFloat(newFocus, "scaleY", 1.0f, 1.20f);
                    ObjectAnimator translationZ = ObjectAnimator.ofFloat(newFocus, "translationZ", 0f, 1.0f);
                    AnimatorSet animatorSet = new AnimatorSet();
                    animatorSet.play(scaleX).with(scaleY).with(translationZ);
                    animatorSet.setDuration(200);
                    animatorSet.start();
                }
                if (oldFocus != null) {
//                    oldFocus.animate().scaleX(1.0f).scaleY(1.0f).translationZ(1.0f).setDuration(200).start();
                    ObjectAnimator scaleX = ObjectAnimator.ofFloat(oldFocus, "scaleX", 1.20f, 1.0f);
                    ObjectAnimator scaleY = ObjectAnimator.ofFloat(oldFocus, "scaleY", 1.20f, 1.0f);
                    ObjectAnimator translationZ = ObjectAnimator.ofFloat(oldFocus, "translationZ", 1.0f, 0f);
                    AnimatorSet animatorSet = new AnimatorSet();
                    animatorSet.setDuration(200);
                    animatorSet.play(scaleX).with(scaleY).with(translationZ);
                    animatorSet.start();
                }
                flyBorderView.attachToView(newFocus, 1.20f);
            }
        });
        rootView.post(new Runnable() {
            @Override
            public void run() {
               rootView.getChildAt(0).requestFocus();
            }
        });
    }
}
