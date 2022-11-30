package com.example.homepage.presentation.homepage.domaine

class NetworkCategoryImpl(
    private val filmsApi: CategoryFilmsApi
): NetworkCategoryRepository
{
    override suspend fun getPremieresInNetwork(year: Int, month: String) =
        filmsApi.getPremieres(year, month).items

    override suspend fun getPopularInNetwork(page: Int) =
        filmsApi.getPopular(page).films

    override suspend fun getBestFilmsInNetwork(page: Int) = filmsApi.getBest(page).films

    override suspend fun getListGenreAndCounter() = filmsApi.getListGenreAndCountry()

    override suspend fun getFilmsGenreAndCounter(page: Int, counterID: Int, genreId: Int) =
        filmsApi.getFilmsByGenreAndCountry(page, counterID, genreId).items
}