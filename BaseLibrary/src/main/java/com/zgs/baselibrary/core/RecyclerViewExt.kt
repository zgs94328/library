package com.zgs.baselibrary.core

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.View
import androidx.recyclerview.widget.*
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemChildClickListener
import com.chad.library.adapter.base.listener.OnItemChildLongClickListener
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.chad.library.adapter.base.listener.OnItemLongClickListener
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.google.android.flexbox.FlexboxLayoutManager
import com.zgs.baselibrary.util.decoration.RecyclerViewDivider
import java.util.*

/**
 * Description: RecyclerView扩展
 * Create by lxj, at 2018/12/25
 */

/**
 * 设置分割线
 * @param color 分割线的颜色，默认是#DEDEDE
 * @param size 分割线的大小，默认是1px
 * @param isReplace 是否覆盖之前的ItemDecoration，默认是true
 *
 */
fun RecyclerView.divider(color: Int = Color.parseColor("#f5f5f5"), size: Int = dp2px(1), isReplace: Boolean = true): RecyclerView {
    val decoration = RecyclerViewDivider(context, orientation)
    decoration.setDrawable(GradientDrawable().apply {
        setColor(color)
        shape = GradientDrawable.RECTANGLE
        setSize(size, size)
    })
    if(isReplace && itemDecorationCount>0){
        removeItemDecorationAt(0)
    }
    addItemDecoration(decoration)
    return this
}

/**
 * 设置分割线
 */
fun RecyclerView.divider(decoration: ItemDecoration): RecyclerView {
    addItemDecoration(decoration)
    return this
}

/**
 * 实现流式布局
 * @receiver RecyclerView
 * @return RecyclerView
 */
fun RecyclerView.flexbox(): RecyclerView {
    layoutManager = FlexboxLayoutManager(context)
    return this
}

/**
 *
 * @receiver RecyclerView
 * @param spanCount Int 列数
 * @param isStaggered Boolean 是否开启瀑布流
 * @return RecyclerView
 */
fun RecyclerView.vertical(spanCount: Int = 0, isStaggered: Boolean = false): RecyclerView {
    layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
    if (spanCount != 0) {
        layoutManager = GridLayoutManager(context, spanCount)
    }
    if (isStaggered) {
        layoutManager = StaggeredGridLayoutManager(spanCount, StaggeredGridLayoutManager.VERTICAL)
    }
    return this
}

fun RecyclerView.horizontal(): RecyclerView {
    layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

    return this
}

/**
 * 获取RecyclerView.data
 */
inline val RecyclerView.data
    get() = (adapter as BaseQuickAdapter<*, *>).data

/**
 * 获取RecyclerView.orientation
 */
inline val RecyclerView.orientation
    get() = if (layoutManager == null) -1 else layoutManager.run {
        when (this) {
            is LinearLayoutManager -> orientation
            is GridLayoutManager -> orientation
            is StaggeredGridLayoutManager -> orientation
            else -> -1
        }
    }


fun <T> RecyclerView.bindData(data: ArrayList<T>, layoutId: Int, bindFn: (holder: BaseViewHolder, t: T, position: Int) -> Unit): RecyclerView {
    adapter = object :  BaseQuickAdapter<T, BaseViewHolder>(layoutId,data) {

        override fun convert(holder: BaseViewHolder, item: T) {
            bindFn(holder, item, holder.adapterPosition - headerLayoutCount)
        }
    }
    return this
}
fun  RecyclerView.bindData(adapter: BaseQuickAdapter<*, *>): RecyclerView {
    this.adapter = adapter
    return this
}


//fun <T> RecyclerView.updateData(data: ArrayList<T>){
//    (this.adapter as? BaseQuickAdapter<*, *>?)?.setList(data)
//}



/**
 *  添加头部
 * @param position Int 指定位置
 */
fun RecyclerView.addHeader(headerView: View,position: Int=0): RecyclerView {
    adapter?.apply {
        (this as BaseQuickAdapter<*, *>).addHeaderView(headerView,position)
    }
    return this
}

/**
 *  添加脚部
 * @param position Int 指定位置
 */
fun RecyclerView.addFooter(footerView: View,position: Int=0): RecyclerView {
    adapter?.apply {
        (this as BaseQuickAdapter<*, *>).addFooterView(footerView,position)
    }
    return this
}

fun RecyclerView.itemClick(listener: (adapter: BaseQuickAdapter<*, *>, view: View, position: Int) -> Unit): RecyclerView {
    adapter?.apply {
        (adapter as BaseQuickAdapter<*, *>).setOnItemClickListener(object : OnItemClickListener {

            override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
                listener(adapter, view, position)
            }
        })
    }
    return this
}
fun RecyclerView.itemLongClick(listener: (adapter: BaseQuickAdapter<*, *>, view: View, position: Int) -> Unit): RecyclerView {
    adapter?.apply {
        (adapter as BaseQuickAdapter<*, *>).setOnItemLongClickListener(object : OnItemLongClickListener {



            override fun onItemLongClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int): Boolean {
                listener(adapter, view, position)
                return true;
            }
        })
    }
    return this
}

fun RecyclerView.itemChildClick(vararg param: Int,listener: (adapter: BaseQuickAdapter<*, *>, view: View, position: Int) -> Unit): RecyclerView {
    adapter?.apply {
        param.forEach {
            (adapter as BaseQuickAdapter<*, *>).addChildClickViewIds(it)
        }
        (adapter as BaseQuickAdapter<*, *>).setOnItemChildClickListener(object : OnItemChildClickListener {
            override fun onItemChildClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
                listener(adapter, view, position)
            }


        })
    }
    return this
}

fun RecyclerView.itemChildLongClick(vararg param: Int,listener: (adapter: BaseQuickAdapter<*, *>, view: View, position: Int) -> Unit): RecyclerView {
    adapter?.apply {
        param.forEach {
            (adapter as BaseQuickAdapter<*, *>).addChildLongClickViewIds(it)
        }
        (adapter as BaseQuickAdapter<*, *>).setOnItemChildLongClickListener(object : OnItemChildLongClickListener {
            override fun onItemChildLongClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int): Boolean {
                listener(adapter, view, position)
                return true
            }

        })
    }
    return this
}


fun RecyclerView.smoothScrollToEnd(){
    if(adapter!=null && adapter!!.itemCount>0){
        smoothScrollToPosition(adapter!!.itemCount-1)
    }
}

fun RecyclerView.scrollToEnd(){
    if(adapter!=null && adapter!!.itemCount>0){
        scrollToPosition(adapter!!.itemCount-1)
    }
}

/**
 * 滚动置顶，只支持线性布局
 */
fun RecyclerView.scrollTop(position: Int){
    if(layoutManager is LinearLayoutManager){
        (layoutManager as LinearLayoutManager).scrollToPositionWithOffset(position, 0)
    }
}

/**
 * 启用条目拖拽，必须在设置完adapter之后调用
 * @param isDisableLast 是否禁用最后一个拖拽
 */
fun RecyclerView.enableItemDrag(isDisableLast: Boolean = false, onDragFinish: (()->Unit)? = null ){
    ItemTouchHelper(object : ItemTouchHelper.Callback() {
        override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int {
            if(adapter==null) return 0
            if(isDisableLast && viewHolder.adapterPosition == (adapter!!.itemCount-1) ) return 0
            return if (recyclerView.layoutManager is GridLayoutManager) {
                val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN or
                        ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
                val swipeFlags = 0
                makeMovementFlags(dragFlags, swipeFlags)
            } else {
                val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
                val swipeFlags = 0
                makeMovementFlags(dragFlags, swipeFlags)
            }
        }
        override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
        ): Boolean {
            if(adapter==null) return false
            //得到当拖拽的viewHolder的Position
            val fromPosition = viewHolder.adapterPosition
            //拿到当前拖拽到的item的viewHolder
            val toPosition = target.adapterPosition
            if(isDisableLast && toPosition== (adapter!!.itemCount-1) )return false
            if (fromPosition < toPosition) {
                for (i in fromPosition until toPosition) {
                    Collections.swap((adapter as BaseQuickAdapter<*, *>).data, i, i + 1)
                }
            } else {
                for (i in fromPosition downTo toPosition + 1) {
                    Collections.swap((adapter as BaseQuickAdapter<*, *>).data, i, i - 1)
                }
            }
            recyclerView.adapter?.notifyItemMoved(fromPosition, toPosition)
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {}

        override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
            super.onSelectedChanged(viewHolder, actionState)
        }

        override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
            super.clearView(recyclerView, viewHolder)
            onDragFinish?.invoke()
        }

    }).attachToRecyclerView(this)
}