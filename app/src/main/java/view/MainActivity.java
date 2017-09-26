package view;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.example.jasonchen.flyborderviewdemo.R;

/**
 * Created by JasonChen on 2017/9/26.
 */

public class MainActivity extends BaseActivity implements View.OnClickListener{
    private Button attachToView;
    private Button attachToRecyclerView;

    @Override
    protected void setView() {
        setContentView(R.layout.activity_main);
        attachToView= (Button) findViewById(R.id.AttachToView);
        attachToRecyclerView= (Button) findViewById(R.id.AttachToRecyclerView);
    }

    @Override
    protected void setData() {

    }

    @Override
    protected void setListener() {
        attachToView.setOnClickListener(this);
        attachToRecyclerView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()){
            case R.id.AttachToView:
                intent=new Intent(this,AttachToViewActivity.class);
                startActivity(intent);
                break;
            case R.id.AttachToRecyclerView:
                intent=new Intent(this,AttachToRecyclerViewActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
