package com.tak.something

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.navercorp.nid.NaverIdLoginSDK
import com.navercorp.nid.oauth.NidOAuthLogin
import com.navercorp.nid.oauth.OAuthLoginCallback
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


    /**
     * 연동해제
     * 네이버 아이디와 애플리케이션의 연동을 해제하는 기능은 다음과 같이
     * NidOAuthLogin().callDeleteTokenApi() 메서드로 구현합니다.
     * 연동을 해제하면 클라이언트에 저장된 토큰과 서버에 저장된 토큰이 모두 삭제됩니다.
     */
    private fun startNaverDeleteToken(){
        NidOAuthLogin().callDeleteTokenApi(this, object : OAuthLoginCallback{
            override fun onSuccess() {
                //서버에서 토큰 삭제에 성공한 상태입니다.
                setLayoutState(false)
                Toast.makeText(this@NaverLogin, "네이버 아이디 토큰삭제 성공!", Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(httpStatus: Int, message: String) {
                //서버에서 토큰 삭제에 실패했어도 클라이언트에 있는 토큰은 삭제되어 로그아웃된 상태입니다.
                //클라이언트에 토큰 정보가 없기 때문에 추가로 처리할 수 있는 작업은 없습니다.
                Log.d("naver", "errorCode: ${NaverIdLoginSDK.getLastErrorCode().code}")
                Log.d("naver", "errorDesc: ${NaverIdLoginSDK.getLastErrorDescription()}")
            }

            override fun onError(errorCode: Int, message: String) {
                //서버에서 토큰 삭제에 실패했어도 클라이언트에 있는 토큰은 삭제되어 로그아웃된 상태입니다.
                //클라이언트에 토큰 정보가 없기 때문에 추가로 처리할 수 있는 작업은 없습니다.
                onFailure(errorCode, message)
            }
        })
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