package com.alorma.rvmultigridsample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.OrientationHelper
import android.support.v7.widget.PagerSnapHelper
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.amulyakhare.textdrawable.TextDrawable
import com.amulyakhare.textdrawable.util.ColorGenerator
import java.util.*

class MainActivity : AppCompatActivity() {

    private val mapSnaps: MutableSet<String> = HashSet()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        recyclerView?.let { setupRecycler(it) }
    }

    private fun setupRecycler(recyclerView: RecyclerView) {
        val layoutManager = LinearLayoutManager(this, OrientationHelper.VERTICAL, false)
        recyclerView.layoutManager = layoutManager

        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(recyclerView)

        val personas = listOf(
                Persona("Bernat"),
                Persona("Jorge"),
                Persona("David"),
                Persona("Luis"),
                Persona("Carlos"),
                Persona("Marc"),
                Persona("Sonia")
        )

        val inflater = LayoutInflater.from(this)
        recyclerView.adapter = DimensionalAdapter(personas, inflater) { recycler, itemView, persona, position ->
            configureRowRecycler(recycler, itemView, persona, inflater, position)
        }
    }

    private fun configureRowRecycler(recyclerView: RecyclerView, itemView: View, persona: Persona,
                                     inflater: LayoutInflater, position: Int) {
        buildPersonaInfo(itemView, inflater, position, persona)

        val layoutManager = LinearLayoutManager(this, OrientationHelper.HORIZONTAL, false)
        recyclerView.layoutManager = layoutManager

        val snapHelper = PagerSnapHelper()
        recyclerView.onFlingListener = null
        snapHelper.attachToRecyclerView(recyclerView)

        val max = Random().nextInt((4 - 1) + 1) + 1
        val items = (1..max).map { "Video: $it" }
        recyclerView.adapter = DummyRowAdapter(items, inflater)
    }

    private fun buildPersonaInfo(itemView: View, inflater: LayoutInflater, position: Int, persona: Persona) {
        if (itemView is ViewGroup) {
            val pageUserInfo = itemView.findViewById<View>(R.id.pageUserInfo)
            if (pageUserInfo != null) {
                itemView.removeView(pageUserInfo)
            }

            val layout = when {
                persona.name == "Bernat" -> R.layout.persona_info_user
                position.isOdd() -> R.layout.persona_info
                else -> R.layout.persona_info_other
            }

            val personaView = inflater.inflate(layout, itemView, false)
            itemView.addView(personaView)

            val pageUserName: TextView = personaView.findViewById<TextView>(R.id.pageUserName)
            val pageUserImage: ImageView = personaView.findViewById<ImageView>(R.id.pageUserAvatar)

            pageUserName.text = persona.name

            val generator = ColorGenerator.MATERIAL
            val key = persona.name[0].toString()
            val drawable = TextDrawable.builder().buildRound(key, generator.getColor(key))
            pageUserImage.setImageDrawable(drawable)
        }
    }

}

private fun Int.isOdd(): Boolean {
    return this % 2 == 0
}
