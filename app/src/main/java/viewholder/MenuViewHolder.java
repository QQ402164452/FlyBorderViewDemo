package viewholder;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jasonchen.flyborderviewdemo.R;

import model.Movie;

/**
 * Created by JasonChen on 2017/9/20.
 */

public class MenuViewHolder extends BaseViewHolder<Movie> {
    private TextView title;
    private TextView content;

    public MenuViewHolder(View itemView) {
        super(itemView);
        title= (TextView) itemView.findViewById(R.id.Menu_Title);
        content= (TextView) itemView.findViewById(R.id.Menu_Content);
        this.itemView=itemView;
        itemView.setFocusable(true);
    }

    @Override
    public void setUpView(Movie model, final int position, RecyclerView.Adapter adapter) {
        title.setText(model.getTitle());
        content.setText(model.getContent());
        itemView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b){
                    focusStatus(view);
                }else{
                    normalStatus(view);
                }
            }
        });
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Log.e("No"+position,"Click");
            }
        });
    }

    private void focusStatus(View itemView){
        if(itemView!=null){
            if(Build.VERSION.SDK_INT>=21){
//                ViewCompat.animate(itemView).scaleX(1.20f).scaleY(1.20f).translationZ(1).start();

                ObjectAnimator scaleX=ObjectAnimator.ofFloat(itemView,"scaleX",1.0f,1.20f);
                ObjectAnimator scaleY=ObjectAnimator.ofFloat(itemView,"scaleY",1.0f,1.20f);
                ObjectAnimator translationZ=ObjectAnimator.ofFloat(itemView,"translationZ",0f,1.0f);
                AnimatorSet animatorSet=new AnimatorSet();
                animatorSet.play(scaleX).with(scaleY).with(translationZ);
                animatorSet.setDuration(200);
                animatorSet.start();
            }else{
//                ViewCompat.animate(itemView).scaleX(1.20f).scaleY(1.20f).start();
                ObjectAnimator scaleX=ObjectAnimator.ofFloat(itemView,"scaleX",1.0f,1.20f);
                ObjectAnimator scaleY=ObjectAnimator.ofFloat(itemView,"scaleY",1.0f,1.20f);
                ObjectAnimator translationZ=ObjectAnimator.ofFloat(itemView,"translationZ",0f,1.0f);
                AnimatorSet animatorSet=new AnimatorSet();
                animatorSet.play(scaleX).with(scaleY).with(translationZ);
                animatorSet.setDuration(200);
                animatorSet.start();

                ViewGroup parent= (ViewGroup) itemView.getParent();
                parent.requestLayout();
                parent.invalidate();
            }
        }
    }

    private void normalStatus(View itemView){
        if(itemView!=null){
            if(Build.VERSION.SDK_INT>=21){
//                ViewCompat.animate(itemView).scaleX(1.0f).scaleY(1.0f).translationZ(0f).start();

                ObjectAnimator scaleX=ObjectAnimator.ofFloat(itemView,"scaleX",1.20f,1.0f);
                ObjectAnimator scaleY=ObjectAnimator.ofFloat(itemView,"scaleY",1.20f,1.0f);
                ObjectAnimator translationZ=ObjectAnimator.ofFloat(itemView,"translationZ",1.0f,0f);
                AnimatorSet animatorSet=new AnimatorSet();
                animatorSet.setDuration(200);
                animatorSet.play(scaleX).with(scaleY).with(translationZ);
                animatorSet.start();
            }else{
//                ViewCompat.animate(itemView).scaleX(1.0f).scaleY(1.0f).translationZ(0f).start();
                ObjectAnimator scaleX=ObjectAnimator.ofFloat(itemView,"scaleX",1.20f,1.0f);
                ObjectAnimator scaleY=ObjectAnimator.ofFloat(itemView,"scaleY",1.20f,1.0f);
                ObjectAnimator translationZ=ObjectAnimator.ofFloat(itemView,"translationZ",1.0f,0f);
                AnimatorSet animatorSet=new AnimatorSet();
                animatorSet.setDuration(200);
                animatorSet.play(scaleX).with(scaleY).with(translationZ);
                animatorSet.start();

                ViewGroup parent= (ViewGroup) itemView.getParent();
                parent.requestLayout();
                parent.invalidate();
            }

        }
    }
}
