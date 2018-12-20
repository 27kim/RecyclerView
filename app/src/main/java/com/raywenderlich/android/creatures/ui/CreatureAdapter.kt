package com.raywenderlich.android.creatures.ui

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.raywenderlich.android.creatures.R
import com.raywenderlich.android.creatures.app.inflate
import com.raywenderlich.android.creatures.model.Creature
import kotlinx.android.synthetic.main.list_item_creature.view.*

class CreatureAdapter(private  val creatures : MutableList<Creature>) : RecyclerView.Adapter<CreatureAdapter.ViewHolder>(){

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            val context = view.context
            val intent = CreatureActivity.newIntent(context, creature.id)
            context.startActivity(intent)
        }

        private lateinit var creature : Creature

        fun bind(creature : Creature){
            this.creature = creature

            val context = itemView.context
            itemView.creatureImage.setImageResource(context.resources.getIdentifier(creature.uri, null, context.packageName))

            itemView.fullName2.text =  creature.fullName
            itemView.nickName.text = creature.nickname
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CreatureAdapter.ViewHolder {
        return ViewHolder(parent.inflate(R.layout.list_item_creature))
    }

    override fun getItemCount(): Int = creatures.size


    override fun onBindViewHolder(holder: CreatureAdapter.ViewHolder, position: Int) {
        holder.bind(creatures[position])
        println("creatures[position] ? ${creatures[position].fullName}")
    }

    fun updateCreatures(creatures: List<Creature>){
        this.creatures.clear()
        this.creatures.addAll(creatures)
        notifyDataSetChanged()
    }
}