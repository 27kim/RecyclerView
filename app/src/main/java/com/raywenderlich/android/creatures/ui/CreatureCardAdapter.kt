package com.raywenderlich.android.creatures.ui

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Color
import android.support.v4.content.ContextCompat
import android.support.v7.graphics.Palette
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.raywenderlich.android.creatures.R
import com.raywenderlich.android.creatures.app.inflate
import com.raywenderlich.android.creatures.model.Creature
import kotlinx.android.synthetic.main.list_item_creature_card.view.*

class CreatureCardAdapter(private val creatures: MutableList<Creature>) : RecyclerView.Adapter<CreatureCardAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            val context = view.context
            val intent = CreatureActivity.newIntent(context, creature.id)
            context.startActivity(intent)
        }

        private lateinit var creature: Creature

        fun bind(creature: Creature) {
            this.creature = creature

            val context = itemView.context
            val imageResource = context.resources.getIdentifier(creature.uri, null, context.packageName)
            itemView.creatureImage.setImageResource(imageResource)
            itemView.nickName.text = creature.nickname
            setBackgroudColor(context, imageResource)
        }


        fun setBackgroudColor(context: Context, imageResource: Int) {
            val image = BitmapFactory.decodeResource(context.resources, imageResource)
            Palette.from(image).generate {
                val backgroundColor = it.getDominantColor(ContextCompat.getColor(context, R.color.colorPrimaryDark))
                itemView.creatureCard.setBackgroundColor(backgroundColor)
                itemView.nicknameHolder.setBackgroundColor(backgroundColor)
                val textColor = if (isColorDark(backgroundColor)) Color.WHITE else Color.BLACK
                itemView.nickName.setTextColor(textColor)
            }
        }

        fun isColorDark(color : Int) : Boolean{
            val darkness = 1-(0.299 * Color.red(color) + 0.587 * Color.green(color) + 0.114 * Color.blue(color)) /255
            return darkness >= 0.5
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CreatureCardAdapter.ViewHolder {
        return ViewHolder(parent.inflate(R.layout.list_item_creature_card))
    }

    override fun getItemCount(): Int = creatures.size


    override fun onBindViewHolder(holder: CreatureCardAdapter.ViewHolder, position: Int) {
        holder.bind(creatures[position])
        println("creatures[position] ? ${creatures[position].fullName}")
    }

}