package com.sptechinfo.genericadapter;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.sptechinfo.genericadapter.adapter.GenericRecycleviewAdapter;
import com.sptechinfo.genericadapter.model.GeneralDataModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private GenericRecycleviewAdapter<GeneralDataModel> adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler);
        createAdapter(mRecyclerView);
    }

    private void createAdapter(RecyclerView recyclerView) {
        final List<GeneralDataModel> content = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            GeneralDataModel chat = new GeneralDataModel();
            chat.setName("Sunil_"+i);
            content.add(chat);
        }

        adapter = new GenericRecycleviewAdapter<GeneralDataModel>(content) {


            @Override
            public void onBindViewHolderImpl(RecyclerView.ViewHolder viewHolder, GenericRecycleviewAdapter<GeneralDataModel> adapter, int i) {
                ((ViewHolder) viewHolder).bind(i);
            }

            @Override
            public RecyclerView.ViewHolder onCreateViewHolderImpl(ViewGroup viewGroup, GenericRecycleviewAdapter<GeneralDataModel> adapter, int i) {
                return new ViewHolder(getLayoutInflater().inflate(R.layout.item_list, viewGroup, false));

            }

            @Override
            public int getItemCountImpl(GenericRecycleviewAdapter<GeneralDataModel> adapter) {

                return content.size();
            }
        };
        adapter.getData();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.setData(content);
        recyclerView.setAdapter(adapter);

        adapter.setOnClickEvent(new GenericRecycleviewAdapter.OnClickEvent() {
            @Override
            public void onClick(View v, int position) {
                Log.v("generic_adapter", "click pos:" + position);
                Toast.makeText(MainActivity.this,"Name is: "+adapter.getData().get(position).getName(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView userName;

        public ViewHolder(View itemView) {
            super(itemView);
            userName = (TextView) itemView.findViewById(R.id.userName);
        }

        public void bind(final int position) {
            GeneralDataModel model = adapter.getData().get(position);
            userName.setText(model.getName());
        }
    }
}
