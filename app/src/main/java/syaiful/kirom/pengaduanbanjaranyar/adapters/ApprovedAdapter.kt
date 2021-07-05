package syaiful.kirom.pengaduanbanjaranyar.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import kotlinx.android.synthetic.main.list_item_aduan.view.*
import syaiful.kirom.pengaduanbanjaranyar.R
import syaiful.kirom.pengaduanbanjaranyar.models.Aduan

class ApprovedAdapter(private var data: List<Aduan>, private var context: Context) : RecyclerView.Adapter<ApprovedAdapter.MyHolder>() {

    inner class MyHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        fun bind(aduan : Aduan, context: Context){
            if(aduan.complaint_image != null){
                itemView.ivImageContent.load(aduan.complaint_image)
            }else{
                itemView.ivImageContent.load(R.drawable.no_image)
            }
            itemView.tvComplaintContent.text = aduan.complaint_content
            itemView.tvTanggal.text = aduan.created_at
            itemView.tvPenanggungJawab.text = aduan.user
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return MyHolder(LayoutInflater.from(context).inflate(R.layout.list_item_aduan, parent, false))
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) = holder.bind(data[position], context)

    override fun getItemCount(): Int = data.size
}