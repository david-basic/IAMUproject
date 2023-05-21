package hr.algebra.iamuprojekt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import hr.algebra.iamuprojekt.adapter.ItemPagerAdapter
import hr.algebra.iamuprojekt.databinding.ActivityItemPagerBinding
import hr.algebra.iamuprojekt.framework.fetchItems
import hr.algebra.iamuprojekt.model.Item

const val POSITION = "hr.algebra.iamuprojekt.position"

@Suppress("DEPRECATION")
class ItemPagerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityItemPagerBinding
    private lateinit var items: MutableList<Item>
    private var itemPosition = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityItemPagerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initPager()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    private fun initPager() {
        items = fetchItems()
        itemPosition = intent.getIntExtra(POSITION, itemPosition)
        binding.viewPager.adapter = ItemPagerAdapter(this, items)
        binding.viewPager.currentItem = itemPosition
    }
}