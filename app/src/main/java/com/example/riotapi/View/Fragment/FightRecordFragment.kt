package com.example.riotapi.View.Fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.riotapi.Adapter.MatchInfoAdapter
import com.example.riotapi.R
import com.example.riotapi.ViewModel.MatchViewModel
import com.example.riotapi.databinding.FragmentFightRecordBinding


class FightRecordFragment : Fragment() {

    private lateinit var mBinding : FragmentFightRecordBinding
    private val matchViewModel by lazy { ViewModelProvider(requireActivity())[MatchViewModel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        mBinding = FragmentFightRecordBinding.inflate(inflater,container,false)
        return mBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding.matchRecordRecycler.layoutManager = LinearLayoutManager(requireContext())

        matchViewModel.fetchMatchIds()

        matchViewModel.summonerMatchDtoList.observe(requireActivity()){ matchInfoDtoList ->
            mBinding.matchRecordRecycler.adapter = MatchInfoAdapter(matchInfoDtoList)
        }


    }




}