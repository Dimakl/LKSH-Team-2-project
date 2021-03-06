package team2.lksh.p.formuland.adapters

import android.content.Context
import android.content.res.AssetManager
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.ViewGroup
import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.formul_row.view.*
import team2.lksh.p.formuland.R
import team2.lksh.p.formuland.JsonDataProcessor
import javax.security.auth.Subject

fun getImgDrawable(activity: Context, name : String) : Drawable {
    val res = activity.resources
    val resId = res.getIdentifier(name, "drawable", activity.packageName)
    val draw = res.getDrawable(resId)
    return draw
}

class FormulsAdapter(val activity: Context, val subject: String) : RecyclerView.Adapter<FormulaViewHolder>() {

    val jsonDataProcessor = JsonDataProcessor(activity)
    val data = jsonDataProcessor.getMenuData(subject)

    override fun getItemCount(): Int {
        return data.idList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FormulaViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        val cellForRow = layoutInflater.inflate(R.layout.formul_row, parent, false)
        return FormulaViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: FormulaViewHolder, position: Int) {
        val v = holder.itemView

        val title = data.names[position]
        val imgPath = data.images[position]
        holder.index = data.idList[position]

        v.title.text = title

        val drawable = getImgDrawable(activity, imgPath)

        v.img.setImageDrawable(drawable)
        v.pos_text.text = data.idList[position].toString()
    }
}
class FormulaViewHolder(v: View) : CustomViewHolder(v) {
    var index = -1
}