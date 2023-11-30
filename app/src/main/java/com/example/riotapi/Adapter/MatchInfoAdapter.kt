package com.example.riotapi.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
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

                val item0Uri = "https://ddragon.leagueoflegends.com/cdn/13.23.1/img/item/" +  participant.item0 + ".png"
                val item1Uri = "https://ddragon.leagueoflegends.com/cdn/13.23.1/img/item/" +  participant.item1 + ".png"
                val item2Uri = "https://ddragon.leagueoflegends.com/cdn/13.23.1/img/item/" +  participant.item2 + ".png"
                val item3Uri = "https://ddragon.leagueoflegends.com/cdn/13.23.1/img/item/" +  participant.item3 + ".png"
                val item4Uri = "https://ddragon.leagueoflegends.com/cdn/13.23.1/img/item/" +  participant.item4 + ".png"
                val item5Uri = "https://ddragon.leagueoflegends.com/cdn/13.23.1/img/item/" +  participant.item5 + ".png"
                val item6Uri = "https://ddragon.leagueoflegends.com/cdn/13.23.1/img/item/" +  participant.item6 + ".png"


                Glide.with(holder.itemView.context)
                    .load(item0Uri)
                    .fitCenter()
                    .into(holder.binding.item0)

                Glide.with(holder.itemView.context)
                    .load(item1Uri)
                    .fitCenter()
                    .into(holder.binding.item1)

                Glide.with(holder.itemView.context)
                    .load(item2Uri)
                    .fitCenter()
                    .into(holder.binding.item2)

                Glide.with(holder.itemView.context)
                    .load(item3Uri)
                    .fitCenter()
                    .into(holder.binding.item3)

                Glide.with(holder.itemView.context)
                    .load(item4Uri)
                    .fitCenter()
                    .into(holder.binding.item4)

                Glide.with(holder.itemView.context)
                    .load(item5Uri)
                    .fitCenter()
                    .into(holder.binding.item5)

                Glide.with(holder.itemView.context)
                    .load(item6Uri)
                    .fitCenter()
                    .into(holder.binding.item6)



                holder.binding.kill.text = participant.kills.toString()
                holder.binding.death.text = participant.deaths.toString()
                holder.binding.assist.text = participant.assists.toString()

                /*
                holder.binding.kda.text = participant.challenges.kda.toString()
                holder.binding.killParticipate.text = participant.challenges.killParticipation.toString()
                */

            }

        }


    }

    override fun getItemCount() = matchInfoList.size


}