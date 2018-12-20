package com.raywenderlich.android.creatures.ui

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.raywenderlich.android.creatures.R
import com.raywenderlich.android.creatures.R.id.fullName2
import com.raywenderlich.android.creatures.R.id.nickName
import com.raywenderlich.android.creatures.app.inflate
import com.raywenderlich.android.creatures.model.Creature
import kotlinx.android.synthetic.main.activity_creature.view.*
import kotlinx.android.synthetic.main.list_item_creature.view.*

class FavoriteAdapter(private val creatures: List<Creature>) : RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {

    //todo ViewHolder는 별도로 클래스를 만들어서 사용하고 리턴은 RecyclerView.ViewHolder 임
    class ViewHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {
        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View) {

        }

        private lateinit var creature: Creature

        //onBindViewHolder 에서 각 아이템들을 넘겨서 bind 시킨다.
        fun bind(creature: Creature) {
            //todo 굳이 creature 를 왜 받지?
            this.creature = creature

            //context 를 가지고 온다.
            val context = itemView.context
            itemView.nickName.text = creature.nickname
            itemView.fullName2.text = creature.fullName
            itemView.creatureImage.setImageResource(context.resources.getIdentifier(creature.uri, null, context.packageName))
        }

    }

    //ViewHolder 를 생성한다.
    //ViewHolder 는 내부에 별도의 클래스로 RecyclerView 의 ViewHolder를 return 하도록 선언을 해놓고 사용한다.
    //ViewHolder 안에서 bind 함수를 만들어서 onBindViewHolder 할 때 사용하도록 한다.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteAdapter.ViewHolder {
        //onCreateViewHolder function 명 그대로 ViewHolder 를 생성하는데
        //ViewHolder 에 view 를 전달해야 하기 때문에 layout xml 을 inflate 한다.
        return ViewHolder(parent.inflate(R.layout.list_item_creature))
    }

    override fun getItemCount(): Int {
        return creatures.size
    }

    override fun onBindViewHolder(holder: FavoriteAdapter.ViewHolder, position: Int) {
        //todo bind 하는 방식 보기
        holder.bind(creatures[position])
    }


}