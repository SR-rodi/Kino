package com.example.feature_details.presentation.films_ditails


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.core.R
import com.example.core.tools.BaseFragment
import com.example.core.tools.all.CategoryInfo
import com.example.core.tools.extensions.*
import com.example.feature_details.data.ButtonPoster
import com.example.feature_details.data.detailsFilm_page.model.PosterFilm
import com.example.feature_details.databinding.FragmentFilmInfoBinding
import com.example.feature_details.presentation.films_ditails.adapters_delegates.adapters.InfoAdapter
import com.example.feature_details.tools.createGalleryDialog
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class FilmInfoFragment : BaseFragment<FragmentFilmInfoBinding>() {

    override fun initBinding(inflater: LayoutInflater) = FragmentFilmInfoBinding.inflate(inflater)

    private val viewModel by viewModel<FilmInfoViewModel>()

    private val adapter by lazy { InfoAdapter({ onClickItem(it) }, { onClickAll(it) }) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.createID(getArgsInt())

        viewModel.getFilmForID()

      //  viewModel.getStatusFilm()

        binding.backArrow.popBackStack()

        binding.detailRecyclerView.adapter = adapter

        observeLoadState(viewModel.loadState, binding.newPoster.loading) {}

        observeFilm()
        onClickStatusButton(binding.newPoster.like, ButtonPoster.LIKE)
        onClickStatusButton(binding.newPoster.favorite, ButtonPoster.FAVORITE)
        onClickStatusButton(binding.newPoster.look, ButtonPoster.LOOK)
        observeFilmStatus()
        Log.d(
            "Kart", "like ${ binding.newPoster.like.isSelected}" +
                    " favorite ${binding.newPoster.favorite.isSelected}" +
                    " look ${binding.newPoster.look.isSelected}"
        )


    }

    private fun observeFilmStatus() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.filmStatus.collect {
                binding.newPoster.like.isSelected = it.isLike
                binding.newPoster.favorite.isSelected = it.isFavorite
                binding.newPoster.look.isSelected = it.isLook

            }
        }
    }

    private fun observeFilm() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.film.collect {
                setPoster(it.first)
                adapter.items = it.second

            }
        }
    }

    private fun onClickStatusButton(view: ImageView, statusButton: ButtonPoster) {
        view.setOnClickListener { button ->
            button.isSelected = !button.isSelected
            viewModel.workDatabase(statusButton)
        }
    }

    private fun setPoster(item: PosterFilm) {
        binding.newPoster.apply {
            poster.glide(item.posterUrl)
            info.text =
                item.createInfoText(item.genres.createName(), item.countries.createName())

            binding.categoryTitle.text = item.nameOriginal


            binding.newPoster.menu.setOnClickListener {
                viewModel.createHandle()
                findNavController().navigate(R.id.action_filmInfoFragment_to_bottomSheetMenuFragment)
            }

            share.clickAndShowShareDialog(item.posterUrl)

        }
    }

    private fun onClickItem(categoryInfo: CategoryInfo) {
        when (categoryInfo) {
            CategoryInfo.STAFF ->
                findNavController().navigate(
                    R.id.action_filmInfoFragment_to_staffInfoFragment,
                    categoryInfo.createBundle()
                )
            CategoryInfo.GALLERY -> createGalleryDialog(categoryInfo)
            CategoryInfo.FILMS -> {}
        }

    }

    private fun onClickAll(categoryInfo: CategoryInfo) {
        when (categoryInfo) {
            CategoryInfo.STAFF -> {}
            CategoryInfo.GALLERY ->
                findNavController().navigate(R.id.action_filmInfoFragment_to_galleryFragment)
            CategoryInfo.FILMS -> {}
        }

    }
}

