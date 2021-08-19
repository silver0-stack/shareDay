package com.example.shareDay

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.shareDay.chats.UserInfo
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase

//홈 채팅 탭에 뜨는 채팅방 목록 fragment의 recyclerView를 띄우는 Adapter
class UserListAdapter(val listData: List<UserInfo>, val clickListener: ClickListener)
    : RecyclerView.Adapter<UserListAdapter.MyViewHoler>() {

    val userUID = Firebase.auth.currentUser?.uid
    var userNick :String = "익명"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHoler {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.chat_list_item, parent, false)

        //유저 식별을 위한 닉네임 정보 받아오기
        //USER_NAME_ME = Firebase.auth.currentUser?.email //이메일

        var db: DatabaseReference
        db = FirebaseDatabase.getInstance().reference
        db.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                userNick = snapshot.child("Users").child(userUID.toString()).child("userNickname").value.toString()
                Log.e("nick", userNick)
            }
            override fun onCancelled(error: DatabaseError) {}
        })

        return MyViewHoler(view)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    class MyViewHoler(view: View): RecyclerView.ViewHolder(view){
        var name_text: TextView
        var about: TextView
        var chatNum: TextView
        var profile_img : ImageView

        init{
            name_text = view.findViewById(R.id.name_text)
            about = view.findViewById(R.id.about)
            chatNum = view.findViewById(R.id.chatNum)
            profile_img = view.findViewById(R.id.profile_img)
        }
    }

    override fun onBindViewHolder(holder: MyViewHoler, position: Int) {
        holder.name_text.text = listData.get(position).name
        holder.about.text = listData.get(position).about
        holder.chatNum.text = listData.get(position).chatNum.toString()

        holder.itemView.setOnClickListener {
            listener?.onItemClick()

            val intent = Intent(holder.itemView?.context, ChatActivity::class.java)
            intent.putExtra("chatName", listData.get(position).name)
            intent.putExtra("userName", userNick)
            ContextCompat.startActivity(holder.itemView.context, intent, null)
        }
    }

    interface ClickListener{
        //fun onItemClick(dataModel: UserInfo)
        fun onItemClick()
    }
    private var listener : ClickListener? = null
    fun setOnItemClickListener(listener : ClickListener) {
        this.listener = listener
    }
}
