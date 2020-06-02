package me.androidbox.tamboon.presentation.adapter

import android.os.Build
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import me.androidbox.tamboon.presentation.utils.DataFactory.createCharities
import me.androidbox.tamboon.presentation.viewholders.CharitiesViewHolder
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@Config(sdk = [Build.VERSION_CODES.O_MR1])
@RunWith(AndroidJUnit4::class)
class CharitiesAdapterTest {

    private lateinit var charitiesAdapter: CharitiesAdapter

    @Before
    fun setUp() {
        charitiesAdapter = CharitiesAdapter()
    }

    @Test
    fun `should create viewHolder`() {
        // Act & Assert
        assertThat(createViewHolder()).isNotNull
    }

    @Test
    fun `should bind viewHolder`() {
        // Arrange
        val viewHolder = createViewHolder()
        val charities = createCharities(10)

        charitiesAdapter.populate(charities.charityList)

        // Act
        charitiesAdapter.onBindViewHolder(viewHolder, 0)

        // Assert
        assertThat(viewHolder.id.text.toString()).isEqualTo(charities.charityList[0].id.toString())
        assertThat(viewHolder.name.text.toString()).isEqualTo(charities.charityList[0].name)
    }

    @Test
    fun `should not bind viewHolder if the list is empty`() {
        // Arrange
        val viewHolder = createViewHolder()

        // Act
        charitiesAdapter.onBindViewHolder(viewHolder, 0)

        // Assert
        assertThat(viewHolder.id.text.toString()).isEmpty()
        assertThat(viewHolder.name.text.toString()).isEmpty()
    }

    @Test
    fun `should return the correct number in the list`() {
        // Arrange
        val charities = createCharities(10)

        charitiesAdapter.populate(charities.charityList)

        // Act & Assert
        assertThat(charitiesAdapter.itemCount).isEqualTo(10)
    }

    private fun createViewHolder(): CharitiesViewHolder {
        val constraintLayout = ConstraintLayout(ApplicationProvider.getApplicationContext())

        return charitiesAdapter.onCreateViewHolder(constraintLayout, 0)
    }
}