package com.example.shareDay.board;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shareDay.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class WriteList extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Board> arrayList;
    private FirebaseDatabase database;
    private DatabaseReference mStore;
    private ImageButton main_write;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.helpme_write_list);

        main_write = findViewById(R.id.main_write);


        recyclerView = findViewById(R.id.main_recycler_view);
        layoutManager = new LinearLayoutManager(this);
        ((LinearLayoutManager)layoutManager).setReverseLayout(true);
        ((LinearLayoutManager)layoutManager).setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);

        arrayList = new ArrayList<>();  //보드 객체를 담아 어탭터로 넘겨 배열리스트줄

        database = FirebaseDatabase.getInstance(); //파이어 베이스 데이터 베이스 연동
        mStore = database.getReference("Board");//데이터베이스 테이블 연




        //데이터베이스에서 데이터 가져오기
        //데이터베이스에 저장되어 있는 데이터들 리스트 뷰에 불러오기
        mStore.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrayList.clear();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                   Board board=dataSnapshot.getValue(Board.class);
                   arrayList.add(board);

                }
                adapter.notifyDataSetChanged();

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




        adapter = new BoardAdapter(arrayList, this);
        recyclerView.setAdapter(adapter); //리사이클러뷰에 어댑터 연결

        main_write.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(WriteList.this, WriteActivity.class);
                startActivity(intent);



            }
        });




    }
}
