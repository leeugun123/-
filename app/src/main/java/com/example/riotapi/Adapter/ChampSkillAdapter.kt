package com.example.riotapi.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.riotapi.Data.JsonData.ChampHashMap.champHashInfo
import com.example.riotapi.Data.RetrofitData.ChampSkillInfo
import com.example.riotapi.databinding.ChampSkillInfoBinding

class ChampSkillAdapter(private val champSkillList : List<ChampSkillInfo>) : RecyclerView.Adapter<ChampSkillAdapter.ViewHolder>() {

    inner class ViewHolder(val binding : ChampSkillInfoBinding) : RecyclerView.ViewHolder(binding.root),
        View.OnClickListener{

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChampSkillAdapter.ViewHolder {
        val binding = ChampSkillInfoBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder : ChampSkillAdapter.ViewHolder, position: Int) {

        val skill_List = champSkillList[position]
        val champUri = "https://ddragon.leagueoflegends.com/cdn/13.23.1/img/champion/" + champHashInfo[skill_List.championId.toString()]  + ".png"

        holder.binding.championId.text = champHashInfo[skill_List.championId.toString()]

        Glide.with(holder.itemView.context)
            .load(champUri)
            .fitCenter()
            .override(50,50)
            .into(holder.binding.champImg)



        holder.binding.championLevel.text = skill_List.championLevel.toString()
        holder.binding.championPoint.text  = skill_List.championPoints.toString()


    }


    override fun getItemCount() = champSkillList.size


}