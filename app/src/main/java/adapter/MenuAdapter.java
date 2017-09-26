package adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.jasonchen.flyborderviewdemo.R;

import java.util.List;

import model.Movie;
import viewholder.MenuViewHolder;

/**
 * Created by JasonChen on 2017/9/20.
 */

public class MenuAdapter extends RecyclerView.Adapter<MenuViewHolder> {
    private List<Movie> list;

    public MenuAdapter(List<Movie> list){
        this.list=list;
    }

    @Override
    public MenuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MenuViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item_menu,parent,false));
    }

    @Override
    public void onBindViewHolder(MenuViewHolder holder, int position) {
        holder.setUpView(list.get(position),position,this);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
