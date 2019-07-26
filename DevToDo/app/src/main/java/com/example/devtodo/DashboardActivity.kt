package com.example.devtodo

import android.content.Context
import android.content.DialogInterface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import com.example.devtodo.DTO.ToDo
import kotlinx.android.synthetic.main.activity_dashboard.*

class DashboardActivity : AppCompatActivity() {

    lateinit var dbHandler: DBHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        setSupportActionBar(dashboard_toolbar)
        title = "Dashboard"

        dbHandler = DBHandler(this)

        rv_dashboard.layoutManager = LinearLayoutManager(this)


        fab_dashboard.setOnClickListener{
            val dialog = AlertDialog.Builder(this)
            val view = layoutInflater.inflate(R.layout.dialog_dashboard, null)
            val toDoName = view.findViewById<EditText>(R.id.ev_todo)
            dialog.setView(view)
            dialog.setPositiveButton("Add") { _: DialogInterface, _: Int ->
                if (toDoName.text.isNotEmpty()) {
                    val toDo = ToDo()
                    toDo.name = toDoName.text.toString()
                    dbHandler.addToDo(toDo)
                    refreshlist()
                }
            }

            dialog.setNegativeButton("Cancel") { _: DialogInterface, _: Int -> }

            dialog.show()

        }
    }

    override fun onResume() {
        refreshlist()
        super.onResume()
    }

    private fun refreshlist() {
        rv_dashboard.adapter = DashboardAdapter(this, dbHandler.getToDos())
    }



    class DashboardAdapter(val context: Context, val list: MutableList<ToDo>) : RecyclerView.Adapter<DashboardAdapter.ViewHolder>() {
        override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
            return ViewHolder(LayoutInflater.from(context).inflate(R.layout.rv_child_dashboard, p0,false))
        }

        override fun getItemCount(): Int {
            return list.size
        }

        override fun onBindViewHolder(holder: ViewHolder, p1: Int) {
            holder.toDoName.text = list[p1].name
        }

        class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
            val toDoName : TextView = v.findViewById(R.id.tv_todo_name)

        }
    }
}
