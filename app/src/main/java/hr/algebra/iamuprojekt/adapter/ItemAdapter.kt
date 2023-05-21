package hr.algebra.iamuprojekt.adapter

import android.content.ContentUris
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import hr.algebra.iamuprojekt.IAMU_PROVIDER_CONTENT_URI
import hr.algebra.iamuprojekt.ItemPagerActivity
import hr.algebra.iamuprojekt.POSITION
import hr.algebra.iamuprojekt.R
import hr.algebra.iamuprojekt.framework.startActivity
import hr.algebra.iamuprojekt.model.Item
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation
import java.io.File

class ItemAdapter(private val context: Context, private val items: MutableList<Item>) :
    RecyclerView.Adapter<ItemAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ivItem = itemView.findViewById<ImageView>(R.id.ivItem)
        private val tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)

        fun bind(item: Item) {
            Picasso.get()
                .load(File(item.picturePath))
                .error(R.drawable.nasa)
                .transform(RoundedCornersTransformation(50, 5))
                .into(ivItem)
            tvTitle.text = item.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]

        holder.itemView.setOnLongClickListener {
            deleteItem(position)
            true
        }
        holder.itemView.setOnClickListener {
            context.startActivity<ItemPagerActivity>(POSITION, position)
        }

        holder.bind(item)
    }

    private fun deleteItem(position: Int) {
        AlertDialog.Builder(context).apply {
            setTitle(context.getString(R.string.deleteTitle))
            setIcon(R.drawable.delete)
            setMessage(context.getString(R.string.deleteMessage))
            setCancelable(true)
            setPositiveButton(context.getString(R.string.acceptDelete)) { _, _ -> delete(position) }
            setNegativeButton(context.getString(R.string.cancelDelete), null)
            show()
        }
    }

    private fun delete(position: Int) {
        val item = items[position]
        context.contentResolver.delete(
            ContentUris.withAppendedId(IAMU_PROVIDER_CONTENT_URI, item._id!!),
            null,
            null
        )
        File(item.picturePath).delete()
        items.removeAt(position)
        notifyDataSetChanged()
    }

    override fun getItemCount() = items.size


}