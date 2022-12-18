package com.example.goodday

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.activity.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.goodday.databinding.ActivityNewsInformatinBinding
import com.example.goodday.newsInfoModel.NewsArticle
import com.example.goodday.newsInfoUI.NewsAdapter
import com.example.goodday.newsInfoUI.NewsViewModel


class NewsInformatinActivity : AppCompatActivity() {

    private var binding: ActivityNewsInformatinBinding? = null
    var articleArrayList: ArrayList<NewsArticle> = ArrayList<NewsArticle>()
//    var article:ArrayList<NewsArticle> = ArrayList<NewsArticle>()
    var newsAdapter: NewsAdapter? = null
    private val newsViewModel: NewsViewModel by viewModels()

    var searchWord: String? = ""
    var sortBase: String? = ""
    var language: String? = ""

    var sortBaseSelect = arrayOf("normal","popularity", "publishedAt","relevancy")
    var languageSelect = arrayOf("All","Arabic", "German", "English","Spanish","French", "Hebrew", "Italian"
        ,"Dutch","Norwegian", "Portuguese", "Russian","Swedish","Udmurt","Chinese")
    var languageArr = arrayOf("","ar", "de", "en", "es", "fr", "he", "it", "nl",
            "no", "pt", "ru", "sv", "ud", "zh.")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_news_informatin)

        getSupportActionBar()?.hide()
        binding = ActivityNewsInformatinBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        newsViewModel!!.initActivity("","","")
        newsViewModel!!.getNewsRepository()?.observe(this) { newsResponse ->
            val newsArticles: List<NewsArticle> = newsResponse.articles!!
            articleArrayList.addAll(newsArticles)
            newsAdapter!!.notifyDataSetChanged()
        }
        setupRecyclerView()

        val back: ImageButton = findViewById<ImageButton>(R.id.ivBackHomeNewsInfo)
        back.setOnClickListener {
            finish()
        }
        setSpinner()
        setSearch()
        binding?.ivSearchButton?.setOnClickListener {
            setSearch()
        }

    }
    private fun setupRecyclerView() {
        if (newsAdapter == null) {
            updateRecyclerView()
        } else {
            newsAdapter!!.notifyDataSetChanged()
        }
    }
    private fun updateRecyclerView() {
        newsAdapter = NewsAdapter(this@NewsInformatinActivity, articleArrayList)
        binding?.recylerViewNewsInformation?.layoutManager = LinearLayoutManager(this)
        binding?.recylerViewNewsInformation?.adapter = newsAdapter
        binding?.recylerViewNewsInformation?.itemAnimator = DefaultItemAnimator()
        binding?.recylerViewNewsInformation?.isNestedScrollingEnabled = true
        newsAdapter!!.onItemClick={
            val intent = Intent(this,ArticleActivity::class.java)
            intent.putExtra("news", it)
            startActivity(intent)
        }
    }
    private fun setSearch(){
        articleArrayList= ArrayList<NewsArticle>()
        searchWord = binding?.svNewsInfo?.text.toString()
        searchWord = "health,"+searchWord.toString()
        newsViewModel!!.searchActivity(searchWord,sortBase,language)
        newsViewModel!!.getNewsRepository()?.observe(this) { newsResponse ->
            val newsArticles: List<NewsArticle> = newsResponse.articles!!
            articleArrayList.addAll(newsArticles)
            newsAdapter!!.notifyDataSetChanged()
        }
        updateRecyclerView()
        binding?.svNewsInfo?.getText()?.clear()

    }

    private fun setSpinner(){
        //spinner
        val spinner_sortBase = findViewById<Spinner>(R.id.spinner_view)
        val spinner_language = findViewById<Spinner>(R.id.spinner_latest)
        if (spinner_sortBase != null) {
            val adapter = ArrayAdapter(this,
                android.R.layout.simple_spinner_item, sortBaseSelect)
            spinner_sortBase.adapter = adapter

            spinner_sortBase.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>,
                                            view: View, position: Int, id: Long) {
                    articleArrayList= ArrayList<NewsArticle>()
                    sortBase = sortBaseSelect[position]
                    if (language.toString() == "All"){
                        language = ""
                    }
                    if(sortBase.toString() == "normal"){
                        sortBase = ""
                    }
                    sortBase = sortBase.toString()

                    Toast.makeText(this@NewsInformatinActivity,
                        sortBaseSelect[position], Toast.LENGTH_SHORT).show()
                    setSearch()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }

            }
        }
        if (spinner_language != null) {
            val adapter = ArrayAdapter(this,
                android.R.layout.simple_spinner_item,languageSelect)
            spinner_language.adapter = adapter

            spinner_language.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>,
                                            view: View, position: Int, id: Long) {
                    articleArrayList= ArrayList<NewsArticle>()
                    language = languageArr[position]
                    if (language.toString() == "All"){
                        language = ""
                    }
                    if(sortBase.toString() == "normal"){
                        sortBase = ""
                    }
                    language= language.toString()

                    Toast.makeText(this@NewsInformatinActivity,
                        languageSelect[position], Toast.LENGTH_SHORT).show()
                    setSearch()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }


    }

}
// Recycler view
//    private lateinit var RecyclerView_news_information: RecyclerView
//    private lateinit var newsAdapter: NewsAdapter
//    private lateinit var ArrayList_news_information: ArrayList<NewsModel>
//    lateinit var imageId_news_information: Array<Int>
//    lateinit var heading_news_information: Array<String>
//    lateinit var author_news_information: Array<String>

// Recycler view
//        imageId_news_information = arrayOf(
//            R.drawable.newspic1,R.drawable.newspic2,R.drawable.newspic3,R.drawable.newspic4,R.drawable.newspic5
//        )
//        heading_news_information = arrayOf(
//            "Legal,ethical concerns of involving children in medical decision",
//            "Health Hacks, things you need to know",
//            "The best health article",
//            "SUMMARY Of health","Flat world health day poster"
//        )
//        author_news_information = arrayOf(
//            "author1","author2","author3","author4","author5"
//        )
//        RecyclerView_news_information = findViewById<RecyclerView>(R.id.recylerView_news_information)
//        RecyclerView_news_information.layoutManager = LinearLayoutManager(this)
//        RecyclerView_news_information.setHasFixedSize(true)
//        ArrayList_news_information = arrayListOf<NewsModel>()
//        getUserdata()
//    private fun getUserdata(){
//        for (i in imageId_news_information.indices){
//            val news = NewsModel(imageId_news_information[i],heading_news_information[i],author_news_information[i])
//            ArrayList_news_information.add(news)
//        }
//        newsAdapter= NewsAdapter(ArrayList_news_information)
//        RecyclerView_news_information.adapter = newsAdapter
//        newsAdapter.onItemClick={
//            val intent = Intent(this,ArticleActivity::class.java)
//            intent.putExtra("news",it)
//            startActivity(intent)
//        }
//    }