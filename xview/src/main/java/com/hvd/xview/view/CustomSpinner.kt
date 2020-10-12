package com.hvd.xview.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.annotation.ArrayRes
import androidx.annotation.LayoutRes
import androidx.appcompat.widget.AppCompatSpinner
import com.hvd.xview.R


public open class CustomSpinner<V> : AppCompatSpinner {

    private var reqDefaultIndex : Int = 0
    private var adapter: SpinnerAdapter<V>? = null
    private var items: List<V> = ArrayList()
    private var render: ((View, V, Int) -> Unit) = { view: View, v: V, i: Int ->

        val textTitle = view.findViewById<TextView>(R.id.textTitle)
        if (textTitle != null) {
            textTitle.setText(v.toString())
        }
    }


    var attrs: AttributeSet? = null
    var defStyleAttr: Int = 0

    @LayoutRes
    private var defaultItem = R.layout.spinner_dropdown_item
    @LayoutRes
    private var dropDownItem = R.layout.spinner_dropdown_item


    constructor(context: Context) : super(context)

    constructor(
        context: Context,
        item: List<V>,
        default: Int = 0,
        render: ((View, V, Int) -> Unit)? = null
    ) : super(context) {

        reqDefaultIndex = default
        if (render != null) {
            set(item, render)
        } else {
            set(item)
        }
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        this.attrs = attrs
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        this.attrs = attrs
        this.defStyleAttr = defStyleAttr
    }

    init {

        val ta = context.obtainStyledAttributes(attrs, R.styleable.CustomSpinner, defStyleAttr, 0)

        defaultItem = ta.getResourceId(R.styleable.CustomSpinner_defaultItem, this.defaultItem)
        dropDownItem = ta.getResourceId(R.styleable.CustomSpinner_dropdownItem, this.dropDownItem)

        ta.recycle()
    }

    fun set(item: List<V>, render: ((View, V, Int) -> Unit)) {
        items = item
        this.render = render;

        setAdapter(SpinnerAdapter(context, items))
        setSelection(reqDefaultIndex)
    }

    open fun set(item: List<V>) {
        items = item

        setAdapter(SpinnerAdapter(context, items))
        setSelection(reqDefaultIndex)
    }

    operator fun get(index: Int): V {
        return items.get(index)
    }

    fun getItems(): List<V> {
        return items
    }

    fun remove(index: Int) {
        items.drop(index)
        adapter?.notifyDataSetChanged()
    }


    private inner class SpinnerAdapter<T>(context: Context, list: List<T>) :
        ArrayAdapter<T>(context, 0, list) {

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            return renderView(defaultItem, position, convertView, parent)
        }

        override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
            return renderView(dropDownItem, position, convertView, parent)
        }

        private fun renderView(
            itemResource: Int,
            position: Int,
            convertView: View?,
            parent: ViewGroup
        ): View {
            var view = convertView
            if (view == null) {
                val inflater =
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                view = inflater.inflate(itemResource, parent, false)
            }

            (render)(view!!, items.get(position), position)

            return view
        }
    }

    fun onRender(render: ((View, V, Int) -> Unit)) {
        this.render = render;
    }

    companion object {
        fun withArray(
            context: Context,
            @ArrayRes items: Int,
            default: Int = 0,
            render: ((View, String, Int) -> Unit)? = null
        ): CustomSpinner<String> {

            return CustomSpinner(
                context,
                context.resources.getStringArray(items).toList(),
                default,
                render
            )
        }

    }
}