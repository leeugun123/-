package com.example.riotapi.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.riotapi.Data.JsonData.Champ.ChampHashMap
import com.example.riotapi.Data.JsonData.Spell.SpellHashMap
import com.example.riotapi.Data.JsonData.Spell.SpellHashMap.spellHashInfo
import com.example.riotapi.Data.JsonData.Spell.SpellMap
import com.example.riotapi.Data.RetrofitData.MatchData.MatchDto
import com.example.riotapi.Data.UserInfo
import com.example.riotapi.R
import com.example.riotapi.databinding.MatchInfoListBinding
import java.math.BigDecimal
import java.math.RoundingMode

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

                // ================================  승패 여부  =====================================

                if(participant.win){
                    holder.binding.winLoose.text = "승"
                    holder.binding.winLoose.setBackgroundResource(R.color.win)
                }else{
                    holder.binding.winLoose.text = "패"
                    holder.binding.winLoose.setBackgroundResource(R.color.lose)
                }

                // ==================================================================================




                // ================================  챔피언 이미지 =====================================

                val champUri = "https://ddragon.leagueoflegends.com/cdn/13.23.1/img/champion/" + participant.championName  + ".png"

                Glide.with(holder.itemView.context)
                    .load(champUri)
                    .fitCenter()
                    .into(holder.binding.champImg)

                // ==================================================================================





                // ================================  스펠  =====================================

                val spellUri_1 = "https://ddragon.leagueoflegends.com/cdn/13.23.1/img/spell/" + spellHashInfo[participant.spell_1.toString()]  + ".png"
                val spellUri_2 = "https://ddragon.leagueoflegends.com/cdn/13.23.1/img/spell/" + spellHashInfo[participant.spell_2.toString()]  + ".png"

                Glide.with(holder.itemView.context)
                    .load(spellUri_1)
                    .fitCenter()
                    .into(holder.binding.spell1)


                Glide.with(holder.itemView.context)
                    .load(spellUri_2)
                    .fitCenter()
                    .into(holder.binding.spell2)

                // =======================================================================================




                // ================================  아이템 이미지  =====================================

                val itemList = listOf(
                    participant.item0, participant.item1 , participant.item2 , participant.item3 , participant.item4
                    ,participant.item4, participant.item5 , participant.item6
                )

                val itemViews = listOf(
                    holder.binding.item0, holder.binding.item1, holder.binding.item2,
                    holder.binding.item3, holder.binding.item4, holder.binding.item5, holder.binding.item6
                )

                itemList.zip(itemViews).forEach { (itemId, itemView) ->

                    if(itemId != 0){
                        val itemUri = "https://ddragon.leagueoflegends.com/cdn/13.23.1/img/item/$itemId.png"

                        Glide.with(holder.itemView.context)
                            .load(itemUri)
                            .fitCenter()
                            .into(itemView)
                    }

                }



                // =======================================================================================


                holder.binding.kill.text = participant.kills.toString()
                holder.binding.death.text = participant.deaths.toString()
                holder.binding.assist.text = participant.assists.toString()

                holder.binding.killParticipate.text = removeDecimal(participant.challenges.killParticipation * 100).toString() + "%"

            }

        }


    }

    override fun getItemCount() = matchInfoList.size

    private fun removeDecimal(value: Double) = value.toInt()

}