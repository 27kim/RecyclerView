/*
 * Copyright (c) 2018 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * Notwithstanding the foregoing, you may not use, copy, modify, merge, publish,
 * distribute, sublicense, create a derivative work, and/or sell copies of the
 * Software in any work that is designed, intended, or marketed for pedagogical or
 * instructional purposes related to programming, coding, application development,
 * or information technology.  Permission for such use, copying, modification,
 * merger, publication, distribution, sublicensing, creation of derivative works,
 * or sale is expressly withheld.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.raywenderlich.android.creatures.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.*
import com.raywenderlich.android.creatures.R
import com.raywenderlich.android.creatures.model.CreatureStore
import kotlinx.android.synthetic.main.fragment_all.*


class AllFragment : Fragment() {

    private val adapter = CreatureCardAdapter(CreatureStore.getCreatures().toMutableList())
    private lateinit var layoutManager: StaggeredGridLayoutManager

    companion object {
        fun newInstance(): AllFragment {
            return AllFragment()
        }
    }

    //메뉴 위해서 아래 펑션 추가 setHasOptionsMenu(true)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_all, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        layoutManager = StaggeredGridLayoutManager(2, GridLayoutManager.VERTICAL)
        //메뉴 위해서 아래 주석처리
//        val layoutManager = StaggeredGridLayoutManager(2, GridLayoutManager.VERTICAL)


//    val layoutManager = GridLayoutManager(activity, 3, GridLayoutManager.VERTICAL, false)
//    layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
//      override fun getSpanSize(position: Int): Int {
//        return if ((position + 1) % 7 == 0) 3 else 1
//      }
//    }
        creatureRecyclerView.layoutManager = layoutManager
        creatureRecyclerView.adapter = adapter
    }

    //옵션 메뉴 만들기 위
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_all, menu)
    }

    private fun showListView() {
        layoutManager.spanCount = 1
    }

    private fun showGridView() {
        layoutManager.spanCount = 2
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        when (id) {
            R.id.action_span_1 -> {
                showListView()
                return true
            }
            R.id.action_span_2 -> {
                showGridView()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}