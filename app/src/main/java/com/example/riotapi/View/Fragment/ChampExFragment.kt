package com.example.riotapi.View.Fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.riotapi.Adapter.ChampSkillAdapter
import com.example.riotapi.R
import com.example.riotapi.ViewModel.ChampSkillViewModel
import com.example.riotapi.ViewModel.NickNameViewModel
import com.example.riotapi.databinding.FragmentChampExBinding

class ChampExFragment : Fragment() {

    private lateinit var mBinding : FragmentChampExBinding
    private val champSkillViewModel by lazy {ViewModelProvider(requireActivity())[ChampSkillViewModel::class.java]}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        mBinding = FragmentChampExBinding.inflate(inflater,container,false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding.champSkillRecycler.layoutManager = LinearLayoutManager(requireContext())

        champSkillViewModel.fetchSkillInfo()

        champSkillViewModel.champSkillInfoList.observe(requireActivity()) { skillInfo ->
            mBinding.champSkillRecycler.adapter = ChampSkillAdapter(skillInfo)
        }



    }



}