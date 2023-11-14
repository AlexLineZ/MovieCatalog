package com.example.moviecatalog.presentation.screen.profilescreen

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.moviecatalog.R
import com.example.moviecatalog.common.Constants
import com.example.moviecatalog.presentation.navigation.bottombar.BottomBar
import com.example.moviecatalog.presentation.navigation.bottombar.Routes
import com.example.moviecatalog.presentation.router.BottomBarRouter
import com.example.moviecatalog.presentation.screen.profilescreen.components.DatePickerFieldForProfile
import com.example.moviecatalog.presentation.screen.common.GenderSelectionButton
import com.example.moviecatalog.presentation.screen.common.OutlinedTextFieldWithLabel
import com.example.moviecatalog.presentation.screen.common.PairButtons
import com.example.moviecatalog.presentation.screen.mainscreen.components.PullIndicator
import com.example.moviecatalog.presentation.ui.theme.AccentColor
import com.example.moviecatalog.presentation.ui.theme.Values.BasePadding
import com.example.moviecatalog.presentation.ui.theme.Values.BigPadding
import com.example.moviecatalog.presentation.ui.theme.Values.MoreSpaceBetweenObjects
import com.example.moviecatalog.presentation.ui.theme.Values.SpaceBetweenObjects
import eu.bambooapps.material3.pullrefresh.pullRefresh
import eu.bambooapps.material3.pullrefresh.rememberPullRefreshState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun ProfileScreen (
    viewModel: ProfileViewModel,
    router: BottomBarRouter
) {
    LaunchedEffect(Unit) {
        viewModel.performData()
    }

    Scaffold(
        bottomBar = {
            BottomBar(
                router = router,
                currentRoute = Routes.Profile.route
            )
        }
    ) {
        Box(modifier = Modifier.padding(it)) {
            ProfileItemsList(
                viewModel = viewModel,
                router = router
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileItemsList(
    viewModel: ProfileViewModel,
    router: BottomBarRouter
){
    val focusManager = LocalFocusManager.current
    val state by viewModel.state.collectAsState()

    val refreshScope = rememberCoroutineScope()

    fun refresh() = refreshScope.launch {
        viewModel.processIntent(ProfileIntent.UpdateLoading)
        viewModel.performData()
        delay(1500)
        viewModel.processIntent(ProfileIntent.UpdateLoading)
    }

    val pullState = rememberPullRefreshState(state.isLoading, ::refresh)
    val rotation = animateFloatAsState(pullState.progress * 120)

    Box(
        Modifier
            .fillMaxSize()
            .pullRefresh(pullState)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(BasePadding)
                .pointerInput(Unit) {
                    detectTapGestures(onTap = {
                        focusManager.clearFocus()
                    })
                },
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = if (state.avatarLink == null || state.avatarLink == Constants.EMPTY_STRING)
                    R.drawable.anonymus
                else state.avatarLink,
                contentDescription = null,
                modifier = Modifier
                    .size(88.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )

            Text(
                text = state.nickName ?: Constants.EMPTY_STRING,
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.W700
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 6.dp)
            )

            Text(
                text = stringResource(id = R.string.leave),
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                ),
                color = AccentColor,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(top = 12.dp, bottom = BigPadding)
                    .clickable {
                        viewModel.processIntent(ProfileIntent.Logout { router.toAuth() })
                    }
            )

            LazyColumn {
                item{
                    OutlinedTextFieldWithLabel(
                        label = stringResource(R.string.email),
                        value = state.email,
                        onValueChange = {
                            viewModel.processIntent(ProfileIntent.UpdateEmail(it))
                        },
                        error = state.emailError?.let { stringResource(it) },
                        modifier = Modifier
                    )

                    OutlinedTextFieldWithLabel(
                        label = stringResource(R.string.avatar_link),
                        value = state.avatarLink ?: Constants.EMPTY_STRING,
                        onValueChange = {viewModel.processIntent(ProfileIntent.UpdateAvatarLink(it))},
                        modifier = Modifier.padding(top = SpaceBetweenObjects)
                    )

                    OutlinedTextFieldWithLabel(
                        label = stringResource(R.string.name),
                        value = state.name,
                        onValueChange = { viewModel.processIntent(ProfileIntent.UpdateName(it)) },
                        error = state.nameError?.let { stringResource(it) },
                        modifier = Modifier.padding(top = SpaceBetweenObjects)
                    )

                    GenderSelectionButton(
                        updateGender = { viewModel.processIntent(ProfileIntent.UpdateGender(it)) },
                        state = state.gender,
                        modifier = Modifier.padding(top = SpaceBetweenObjects)
                    )

                    DatePickerFieldForProfile(
                        viewModel = viewModel,
                        state = state,
                        modifier = Modifier.padding(top = SpaceBetweenObjects)
                    )

                    PairButtons(
                        firstLabel = stringResource(R.string.save),
                        firstClick = { viewModel.processIntent(ProfileIntent.SaveData) },
                        secondLabel = stringResource(R.string.cancel),
                        secondClick = { viewModel.processIntent(ProfileIntent.Cancel) },
                        modifier = Modifier.padding(
                            top = MoreSpaceBetweenObjects,
                            bottom = BasePadding
                        ),
                        firstEnabled = viewModel.isSaveButtonAvailable()
                    )
                }
            }
        }
        PullIndicator(
            pullState = pullState,
            rotation = rotation.value,
            refreshing = state.isLoading,
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }
}