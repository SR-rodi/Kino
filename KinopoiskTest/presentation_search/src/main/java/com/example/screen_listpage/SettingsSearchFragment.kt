package com.example.screen_listpage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.core.tools.BaseFragment
import com.example.screen_listpage.data.Order
import com.example.screen_listpage.data.SetSearch
import com.example.screen_listpage.data.Type
import com.example.screen_listpage.databinding.FragmentSettingsSearchBinding
import com.example.screen_listpage.databinding.ItemSelectBinding

class SettingsSearchFragment : BaseFragment<FragmentSettingsSearchBinding>() {
    override fun initBinding(inflater: LayoutInflater) =
        FragmentSettingsSearchBinding.inflate(inflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setStartParam()

        setListenerSlider()

        onClickSettings(binding.country)
        onClickSettings(binding.genres)
    }

    private fun onClickSettings(item: ItemSelectBinding) {
        item.container.setOnClickListener{
            findNavController().navigate(com.example.core.R.id.action_settingsSearchFragment_to_chooseSettingFragment)
        }
    }

    private fun setListenerSlider() {
        binding.rangeSlider.addOnChangeListener{rangeSlider, _, _ ->
            binding.rangeFrom.text = rangeSlider.values[0].toString()
            binding.rangeTo.text = rangeSlider.values[1].toString()
            SetSearch.ratingFrom = rangeSlider.values[0].toInt()
            SetSearch.ratingTo = rangeSlider.values[1].toInt()
            setText(binding.rating, "Рейтинг", "${SetSearch.ratingFrom} - ${SetSearch.ratingTo}")
        }
    }

    private fun setStartParam(){
        when (SetSearch.order) {
            Order.YEAR -> binding.dateBestRatting.oneButton.isChecked = true
            Order.NUM_VOTE -> binding.dateBestRatting.twoButton.isChecked = true
            Order.RATING -> binding.dateBestRatting.threeButton.isChecked = true
        }
        binding.dateBestRatting.filter.setOnCheckedChangeListener { _, i ->
            when (i) {
                R.id.one_button -> SetSearch.order = Order.YEAR
                R.id.two_button -> SetSearch.order = Order.NUM_VOTE
                R.id.three_button -> SetSearch.order = Order.RATING
            }
        }

        when (SetSearch.type) {
            Type.ALL -> binding.type.oneButton.isChecked = true
            Type.FILM -> binding.type.twoButton.isChecked = true
            Type.TV_SERIES -> binding.type.threeButton.isChecked = true
        }

        binding.type.filter.setOnCheckedChangeListener { _, i ->
            when (i) {
                R.id.one_button -> SetSearch.type = Type.ALL
                R.id.two_button -> SetSearch.type = Type.FILM
                R.id.three_button -> SetSearch.type = Type.TV_SERIES
            }
        }

        binding.type.oneButton.text = Type.ALL.name
        binding.type.twoButton.text = Type.FILM.name
        binding.type.threeButton.text = Type.TV_SERIES.name

        binding.dateBestRatting.oneButton.text = Order.YEAR.name
        binding.dateBestRatting.twoButton.text = Order.NUM_VOTE.name
        binding.dateBestRatting.threeButton.text = Order.RATING.name

        setText(binding.rating, "Рейтинг", "${SetSearch.ratingFrom} - ${SetSearch.ratingTo}")
        setText(binding.country, "Страна", "${SetSearch.counterID}")
        setText(binding.genres, "Жанр", SetSearch.order.name)
        setText(binding.years, "Год", "${SetSearch.yearFrom} - ${SetSearch.yearTo}")

    }

    private fun setText(textView: ItemSelectBinding, title: String, subtitle: String) {
        textView.title.text = title
        textView.subtitle.text = subtitle
    }
}