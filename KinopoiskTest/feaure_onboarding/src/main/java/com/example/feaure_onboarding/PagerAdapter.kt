package com.example.feaure_onboarding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.feaure_onboarding.databinding.ItemOnboardingBinding

class PagerAdapter(
    private val items:List<ListOnBoarding>
): RecyclerView.Adapter<OnBoardingViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= OnBoardingViewHolder(
        ItemOnboardingBinding.inflate(LayoutInflater.from(parent.context),parent,false)
    )

    override fun onBindViewHolder(holder: OnBoardingViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() =items.size
}