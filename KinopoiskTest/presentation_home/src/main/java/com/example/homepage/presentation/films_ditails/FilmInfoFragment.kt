package com.example.homepage.presentation.films_ditails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.core.tools.BaseFragment
import com.example.core.tools.extensions.createName
import com.example.core.tools.extensions.glide
import com.example.data.detailsFilm_page.model.InfoFilms
import com.example.data.detailsFilm_page.model.PosterFilm
import com.example.homepage.databinding.FragmentFilmInfoBinding
import com.example.homepage.presentation.films_ditails.adapters_delegates.adapters.InfoAdapter
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class FilmInfoFragment : BaseFragment<FragmentFilmInfoBinding>() {

    override fun initBinding(inflater: LayoutInflater) = FragmentFilmInfoBinding.inflate(inflater)

    private val viewModel by viewModel<FilmInfoViewModel>()

    /*private val args by navArgs<>()*/

    private val adapter by lazy { InfoAdapter{onClickItem(it)} }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getFilmForID(TEST_ID)

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.film.collect {
                setPoster(it.first)
                setAdapter(it.second)
            }
        }

    }

    private fun setPoster(item: PosterFilm) {
        binding.newPoster.apply {
            poster.glide(item.posterUrl)
            this.info.text =
                item.createInfoText(item.genres.createName(), item.countries.createName())
            binding.categoryTitle.text = item.nameOriginal
        }
    }

    private fun onClickItem(categoryInfo: CategoryInfo) {
        when(categoryInfo){
          CategoryInfo.STAFF ->
              findNavController().navigate(FilmInfoFragmentDirections.actionFilmInfoFragmentToStaffInfoFragment())
            CategoryInfo.GALLERY -> {}
            CategoryInfo.FILMS ->{}
        }

    }


    private fun setAdapter(listIfo: List<InfoFilms>) {
        binding.detailRecyclerView.adapter = adapter
        adapter.items = listIfo

    }

    companion object {
        const val TEST_ID = 301
    }
}

enum class CategoryInfo(var itemId:Int){
    STAFF(0),GALLERY(0),FILMS(0)
}