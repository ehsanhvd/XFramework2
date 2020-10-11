package com.hvd.xview.activity

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.hvd.xcustomview.fragment.XFragment
import com.hvd.xview.R
import kotlinx.android.synthetic.main.activity_drawer.*

abstract class XDrawerActivity : AppCompatActivity() {

    //fragmentActivity or drawerActivity???

    var fragmentItems: List<Fragment> = arrayListOf()
    var currentIndex = -1;

    override fun onCreate(savedInstanceState: Bundle?) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        }
        super.onCreate(savedInstanceState)

    }

    fun getDrawerLayout(): DrawerLayout {
        return drawerView
    }

    override fun setContentView(layoutResID: Int) {
        val activity = createActivityView()
        layoutInflater.inflate(layoutResID, activity.findViewById(R.id.frameContent))
        super.setContentView(activity)
        onContentViewSet()
    }

    override fun setContentView(view: View?) {
        val activity = createActivityView()
        activity.findViewById<ViewGroup>(R.id.frameContent).addView(view)
        super.setContentView(activity)
        onContentViewSet()
    }

    open fun onContentViewSet() {

    }

    private fun createActivityView(): View {
        return layoutInflater.inflate(R.layout.activity_drawer, null);
    }

    fun setDrawerView(view: View) {
        frameDrawer.addView(view)
    }

    fun setDrawerView(layoutResID: Int) {
        setDrawerView(layoutInflater.inflate(layoutResID, frameDrawer, false))
    }

    fun toggleDrawer() {
        if (getDrawerLayout().isDrawerOpen(GravityCompat.START)) {
            getDrawerLayout().closeDrawer(GravityCompat.START)
        } else {
            getDrawerLayout().openDrawer(GravityCompat.START)
        }
    }

    fun toggleDrawerDelayed() {
        Handler().postDelayed({ toggleDrawer() }, 500)
    }

    protected fun setFragments(fragments: List<Fragment>) {
        this.fragmentItems = fragments
    }

    var BACK_STACK_ROOT_TAG = "root_fragment"

    protected fun setFragment(position: Int, intRes: Int) {
        val fragmentManager = supportFragmentManager
        fragmentManager.popBackStack(BACK_STACK_ROOT_TAG, FragmentManager.POP_BACK_STACK_INCLUSIVE)

        fragmentManager.beginTransaction()
            .replace(
                intRes,
                fragmentItems[position],
                position.toString()
            )
            .addToBackStack(null)
            .commit()
        fragmentManager.executePendingTransactions()

        if (fragmentItems[position] is XFragment) {
            (fragmentItems[position] as XFragment).onSelected()
        }
        currentIndex = position
        onFragmentChanged(position, fragmentItems[position])
    }

    protected open fun onFragmentChanged(position: Int, fragment: Fragment) {

    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 1) {
            supportFragmentManager.popBackStackImmediate()
        } else {
            if (fragmentItems[currentIndex] is XFragment) {
                if (!(fragmentItems[currentIndex] as XFragment).onBackPressed()) {
                    supportFinishAfterTransition()
                }
            } else {
                supportFinishAfterTransition()
            }
        }
    }

    protected fun getActivity(): AppCompatActivity {
        return this
    }

}