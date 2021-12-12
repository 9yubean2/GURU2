package com.example.mywebbrowser

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.EditorInfo
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    lateinit var webView: WebView
    lateinit var urlEditText: EditText
    lateinit var btnGo: Button
    lateinit var btnBack: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        webView = findViewById(R.id.webView)
        urlEditText = findViewById(R.id.urlEditText)
        btnGo = findViewById(R.id.btnGo)
        btnBack = findViewById(R.id.btnBack)

        webView.apply{
            settings.javaScriptEnabled = true //on
            webViewClient = WebViewClient() //웹뷰에 페이지 표시
        }

        webView.loadUrl("http://www.google.com")

        urlEditText.setOnEditorActionListener { v, actionId, event ->
            if(actionId == EditorInfo.IME_ACTION_SEARCH)//검색 버튼 눌러졌는지 아닌지 체크
            {
                webView.loadUrl(urlEditText.text.toString())//urlEditText 값 불러오기
                true
            }
            else{
                false
            }
        }

        //뒤로가기, 앞으로가기 버튼 클릭 이벤트 처리
        btnBack.setOnClickListener {
            webView.goBack()
        }

        btnGo.setOnClickListener {
            webView.goForward()
        }

        registerForContextMenu(webView)//컨텍스트 메뉴 등록
    }

    override fun onBackPressed() { //뒤로가기 기능
        if(webView.canGoBack()){
            webView.goBack()
        }
        else{
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item?.itemId) {
            R.id.action_google, R.id.action_home ->{
                webView.loadUrl("http://www.google.com")
                return true
            }
            R.id.action_refresh ->{
                webView.reload()
                return true
            }
            R.id.action_naver ->{
                webView.loadUrl("http://www.naver.com")
                return true
            }
            R.id.action_daum ->{
                webView.loadUrl("http://www.daum.net")
                return true
            }
            R.id.action_call ->{
                val intent = Intent(Intent.ACTION_DIAL)
                intent.data = Uri.parse("tel:010-2717-3024")
                if(intent.resolveActivity(packageManager) != null){
                    startActivity(intent)
                }
                return true
            }
            R.id.action_send_text ->{
                val intent = Intent(Intent.ACTION_SENDTO)
                intent.data = Uri.parse("smsto:"+Uri.encode("010-2717-3024"))
                if(intent.resolveActivity(packageManager) != null){
                    startActivity(intent)
                }
                return true
            }
            R.id.action_email ->{
                val intent = Intent(Intent.ACTION_SENDTO)
                intent.data = Uri.parse("mailto:example@example.com")
                if(intent.resolveActivity(packageManager) != null){
                    startActivity(intent)
                }
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.context,menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when (item?.itemId){
            R.id.action_share ->{
                val intent = Intent(Intent.ACTION_SEND)
                intent.setType("text/plain")
                intent.putExtra(Intent.EXTRA_TEXT,webView.url.toString())
                val shareIntent = Intent.createChooser(intent,"공유 페이지")
                startActivity(shareIntent)
                return true
            }
            R.id.action_browser ->{
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(webView.url))
                startActivity(Intent.createChooser(intent,"Browser"))
                return true
            }
        }
        return super.onContextItemSelected(item)
    }
}


