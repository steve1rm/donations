package me.androidbox.tamboon.screens

import android.view.View
import com.agoda.kakao.common.views.KView
import com.agoda.kakao.image.KImageView
import com.agoda.kakao.recycler.KRecyclerItem
import com.agoda.kakao.recycler.KRecyclerView
import com.agoda.kakao.screen.Screen
import com.agoda.kakao.text.KTextView
import me.androidbox.tamboon.R
import org.hamcrest.Matcher

class CharitiesScreen : Screen<CharitiesScreen>() {

    val container: KView = KView { withId(R.id.fragment_charities) }

    class Item(parent: Matcher<View>) : KRecyclerItem<Item>(parent) {
        val id: KTextView = KTextView(parent) { withId(R.id.tvId) }
        val name: KTextView = KTextView(parent) { withId(R.id.tvName) }
        val logo: KImageView = KImageView(parent) { withId(R.id.ivLogo) }
    }

    val charities: KRecyclerView =
        KRecyclerView({
            withId(R.id.rvCharities) }, { itemType(::Item) })
}
