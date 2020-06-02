package me.androidbox.tamboon.screens

import com.agoda.kakao.common.views.KView
import com.agoda.kakao.screen.Screen
import com.agoda.kakao.text.KButton
import com.agoda.kakao.text.KTextView
import me.androidbox.tamboon.R

class HomeMenuScreen : Screen<HomeMenuScreen>() {
    val homeMenuContainer: KView = KView { withId(R.id.fragment_homemenu) }

    val titleCharities: KTextView = KTextView { withId(R.id.tvTitleCharities) }

    val fetchCharities: KButton = KButton { withId(R.id.btnFetchCharities) }
}
