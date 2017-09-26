package custom;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.util.Log;

/**
 * Created by JasonChen on 2017/9/20.
 */

public class TvRecyclerView extends RecyclerView {


    public TvRecyclerView(Context context) {
        this(context,null);
    }

    public TvRecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TvRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev){
//        return ev.getAction()==MotionEvent.ACTION_MOVE||super.dispatchTouchEvent(ev);
//    }

    //第一个条目是否可见
    public boolean isFirstItemVisible(){
        LayoutManager layoutManager=getLayoutManager();
        if(layoutManager instanceof StaggeredGridLayoutManager){
            int[] firstVisibleItems=null;
            firstVisibleItems=((StaggeredGridLayoutManager) layoutManager).findFirstCompletelyVisibleItemPositions(firstVisibleItems);
            int position=firstVisibleItems[0];
            Log.d("isFirstItemVisible",position+"");
            return position==0;
        }else if(layoutManager instanceof LinearLayoutManager){
            int position=((LinearLayoutManager) layoutManager).findFirstCompletelyVisibleItemPosition();
            return position==0;
        }
        return false;
    }

    //最后一个条目是否可见
    public boolean isLastItemVisible(int rowCount,int allItemNum){
        LayoutManager layoutManager=getLayoutManager();
        if(layoutManager instanceof StaggeredGridLayoutManager){
            int[] lastVisibleItems=null;
            lastVisibleItems=((StaggeredGridLayoutManager) layoutManager).findLastCompletelyVisibleItemPositions(lastVisibleItems);
            int position=lastVisibleItems[0];
            boolean isVisible=position>=(allItemNum-rowCount);
            if(isVisible){
                scrollBy(1,0);
            }
            Log.d("isLastItemVisible",position+"");
            return isVisible;
        }else if(layoutManager instanceof LinearLayoutManager){
            int position=((LinearLayoutManager) layoutManager).findLastCompletelyVisibleItemPosition();
            return position==allItemNum-1;
        }
        return false;
    }
}
