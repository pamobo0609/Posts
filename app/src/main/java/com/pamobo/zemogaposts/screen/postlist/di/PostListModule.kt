package com.pamobo.zemogaposts.screen.postlist.di

import com.pamobo.zemogaposts.application.rest.PostRestClient
import com.pamobo.zemogaposts.model.data.FavoritePostDataSource
import com.pamobo.zemogaposts.model.data.FavoritePostRepository
import com.pamobo.zemogaposts.model.data.PostDataSource
import com.pamobo.zemogaposts.model.data.PostRepository
import com.pamobo.zemogaposts.model.framework.FavoritePostDataSourceImpl
import com.pamobo.zemogaposts.model.framework.PostDataSourceImpl
import com.pamobo.zemogaposts.model.interactor.ClearFavorites
import com.pamobo.zemogaposts.model.interactor.GetFavorites
import com.pamobo.zemogaposts.model.interactor.GetPosts
import com.pamobo.zemogaposts.model.room.PostDAO
import com.pamobo.zemogaposts.screen.postlist.PostListViewModelFactory
import dagger.Module
import dagger.Provides

/**
 * Zemoga Posts
 *
 * Created by Jose Monge on 5/5/22.
 *
 */
@Module
class PostListModule {

    @Provides
    fun providePostDataSource(client: PostRestClient): PostDataSource = PostDataSourceImpl(client)

    @Provides
    fun provideFavoritePostDataSource(dao: PostDAO): FavoritePostDataSource =
        FavoritePostDataSourceImpl(dao)

    @Provides
    fun providePostRepository(dataSource: PostDataSource): PostRepository =
        PostRepository(dataSource)

    @Provides
    fun provideFavoritePostRepository(dataSource: FavoritePostDataSource): FavoritePostRepository =
        FavoritePostRepository(dataSource)

    @Provides
    fun provideGetPostsInteractor(repository: PostRepository): GetPosts = GetPosts(repository)

    @Provides
    fun provideGetFavoritesInteractor(favoritePostRepository: FavoritePostRepository): GetFavorites =
        GetFavorites(favoritePostRepository)

    @Provides
    fun provideClearFavoritesInteractor(favoritePostRepository: FavoritePostRepository): ClearFavorites =
        ClearFavorites(favoritePostRepository)

    @Provides
    fun provideViewModelFactory(
        getPostUseCase: GetPosts, getFavorites: GetFavorites,
        clearFavorites: ClearFavorites
    ) = PostListViewModelFactory(
        getPostUseCase,
        getFavorites,
        clearFavorites
    )

}
