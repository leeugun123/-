package com.example.riotapi.Adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.riotapi.Data.JsonData.ChampHashMap
import com.example.riotapi.Data.RetrofitData.ChampSkillInfo
import com.example.riotapi.Data.RetrofitData.MatchData.MatchDto
import com.example.riotapi.Data.UserInfo
import com.example.riotapi.R
import com.example.riotapi.databinding.MatchInfoListBinding

class MatchInfoAdapter(private val matchInfoList : List<MatchDto>) : RecyclerView.Adapter<MatchInfoAdapter.ViewHolder>() {

    inner class ViewHolder(val binding : MatchInfoListBinding) : RecyclerView.ViewHolder(binding.root),
            View.OnClickListener{

        init {
            itemView.setOnClickListener(this)
        }
        override fun onClick(v: View?) {

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchInfoAdapter.ViewHolder {
        val binding = MatchInfoListBinding.inflate(LayoutInflater.from(parent.context) , parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MatchInfoAdapter.ViewHolder, position: Int) {

        val participant_list = matchInfoList[position]

        participant_list.info.participants.forEach { participant ->

            if(participant.puuid == UserInfo.puuId){

                if(participant.win){
                    holder.binding.winLoose.text = "승"
                    holder.binding.winLoose.setBackgroundResource(R.color.win)
                }else{
                    holder.binding.winLoose.text = "패"
                    holder.binding.winLoose.setBackgroundResource(R.color.lose)
                }

                val champUri = "https://ddragon.leagueoflegends.com/cdn/13.23.1/img/champion/" + participant.championName  + ".png"

                Glide.with(holder.itemView.context)
                    .load(champUri)
                    .fitCenter()
                    .into(holder.binding.champImg)

                holder.binding.kill.text = participant.kills.toString()
                holder.binding.death.text = participant.deaths.toString()
                holder.binding.assist.text = participant.assists.toString()



            }

        }


    }

    override fun getItemCount() = matchInfoList.size


}