package com.tak.something

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.navercorp.nid.NaverIdLoginSDK
import com.tak.something.databinding.ActivityNaverLoginBinding

class NaverLogin : AppCompatActivity() {

    private lateinit var binding: ActivityNaverLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNaverLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /** Naver Login Module Initialize */
        val naverClientId = getString(R.string.social_login_info_naver_client_id)
        val naverClientSecret = getString(R.string.social_login_info_naver_client_secret)
        val naverClientName = getString(R.string.social_login_info_naver_client_name)
        NaverIdLoginSDK.initialize(this, naverClientId, naverClientSecret, naverClientName)



    }


    private fun setLayoutState(login: Boolean){
        if(login){
            binding.tvNaverLogin.visibility = View.GONE
            binding.tvNaverLogout.visibility = View.VISIBLE
            binding.tvNaverDeleteToken.visibility = View.VISIBLE
        } else {
            binding.tvNaverLogin.visibility = View.VISIBLE
            binding.tvNaverLogout.visibility = View.GONE
            binding.tvNaverDeleteToken.visibility = View.GONE
            binding.tvResult.text = ""
        }
    }
}