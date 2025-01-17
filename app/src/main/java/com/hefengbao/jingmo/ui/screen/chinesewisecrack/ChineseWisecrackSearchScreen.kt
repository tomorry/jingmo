package com.hefengbao.jingmo.ui.screen.chinesewisecrack

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hefengbao.jingmo.data.database.entity.ChineseWisecrackEntity
import com.hefengbao.jingmo.ui.component.SimpleSearchScaffold

@Composable
fun ChineseWisecrackSearchRoute(
    viewModel: ChineseWisecrackSearchViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onItemClick: (id: Int, query: String) -> Unit
) {
    val list by viewModel.searchWisecrackList.collectAsState(initial = emptyList())

    ChineseWisecrackSearchScreen(
        onBackClick = onBackClick,
        list = list,
        onSearch = { viewModel.search(it) },
        onItemClick = onItemClick
    )
}

@Composable
private fun ChineseWisecrackSearchScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    list: List<ChineseWisecrackEntity>,
    onSearch: (String) -> Unit,
    onItemClick: (id: Int, query: String) -> Unit
) {
    var query by rememberSaveable {
        mutableStateOf("")
    }

    SimpleSearchScaffold(
        onBackClick = onBackClick,
        query = query,
        onQueryChange = {
            query = it
            if (query.isNotEmpty()) {
                onSearch(query)
            }
        },
        onSearch = onSearch
    ) {
        LazyColumn(
            modifier = modifier
                .fillMaxWidth(),
        ) {
            itemsIndexed(
                items = list,
            ) { index, item ->
                Text(
                    modifier = modifier
                        .clickable {
                            onItemClick(item.id, query)
                        }
                        .padding(16.dp)
                        .fillMaxWidth(),
                    text = item.riddle,
                )
                if (index != list.lastIndex) {
                    Divider(thickness = 0.5.dp)
                }
            }
        }
    }
}