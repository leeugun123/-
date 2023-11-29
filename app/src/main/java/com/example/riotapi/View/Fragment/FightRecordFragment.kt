package com.example.riotapi.View.Fragment

import android.os.Bundle
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
    private lateinit var matchViewModel : MatchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        mBinding = FragmentFightRecordBinding.inflate(inflater,container,false)

        mBinding.matchRecordRecycler.layoutManager = LinearLayoutManager(requireContext())

        matchViewModel = ViewModelProvider(requireActivity())[MatchViewModel::class.java]

        matchViewModel.fetchMatchIds()

        matchViewModel.summonerMatchInfoList.observe(requireActivity()){ matchListInfo ->
            mBinding.matchRecordRecycler.adapter = MatchInfoAdapter(matchListInfo)
        }

        return mBinding.root

    }




}