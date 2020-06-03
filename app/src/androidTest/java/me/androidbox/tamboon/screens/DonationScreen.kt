package me.androidbox.tamboon.screens

import com.agoda.kakao.common.views.KView
import com.agoda.kakao.edit.KEditText
import com.agoda.kakao.screen.Screen
import com.agoda.kakao.text.KButton
import com.agoda.kakao.text.KTextView
import me.androidbox.tamboon.R

class DonationScreen : Screen<DonationScreen>() {
    val container: KView = KView { withId(R.id.fragment_donation) }
    val charityName: KTextView = KTextView { withId(R.id.tvCharityName) }
    val amount: KEditText = KEditText { withId(R.id.etAmount) }
    val submitDonation: KButton = KButton { withId(R.id.btnSubmitDonation) }
}
