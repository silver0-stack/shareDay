package com.example.shareDay.chats

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shareDay.R
import java.util.ArrayList


class UserListAdapter(items: RecyclerView?) :
    RecyclerView.Adapter<UserListAdapter.Holder>() {
    var items = ArrayList<UserInfo>()

    //View holder 생성하는 부분
    //inflate된 view에 listener 설정 가능
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val v: View = LayoutInflater.from(parent.context).inflate(R.layout.chat_list_item, parent, false)
        return Holder(v)
    }

    //생성된 View holder를 데이터 바인딩 될 때마다 호출
    override fun onBindViewHolder(holder: Holder, position: Int) {
        val item = items[position]
        holder.profileImg.setImageDrawable(item.profile)
        holder.nameAge.setText(item.name)
        holder.about.setText(item.about)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val profileImg: ImageView
        val nameAge: TextView
        val about: TextView

        init {
            profileImg = itemView.findViewById(R.id.profile_img)
            nameAge = itemView.findViewById(R.id.name_age)
            about = itemView.findViewById(R.id.about)
        }
    }

}
