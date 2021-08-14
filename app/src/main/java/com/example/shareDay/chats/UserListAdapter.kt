package com.example.shareDay.chats

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shareDay.R
import java.util.ArrayList


class UserListAdapter(private val chatList : ArrayList<UserInfo>) :
    RecyclerView.Adapter<UserListAdapter.ViewHolder>() {

    //View holder 생성하는 부분
    //inflate된 view에 listener 설정 가능
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v: View =
            LayoutInflater.from(parent.context).inflate(R.layout.chat_list_item, parent, false)

        //1:1 chat element
        return ViewHolder(v).apply {
            v.setOnClickListener {
                val curPos: Int = adapterPosition  //curPos는 현재 클릭하는 포지션
                val chatlist: UserInfo = chatList[curPos]
                chatlist.name =nameAge.text.toString().trim()
                chatlist.about =about.text.toString().trim()

                val intent = Intent(parent.context, ChatActivity::class.java)
                intent.putExtra("name","person1") /*송신*/
                intent.putExtra("about","want one tampon!")
                parent.context.startActivity(intent)
            }
        }
    }
    //생성된 View holder를 데이터 바인딩 될 때마다 호출
    //이 지점에서 데이터세트로부터 요소 얻고
    // 뷰의 컨텐츠를 그 요소로 대체함
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item =chatList[position]
        val imageName:String?=item.profile //저장된 이미지 이름 받아오기
        val imgUrl : String =  "https://firebasestorage.googleapis.com/" +
                "gs://shareday-1628535459047.appspot.com/images/IMAGE_20210814_072523_.png"+
                imageName+ "?alt=media&token=2cf9823c-f7f7-4a04-ae3c-85d3d3ca41ae" ////이미지 url

        Glide.with(holder.itemView).load(imgUrl).into(holder.profileImg)//이미지 배치할 곳에 url 로드

        holder.nameAge.text = item.name
        holder.about.text = item.about
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val profileImg: ImageView = itemView.findViewById(R.id.profile_img)
        val nameAge: TextView = itemView.findViewById(R.id.name_age)
        val about: TextView = itemView.findViewById(R.id.about)
    }
    //레이아웃 매니저에 의해 생겨난 데이터세트의 사이즈 리턴
    override fun getItemCount(): Int {
        return chatList.size
    }


}

