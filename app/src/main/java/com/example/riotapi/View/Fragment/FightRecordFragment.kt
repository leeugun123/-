package com.example.riotapi.View.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.riotapi.R
import com.example.riotapi.ViewModel.MatchViewModel
import com.example.riotapi.databinding.FragmentFightRecordBinding


class FightRecordFragment : Fragment() {

    private lateinit var mBinding : FragmentFightRecordBinding
    private lateinit var matchViewModel : MatchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        mBinding = FragmentFightRecordBinding.inflate(inflater,container,false)

        //mBinding.recyclerViewId.layoutManager = = LinearLayoutManager(requireContext())

        matchViewModel = ViewModelProvider(requireActivity())[MatchViewModel::class.java]

        matchViewModel.summonerMatchInfoList.observe(requireActivity()){ matchListInfo ->
            // mBinding.recyclerViewId.adapter = --Adapter(matchListInfo)
        }

        return mBinding.root

    }




}