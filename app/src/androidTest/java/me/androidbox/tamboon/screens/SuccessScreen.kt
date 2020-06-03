package me.androidbox.tamboon.screens

import com.agoda.kakao.common.views.KView
import com.agoda.kakao.screen.Screen
import com.agoda.kakao.text.KButton
import com.agoda.kakao.text.KTextView
import me.androidbox.tamboon.R

class SuccessScreen : Screen<SuccessScreen>() {
    val container: KView = KView { withId(R.id.fragment_success) }
    val successMessage: KTextView = KTextView { withId(R.id.tvSuccessMessage) }
    val dismiss: KButton = KButton { withId(R.id.btnDismiss) }
}
