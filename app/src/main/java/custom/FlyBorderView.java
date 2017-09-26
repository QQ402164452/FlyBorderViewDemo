package custom;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.LinearInterpolator;

import com.example.jasonchen.flyborderviewdemo.R;


/**
 * Created by Jason on 2017/9/24.
 */

public class FlyBorderView extends View {
    private int borderWidth;//焦点移动飞框的边框
    private int duration = 200;//动画持续时间

    public FlyBorderView(Context context) {
        this(context, null);
    }

    public FlyBorderView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FlyBorderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        borderWidth = getContext().getResources().getDimensionPixelSize(R.dimen.FlyBorderWidth);
    }

    /**
     * @param newFocus 下一个选中项视图
     * @param scale    选中项视图的伸缩大小
     */
    public void attachToView(View newFocus, float scale) {
        final int widthInc = (int) ((newFocus.getWidth() * scale + 2 * borderWidth - getWidth()));//当前选中项与下一个选中项的宽度偏移量
        final int heightInc = (int) ((newFocus.getHeight() * scale + 2 * borderWidth - getHeight()));//当前选中项与下一个选中项的高度偏移量
        float translateX = newFocus.getLeft() - borderWidth
                - (newFocus.getWidth() * scale - newFocus.getWidth()) / 2;//飞框到达下一个选中项的X轴偏移量
        float translateY = newFocus.getTop() - borderWidth
                - (newFocus.getHeight() * scale - newFocus.getHeight()) / 2;//飞框到达下一个选中项的Y轴偏移量

        startTotalAnim(widthInc, heightInc, translateX, translateY);//调用飞框 自适应和移动 动画效果
    }

    public void attachToRecyclerView(final RecyclerView recyclerView) {
        View childView = recyclerView.getChildAt(0);
        final int childWidth = childView.getWidth();
        final int childHeight = childView.getHeight();
        startTotalAnim(childWidth + 2 * borderWidth, childHeight + 2 * borderWidth,
                childView.getLeft() - borderWidth, childView.getTop() - borderWidth);

        final DisplayMetrics displayMetrics = getContext().getApplicationContext().getResources().getDisplayMetrics();
        recyclerView.getRootView().getViewTreeObserver().addOnGlobalFocusChangeListener(new ViewTreeObserver.OnGlobalFocusChangeListener() {
            @Override
            public void onGlobalFocusChanged(View oldFocus, View newFocus) {

                if (newFocus.getLayoutParams() instanceof RecyclerView.LayoutParams && oldFocus.getLayoutParams() instanceof RecyclerView.LayoutParams) {
                    int left;
                    if (newFocus.getLeft() >= displayMetrics.widthPixels - childWidth) {
                        left = displayMetrics.widthPixels - childWidth;
                    } else if (newFocus.getLeft() <= 0) {
                        left = 0;
                    } else {
                        left = newFocus.getLeft();
                    }
                    int widthInc = newFocus.getWidth() + 2 * borderWidth - getWidth();
                    int heightInc = newFocus.getHeight() + 2 * borderWidth - getHeight();
                    int translationX = left - borderWidth;
                    int translationY = newFocus.getTop() - borderWidth;
                    startTotalAnim(widthInc, heightInc, translationX, translationY);
                }

            }
        });
    }

    /**
     * 飞框 自适应和移动 动画效果
     *
     * @param widthInc   宽度偏移量
     * @param heightInc  高度偏移量
     * @param translateX X轴偏移量
     * @param translateY Y轴偏移量
     */
    private void startTotalAnim(final int widthInc, final int heightInc, float translateX, float translateY) {
        final int width = getWidth();//当前飞框的宽度
        final int height = getHeight();//当前飞框的高度
        ValueAnimator widthAndHeightChangeAnimator = ValueAnimator.ofFloat(0, 1).setDuration(duration);//数值变化动画器，能获取平均变化的值
        widthAndHeightChangeAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                setFlyBorderLayoutParams((int) (width + widthInc * Float.parseFloat(valueAnimator.getAnimatedValue().toString())),
                        (int) (height + heightInc * Float.parseFloat(valueAnimator.getAnimatedValue().toString())));//设置当前飞框的宽度和高度的自适应变化
            }
        });

        ObjectAnimator translationX = ObjectAnimator.ofFloat(this, "translationX", translateX);//X轴移动的属性动画
        ObjectAnimator translationY = ObjectAnimator.ofFloat(this, "translationY", translateY);//y轴移动的属性动画

        AnimatorSet set = new AnimatorSet();//动画集合
        set.play(widthAndHeightChangeAnimator).with(translationX).with(translationY);//动画一起实现
        set.setDuration(duration);
        set.setInterpolator(new LinearInterpolator());//设置动画插值器
        set.start();//开始动画
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    private void setFlyBorderLayoutParams(int width, int height) {//设置焦点移动飞框的宽度和高度
        ViewGroup.LayoutParams params = getLayoutParams();
        params.width = width;
        params.height = height;
        setLayoutParams(params);
    }

    private Rect findLocation(View view) {
        ViewGroup viewGroup = (ViewGroup) this.getParent();
        if (viewGroup != null && view != null) {
            Rect rect = new Rect();
            viewGroup.offsetDescendantRectToMyCoords(view, rect);//将一个在该视图的子视图坐标系中的Rect偏移到该视图的坐标系中
            return rect;
        }
        return null;
    }
}
