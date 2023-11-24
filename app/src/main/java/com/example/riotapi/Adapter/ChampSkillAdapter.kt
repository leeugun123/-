package com.example.riotapi.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.riotapi.Data.ChampSkillInfo
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

        holder.binding.championId.text = skill_List.championId.toString()
        holder.binding.championLevel.text = skill_List.championLevel.toString()
        holder.binding.championPoint.text  = skill_List.championPoints.toString()


    }


    override fun getItemCount() = champSkillList.size


}