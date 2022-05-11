package com.example.pantrypalette
//
//import android.R
//import android.content.Context
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.BaseAdapter
//import android.widget.ImageView
//import android.widget.TextView
//import com.example.pantrypalette.api.RecipesResult
//
//
//internal class Custom(private val recipes: List<RecipesResult>?, private val context: Context, private val layout: Int) :
//    BaseAdapter() {
//
//    override fun getCount(): Int {
//        if (recipes != null) {
//            return recipes.size
//        }
//
//        return 0
//    }
//
//    override fun getItem(position: Int): Any {
//        return recipes!![position]
//    }
//
//    override fun getItemId(position: Int): Long {
//        return position.toLong()
//    }
//
//    private inner class ViewHolder {
//        val recipeName: TextView? = null
//        var recipeImg: ImageView? = null
//    }
//
//    override fun getView(position: Int, convertView: View, parent: ViewGroup): View {
//        var cv = convertView
//        val viewHolder = ViewHolder()
//        val layoutInflater =
//            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
//        cv = layoutInflater.inflate(layout, null)
//
//        viewHolder.recipeName = convertView.findViewById(R.id.recipeName)
//        viewHolder.titletxt = convertView.findViewById(R.id.titletxt)
//        viewHolder.bodytxt = convertView.findViewById(R.id.bodytxt)
//
//        //set position
//        val model: Model = modelArrayList[position]
//        viewHolder.idtxt.setText(
//            """
//                Id:-${model.getId().toString()}
//
//                """.trimIndent()
//        )
//        viewHolder.titletxt.setText(
//            """
//                Title:-${model.getTitle().toString()}
//
//                """.trimIndent()
//        )
//        viewHolder.bodytxt.setText("Body:-" + model.getBody())
//        return convertView
//    }
//}