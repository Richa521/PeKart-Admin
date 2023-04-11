package com.example.kartadmin.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.kartadmin.R
import com.example.kartadmin.adapter.AllOrderAdapter
import com.example.kartadmin.model.AllOrderModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class AllOrderActivity : AppCompatActivity() {
    private lateinit var list : ArrayList<AllOrderModel>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_order)
        list = ArrayList()

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        Firebase.firestore.collection("allOrders").get().addOnSuccessListener {
            list.clear()
            for(doc in it){
                val data = doc.toObject(AllOrderModel::class.java)
                list.add(data)
            }
        }


        recyclerView.adapter = AllOrderAdapter(list ,this  )
    }
}