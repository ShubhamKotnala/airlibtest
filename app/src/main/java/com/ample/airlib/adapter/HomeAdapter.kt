package com.ample.airlib.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.ample.airlib.R
import com.ample.airlib.data.model.login.Problems
import com.ample.airlib.databinding.ItemHomeBinding

class HomeAdapter(private val mList: List<Problems>, private val callback: (Problems) -> Unit) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return  ViewHolder(ItemHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mList[position]

        holder.tvName.text = item.name
        holder.tvDose1.text = item.medications!!.medicationsClasses[0].className[0].associatedDrug[0].name
        holder.tvDose2.text = item.medications!!.medicationsClasses[0].className[0].associatedDrug2[0].name
        holder.tvStrength1.text = item.medications!!.medicationsClasses[0].className[0].associatedDrug[0].strength
        holder.tvStrength2.text = item.medications!!.medicationsClasses[0].className[0].associatedDrug2[0].strength
        holder.container.setOnClickListener { callback(item) }
    }

    override fun getItemCount() = mList.size

    class ViewHolder(ItemView: ItemHomeBinding) : RecyclerView.ViewHolder(ItemView.root) {
       val tvName: TextView = itemView.findViewById(R.id.name)
       val tvDose1: TextView = itemView.findViewById(R.id.dose1)
       val tvDose2: TextView = itemView.findViewById(R.id.dose2)
       val tvStrength1: TextView = itemView.findViewById(R.id.strength1)
       val tvStrength2: TextView = itemView.findViewById(R.id.strength2)
       val container: CardView = itemView.findViewById(R.id.container)
    }
}
