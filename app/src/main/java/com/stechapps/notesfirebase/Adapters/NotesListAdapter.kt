package com.stechapps.notesfirebase.Adapters

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.stechapps.notesfirebase.Activity.NotesDescriptActivity
import com.stechapps.notesfirebase.R
import com.stechapps.notesfirebase.models.NotesListModel
import kotlinx.android.synthetic.main.row_notes_list.view.*

class NotesListAdapter(private  val Dataset:ArrayList<NotesListModel>,val context:Context):RecyclerView.Adapter<NotesListAdapter.NotesViewHolder>(){

    class NotesViewHolder(v:View):RecyclerView.ViewHolder(v){
         val tvHeading=v.heading
         val tvAbs=v.abs

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
       val v=LayoutInflater.from(context).inflate(R.layout.row_notes_list,parent,false)
        return NotesViewHolder(v)
    }

    override fun getItemCount(): Int {
        return Dataset.size
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        holder.tvHeading?.text=Dataset.get(position).Heading.toString()
        holder.tvAbs?.text=Dataset.get(position).Abstract.toString()
        holder.itemView.setOnClickListener{
            val intent  = Intent(context,NotesDescriptActivity::class.java)
            intent.putExtra("Heading", Dataset[position].Heading)
            ContextCompat.startActivity(context,intent, Bundle.EMPTY)
        }
    }


}