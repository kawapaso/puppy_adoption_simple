/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.ui.page.detail

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.androiddevchallenge.domain.Puppy
import com.example.androiddevchallenge.ui.router.localRouter
import com.example.androiddevchallenge.ui.theme.MyTheme

@Composable
fun PuppyDetailPage(
    viewModel: PuppyDetailViewModel
) {
    val uiState by viewModel.uiState.collectAsState()
    val localRouter = localRouter.current
    Crossfade(targetState = "") {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = "Puppy Detail") },
                    navigationIcon = {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = null,
                            modifier = Modifier
                                .clickable { localRouter.back() }
                                .padding(start = 8.dp)
                        )
                    }
                )
            },
        ) {
            PuppyView(puppy = uiState.puppy)
        }
    }
}

@Composable
fun PuppyView(puppy: Puppy) {
    Column {
        Image(
            painter = painterResource(id = puppy.imageResId),
            contentDescription = null,
            modifier = Modifier.aspectRatio(1f, matchHeightConstraintsFirst = false)
        )
        Text(
            text = puppy.name,
            fontSize = 18.sp,
            modifier = Modifier.padding(16.dp)
        )
        Text(
            text = puppy.breed,
            fontSize = 16.sp,
            modifier = Modifier.padding(16.dp)
        )
        Text(
            text = puppy.description,
            fontSize = 12.sp,
            fontStyle = FontStyle.Italic,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyTheme {
        PuppyDetailPage(viewModel())
    }
}
