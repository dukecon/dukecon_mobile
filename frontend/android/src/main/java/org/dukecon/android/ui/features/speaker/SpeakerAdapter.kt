package org.dukecon.android.ui.features.speaker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import kotlinx.android.synthetic.main.item_speaker.view.*
import org.dukecon.android.ui.R
import org.dukecon.presentation.model.SpeakerView

internal class SpeakerAdapter(private val wrapsWidth: Boolean = true, private val onSpeakerClickedListener: ((speaker: SpeakerView) -> Unit)) :
        RecyclerView.Adapter<SpeakerAdapter.ViewHolder>() {

    val speakers: MutableList<SpeakerView> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_speaker, parent, false)
        if (!wrapsWidth) {
            v.layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
        }
        return ViewHolder(v, onSpeakerClickedListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(speakers[position])
    }

    override fun getItemCount(): Int {
        return speakers.size
    }

    internal class ViewHolder(itemView: View, private val onSpeakerClickedListener: ((speaker: SpeakerView) -> Unit)) :
            RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private var speaker: SpeakerView? = null
        val image: ImageView
        val name: TextView
        val title: TextView

        init {
            image = itemView.image
            name = itemView.name
            title = itemView.title
            itemView.setOnClickListener(this)
        }

        fun bind(speaker: SpeakerView) {
            this.speaker = speaker
            name.text = speaker.name
            title.text = speaker.title

            if (speaker.avatar.isNotBlank()) {
                image.apply {
                    load(speaker.avatar) {
                        placeholder(R.drawable.ph_speaker)
                    }
                }
            } else {
                image.setImageResource(R.drawable.ph_speaker)
            }
        }

        override fun onClick(v: View?) {
            val sp = speaker
            if (sp != null) {
                onSpeakerClickedListener(sp)
            }
        }
    }
}