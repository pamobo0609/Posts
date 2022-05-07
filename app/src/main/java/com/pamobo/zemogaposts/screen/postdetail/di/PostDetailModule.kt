package com.pamobo.zemogaposts.screen.postdetail.di

import com.pamobo.zemogaposts.application.rest.CommentRestClient
import com.pamobo.zemogaposts.application.rest.UserRestClient
import com.pamobo.zemogaposts.model.data.*
import com.pamobo.zemogaposts.model.framework.CommentDataSourceImpl
import com.pamobo.zemogaposts.model.framework.FavoritePostDataSourceImpl
import com.pamobo.zemogaposts.model.framework.UserDataSourceImpl
import com.pamobo.zemogaposts.model.interactor.AddToFavorites
import com.pamobo.zemogaposts.model.interactor.GetComments
import com.pamobo.zemogaposts.model.interactor.GetUser
import com.pamobo.zemogaposts.model.interactor.RemoveFromFavorites
import com.pamobo.zemogaposts.model.room.PostDAO
import com.pamobo.zemogaposts.screen.postdetail.PostDetailViewModelFactory
import dagger.Module
import dagger.Provides

/**
 * Zemoga Posts
 *
 * Created by Jose Monge on 6/5/22.
 *
 */
@Module
class PostDetailModule {

    @Provides
    fun provideUserDataSource(client: UserRestClient): UserDataSource = UserDataSourceImpl(client)

    @Provides
    fun provideCommentDataSource(client: CommentRestClient): CommentDataSource =
        CommentDataSourceImpl(client)

    @Provides
    fun provideFavoritePostDataSource(dao: PostDAO): FavoritePostDataSource =
        FavoritePostDataSourceImpl(dao)

    @Provides
    fun provideFavoritePostRepository(dataSource: FavoritePostDataSource): FavoritePostRepository =
        FavoritePostRepository(dataSource)

    @Provides
    fun provideUserRepository(dataSource: UserDataSource): UserRepository =
        UserRepository(dataSource)

    @Provides
    fun provideCommentRepository(dataSource: CommentDataSource): CommentRepository =
        CommentRepository(dataSource)

    @Provides
    fun provideGetUserInteractor(repository: UserRepository): GetUser = GetUser(repository)

    @Provides
    fun provideGetCommentsInteractor(repository: CommentRepository): GetComments =
        GetComments(repository)

    @Provides
    fun provideAddToFavoritesInteractor(favoritePostRepository: FavoritePostRepository): AddToFavorites =
        AddToFavorites(favoritePostRepository)

    @Provides
    fun provideRemoveFromFavoritesInteractor(favoritePostRepository: FavoritePostRepository): RemoveFromFavorites =
        RemoveFromFavorites(favoritePostRepository)

    @Provides
    fun provideViewModelFactory(
        getUser: GetUser,
        getComments: GetComments,
        addToFavorites: AddToFavorites,
        removeFromFavorites: RemoveFromFavorites
    ): PostDetailViewModelFactory =
        PostDetailViewModelFactory(getUser, getComments, addToFavorites, removeFromFavorites)

}
