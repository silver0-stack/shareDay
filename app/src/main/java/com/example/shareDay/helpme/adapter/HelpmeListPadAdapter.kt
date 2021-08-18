package com.example.shareDay.helpme.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shareDay.ChatActivity
import com.example.shareDay.R
import com.example.shareDay.helpme.dto.pad
import com.example.shareDay.mapmenu.MapActivity
import kotlinx.android.synthetic.main.helpyou_write_list.view.*

class HelpmeListPadAdapter(private val userList: ArrayList<pad>) :
    RecyclerView.Adapter<HelpmeListPadAdapter.MyViewHolder>() {

    lateinit var chatIcon: ImageButton
    lateinit var mapIcon: ImageButton

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context.applicationContext).inflate(
            R.layout.helpme_item_list,
            parent,
            false
        )

        chatIcon = itemView.findViewById(R.id.startChat)
        mapIcon=itemView.findViewById(R.id.checkLoc)

        return MyViewHolder(itemView).apply {
            //채팅아이콘 클릭 이벤트
            chatIcon.setOnClickListener {
                val Img = userImg.text.toString()
                val intent = Intent(parent.context.applicationContext, ChatActivity::class.java)
                intent.putExtra("pofileImg", Img) /*1:1 채팅방으로 프사 송신*/
                parent.context.startActivity(intent)
            }
            //지도아이콘 클릭 이벤트
            mapIcon.setOnClickListener {
                val Img = userImg.text.toString()
                val Loc = userLocation.text.toString()
                val Name = userName.text.toString()
                val Contents = contents.text.toString()

                val intent =
                    Intent(parent.context.applicationContext, MapActivity::class.java) //일단은 지도로 인텐트 해놓음 //자세한건 다음
                intent.putExtra("Img", Img) /*해당 위치 레이아웃에 이름,프사,내용,위치 송신*/
                intent.putExtra("Loc", Loc)
                intent.putExtra("Name", Name)
                intent.putExtra("Contents", Contents)

                parent.context.startActivity(intent)
            }
        }
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = userList[position]

        val imageName: String? = currentItem.userImg //저장된 이미지 이름 받아오기
        val imgUrl: String = "https://firebasestorage.googleapis.com/" +
                "v0/b/nami-market.appspot.com/o/images%2F" + imageName +
                "?alt=media&token=8770eebd-9052-4fe7-9e1a-a70273921fbf" //이미지 url

        Glide.with(holder.userImg).load(imgUrl).into(holder.userImg) //이미지 배치할 곳에 url 로드

        //holder.userImg.text = currentItem.userImg
        holder. userName.text =currentItem.userName
        holder.userLocation .text =currentItem.userLocation
        holder.contents.text =currentItem.contents
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val userImg = itemView.findViewById<ImageView>(R.id.HmItemImg)
        val userName = itemView.findViewById<TextView>(R.id.HmItemName)
        val userLocation = itemView.findViewById<TextView>(R.id.HmItemLoc)
        val contents = itemView.findViewById<TextView>(R.id.HmItemContents)
    }

}
