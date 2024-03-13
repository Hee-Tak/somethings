package com.tak.something

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.HapticFeedbackConstants
import android.view.Menu
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView
import com.tak.something.data.StreamItem
import com.tak.something.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var toolbar: Toolbar
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView

    //private lateinit var recyclerView: RecyclerView
    //private var streamList: List<StreamItem> = listOf() // API 에서 받아온 데이터로 초기화

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Toolbar 를 액션바로 설정
        toolbar = binding.myToolbar
        setSupportActionBar(toolbar)

        //액션바 타이틀 설정
        supportActionBar?.title = "[타이틀]"
        supportActionBar?.setDisplayShowTitleEnabled(false)

        //액션바의 홈 버튼 활성화
        //supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setTooltip(toolbar, "${toolbar.title}")









        drawerLayout = binding.drawerLayout
        navView = binding.navView
        //val toggle = ActionBarDrawerToggle(
        //    this, drawerLayout, toolbar,
        //    R.string.navigation_drawer_open,
        //    R.string.navigation_drawer_close
        //)
        //drawerLayout.addDrawerListener(toggle)
        //toggle.syncState()                        //ActionBarDrawerToggle을 사용하지 않고 직접 제어할 경우, 얘도 필요하진 않음.

        val btnNavigation = binding.btnNavigation
        // 네비게이션 드로어 열기
        btnNavigation.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }


        navView.setNavigationItemSelectedListener {

            //네비게이션 메뉴 아이템 클릭 이벤트 처리
            when(it.itemId) {
                R.id.nav_item1 -> {
                    //처리할 내용 추가
                    true
                }
                R.id.nav_item2 -> {
                    //처리할 내용 추가
                    true
                }
                else -> false
            }
        }





    }

    //추가적인 메소드, 이벤트 핸들러 정의


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    private fun showToast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

    private fun setTooltip(view: View, tooltipText: String){
        view.setOnHoverListener(object : View.OnHoverListener{
            override fun onHover(v: View?, event: MotionEvent?): Boolean {
                when(event?.action){
                    MotionEvent.ACTION_HOVER_ENTER -> {
                        v?.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS)
                        showToast(tooltipText)
                        return true
                    }
                    else -> return false
                }
            }
        })
    }

}