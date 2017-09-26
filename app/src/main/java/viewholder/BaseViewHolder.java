package viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by JasonChen on 2017/9/20.
 */

public abstract  class BaseViewHolder<T> extends RecyclerView.ViewHolder {
    public View itemView;

    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void setUpView(T model, int position, RecyclerView.Adapter adapter);
}
