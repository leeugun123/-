package com.example.riotapi.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.riotapi.Adapter.ChampSkillAdapter
import com.example.riotapi.R
import com.example.riotapi.ViewModel.NickNameViewModel
import com.example.riotapi.databinding.FragmentChampExBinding

class ChampExFragment : Fragment() {

    private lateinit var mBinding : FragmentChampExBinding
    private lateinit var nickNameViewModel: NickNameViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        mBinding = FragmentChampExBinding.inflate(inflater,container,false)

        mBinding.champSkillRecycler.layoutManager = LinearLayoutManager(requireContext())

        nickNameViewModel = ViewModelProvider(requireActivity())[NickNameViewModel::class.java]

        nickNameViewModel.champSkillInfoList.observe(requireActivity()) { skillInfo ->
            mBinding.champSkillRecycler.adapter = ChampSkillAdapter(skillInfo)
        }

        return mBinding.root


    }



}