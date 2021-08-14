package com.example.shareDay.chats;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.shareDay.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class StartChatActivity extends AppCompatActivity {

    private final String nickname = "익명1";
    private UserListAdapter adapter;
    private ArrayList<UserInfo> list=new ArrayList<>();
    private EditText chatText;
    private final FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private final DatabaseReference databaseReference = firebaseDatabase.getReference();
    private RecyclerView Chat_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        chatText = findViewById(R.id.chatText);
        Button sendButton = findViewById(R.id.sendButton);

        //입력창에 메시지를 입력 후 버튼클릭했을 때
        sendButton.setOnClickListener(v -> {
                    String msg = chatText.getText().toString();
                    if (msg.equals("")) return;
                    Intent intent = new Intent(StartChatActivity.this, ChatActivity.class);
                    intent.putExtra("name", nickname);
                    intent.putExtra("msg", msg);
                    chatText.setText("");
                    startActivity(intent);
                }
        );
        showChatList();
    }

    //        //리사이클러뷰에 어댑터 적용
//        RecyclerView recyclerView = findViewById(R.id.recyclerView);
//        recyclerView.setHasFixedSize(true);
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(layoutManager);
//
//        List<Chat> chatList = new ArrayList<>();
//        adapter = new ChatAdapter(chatList, nickname);
//        recyclerView.setAdapter(adapter);
//
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        myRef = database.getReference("message");
//
//        //데이터들을 추가, 변경, 제거, 이동, 취소
//        myRef.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//
//                //어댑터에 DTO추가
//                Chat chat = snapshot.getValue(Chat.class);
//                assert chat != null;
//                ((ChatAdapter)adapter).addChat(chat);
//            }
//
//            @Override
//            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//
//            }
//
//            @Override
//            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//    }

    // 채팅 프래그먼트 생성
    @SuppressLint("InflateParams")
    private void showChatList() {
        LayoutInflater inflater = getLayoutInflater();
        View mView = inflater.inflate(R.layout.chat_fragment, null);
        RecyclerView recyclerView = mView.findViewById(R.id.list);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this); //채팅 리스트 하나 추가
        recyclerView.setLayoutManager(layoutManager);

        // 데이터 받아오기 및 어댑터 데이터 추가 및 삭제 등..리스너 관리
        databaseReference.child("chat").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, String s) {
                //어댑터에 DTO추가
                Chat chat = dataSnapshot.getValue(Chat.class);
                assert chat != null;
                Log.e("LOG", "dataSnapshot.getKey() : " + dataSnapshot.getKey());
                adapter=new UserListAdapter(list);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}


