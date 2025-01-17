package com.hefengbao.jingmo.ui.screen.idiom

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ReadMore
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Bookmarks
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.hefengbao.jingmo.data.database.entity.IdiomCollectionEntity
import com.hefengbao.jingmo.data.database.entity.IdiomEntity
import com.hefengbao.jingmo.ui.component.SimpleScaffold
import com.hefengbao.jingmo.ui.screen.idiom.components.IdiomShowPanel

@Composable
fun IdiomIndexRoute(
    viewModel: IdiomIndexViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onReadMoreClick: () -> Unit,
    onBookmarksClick: () -> Unit,
    onSearchClick: () -> Unit,
) {
    val idiom by viewModel.idiom.collectAsState(initial = null)
    val idiomCollectionEntity by viewModel.idiomCollectionEntity.collectAsState(initial = null)

    IdiomIndexScreen(
        idiom = idiom,
        idiomCollectionEntity = idiomCollectionEntity,
        onBackClick = onBackClick,
        onReadMoreClick = onReadMoreClick,
        onBookmarksClick = onBookmarksClick,
        onSearchClick = onSearchClick,
        onFabClick = { viewModel.getRandomIdiom() },
        setCollect = { viewModel.collect(it) },
        setUncollect = { viewModel.uncollect(it) },
        getIdiomCollectionEntity = {
            viewModel.getIdiomCollectionEntity(it)
        }
    )
}

@Composable
private fun IdiomIndexScreen(
    idiom: IdiomEntity?,
    idiomCollectionEntity: IdiomCollectionEntity?,
    onBackClick: () -> Unit,
    onReadMoreClick: () -> Unit,
    onBookmarksClick: () -> Unit,
    onSearchClick: () -> Unit,
    onFabClick: () -> Unit,
    setCollect: (Int) -> Unit,
    setUncollect: (Int) -> Unit,
    getIdiomCollectionEntity: (Int) -> Unit,
) {
    idiom?.let { entity ->
        LaunchedEffect(entity) {
            getIdiomCollectionEntity(entity.id)
        }
        SimpleScaffold(
            onBackClick = onBackClick,
            title = "成语",
            actions = {
                IconButton(onClick = onBookmarksClick) {
                    Icon(imageVector = Icons.Outlined.Bookmarks, contentDescription = "收藏")
                }
                IconButton(onClick = onReadMoreClick) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ReadMore,
                        contentDescription = "阅读"
                    )
                }
                IconButton(onClick = onSearchClick) {
                    Icon(imageVector = Icons.Default.Search, contentDescription = "查找")
                }
            },
            bottomBar = {
                BottomAppBar(
                    floatingActionButton = {
                        FloatingActionButton(onClick = onFabClick) {
                            Icon(imageVector = Icons.Default.Refresh, contentDescription = "刷新")
                        }
                    },
                    actions = {
                        IconButton(
                            onClick = {
                                if (idiomCollectionEntity != null) {
                                    setUncollect(entity.id)
                                } else {
                                    setCollect(entity.id)
                                }
                            }
                        ) {
                            if (idiomCollectionEntity != null) {
                                Icon(
                                    imageVector = Icons.Default.Bookmark,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.primary
                                )
                            } else {
                                Icon(
                                    imageVector = Icons.Default.BookmarkBorder,
                                    contentDescription = null
                                )
                            }
                        }
                    }
                )
            },

            ) {
            IdiomShowPanel(idiom = entity)
        }
    }
}