package com.raywenderlich.android.creatures.ui

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.LinearSnapHelper
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.raywenderlich.android.creatures.R
import com.raywenderlich.android.creatures.app.inflate
import com.raywenderlich.android.creatures.model.Creature
import com.raywenderlich.android.creatures.model.CreatureStore
import kotlinx.android.synthetic.main.list_item_creature_with_food.view.*

class CreatureWithFoodAdapter(private val creatures: MutableList<Creature>) : RecyclerView.Adapter<CreatureWithFoodAdapter.ViewHolder>() {

    //viewPool을 생성한다
    //oCreateViewHolder 에서 초기화를 해줘야한다.
    private val viewPool = RecyclerView.RecycledViewPool()

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {

        private val adapter = FoodAdapter(mutableListOf())

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
            itemView.creatureImage.setImageResource(context.resources.getIdentifier(creature.uri, null, context.packageName))
//
            setupFoods()
        }


        private fun setupFoods() {
            itemView.foodRecyclerView.layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
            itemView.foodRecyclerView.adapter = adapter

            val foods = CreatureStore.getFoodCreatures(creature)
            adapter.updateFoods(foods)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CreatureWithFoodAdapter.ViewHolder {
        val holder = ViewHolder(parent.inflate(R.layout.list_item_creature_with_food))
        //todo viewpool의 역할?
        holder.itemView.foodRecyclerView.recycledViewPool = viewPool
        //todo LinearSnapHelper의 역할?
        /**
            //가로 RecyclerView 를 좌우로 스크롤해보면, item들이 아무데서나 멈추는게 아니라
            //자석이 끌어 당기는 것처럼 position 1번 자리의 좌표에서 멈추는 것을 볼 수 있습니다.
            //위 같은 애니메이션?? 을 스냅이라고 합니다.
        */
        LinearSnapHelper().attachToRecyclerView(holder.itemView.foodRecyclerView)
        return holder
    }

    override fun getItemCount(): Int = creatures.size


    override fun onBindViewHolder(holder: CreatureWithFoodAdapter.ViewHolder, position: Int) {
        holder.bind(creatures[position])
        println("creatures[position] ? ${creatures[position].fullName}")
    }

    fun updateCreatures(creatures: List<Creature>) {
        this.creatures.clear()
        this.creatures.addAll(creatures)
        notifyDataSetChanged()
    }
}