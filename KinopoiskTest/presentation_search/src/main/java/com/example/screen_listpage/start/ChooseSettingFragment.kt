package com.example.screen_listpage.start

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.core.tools.BaseFragment
import com.example.screen_listpage.R
import com.example.screen_listpage.databinding.FragmentChooseSettingBinding

class ChooseSettingFragment : BaseFragment<FragmentChooseSettingBinding>() {
    override fun initBinding(inflater: LayoutInflater) = FragmentChooseSettingBinding.inflate(inflater)
}