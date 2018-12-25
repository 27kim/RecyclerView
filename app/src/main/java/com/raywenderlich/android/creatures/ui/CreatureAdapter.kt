package com.raywenderlich.android.creatures.ui

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import com.raywenderlich.android.creatures.R
import com.raywenderlich.android.creatures.app.inflate
import com.raywenderlich.android.creatures.model.CompositeItem
import com.raywenderlich.android.creatures.model.Creature
import kotlinx.android.synthetic.main.list_item_creature.view.*
import kotlinx.android.synthetic.main.list_item_planet_header.view.*

class CreatureAdapter(private val compositeItems: MutableList<CompositeItem>) : RecyclerView.Adapter<CreatureAdapter.ViewHolder>() {
//class CreatureAdapter(private val creatures: MutableList<CompositeItem>) : RecyclerView.Adapter<CreatureAdapter.ViewHolder>() {

    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            val context = view.context
            val intent = CreatureActivity.newIntent(context, creature.id)
            context.startActivity(intent)
        }

        private lateinit var creature : Creature

       /* fun bind(creature : Creature){
            this.creature = creature

            val context = itemView.context
            itemView.creatureImage.setImageResource(context.resources.getIdentifier(creature.uri, null, context.packageName))

            itemView.fullName2.text =  creature.fullName
            itemView.nickName.text = creature.nickname

            //bind시에 animateView 호출함
            animateView(itemView)
        }*/

        fun bind(compositeItem: CompositeItem) {
            if (compositeItem.isHeader) {
                itemView.headerName.text = compositeItem.header.name
            } else {
                creature = compositeItem.creature
                val context = itemView.context
                itemView.creatureImage.setImageResource(context.resources.getIdentifier(creature.thumbnail, null, context.packageName))
                itemView.fullName2.text = creature.fullName
                itemView.nickName.text = creature.nickname
                animateView(itemView)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CreatureAdapter.ViewHolder {
        //return ViewHolder(parent.inflate(R.layout.list_item_creature))
        return when(viewType) {
            ViewType.HEADER.ordinal -> ViewHolder(parent.inflate(R.layout.list_item_planet_header))
            ViewType.CREATURE.ordinal -> ViewHolder(parent.inflate(R.layout.list_item_creature))
            else -> throw IllegalArgumentException("Illegal value for viewType")
        }
    }

    override fun getItemCount(): Int = compositeItems.size

    override fun getItemViewType(position: Int): Int {
        return if (compositeItems[position].isHeader) {
            ViewType.HEADER.ordinal
        } else {
            ViewType.CREATURE.ordinal
        }
    }

    override fun onBindViewHolder(holder: CreatureAdapter.ViewHolder, position: Int) {
        holder.bind(compositeItems[position])
    }

    private fun animateView(viewToAnimate: View) {
        if (viewToAnimate.animation == null) {
            val animation = AnimationUtils.loadAnimation(viewToAnimate.context, R.anim.scale_xy)
            viewToAnimate.animation = animation
        }
    }

    fun updateCreatures(creatures: List<CompositeItem>){
//    fun updateCreatures(creatures: List<Creature>){
        this.compositeItems.clear()
        this.compositeItems.addAll(creatures)
        notifyDataSetChanged()
    }

    enum class ViewType {
        HEADER, CREATURE
    }


}