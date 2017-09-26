package view;

import android.app.Instrumentation;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.KeyEvent;
import android.widget.RelativeLayout;

import com.example.jasonchen.flyborderviewdemo.R;

import java.util.ArrayList;
import java.util.List;

import adapter.MenuAdapter;
import custom.FlyBorderView;
import custom.TvRecyclerView;
import model.Movie;

/**
 * Created by JasonChen on 2017/9/26.
 */

public class AttachToRecyclerViewActivity extends BaseActivity {
    private TvRecyclerView recyclerView;
    private MenuAdapter menuAdapter;
    private FlyBorderView flyBorder;
    private RelativeLayout parentView;

    @Override
    protected void setView() {
        setContentView(R.layout.activity_attachtorecyclerview);
        recyclerView = (TvRecyclerView) findViewById(R.id.Main_RecyclerView);
        flyBorder = (FlyBorderView) findViewById(R.id.FlyBorder);
        parentView = (RelativeLayout) findViewById(R.id.ParentView);
    }

    @Override
    protected void setData() {
        List<Movie> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(new Movie("Sakura" + i, "ZZZZ"));
        }
        menuAdapter = new MenuAdapter(list);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.HORIZONTAL));
        recyclerView.setAdapter(menuAdapter);

        recyclerView.post(new Runnable() {
            @Override
            public void run() {
                flyBorder.attachToRecyclerView(recyclerView);
                recyclerView.getChildAt(0).setFocusable(true);
                recyclerView.getChildAt(0).setFocusableInTouchMode(true);
                recyclerView.getChildAt(0).requestFocus();
            }
        });

    }

    @Override
    protected void setListener() {

    }
}
