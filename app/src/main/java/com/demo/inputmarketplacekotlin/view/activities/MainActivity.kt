package com.demo.inputmarketplacekotlin.view.activities

import android.os.Bundle
import android.view.Gravity
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import com.demo.inputmarketplacekotlin.R
import com.demo.inputmarketplacekotlin.view.base.BaseActivity
import com.demo.inputmarketplacekotlin.view.callbacks.ToolbarCallbacks
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.custom_tooltip.*
import kotlinx.android.synthetic.main.layout_cart_count.*
import kotlinx.android.synthetic.main.marketplace_common_toolbar.*

class MainActivity : BaseActivity(), ToolbarCallbacks, View.OnClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews();
    }

    private fun initViews() {

        setSupportActionBar(toolbar)
        iv_menu.setOnClickListener(this)

        val toggle=ActionBarDrawerToggle(this,drawer_layout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close)
        /*ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
            }
             Called when a drawer has settled in a completely open state.
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                IOUtils.hideKeyBoard(mContext);
            }
        };*/
        drawer_layout.addDrawerListener(toggle);
    }

    override fun setToolbarTitle(title: String?) {
        tv_title.text = title
    }

    override fun updateCartCount(count: Int?) {
        tv_cart_count.text = count.toString()
    }

    override fun onClick(p0: View?) {
        if (p0 == iv_menu) {
            if (drawer_layout.isDrawerOpen(Gravity.RIGHT))
                drawer_layout.closeDrawer(Gravity.LEFT) else
                drawer_layout.openDrawer(Gravity.LEFT)

        }
    }
}
